/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
public class detallesCargoController implements Initializable {

    @FXML
    private Button cargoImagen;
    @FXML
    private ImageView tesstImagen;
    @FXML
    private Button butonAddCat;
    @FXML
    private TextField detailNom;
    @FXML
    private TextField detailDesc;
    @FXML
    private TextField detailCoste;
    @FXML
    private TextField detailUnidad;
    @FXML
    private ComboBox<Category> desplefableListaCaategorias;
    @FXML
    private DatePicker cargoFecha;
    
    //private PrimeraPantallaController principal;
    private MiPerfilController principalLoged;
    private Acount cuenta;
    private AnchorPane screen;
    private boolean editableE= false;
    private Charge cargo;
    private Image picture;
    private Stage stage;
    private ObservableList<Category> categorias = null;
    @FXML
    private Text titulosso;
    @FXML
    private Button cancelButton;
    @FXML
    private Text wrongNom;
    @FXML
    private Text wrongDesc;
    @FXML
    private Text wrongCost;
    @FXML
    private Text wrongUnity;
    @FXML
    private Text wrongDate;
    @FXML
    private Text wrongImage;
    
    private static final String GASTOS = "/fxmls/misGastos";
    private boolean compruebaSelectedCategory ;
    @FXML
    private Button aceptarBD;
    @FXML
    private VBox vBoxCatégoria;
    @FXML
    private Separator separator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Define el patrón para solo permitir números
        
        Pattern patronInt = Pattern.compile("\\d*");
        Pattern patronDouble = Pattern.compile("\\d*|\\d+\\.\\d*");
        // Crea un TextFormatter con un filtro basado en el patrón
        TextFormatter<String> formatoD = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            if (patronDouble.matcher(change.getControlNewText()).matches()) {
                return change;
            } else {
                return null;
            }
        });
        TextFormatter<String> formatoI = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            if (patronInt.matcher(change.getControlNewText()).matches()) {
                return change;
            } else {
                return null;
            }
        });
        detailCoste.setTextFormatter(formatoD);
        detailUnidad.setTextFormatter(formatoI);
        editable(false);
        wrongVisible(false);
        aceptarBD.setDisable(false);
        
    }    
    
    

    @FXML
    private void cancelarMethod(ActionEvent event) throws IOException {
        
        if(editableE){
            ButtonType ok = new ButtonType("Acceptar", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.NONE, "Esta a punto de elminiar todos los datos rellenados",
        ok, no);
            alert.setContentText("¿Esta seguro de que quiere descartar los cambios realizados?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ok) { 
                inputClear(detailNom);
                inputClear(detailDesc);
                inputClear(detailCoste);
                inputClear(detailUnidad);
                
                tesstImagen.setVisible(false);
                picture = null;
                cargoImagen.setVisible(true);
                textClear(); 
                vueltaAtras();
            }else{
                vueltaAtras();
            }
        }
    }
    
    private void textClear() {
        wrongNom.setVisible(false);
        wrongDesc.setVisible(false);
        wrongUnity.setVisible(false);
        wrongCost.setVisible(false);
        wrongImage.setVisible(false);
        wrongDate.setVisible(false);
    }
    private void inputClear(TextInputControl e) {
        if(!(e == null || e.getText().equals(""))) e.clear();
    }
    
    @FXML
    private void aceptarMethod(ActionEvent event) throws IOException {
        if(editableE== true && comprueba()){
            cargo.setCategory(desplefableListaCaategorias.getValue());                                     cargo.setName(detailNom.getText());
            cargo.setDescription(detailDesc.getText());         
            cargo.setCost(Double.parseDouble(detailCoste.getText()));
            cargo.setUnits(Integer.parseInt(detailUnidad.getText())); cargo.setDate(LocalDate.MAX);
            cargo.setImageScan(picture); vueltaAtras();
            System.out.println("Bien al tratar de cambiar los datos del cargo");
        }else{
            vueltaAtras();
            System.out.println("error al tratar de cambiar los datos del cargo");
        }
    }

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

    @FXML
    private void addCategoryMethod(ActionEvent event) {
    }
    public void initMiPerfil(MiPerfilController princ, Acount cuenta, AnchorPane screen) {
        principalLoged = princ;
        this.cuenta= cuenta;
        this.screen=screen;
        inicializaCategorias();
        
        
    }
    
    public void editable(boolean c){
        detailNom.setEditable(c);
        detailDesc.setEditable(c);
        detailCoste.setEditable(c);
        detailUnidad.setEditable(c);
        butonAddCat.setVisible(c);
        
        if(c) {
            titulosso.setText("Editar Cargo");
            editableE= true;
            
            
        
        }
        cancelButton.setVisible(true);
        
        detailComprovation();
        aceptarBD.setDisable(true);
       
    }
    
    public void wrongVisible(boolean c){
         wrongCost.setVisible(c);
        wrongDate.setVisible(c);
        wrongDesc.setVisible(c);
        wrongImage.setVisible(c);
        wrongNom.setVisible(c);
        wrongUnity.setVisible(c);
        cancelButton.setVisible(c);
    }
    public void pasaCargo(Charge cargo){
        this.cargo = cargo;
        detailNom.setText(cargo.getName());
        detailDesc.setText(cargo.getDescription());
        detailCoste.setText(String.valueOf(cargo.getCost()));
        detailUnidad.setText(String.valueOf(cargo.getUnits()));
        if(editableE== false){
        desplefableListaCaategorias.setDisable(true);
        desplefableListaCaategorias.setPromptText(cargo.getCategory().getName());
        cargoFecha.setDisable(true);
        cargoFecha.setPromptText(this.cargo.getDate().toString());
        cargoImagen.setVisible(false);
        tesstImagen.setImage(cargo.getImageScan());
        }
    }
    private void resizable(AnchorPane pan) {
        pan.setBottomAnchor(pan, 0.0);
        pan.setTopAnchor(pan, 1.0);
        pan.setLeftAnchor(pan, 0.0);
        pan.setRightAnchor(pan, 1.0);
    }
    private void detailComprovation(){
        detailNom.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrongNom.setVisible(true);}else{wrongNom.setVisible(false);}});
        
        detailDesc.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrongDesc.setVisible(true);}else{wrongDesc.setVisible(false);}});
        
        detailCoste.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrongCost.setVisible(true);}else{wrongCost.setVisible(false);}});
        
        detailUnidad.textProperty().addListener((observable,oldValue, newValue)->{
            if(newValue.isEmpty()){wrongUnity.setVisible(true);}else{wrongUnity.setVisible(false);}});
        
        cargoFecha.valueProperty().addListener((observable,oldValue, newValue)->{
            if(newValue==null){wrongDate.setVisible(true);}else{wrongDate.setVisible(false);}});
        
        tesstImagen.imageProperty().addListener((observable,oldValue, newValue)->{
            if(newValue==null){wrongImage.setVisible(true);}else{wrongImage.setVisible(false);}});
        
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
                            aceptarBD.setDisable(false);
                            
                        }
                    }
                    
                });
        if(wrongNom.isVisible()&& wrongCost.isVisible()&& wrongDate.isVisible()&&
           wrongDesc.isVisible()&& wrongUnity.isVisible()&& wrongImage.isVisible() && compruebaSelectedCategory)
        {}else{
        aceptarBD.setDisable(false);}
    }

    private void vueltaAtras() throws IOException{
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource(GASTOS + ".fxml"));
        AnchorPane root = verGasto.load();
        
        MisGastosController control = verGasto.getController();
        control.initMiperfil(principalLoged, cuenta, screen);
        resizable(root);
        screen.getChildren().setAll(root);
    }
    
    private boolean comprueba(){
        if(wrongNom.isVisible()|| wrongCost.isVisible() || wrongDate.isVisible()||
           wrongDesc.isVisible()|| wrongUnity.isVisible()|| wrongImage.isVisible() || !compruebaSelectedCategory)
        {return false; }
        
        return true;
    }
    private void setImage(Image p, ImageView t, Button b) {
        b.setVisible(false);
        t.setImage(p);
        t.setVisible(true);
    }
    
    public void inicializaCategorias() {
               
         try {
             categorias = FXCollections.observableList(principalLoged.getAcount().getUserCategories());
         } catch (AcountDAOException ex) {
             Logger.getLogger(detallesCargoController.class.getName()).log(Level.SEVERE, null, ex);
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
}
