/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
    @FXML
    private BorderPane borderPANE;
    @FXML
    private VBox border;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void signup(ActionEvent event) {
    }

    @FXML
    private void login(MouseEvent event) throws IOException {
//        System.out.println("hola");
//        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("formulario.fxml"));
//        Parent root = fxmlLogin.load();
//        border.setDisable(true);
//        border.setClip(root);
    }
    
    
}
