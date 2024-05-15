/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

// TREMENDA MODIFICACION PARA PUBLICAR LA RAM
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.User;

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
    private PasswordField inputPass;
    @FXML
    private PasswordField inputPass1;
    @FXML
    private Text errorPass;
    @FXML
    private Text errorPass1;
    @FXML
    private TextField inputApellido;
    
    private String pass; 
    
    private Stage stage;
    
    private PrimeraPantallaController principal;
    
    private User cuenta;
    
    private Image picture;

    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private PasswordField inputPass2;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {

    }    

    // Introducir los valores por defecto cuando este listo
    
    /** Metodo para modificar el texto de error
     *
     * @param Text t : El texto que queremos modificar (en nuestro caso error...)
     * @param String s : El texto que queremos mostrar en t
     * @param Color c : El colot que queremos en el texto t
     */
  
    private void textModification(Text t, String s, Color c) {
        textClear();
        t.setFill(c);
        t.setText(s);
        t.setVisible(true);
    }
    
    private void textClear() {
        errorEmail.setVisible(false);
        errorImagem.setVisible(false);
        errorNick.setVisible(false);
        errorNombre.setVisible(false);
        errorPass.setVisible(false);
        errorPass1.setVisible(false);
    }
    
    private void inputClear(TextInputControl e) {
        if(!(e == null || e.getText().equals(""))) e.clear();
    }
    
    private void uploadPicture(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ver Imagenes");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) { 
            picture = new Image(new FileInputStream(file));
        }
    }

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
                textClear(); 
            }
    }

    @FXML
    private void acceptar(ActionEvent event) throws AcountDAOException, IOException {

        // Se realiza un check a todos los elementos y si es correcto todo se sube a la base de datos
        
        ButtonType ok = new ButtonType("Acceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, "Confirme que los datos introducidos son correctos",
        ok, no);
        
        alert.show();
        
        // compte = Acount.getInstance();
        Acount compte =principal.getAcount();
        boolean registered = compte.registerUser(inputNombre.getText(), inputApellido.getText(), inputEmail.getText(), inputNick.getText()
                , inputPass2.getText(), (Image) null, LocalDate.now());
        
        if (!registered) { alert = new Alert(Alert.AlertType.NONE, "No ha sido posible realizar el registro",
        ok, no); }
        else { alert = new Alert(Alert.AlertType.NONE, "Su cuenta ha sido registrada exitosamente, Inicie sesion",
        ok, no); }
        
        alert.show();
    }

    private void evaluateNk(KeyEvent event) {
        // Se comprueba si el Nick ya esta siendo usado
        
        String desiredNick = inputNick.getText();
        if(desiredNick.equals("")) { // Completar con el acceso a la base de datos
            String errS = "El nombre de usuario \n ya se encuentra en uso";
            textModification(errorNick, errS, ERR);
        } else { 
            String errS = "El nombre de usuario es valido";
            textModification(errorNick, errS, GOOD);
        }
    }

    private void evaluateNk(MouseEvent event) {
        // Igual que el anterior pero en este caso accedido por el raton
        KeyEvent x = null;
        evaluateNk(x);
    }

    /** Evaluacion EMAIL **/
    
    private void evaluateEm(KeyEvent event) {
        // Se evalua si el email esta en el formato correcto
        String email = inputEmail.getText();
        if(!email.contains("@") || email.equals("")) { // No contiene @ o esta en la BD
            String errS = "El correo introducido no es valido";
            textModification(errorEmail, errS, ERR);
        }else {
            String errS = "El correo introducido es valido";
            textModification(errorEmail, errS, GOOD);
        }
    }

    private void evaluateEm(MouseEvent event) {
        // Igual que el anterior pero accedido por el raton
        KeyEvent x = null;
        evaluateEm(x);
    }
    
    /** EVALUACION CONTRASEÑA 1 **/
    
    
    private void evaluate(KeyEvent event) {
        String pass = inputPass.getText();
        if(pass.length() < 3 && pass.length() > 16) {
            String errS = "La contraseña no se ajusta \n a los parametros indicados";
            textModification(errorPass, errS, ERR);
        } else {
            errorPass.setVisible(false);
        }
    }

    private void evaluate(MouseEvent event) {
        evaluate((KeyEvent) null);
    }
    
    /** AVISO DE PERMITIDOS A LA HORA DE INTRODUCIR UNA CONTRASEÑA **/

    private void inform(MouseEvent event) {
        String errS = "La contraseña tiene que ser de entre 4 y 15 caracteres.";
        textModification(errorPass, errS, GOOD);
    }

    /** EVALUACION DE LAS CONTRASEÑAS **/
    
    private void evaluateEq(KeyEvent event) {
        String pass2 = inputPass1.getText();
        if(!pass.equals(pass2)) {
            String errS = "Las contraseñas no coinciden";
            textModification(errorPass1, errS, ERR);
        }
    }

    private void evaluateEq(MouseEvent event) {
        evaluateEq((KeyEvent) null);
    }

    
    Stage getStage() { return stage; }
    
    public void init(PrimeraPantallaController princ){
        principal = princ;
    }

    @FXML
    private void uploadPicture2(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ver Imagenes");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) { 
            picture = new Image(new FileInputStream(file));
        }
    }
}
