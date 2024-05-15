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



/**
 * FXML Controller class
 *
 * @author Usuario
 */









/*
    todavia falta implementar los datos y demás
*/
public class AñadirCargoController implements Initializable {
     private PrimeraPantallaController principal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void init(PrimeraPantallaController first){
        principal = first;
        
    }

    @FXML
    private void addScannImage(ActionEvent event) {
    }
    
}
