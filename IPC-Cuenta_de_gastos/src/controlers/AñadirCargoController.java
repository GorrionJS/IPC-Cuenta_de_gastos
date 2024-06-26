/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.util.logging.Level;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;


/**
 * FXML Controller class
 *
 * @author elgor
 */


public class AñadirCargoController implements Initializable {
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private String IMAGENPORDEFECTO ="/images/emptyImage.png";
    
    private MiPerfilController principalLoged;
    private Acount cuenta;
    private AnchorPane screen;
    private PrimeraPantallaController principal;
    
    private Stage stage;
    private Image picture;
    private ObservableList<Category> categorias = null;
    private boolean compruebaSelectedCategory;
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private TextField cargoNombre;
    @FXML
    private TextField cargoDescripcion;
    @FXML
    private TextField cargoUnidades;
    @FXML
    private TextField cargoCoste;
    @FXML
    private Button cargoImagen;
    @FXML
    private DatePicker cargoFecha;
    @FXML
    private ImageView tesstImagen;
    @FXML
    private ComboBox<Category> desplefableListaCaategorias;
    @FXML
    private Button butonAddCat;
    @FXML
    private Text wrong1;
    @FXML
    private Text wrong2;
    @FXML
    private Text wrong3;
    @FXML
    private Text wrong4;
    @FXML
    private Text wrong5;
    @FXML
    private Text wrong6;
    @FXML
    private Button aceptarAddCargoB;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aceptarAddCargoB.setDisable(true);
        
        // Define el patrón para solo permitir números
        Pattern patronInt = Pattern.compile("\\d*");
        Pattern patronDouble = Pattern.compile("\\d*|\\d+\\.\\d*");
        
        // Crea un TextFormatter con un filtro basado en el patrón
        TextFormatter<String> formatoD = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            if (patronDouble.matcher(change.getControlNewText()).matches()) {return change;} 
            else {return null;}
        });
        
        TextFormatter<String> formatoI = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            if (patronInt.matcher(change.getControlNewText()).matches()) {return change;} 
            else {return null;}
        });
        
        cargoCoste.setTextFormatter(formatoD);
        cargoUnidades.setTextFormatter(formatoI);
        
        ///////////////////////////////////////////////////////
        // LISTENERS
        ///////////////////////////////////////////////////////
        cargoNombre.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrong1.setVisible(true);}else{wrong1.setVisible(false);}});
        
        cargoDescripcion.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrong2.setVisible(true);}else{wrong2.setVisible(false);}});
        
        cargoCoste.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrong3.setVisible(true);}else{wrong3.setVisible(false);}});
        
        cargoUnidades.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrong4.setVisible(true);}else{wrong4.setVisible(false);}});
        
        cargoFecha.valueProperty().addListener((observable,oldValue, newValue)->{
            if(newValue==null){wrong5.setVisible(true);}else{wrong5.setVisible(false);}});
        
        
        
        desplefableListaCaategorias.valueProperty().addListener(
            new ChangeListener<Category>(){
                @Override
                public void changed(ObservableValue<? extends Category> ov, Category t, Category t1) {
                    if(t1== null){
                        desplefableListaCaategorias.setStyle("-fx-border-color: #FF5100; -fx-border-width: 2px;");
                        compruebaSelectedCategory= false;
                    }else{
                        desplefableListaCaategorias.setStyle("-fx-background-color: #15FF00; -fx-border-width: 2px;");
                        compruebaSelectedCategory=true;
                    }
                }
            }
        );

        BooleanBinding textsInvisible = wrong1.visibleProperty().not()
                .and(wrong2.visibleProperty().not())
                .and(wrong3.visibleProperty().not())
                .and(wrong4.visibleProperty().not())
                .and(wrong5.visibleProperty().not());
                

        BooleanBinding comboBoxSelected = desplefableListaCaategorias.getSelectionModel().selectedItemProperty().isNotNull();

        aceptarAddCargoB.disableProperty().bind(textsInvisible.not().or(comboBoxSelected.not()));
    }
    
    ///////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////
    public void initMiPerfil(MiPerfilController princ, Acount cuenta, AnchorPane screen) throws AcountDAOException, IOException{
        this.principalLoged = princ;
        this.cuenta= cuenta;
        this.screen= screen;
        inicializaCategorias();
    }
             
    public void init(PrimeraPantallaController princ) throws AcountDAOException, IOException{
        this.principal = princ;
        inicializaCategorias();
    }
    
    ///////////////////////////////////////////////////////
    // RESIZE
    ///////////////////////////////////////////////////////
    private void resizable(AnchorPane pan) {
        pan.setBottomAnchor(pan, 0.0);
        pan.setTopAnchor(pan, 1.0);
        pan.setLeftAnchor(pan, 0.0);
        pan.setRightAnchor(pan, 1.0);
    }
    
    ///////////////////////////////////////////////////////
    // METODOS
    ///////////////////////////////////////////////////////
    private boolean comprueba(){
        if(wrong1.isVisible()&& wrong2.isVisible()&& wrong3.isVisible()&&
           wrong4.isVisible()&& wrong5.isVisible() && compruebaSelectedCategory)
        {return false;}
        return true;
    }
    
    
    public void inicializaCategorias() throws IOException {   
        try {
            categorias = FXCollections.observableList(principalLoged.getAcount().getUserCategories());
        } catch (AcountDAOException ex) {
            Logger.getLogger(AñadirCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        desplefableListaCaategorias.setItems(categorias);
        desplefableListaCaategorias.setConverter(new StringConverter<Category>(){

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

    ///////////////////////////////////////////////////////
    // IMAGENES
    ///////////////////////////////////////////////////////
    @FXML
    private void addScannImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ver Imagenes");
        
        // Modificacion de los archivos elegibles
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        // Seleccion de la carpeta inicial donde estara el usuario
        String dir = "user.home"; //Default poned esto en el getProperty o no ira
        fileChooser.setInitialDirectory(new File(System.getProperty(dir)));
        
        // Creacion de un file con el archivo seleccionado
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) { 
            // Transformacion del File a la clase Image
            picture = new Image(new FileInputStream(file));
            setImage(picture, tesstImagen, cargoImagen);
        }
    }

    private void setImage(Image p, ImageView t, Button b) {
        b.setVisible(false);
        t.setImage(p);
        t.setVisible(true);
    }
    
    ///////////////////////////////////////////////////////
    // BOTONES
    ///////////////////////////////////////////////////////
    @FXML
    private void cancelarMethod(ActionEvent event) throws AcountDAOException, IOException {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText("No se ha registrado ningún gasto.");
        alerta.showAndWait();
        System.out.println("No se ha registrado ningún gasto");
        vueltaAtras();
    }

    @FXML
    private void aceptarMethod(ActionEvent event) throws AcountDAOException, FileNotFoundException, IOException {
        String name = cargoNombre.getText();
        String description = cargoDescripcion.getText();
        Double cost = Double.parseDouble(cargoCoste.getText());
        int unidades = Integer.parseInt(cargoUnidades.getText());
        Category categoria = desplefableListaCaategorias.getValue();
        LocalDate dayBuy = cargoFecha.getValue();
        
        ButtonType ok = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Añadir Cargo", ok, no);
        alert.setTitle("Guardar cargo");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de realizar esta operación?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ok) { 
                if(comprueba() && principalLoged.getAcount().registerCharge(name, description, cost, unidades, devuelveImagen(), dayBuy, categoria)){
                    Alert bien = new Alert(Alert.AlertType.CONFIRMATION,"Cargo registrado",ok);
                    bien.setContentText("Se ha registrado el gasto correctamente.");   
                    vueltaAtras();
                }else{
                    Alert mal = new Alert(Alert.AlertType.WARNING,"Cargo no registrado",no);
                    mal.setContentText("No se ha podido realizar la acción.");
                }
            }
    }
        
    private Image devuelveImagen() throws FileNotFoundException{
        if(picture==null){
            picture = new Image(IMAGENPORDEFECTO);
            return picture;
        }else{
            return picture;
        }
    }

    ///////////////////////////////////////////////////////
    // AÑADIMOS CATEGORÍA
    ///////////////////////////////////////////////////////
    @FXML
    private void addCategoryMethod(ActionEvent event) throws AcountDAOException {
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxmls/addCategoy.fxml"));
                
                Stage stage =loader.load();
                AddCategoryController addCat = loader.getController();
                stage.setTitle("Vista añadir Categoría");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                if(addCat.isPressed()){
                    String name = addCat.getNomCat();
                    String description = addCat.getDescCat();
                    principalLoged.getAcount().registerCategory(name, description);
                    inicializaCategorias();
                }
            } catch (IOException ex) {
                Logger.getLogger(AñadirCargoController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void comprobarDouble(KeyEvent event) {
        if(!Character.isDigit(event.getCharacter().charAt(0)) && event.getCharacter().charAt(0)!='.'){
            event.consume();
        }
        if(event.getCharacter().charAt(0)=='.' && cargoCoste.getText().contains(".")){
            event.consume();
        }
    }
    
    @FXML
    private void comprobarInt(KeyEvent event) {
        
    }

    private void vueltaAtras() throws IOException, AcountDAOException{
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource("/fxmls/misGastos.fxml"));
        AnchorPane root = verGasto.load();
        MisGastosController control = verGasto.getController();
        control.initMiperfil(principalLoged, cuenta, screen);
        //principal.getGrid().setCenter(root);
        resizable(root);
        screen.getChildren().setAll(root);
    }
}
