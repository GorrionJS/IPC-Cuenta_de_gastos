/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxmls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField inputNombre;
    @FXML
    private PasswordField inputPass2;
    @FXML
    private PasswordField inputPass1;
    @FXML
    private Text errorPass;
    @FXML
    private Text errorPass1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void acceptar(ActionEvent event) {
    }
    
}
