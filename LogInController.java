/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.AcountDAOException;

// TREMENDA MODIFICACION PARA PUBLICAR LA RAMA
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LogInController implements Initializable {

    @FXML
    private TextField userText;
    @FXML
    private Text wrongUserText;
    @FXML
    private TextField paswordText;
    @FXML
    private Text wrongPassText;
    
    
    // ventana principal, aqui almacenamos el controller del principal, util en cada clase del anchorPane
    private PrimeraPantallaController principal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wrongPassText.setVisible(false);
        wrongUserText.setVisible(false);
    }    
    
    // método presente en todos los controlers
    public void init( PrimeraPantallaController princ){
        principal = princ;
    }

    @FXML
    private void cancelarM(MouseEvent event) {
        
        
    }

    @FXML
    private void aceptarM(MouseEvent event) throws AcountDAOException, IOException {
        String log =userText.getText();
        String con =paswordText.getText();
        
        if(principal.getAcount().logInUserByCredentials(log, con) ){
              
              userText.setText("correcto");
              wrongUserText.setVisible(false);
              paswordText.setText("correcto");
              wrongPassText.setVisible(false);
              
              
              // ACTIVA LAS FUNCIONES UNA VEZ COMPROBADO QUE ES UN USUARIO VALIDO
              principal.getRightPaneController().desactivar(false);
                 
            }else{
            userText.setText("incorrecto");
            wrongUserText.setVisible(true);
            paswordText.setText("incorrecto");
            wrongPassText.setVisible(true);
        }
    }
    
}
