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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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

    private Acount cuenta;
    
    private FXMLLoader setDisplay(String dir, AnchorPane pan) {
        FXMLLoader newFXML = null;
        try {
            newFXML = new FXMLLoader(getClass().getResource(dir + ".fxml"));
            resizable(pan);
            AnchorPane pane = newFXML.load();
            resizable(pane);
            pan.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.err.println("Error al acceder a " + dir + " . Error " + ex); }
        finally { return newFXML;}
    }
    
    private void resizable(AnchorPane pan) {
        pan.setBottomAnchor(pan, 0.0);
        pan.setTopAnchor(pan, 0.0);
        pan.setLeftAnchor(pan, 0.0);
        pan.setRightAnchor(pan, 0.0);
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resizable(screen); resizable(sideScreen);
        setDisplay("/fxmls/FAQ", sideScreen);
        setDisplay("/fxmls/Novedades", screen);
        
        try {
            cuenta = Acount.getInstance();
        } catch (AcountDAOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @FXML
    private void signup(ActionEvent event) throws IOException {
        // Direccion del FXML asociado al registro
        String dir = "/fxmls/Register";
        try {
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource(dir + ".fxml"));
            AnchorPane newW = newFXML.load();
            resizable(newW);
            screen.getChildren().setAll(newW);

            RegisterController controller = newFXML.getController();
            controller.setAcount(cuenta);
            
            singup_button.setDisable(true);
            login_button.setDisable(false);

        } catch (IOException ex) {
            System.err.println("Error al acceder a la ventana de registro. Error " + ex); }
    }
        
    

    @FXML
    private void login(MouseEvent event) throws IOException {
        String dir = "/fxmls/Log_In";
        try {
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource(dir + ".fxml"));
            AnchorPane newW = newFXML.load();
            resizable(newW);
            screen.getChildren().setAll(newW);

            LogInController controller = newFXML.getController();
            controller.setAccount(cuenta);
            
            singup_button.setDisable(false);
            login_button.setDisable(true);

        } catch (IOException ex) {
            System.err.println("Error al acceder a la ventana de registro. Error " + ex); }
    }
    //metodo que permite abrir la ventana en el anchorPane, ya que necesitamos obtener el controller de cada FXML
    public AnchorPane getAnchorPane(){
        return screen;
    }
    
    public Acount getAcount() { return cuenta; }
}
