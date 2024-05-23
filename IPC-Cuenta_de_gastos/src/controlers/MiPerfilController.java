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

    private BorderPane borderPANE;

    private PrimeraPantallaController principal;
    private ImageView userProfile;
    private Label userName;
    
    private Acount cuenta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    
    
    public void init (PrimeraPantallaController prin, Acount a){
        principal = prin;
        cuenta = a;
        userName.setText(cuenta.getLoggedUser().getNickName());
        userProfile.setImage(cuenta.getLoggedUser().getImage());
        // Error en esto nomUsuarioText.setText(nombre);
    }


    private void addGastoMethod(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader addGasto = new FXMLLoader(getClass().getResource("/fxmls/añadirCargo.fxml"));
        AnchorPane root = addGasto.load();
        AñadirCargoController control = addGasto.getController();
        control.init(principal);
        borderPANE.setCenter(root);
    }

    private void verGastoMethod(ActionEvent event) throws IOException {
        
    }


    private void gastos(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource("/fxmls/misGastos.fxml"));
        AnchorPane root = verGasto.load();
        MisGastosController control = verGasto.getController();
        control.init(principal);
        control.initMiperfil(this);
        
        borderPANE.setCenter(root);
    }


    private void signOut(ActionEvent event) throws IOException {
        String dir = "/fxmls/Marco_Vacio_Inicial";
        FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource(dir + ".fxml"));
        Parent root = fxmlMain.load();
            
        PrimeraPantallaController controller = fxmlMain.getController();
        cuenta.logOutUser();
        controller.setAcount(cuenta);
            
        BorderPane p = principal.getGrid();
        p.getChildren().setAll(root);
    }
    
    public BorderPane getBorderPaneMiPerfilController(){
        return borderPANE;
    }
}
