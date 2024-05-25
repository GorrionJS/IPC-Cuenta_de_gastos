/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private static final Color ERR = Color.rgb(32, 223, 236);
    private static final Color GOOD = Color.rgb(240, 240, 240);
    
    private Stage stage;
    private Image picture;
    private BooleanProperty validEmail;
    private BooleanProperty validPass;
    private BooleanProperty eqPass;
    private BooleanProperty validNick;
    private Acount compte;
    private PrimeraPantallaController principal;

    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputNick;
    @FXML
    private TextField inputEmail;
    @FXML
    private Label errorImagem;
    @FXML
    private Label errorEmail;
    @FXML
    private Label errorNick;
    @FXML
    private Label errorNombre;
    @FXML
    private PasswordField inputPass;
    @FXML
    private PasswordField inputPass1;
    @FXML
    private TextField inputApellido;
    @FXML
    private Button pictureUpload;    
    @FXML
    private ImageView testImagen;
    @FXML
    private Button bottonAcceptar;
    @FXML
    private Label errorPass;
    @FXML
    private Label errorPass1;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
        String errS = "La contraseña tiene que  ser de entre 4 y 15 caracteres.";
        textModification(errorPass, errS, Color.BLACK);
        
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
        inputPass.focusedProperty().addListener((object, oldV, newV) -> { 
            if(!newV) { evaluatePass("La contraseña no se ajusta a los valores indicados"); }});
        inputPass1.focusedProperty().addListener((object, oldV, newV) -> { if(!newV) { equalsPass(); }});
        inputEmail.focusedProperty().addListener((object, oldV, newV) -> { if(!newV) { evaluateEmail(); }});
        
        inputNombre.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)){ inputApellido.requestFocus(); }});
        inputApellido.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)){ inputNick.requestFocus(); }});
        inputNick.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)){ inputPass.requestFocus(); }});
        inputPass.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)){ inputPass1.requestFocus(); }});
        inputPass1.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)){ inputEmail.requestFocus(); }});
        inputEmail.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)){ bottonAcceptar.requestFocus(); }});
        
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
        } else { this.textModification(errorImagem, "La imagen seleccionada no es valida", ERR); }
    }
    
        /*************************************************************************
     *          BOTON CANCELAR
     */

    // Metodo a cambiar (de momento funciona)
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
                principal.clear();
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
        if (result.isPresent() && result.get() == ok && evaluate()) { 
 
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
         // Comprovacion de si el nick existe en la BD
        if(compte.existsLogin(desiredNick)) {
            String errS = "El nombre de usuario ya se encuentra en uso";
            textModification(errorNick, errS, ERR);
        } else { 
            if(desiredNick.equals("")) { return ; }
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
        // Validacion acorde al estandard RFC 5322
        // sacado de https://www.baeldung.com/java-email-validation-regex
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pat = Pattern.compile(regex);
        if(pat.matcher(email).matches()) { 
            String errS = "El correo introducido es valido";
            textModification(errorEmail, errS, GOOD);
            validEmail.setValue(Boolean.TRUE);
        } else {
            String errS = "El correo introducido no es valido";
            textModification(errorEmail, errS, ERR);
        }
    }
    
    /*************************************************************************
     * EVALUACION CONTRASEÑA 1 **/
    
    private void evaluatePass(String error) {
        String pass = inputPass.getText();
        // Se busca una contraseña de entre 4 y 15 caracteres
        if(pass.length() < 3 || pass.length() > 16) {
            String errS = error;
            textModification(errorPass, errS, ERR);
        } else {
            errorPass.setVisible(false);
            validPass.setValue(Boolean.TRUE);
        }
    }
    
    /******************************************************************** 
     * AVISO DE PERMITIDOS A LA HORA DE INTRODUCIR UNA CONTRASEÑA **/

    /*************************************************************************** 
     * EVALUACION DE LAS CONTRASEÑAS **/
    private void equalsPass() {
        String pass2 = inputPass1.getText();
        String pass = inputPass.getText();
        if(pass != null && pass.equals(pass2)) {
            eqPass.setValue(Boolean.TRUE);
            errorPass1.setDisable(true);
        } else {
            String errS = "Las contraseñas no coinciden";
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
     * @param Label t : El texto que queremos modificar (en nuestro caso error...)
     * @param String s : El texto que queremos mostrar en t
     * @param Color c : El colot que queremos en el texto t
     */
  
    private void textModification(Label t, String s, Color c) {
        t.setTextFill(c);
        t.setText(s);
        t.setVisible(true);
    }
    
    /** Metodo que desactiva los errores **/
    private void textClear() {
        errorEmail.setVisible(false);
        errorImagem.setVisible(false);
        errorNick.setVisible(false);
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
    
    public Acount getAccount() { return this.compte; }
    
    public void setAcount(Acount c) { this.compte = c; }
    
    public void init(PrimeraPantallaController princ) { this.principal = princ; } 
    
    public void byPass() throws AcountDAOException, IOException {
        this.inputNombre.setText("admin");
        this.inputApellido.setText("admin");
        this.inputNick.setText("admin");
        this.inputPass.setText("admin");
        this.inputPass1.setText("admin");
        this.inputEmail.setText("admin@admin.admin");
        acceptar(new ActionEvent());
    }
    
    private boolean evaluate() throws AcountDAOException, IOException{
        evaluateEmail(); 
        evaluateNick(); 
        evaluatePass("3"); 
        equalsPass();
        
        boolean result = validEmail.get() && validPass.get() && validNick.get() && eqPass.get();
        
        return result;
    }
}
