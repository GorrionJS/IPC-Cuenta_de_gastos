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
    
    private static final String INICIO = "/fxmls/";
    private static final String PERFIL = "/fxmls/";
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
    

    /**************************************************************************
     *                          BOTON INCIO
     *
     */
    
    @FXML
    private void inicio(ActionEvent event) throws IOException {
        FXMLLoader inicio = new FXMLLoader(getClass().getResource(INICIO + ".fxml"));
        AnchorPane pane = inicio.load();
        
    }
    /**************************************************************************
     *                          BOTON PERFIL
     *
     */

    @FXML
    private void miPerfil(ActionEvent event) {
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
        control.init(principal);
        control.initMiperfil(this);
        
        borderPANE.setCenter(root);
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
    }
    
    /**************************************************************************
     *                          BOTON CERRAR SESION
     *
     */

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource(ANTERIOR + ".fxml"));
        Parent root = fxmlMain.load();
            
        PrimeraPantallaController controller = fxmlMain.getController();
        cuenta.logOutUser();
        controller.setAcount(this.cuenta);
            
        BorderPane p = principal.getGrid();
        p.getChildren().setAll(root);
    }
    
    /**************************************************************************
     *                          OTRAS CLASES
     *
     */
    
    public BorderPane getBorderPaneMiPerfilController(){
        return borderPANE;
    }
    
    // METODO DE INICIALIZACION
    public void init (PrimeraPantallaController prin, Acount a){
        principal = prin;
        cuenta = a;
        userName.setText(cuenta.getLoggedUser().getNickName());
        userProfile.setImage(cuenta.getLoggedUser().getImage());
    }
}
