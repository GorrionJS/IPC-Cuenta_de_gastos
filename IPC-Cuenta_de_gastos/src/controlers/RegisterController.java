/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private TextField inputImage;
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
    
    private String pass; 
    
    private Stage stage;
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

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        // El objetivo es limpiar todas las partes
        String archivo = "/fxmls/Conf_cancelar";
        FXMLLoader loader= new  FXMLLoader(getClass().getResource(archivo + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Confirmar acción");
        stage.show();
        
    }

    @FXML
    private void acceptar(ActionEvent event) {
        // Se realiza un check a todos los elementos y si es correcto todo se sube a la base de datos
        
        // Mostrar ventana de confirmar datos
    }

    @FXML
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

    @FXML
    private void evaluateNk(MouseEvent event) {
        // Igual que el anterior pero en este caso accedido por el raton
        KeyEvent x = null;
        evaluateNk(x);
    }

    /** Evaluacion EMAIL **/
    
    @FXML
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

    @FXML
    private void evaluateEm(MouseEvent event) {
        // Igual que el anterior pero accedido por el raton
        KeyEvent x = null;
        evaluateEm(x);
    }
    
    /** EVALUACION CONTRASEÑA 1 **/
    
    
    @FXML
    private void evaluate(KeyEvent event) {
        String pass = inputPass.getText();
        if(pass.length() < 3 && pass.length() > 16) {
            String errS = "La contraseña no se ajusta \n a los parametros indicados";
            textModification(errorPass, errS, ERR);
        } else {
            errorPass.setVisible(false);
        }
    }

    @FXML
    private void evaluate(MouseEvent event) {
        evaluate((KeyEvent) null);
    }
    
    /** AVISO DE PERMITIDOS A LA HORA DE INTRODUCIR UNA CONTRASEÑA **/

    @FXML
    private void inform(MouseEvent event) {
        String errS = "La contraseña tiene que ser de entre 4 y 15 caracteres.";
        textModification(errorPass, errS, GOOD);
    }

    /** EVALUACION DE LAS CONTRASEÑAS **/
    
    @FXML
    private void evaluateEq(KeyEvent event) {
        String pass2 = inputPass1.getText();
        if(!pass.equals(pass2)) {
            String errS = "Las contraseñas no coinciden";
            textModification(errorPass1, errS, ERR);
        }
    }

    @FXML
    private void evaluateEq(MouseEvent event) {
        evaluateEq((KeyEvent) null);
    }

    /** INFORMAR SOBRE LAS CONTRASEÑA 2 **/
    
    @FXML
    private void informPass(MouseEvent event) {
    }
    
    Stage getStage() { return stage; }
}
