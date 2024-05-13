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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Acount;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class BaseInicial_BASEController implements Initializable {

    @FXML
    private BorderPane borderPANE;
    @FXML
    private Button login_button;
    @FXML
    private Button singup_button;
    @FXML
    private AnchorPane screen;
    @FXML
    private VBox right_screen;
    
    
    private Acount cuenta;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String dir = "/fxmls/Novedades";
        try {
            VBox newFXML = FXMLLoader.load((getClass().getResource(dir + ".fxml")));
            screen.getChildren().setAll(newFXML);
        } catch (IOException ex) {
            System.err.println("Error al acceder a las novedades. Error " + ex); }
    }    

    @FXML
    private void signup(ActionEvent event) {
        // Direccion del FXML asociado al registro
        String formulario = "/fxmls/register" ;
        try {
            AnchorPane newFXML2 = FXMLLoader.load((getClass().getResource(formulario + ".fxml")));
            screen.getChildren().setAll(newFXML2);
            singup_button.setDisable(true);
        } catch (IOException ex) {
            System.err.println("Error al acceder al formulario de registro. Error " + ex); }
        
        
 
                
        
    }
    

    @FXML
    private void login(MouseEvent event) throws IOException {
             FXMLLoader fxmlF= new FXMLLoader(getClass().getResource("formulario.fxml"));
             Parent root = fxmlF.load();
           
             borderPANE.setCenter(root);
    }
    
    
    public Acount getAcount(){
        return cuenta;
    }
    
}
