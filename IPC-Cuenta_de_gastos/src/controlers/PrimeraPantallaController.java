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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class PrimeraPantallaController implements Initializable {

    @FXML
    private BorderPane borderPANE;
    @FXML
    private Button login_button;
    @FXML
    private Button singup_button;
    @FXML
    private AnchorPane screen;
    @FXML
    private AnchorPane sideScreen;
   
    private Acount miCuenta;
    
    public void setDisplay(String dir, AnchorPane pan) {
        try {
            AnchorPane newFXML = FXMLLoader.load((getClass().getResource(dir + ".fxml")));
            pan.getChildren().setAll(newFXML);
        } catch (IOException ex) {
            System.err.println("Error al acceder a las novedades. Error " + ex); }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDisplay("/fxmls/Novedades", screen);
        setDisplay("/fxmls/FAQ", sideScreen);
        
        try {
            inicializaCuenta();
        } catch (AcountDAOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void inicializaCuenta() throws AcountDAOException, IOException{
        try {
            miCuenta= Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signup(ActionEvent event) {
        // Direccion del FXML asociado al registro
        setDisplay("/fxmls/Register", screen);
        
    }
    

    @FXML
    private void login(MouseEvent event) throws IOException {
             
             FXMLLoader log= new FXMLLoader(getClass().getResource("/fxmls/Log_In.fxml"));
             AnchorPane login = log.load();
             LogInController loge = log.getController();
             loge.init(this);
            screen.getChildren().setAll(login);
    }
    
    
    public Acount getAcount(){
        return miCuenta;
    }
    
    public AnchorPane getAnchorPane(){
        return screen;
    }
}
