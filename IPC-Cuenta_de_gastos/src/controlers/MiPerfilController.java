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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MiPerfilController implements Initializable {
    
    private static final String INICIO = "/fxmls/vacioPrincipal";
    private static final String PERFIL = "/fxmls/mi_perfil_usuario_datos";
    private static final String GASTOS = "/fxmls/misGastos";
    private static final String AYUDA = "/fxmls/";
    private static final String EXPORTAR = "/fxmls/Exportar";
    private static final String ANTERIOR = "/fxmls/Marco_Vacio_Inicial";

    @FXML
    private BorderPane borderPANE;
    @FXML
    private AnchorPane screen;

    private PrimeraPantallaController principal;
    @FXML
    private ImageView userProfile;
    @FXML
    private Label userName;
    private AnchorPane sideScreen;
    
    private Acount cuenta;
    @FXML
    private Button exportarButton;
    @FXML
    private Button inicioButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button gastosButton;
    @FXML
    private Button signOutButton;
    @FXML
    private Button ayudaButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void init (PrimeraPantallaController prin, Acount a) throws IOException{
        principal = prin;
        cuenta = a;
        userName.setText(cuenta.getLoggedUser().getNickName());
        userProfile.setImage(cuenta.getLoggedUser().getImage());
    }
    
    
    /**************************************************************************
     *                          BOTON INICIO
     *
     */
    
    @FXML
    private void inicio(ActionEvent event) throws IOException {
        FXMLLoader inicio = new FXMLLoader(getClass().getResource(INICIO + ".fxml"));
        AnchorPane root = inicio.load();
        screen.getChildren().setAll(root);
        reEnable();
        inicioButton.setDisable(true);
        
    }
    /**************************************************************************
     *                          BOTON PERFIL
     *
     */

    @FXML
    private void miPerfil(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader miPerfil = new FXMLLoader(getClass().getResource(PERFIL + ".fxml"));
        AnchorPane root = miPerfil.load();
        
        Mi_perfilUsuarioController control = miPerfil.getController();
        control.init(this);
        
        screen.getChildren().setAll(root);
        
        reEnable();
        profileButton.setDisable(true);
    }

    /**************************************************************************
     *                          BOTON GASTOS
     *
     */
    
    @FXML
    private void gastos(ActionEvent event) throws IOException, AcountDAOException {
        FXMLLoader verGasto = new FXMLLoader(getClass().getResource(GASTOS + ".fxml"));
        AnchorPane root = verGasto.load();
        
        MisGastosController control = verGasto.getController();
        control.initMiperfil(this);
        
        screen.getChildren().setAll(root);
        
        reEnable();
        gastosButton.setDisable(true);
    }
    
    /**************************************************************************
     *                          BOTON EXPORTAR
     *
     */

    @FXML
    private void exportar(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource(EXPORTAR + ".fxml"));
        AnchorPane root = fxml.load();
        ExportController controller = fxml.getController();
        controller.init(cuenta, this);
        
        screen.getChildren().setAll(root);
        
        reEnable();
        exportarButton.setDisable(true);
    }
    
        @FXML
    private void ayudaButtonPresionado(ActionEvent event) {
    }
    
    /**************************************************************************
     *                          BOTON CERRAR SESION
     *
     */

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        /*
        FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource(ANTERIOR + ".fxml"));
        Parent root = fxmlMain.load();
            
        PrimeraPantallaController controller = fxmlMain.getController();
        cuenta.logOutUser();
        controller.setAcount(this.cuenta);
            
        BorderPane p = principal.getGrid();
        p.getChildren().setAll(root);
        */
        
        FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource(ANTERIOR + ".fxml"));
        Parent root = fxmlMain.load();
            
        Scene scene = new Scene(root, javafxmlapplication.JavaFXMLApplication.MIN_WIDTH, javafxmlapplication.JavaFXMLApplication.MIN_HEIGHT);
           
        Stage stage = (Stage) screen.getScene().getWindow();
        Stage newStage = new Stage();
            
        newStage.setScene(scene);
        stage.close();
        newStage.show();
    }
    
    /**************************************************************************
     *                          OTRAS CLASES
     *
     */
    
    private void reEnable() {
        inicioButton.setDisable(false);
        profileButton.setDisable(false);
        gastosButton.setDisable(false);
        exportarButton.setDisable(false);
    }
    
    public void setAcount(Acount p) {
        cuenta = p;
    }
    public Acount getAcount() { return cuenta; }
    
}
