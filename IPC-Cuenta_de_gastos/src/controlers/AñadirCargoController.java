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

import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AcountDAOException;
import model.Category;
import model.Charge;



/**
 * FXML Controller class
 *
 * @author Usuario
 */









/*
    todavia falta implementar los datos y demás
*/
public class AñadirCargoController implements Initializable {
     private PrimeraPantallaController principal;
    @FXML
    private TextField cargoID;
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
    private Stage stage;
    private Image picture;
    @FXML
    private ImageView tesstImagen;
    @FXML
    private ComboBox<Category> desplefableListaCaategorias;
    @FXML
    private Button butonAddCat;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Define el patrón para solo permitir números
        Pattern patronDouble = Pattern.compile("\\d*");
        Pattern patronInt = Pattern.compile("\\d*|\\d+\\.\\d*");
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
        cargoCoste.setTextFormatter(formatoD);
        cargoUnidades.setTextFormatter(formatoI);
    }    
    
    
    public void init(PrimeraPantallaController first) throws AcountDAOException{
        
        principal = first;
        
        desplefableListaCaategorias.getItems().addAll(principal.getAcount().getUserCategories());
        
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

    private void setImage(Image p, ImageView t, Button b) {
        b.setVisible(false);
        t.setImage(p);
        t.setVisible(true);
    }
    
    @FXML
    private void cancelarMethod(ActionEvent event) {
    }

    @FXML
    private void aceptarMethod(ActionEvent event) {
        
        String name = cargoNombre.getText();
        String description = cargoDescripcion.getText();
        
        int unidades = Integer.parseInt(cargoUnidades.getText());
//        //Category categoria = principal.getAcount().registerCategory("name", "description");
//        if(){
//            
//        }else{
//            
//        }
//        
//        principal.getAcount().registerCharge(name, description, coste, unidades, picture, LocalDate.MAX, category);
//    }
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
    private void addCategoryMethod(ActionEvent event) {
            try {
            
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxmls/addCategoy.fxml"));
                Stage stage = loader.load();
                stage.setTitle("Vista añadir Categoría");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException ex) {
            Logger.getLogger(AñadirCargoController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void comprobarInt(KeyEvent event) {
        }
    
}
