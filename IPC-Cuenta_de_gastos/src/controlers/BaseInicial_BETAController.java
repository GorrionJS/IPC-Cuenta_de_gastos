/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * 
 */
public class BaseInicial_BETAController implements Initializable {

    @FXML
    // Boton de Iniciar Sesion
    private Button login_button;
    @FXML
    // Boton de Registrarse
    private Button singup_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void signup(ActionEvent event) {
    }
    
}