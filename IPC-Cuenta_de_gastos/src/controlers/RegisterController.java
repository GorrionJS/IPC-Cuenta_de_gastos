/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class RegisterController implements Initializable {
    
    private static final Color ERR = Color.RED;
    private static final Color GOOD = Color.GREEN;

    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputNick;
    @FXML
    private TextField inputEmail;
    @FXML
    private Text errorImagem;
    @FXML
    private Text errorEmail;
    @FXML
    private Text errorNick;
    @FXML
    private Text errorNombre;
    @FXML
    private PasswordField inputPass;
    @FXML
    private PasswordField inputPass1;
    @FXML
    private Text errorPass;
    @FXML
    private Text errorPass1;
    @FXML
    private TextField inputApellido;
    @FXML
    private Button pictureUpload;    
    @FXML
    private ImageView testImagen;
    @FXML
    private Button bottonAcceptar; 
    
    private Stage stage;
    
    private PrimeraPantallaController principal;
    
    private Image picture;
    
    private BooleanProperty validEmail;
    
    private BooleanProperty validPass;
    
    private BooleanProperty eqPass;
    
    private BooleanProperty validNick;
    
    private Acount compte;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
        // properties
        validEmail = new SimpleBooleanProperty();
        validPass = new SimpleBooleanProperty();
        eqPass = new SimpleBooleanProperty();
        validNick = new SimpleBooleanProperty();
        
        validPass.setValue(Boolean.FALSE);
        validEmail.setValue(Boolean.FALSE);
        eqPass.setValue(Boolean.FALSE);
        validNick.setValue(Boolean.FALSE);
        
        // Listeners
        inputNick.focusedProperty().addListener((object, oldV, newV) -> { if(!newV) { evaluateNick(); }});
        inputPass.focusedProperty().addListener((object, oldV, newV) -> { if(!newV) { evaluatePass("Las contraseña no se ajusta \n a los valores indicados"); }});
        inputPass1.focusedProperty().addListener((object, oldV, newV) -> { if(!newV) { equalsPass(); }});
        inputEmail.focusedProperty().addListener((object, oldV, newV) -> { if(!newV) { evaluateEmail(); }});
        
        // Escondemos el ImagenView
        testImagen.setVisible(false);
        // Binding
        bottonAcceptar.disableProperty().bind(validPass.not().or(validEmail.not().or(eqPass.not().or(validNick.not()))));
    }
    
        /*************************************************************************
     *          BOTON SUBIR IMAGEN
     */
    
    /** Metodo para subir una imagen
     * 
     * @param event
     * @throws IOException 
     */
    // Aqui iba la fuente de donde ha salido el codigo pero alguien la ha quitado
    @FXML
    private void uploadPicture(ActionEvent event) throws IOException{
        // Creacion de un FileChooser
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
            setImage(picture, testImagen, pictureUpload);
        }
    }
    
        /*************************************************************************
     *          BOTON CANCELAR
     */

    // MEtodo a cambiar (de momento funciona)
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        ButtonType ok = new ButtonType("Acceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, "Esta a punto de elminiar todos los datos rellenados",
        ok, no);
        
        alert.setContentText("Esta seguro de que quiere eliminar todos los datos");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ok) { 
                inputClear(inputNombre);
                inputClear(inputApellido);
                inputClear(inputNick);
                inputClear(inputEmail);
                inputClear(inputPass);
                inputClear(inputPass1);
                testImagen.setVisible(false);
                picture = null;
                pictureUpload.setVisible(true);
                textClear(); 
            }
    }
    
    /*************************************************************************
     *          BOTON ACCEPTAR
     */
    // Metodo a cambiar (de momento no funciona como deberia)

    @FXML
    private void acceptar(ActionEvent event) throws AcountDAOException, IOException {

        ButtonType ok = new ButtonType("Acceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, "Confirme que los datos introducidos son correctos",
        ok, no);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ok) { 
 
            boolean registered = compte.registerUser(inputNombre.getText(), inputApellido.getText(), inputEmail.getText(), inputNick.getText()
                , inputPass.getText(), picture, LocalDate.now());
        
            if (!registered) { alert = new Alert(Alert.AlertType.NONE, "No ha sido posible realizar el registro",
            ok, no); }
            else { alert = new Alert(Alert.AlertType.NONE, "Su cuenta ha sido registrada exitosamente, Inicie sesion",
            ok, no); }
        
            alert.show();
        }
    }
    
    /***********************************************************
     *      APARTADO DE NICK
     */

    private void evaluateNick() { 
         String desiredNick = inputNick.getText();
        if(desiredNick.equals("")) { // Completar con el acceso a la base de datos
            String errS = "El nombre de usuario \n ya se encuentra en uso";
            textModification(errorNick, errS, ERR);
        } else { 
            String errS = "El nombre de usuario es valido";
            textModification(errorNick, errS, GOOD);
            validNick.setValue(Boolean.TRUE);
        }
    }
    /*******************************************************************
     * Evaluacion EMAIL **/
    
    private void evaluateEmail() {
        // Se evalua si el email esta en el formato correcto
        String email = inputEmail.getText();
        if(!email.contains("@") || email.equals("")) { // No contiene @ o esta en la BD
            String errS = "El correo introducido no es valido";
            textModification(errorEmail, errS, ERR);
        }else {
            String errS = "El correo introducido es valido";
            textModification(errorEmail, errS, GOOD);
            validEmail.setValue(Boolean.TRUE);
        }
    }
    
    /*************************************************************************
     * EVALUACION CONTRASEÑA 1 **/
    
    private void evaluatePass(String error) {
        String pass = inputPass.getText();
        // Se busca una contraseña de entre 4 y 15 caracteres
        if(pass.length() < 3 && pass.length() > 16) {
            String errS = error;
            textModification(errorPass, errS, ERR);
        } else {
            errorPass.setVisible(false);
            validPass.setValue(Boolean.TRUE);
        }
    }
    
    /******************************************************************** 
     * AVISO DE PERMITIDOS A LA HORA DE INTRODUCIR UNA CONTRASEÑA **/

    private void inform(MouseEvent event) {
        String errS = "La contraseña tiene que ser de entre 4 y 15 caracteres.";
        textModification(errorPass, errS, GOOD);
    }

    /*************************************************************************** 
     * EVALUACION DE LAS CONTRASEÑAS **/
    private void equalsPass() {
        String pass2 = inputPass1.getText();
        String pass = inputPass.getText();
        if(pass != null && pass.equals(pass2)) {
            eqPass.setValue(Boolean.TRUE);
        } else {
            String errS = "Las contraseñas no coinciden " + pass + " " + pass2 ;
            textModification(errorPass1, errS, ERR);
        }
        
    }
    
    /**********************************************************************+
     * CLASES UTILES 
     */
        // Este metodo empezo como un test
    private void setImage(Image p, ImageView t, Button b) {
        b.setVisible(false);
        t.setImage(p);
        t.setVisible(true);
    }

    // Introducir los valores por defecto cuando este listo
    
    /** Metodo para modificar el texto de error
     *
     * @param Text t : El texto que queremos modificar (en nuestro caso error...)
     * @param String s : El texto que queremos mostrar en t
     * @param Color c : El colot que queremos en el texto t
     */
  
    private void textModification(Text t, String s, Color c) {
        t.setFill(c);
        t.setText(s);
        t.setVisible(true);
    }
    
    /** Metodo que desactiva los errores **/
    private void textClear() {
        errorEmail.setVisible(false);
        errorImagem.setVisible(false);
        errorNick.setVisible(false);
        errorNombre.setVisible(false);
        errorPass.setVisible(false);
        errorPass1.setVisible(false);
    }
    
    /** Metodo que limpiar el input especificado
     * Si esta vacio este metodo no realiza nada
     * 
     * @param TextInputControl e : Objeto de tipu TextInput con el texto a limpiar 
     */
    private void inputClear(TextInputControl e) {
        if(!(e == null || e.getText().equals(""))) e.clear();
    }
    
    Acount getAccount() { return compte; }
    
    void setAcount(Acount c) { compte = c; }

    /*
    No se que es esto
    
    Stage getStage() { return stage; }
    */
    public void init(PrimeraPantallaController princ){
        principal = princ;
    }
    
}
