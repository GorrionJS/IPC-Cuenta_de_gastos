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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author joanb
 */

// Controlador del MARCO

public class PrimeraPantallaController implements Initializable {

    @FXML
    private BorderPane borderPANE;
    @FXML
    private Button login_button;
    @FXML
    private Button singup_button;
    @FXML
    private AnchorPane screen;
    @FXML
    private AnchorPane sideScreen;

    /**
     * Initializes the controller class.
     */
    
    private static void resizable(AnchorPane pan, Node child) {
        pan.setBottomAnchor(child, 0.0);
        pan.setTopAnchor(child, 0.0);
        pan.setRightAnchor(child, 0.0);
        pan.setLeftAnchor(child, 0.0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String dir = "/fxmls/Novedades";
        try {
            AnchorPane newFXML = FXMLLoader.load((getClass().getResource(dir + ".fxml")));
             screen.getChildren().setAll(newFXML);
             resizable(screen, newFXML);
        } catch (IOException ex) {
            System.err.println("Error al acceder a las novedades. Error " + ex); }
      
        dir = "/fxmls/FAQ";
        try {
            AnchorPane newFXML = FXMLLoader.load((getClass().getResource(dir + ".fxml")));
            sideScreen.getChildren().setAll(newFXML);
            resizable(screen, newFXML);
        } catch (IOException ex) {
            System.err.println("Error al acceder a las FAQ. Error " + ex); }
    }    

    @FXML
    private void signup(ActionEvent event) {
        // Direccion del FXML asociado al registro
        String archivo = "/fxmls/Register_Prueba" ;
        try {
            AnchorPane newFXML = FXMLLoader.load((getClass().getResource(archivo + ".fxml")));
            screen.getChildren().setAll(newFXML);
            resizable(screen, newFXML);
            singup_button.setDisable(true);
        } catch (IOException ex) {
            System.err.println("Error al acceder al formulario de registro. Error " + ex); }
    }

    @FXML
    private void login(MouseEvent event) {
    }
    
}
