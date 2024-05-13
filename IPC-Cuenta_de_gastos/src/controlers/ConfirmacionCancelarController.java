/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class ConfirmacionCancelarController implements Initializable {
    
    // Minimo de redimension de la pantalla (X)
    private static final int MIN_WIDTH = 640;
    // Minimo de redimension de la pantalla (Y) 
    // Actualmente 480 + 32 (VGA standard + title bar Windows 11 (32)) + 4 (Ajuste)
    private static final int MIN_HEIGHT = 516;

    @FXML
    private Button cancel;
    @FXML
    private Button accept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        
        Stage stage = RegisterController.getStage();
        stage.close();
    }

    @FXML
    private void acceptar(ActionEvent event) {
    }
    
}
