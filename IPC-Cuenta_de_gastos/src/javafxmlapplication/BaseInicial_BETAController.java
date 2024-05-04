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
    // Boton de iniciar sesion
    private Button login_button;
    @FXML
    // Boton de registrarse
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
        // Enlazar con el formulario iniciar sesion
    }

    @FXML
    private void signup(ActionEvent event) {
        // Enlazar con el formulario registrar
    }
    
}
