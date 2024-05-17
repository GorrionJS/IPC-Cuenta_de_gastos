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
    private Button inicioButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button gastosButton;
    @FXML
    private Button exportarButton;
    @FXML
    private Button signOutButton;
    
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

    @FXML
    private void inicio(ActionEvent event) {
    }

    @FXML
    private void miPerfil(ActionEvent event) {
    }

    @FXML
    private void gastos(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource("/fxmls/misGastos.fxml"));
        AnchorPane root = verGasto.load();
        MisGastosController control = verGasto.getController();
        control.init(principal);
        control.initMiperfil(this);
        
        borderPANE.setCenter(root);
    }

    @FXML
    private void exportar(ActionEvent event) {
        //boolean out = principal.getAcount().logOutUser();
        
    }

    @FXML
    private void signOut(ActionEvent event) {
    }
    
    public BorderPane getBorderPaneMiPerfilController(){
        return borderPANE;
    }
}
