/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AddCategoryController implements Initializable {

    @FXML
    private Stage stage;
    
    
    private boolean pressed;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField descriptionCategory;
    
    private String nC;
    private String dC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public boolean isPressed(){
        return pressed;
    }
    public String getNomCat(){
        return nC;
    }
    public String getDescCat(){
        return dC;
    }
    
    
    @FXML
    private void aceptar(ActionEvent event) throws AcountDAOException {
        String nomC =categoryName.getText();
        String descC =descriptionCategory.getText();
        if(!nomC.isEmpty() && !descC.isEmpty() ){
            nC = nomC; dC = descC; pressed = true;
            categoryName.getScene().getWindow().hide();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    

    
}
