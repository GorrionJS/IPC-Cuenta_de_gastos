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
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private boolean pressed;
    private String nC;
    private String dC;
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private Stage stage;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField descriptionCategory;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * GETTERS
     */
    
    public boolean isPressed(){
        return this.pressed;
    }
    
    public String getNomCat(){
        return this.nC;
    }
    
    public String getDescCat(){
        return this.dC;
    }
    
    /**
     * BOTONES DE ACEPTAR Y CANCELAR
     * @throws AcountDAOException 
     */
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
