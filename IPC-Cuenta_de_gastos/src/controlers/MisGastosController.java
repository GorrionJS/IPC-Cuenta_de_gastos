/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.util.logging.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MisGastosController implements Initializable {

    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    //private PrimeraPantallaController principal;
    private MiPerfilController principalLoged;
    private Acount cuenta;
    private AnchorPane screen;
    
    private ObservableList<Category> categorias = null;
    private ObservableList<Charge> cargos = null;
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private ComboBox<Category> misCategorias;
    @FXML
    private TableView<Charge> tabla;
    @FXML
    private TableColumn<Charge, String> nombrelList;
    @FXML
    private TableColumn<Charge, String> fechaList;
    @FXML
    private TableColumn<Charge, Double> costoList;
    @FXML
    private TableColumn<Charge, Void> acciones;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nombrelList.setCellValueFactory(new PropertyValueFactory<>("name"));
        fechaList.setCellValueFactory(new PropertyValueFactory<>("date"));
        costoList.setCellValueFactory(new PropertyValueFactory<>("cost"));
        costoList.setCellFactory(column -> new TableCell<Charge,Double>(){
                    @Override
                    protected void updateItem(Double item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty || item ==null){
                            setText(null);
                        }else{
                            setText("€ " + String.format("%.2f",item));
                        }
                    }
                }
        );
        acciones.setCellFactory(param-> new TableCell<Charge, Void>(){
            private final ComboBox<String> combo = new ComboBox<>();
            {
                combo.setValue("🔶");
                combo.getItems().addAll("Ver Detalles", "Editar", "Eliminar");
                combo.setOnAction(event->{
                    String elegido = combo.getSelectionModel().getSelectedItem();
                    Charge gasto = getTableView().getItems().get(getIndex());
                    manejarAccion(elegido, gasto);
                });
            }
            @Override
            protected void updateItem(Void item,boolean empty){
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    setGraphic(combo);
                }
            }
        });
        
        tabla.getColumns().setAll(nombrelList,fechaList,costoList, acciones);
    }    
       
//    public void init(PrimeraPantallaController princ) throws AcountDAOException{
//        principal = princ;
//        inicializarCargos();
//        selectCategories();  
//    }
    
    ///////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////
    public void initMiperfil(MiPerfilController princ, Acount cuenta, AnchorPane screen){
        principalLoged = princ;
        this.cuenta = cuenta;
        this.screen = screen;
        inicializarCargos();
        selectCategories();  
    }

    ///////////////////////////////////////////////////////
    // RESIZE
    ///////////////////////////////////////////////////////
    private void resizable(AnchorPane pan) {
        pan.setBottomAnchor(pan, 0.0);
        pan.setTopAnchor(pan, 0.0);
        pan.setLeftAnchor(pan, 0.0);
        pan.setRightAnchor(pan, 0.0);
    }
    
    ///////////////////////////////////////////////////////
    // METODOS
    ///////////////////////////////////////////////////////
    @FXML
    private void addMethod(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader addGasto = new FXMLLoader(getClass().getResource("/fxmls/añadirCargo.fxml"));
        AnchorPane root = addGasto.load();
        resizable(root);
        AñadirCargoController control = addGasto.getController();
        control.initMiPerfil(principalLoged, cuenta, screen);
       // principal.getGrid().getChildren().setAll(root);
        //principal.getAnchorPane().getChildren().setAll(root);
        screen.getChildren().setAll(root);
        //principal.getGrid().setCenter(root);
    }
    
    public void inicializaCategorias() {        
        try {
            categorias = FXCollections.observableList(principalLoged.getAcount().getUserCategories());
           } catch (AcountDAOException ex) {
               System.out.println("error al cargar categorias");
           }
           misCategorias.setItems(categorias);
           
           misCategorias.setConverter(new StringConverter<Category>(){
            @Override
            public String toString(Category t) {
                if(t==null){
                    return "";
                }
                return t.getName();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });
    }
        
    private void inicializarCargos(){
        try {
            cargos = FXCollections.observableList(principalLoged.getAcount().getUserCharges());
        } catch (AcountDAOException ex) {
            System.out.println("error al cargar cargos");
        }

        tabla.setItems(cargos);
    }

    public void selectCategories(){
        FilteredList<Charge> cargoSelected = new FilteredList<>(cargos, p ->true);
        tabla.setItems(cargoSelected);
        inicializaCategorias();
        
        misCategorias.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
            if(newVal != null){
                cargoSelected.setPredicate(carGO -> carGO.getCategory().getName().equals(newVal.getName()));
            }else {cargoSelected.setPredicate(p->true);}
            
            tabla.refresh();
        });
    }

    private void manejarAccion (String eleg, Charge gast){
        switch(eleg){
            case "Ver Detalles":
                detail(gast);
                break;
            case "Editar":
                edit(gast, true);
                break;
            case "Eliminar":
                remove(gast);
                break;
            default:
            break;
        }
    }
    
    private void remove(Charge c){
        try {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setHeaderText("Estas seguro de salir?");
            alerta.showAndWait();
            if(principalLoged.getAcount().removeCharge(c)){
                System.out.println("si se pudo eliminar");
                inicializarCargos();
                
            }
        } catch (AcountDAOException ex) {
            System.out.println("no se pudo eliminar");
        }
    }
    
    private void detail (Charge c){
        FXMLLoader detalle = new FXMLLoader(getClass().getResource("/fxmls/gastoDetalles.fxml"));
        AnchorPane root;
        try {
            root = detalle.load();
            detallesCargoController control = detalle.getController();
            control.initMiPerfil(principalLoged, cuenta, screen);
            //control.initMiperfil(principalLoged);
            control.pasaCargo(c);
            //principalLoged.getBorderPaneMiPerfilController().setCenter(root);
            resizable(root);
            //principal.getAnchorPane().getChildren().setAll(root);
            screen.getChildren().setAll(root);
            
        } catch (IOException ex) {
            System.out.println("no se pudo cargar detalles");
        } 
        
    }
    private void edit(Charge c, boolean b){
        FXMLLoader editar = new FXMLLoader(getClass().getResource("/fxmls/gastoDetalles.fxml"));
        AnchorPane root;
        try {
            root = editar.load();
            detallesCargoController control = editar.getController();
            control.initMiPerfil(principalLoged,cuenta, screen);
            control.editable(b);
            control.pasaCargo(c);
            
            resizable(root);
            
            screen.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println("no se pudo cargar detalles"+ ex);
        } 
    }
}
