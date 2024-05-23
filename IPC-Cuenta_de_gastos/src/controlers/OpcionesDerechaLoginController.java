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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class OpcionesDerechaLoginController implements Initializable {

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
    
    private Button botonSeleccionado;
    private PrimeraPantallaController principal;
    private Acount cuenta;
    /**
     * Initializes the controller class.
     */
    
    
    public void init (PrimeraPantallaController prin, Acount a){
        principal = prin;
        cuenta = a;
        //userName.setText(cuenta.getLoggedUser().getNickName());
        //userProfile.setImage(cuenta.getLoggedUser().getImage());
        // Error en esto nomUsuarioText.setText(nombre);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    private void handleButtonPress(Button button) {
        if (botonSeleccionado != null) {
            botonSeleccionado.getStyleClass().remove("selected");
        }
        button.getStyleClass().add("selected");
        botonSeleccionado = button;
    }

    @FXML
    private void inicio(ActionEvent event) {
        handleButtonPress(inicioButton);
    }

    @FXML
    private void miPerfil(ActionEvent event) {
        handleButtonPress(profileButton);
    }

    @FXML
    private void gastos(ActionEvent event) throws IOException, AcountDAOException {
        handleButtonPress(gastosButton);
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource("/fxmls/misGastos.fxml"));
        AnchorPane root = verGasto.load();
        resizable(root);
        MisGastosController control = verGasto.getController();
        control.init(principal);
        principal.getAnchorPane().getChildren().setAll(root);
    }

    @FXML
    private void exportar(ActionEvent event) {
        handleButtonPress(exportarButton);
    }

    @FXML
    private void signOut(ActionEvent event) throws IOException {


            Window window = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
            window.hide(); // Esto oculta la ventana actual
        
            // Crear una nueva ventana
            Stage newStage = new Stage();
            FXMLLoader nuevo = new FXMLLoader(getClass().getResource("/fxmls/Marco_Vacio_Inicial.fxml"));
            Parent root = nuevo.load();
            Scene scene = new Scene(root);

            newStage.setScene(scene);
            newStage.setTitle("THE TOOL");
            newStage.show();

            // Cerrar la ventana actual definitivamente
            window.hide();
            ((Stage) window).close();
        
    }
    private void resizable(AnchorPane pan) {
        pan.setBottomAnchor(pan, 0.0);
        pan.setTopAnchor(pan, 0.0);
        pan.setLeftAnchor(pan, 0.0);
        pan.setRightAnchor(pan, 0.0);
    }
    

}
