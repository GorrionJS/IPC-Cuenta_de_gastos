/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class imageNickController implements Initializable {

    @FXML
    private ImageView userProfile;
    @FXML
    private Label userName;
    private PrimeraPantallaController principal;
    @FXML
    private HBox hboxi;
    @FXML
    private VBox vboxi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void init(PrimeraPantallaController princ){
        principal = princ;
        userName.setText(principal.getAcount().getLoggedUser().getName());
        
        userProfile=  new ImageView(principal.getAcount().getLoggedUser().getImage());
        double radio = 33;
        userProfile.setFitHeight(radio *2); userProfile.setFitWidth(radio *2);
        Circle redondo = new Circle(radio,radio,radio);
        userProfile.setClip(redondo);
        hboxi.getChildren().setAll(userProfile,vboxi);

//userProfile.setImage();
    }
    
}
