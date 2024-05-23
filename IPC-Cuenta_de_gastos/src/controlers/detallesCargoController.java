/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    
    private PrimeraPantallaController principal;
    private MiPerfilController principalLoged;
    
    private Charge cargo;
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
        
    }    

    @FXML
    private void cancelarMethod(ActionEvent event) {
    }

    @FXML
    private void aceptarMethod(ActionEvent event) {
    }

    @FXML
    private void addScannImage(ActionEvent event) {
    }

    @FXML
    private void addCategoryMethod(ActionEvent event) {
    }
    
    public void initMiperfil(MiPerfilController princ){
        principalLoged = princ;
    }
    public void editable(boolean c){
        detailNom.setEditable(c);
        detailDesc.setEditable(c);
        detailCoste.setEditable(c);
        detailUnidad.setEditable(c);
        butonAddCat.setVisible(c);
        if(c) titulosso.setText("Editar Cargo");
       
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
    }
}
