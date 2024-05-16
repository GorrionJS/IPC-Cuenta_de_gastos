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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    @FXML
    private Button log_out;

    private PrimeraPantallaController principal;
    @FXML
    private Label nomUsuarioText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void init (PrimeraPantallaController prin){
        principal = prin;
        String nombre = principal.getAcount().getLoggedUser().getName();
        // Error en esto nomUsuarioText.setText(nombre);
    }

    @FXML
    private void inicioMethod(ActionEvent event) {
    }

    @FXML
    private void addGastoMethod(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader addGasto = new FXMLLoader(getClass().getResource("/fxmls/añadirCargo.fxml"));
        AnchorPane root = addGasto.load();
        AñadirCargoController control = addGasto.getController();
        control.init(principal);
        borderPANE.setCenter(root);
    }

    @FXML
    private void verGastoMethod(ActionEvent event) throws IOException {
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource("/fxmls/misGastos.fxml"));
        AnchorPane root = verGasto.load();
        MisGastosController control = verGasto.getController();
        control.init(principal);
        borderPANE.setCenter(root);
    }

    @FXML
    private void exportarMethod(ActionEvent event) {
    }

    @FXML
    private void ayudaMethod(ActionEvent event) {
    }

    @FXML
    private void miPerfilMethod(ActionEvent event) {
    }

    @FXML
    private void cerrarCesiónMethod(ActionEvent event) {
        //boolean out = principal.getAcount().logOutUser();
        
    }
}
