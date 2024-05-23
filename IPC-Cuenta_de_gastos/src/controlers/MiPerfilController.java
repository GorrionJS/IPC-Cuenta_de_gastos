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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MiPerfilController implements Initializable {
    


    @FXML
    private BorderPane borderPANE;
    @FXML
    private AnchorPane screen;

    private PrimeraPantallaController principal;
    @FXML
    private ImageView userProfile;
    @FXML
    private Label userName;
    @FXML
    private AnchorPane sideScreen;
    
    private Acount cuenta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    // METODO DE INICIALIZACION
    public void init (PrimeraPantallaController prin, Acount a) throws IOException{
        principal = prin;
        cuenta = a;
        userName.setText(cuenta.getLoggedUser().getNickName());
        userProfile.setImage(cuenta.getLoggedUser().getImage());
        
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxmls/BotonesIniciadaSesion.fxml"));
        AnchorPane pane = fxml.load();
        
        SideMenuController controller = fxml.getController();
        controller.init(this, sideScreen, screen, cuenta);
            
        sideScreen.getChildren().setAll(pane);
    }
    
    public Acount getAcount() { return cuenta; }
    
    
    
}
