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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.AcountDAOException;
import model.Category;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MisGastosController implements Initializable {

    private PrimeraPantallaController principal;
    private MiPerfilController principalLoged;
    private ObservableList<Category> categorias = null;
    private ObservableList<Charge> cargos = null;
    @FXML
    private ListView<Charge> misGastosLista;
    @FXML
    private ComboBox<Category> misCategorias;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void init(PrimeraPantallaController princ) throws AcountDAOException{
        principal = princ;
        inicializaCategorias();
        inicializarCargos();
       
    }
    public void initMiperfil(MiPerfilController princ){
        principalLoged = princ;
    }

    @FXML
    private void addMethod(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader addGasto = new FXMLLoader(getClass().getResource("/fxmls/añadirCargo.fxml"));
        AnchorPane root = addGasto.load();
        AñadirCargoController control = addGasto.getController();
        control.init(principal);
        control.initMiperfil(principalLoged);
        principalLoged.getBorderPaneMiPerfilController().setCenter(root);
    }
    
        public void inicializaCategorias() {
               
                try {
                    categorias = FXCollections.observableList(principal.getAcount().getUserCategories());
                   } catch (AcountDAOException ex) {
                       System.out.println("error al cargar categorias");
                   }
                   misCategorias.getItems().addAll(categorias);

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
                    cargos = FXCollections.observableList(principal.getAcount().getUserCharges());
                   } catch (AcountDAOException ex) {
                       System.out.println("error al cargar cargos");
                   }
            misGastosLista.setItems(cargos);
            misGastosLista.setCellFactory(lv -> new TextFieldListCell<>(new StringConverter<Charge>() {
            @Override
            public String toString(Charge c) {
                if(c==null){
                    return "";
                }
                return c.getName() + "***" +c.getCost() +"$";
            }

                @Override
                public Charge fromString(String string) {
                return null;                }

        }));
        }

    
}
