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
import javafx.scene.layout.GridPane;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class PrimeraPantallaController implements Initializable {
    private final boolean BYPASS = true;
    private final String NOVEDADES = "/fxmls/Novedades";
    private final String FAQ = "/fxmls/FAQ";
    private final String LOGIN = "/fxmls/LogIn";
    private final String SINGUP = "/fxmls/Register";
            

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
    
    private void byPass() {
        
    }
    
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
        setDisplay(FAQ, sideScreen);
        clear();
        
        try {
            cuenta = Acount.getInstance();
        } catch (AcountDAOException | IOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void clear() { 
        setDisplay(NOVEDADES, screen);
        singup_button.setDisable(false);
        login_button.setDisable(false);
    }
    

    @FXML
    private void signup(ActionEvent event) throws IOException, AcountDAOException {
        // Direccion del FXML asociado al registro
        try {
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource(SINGUP + ".fxml"));
            AnchorPane newW = newFXML.load();
            RegisterController reg = newFXML.getController();
            resizable(newW);
            screen.getChildren().setAll(newW);

            RegisterController controller = newFXML.getController();
            controller.init(this);
            controller.setAcount(cuenta);
            if(BYPASS) { controller.byPass(); }
            
            singup_button.setDisable(true);
            login_button.setDisable(false);

        } catch (IOException ex) {
            System.err.println("Error al acceder a la ventana de registro. Error " + ex); 
        } catch (IllegalStateException ex) {
            System.err.println("Tete eres tonto y no has puest obien el archivo");
        }
    }
        
    

    @FXML
    private void login(MouseEvent event) throws IOException, AcountDAOException {
        try {
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource(LOGIN + ".fxml"));
            AnchorPane newW = newFXML.load();
            resizable(newW);
            screen.getChildren().setAll(newW);

            LogInController controller = newFXML.getController();
            controller.init(this);
            controller.setAccount(cuenta);
            if(BYPASS) { controller.byPass(); }
            
            singup_button.setDisable(false);
            login_button.setDisable(true);

        } catch (IOException ex) {
            System.err.println("Error al acceder a la ventana de registro. Error " + ex); }
    }
 
    public AnchorPane getAnchorPane(){ return screen; }
    
    // Devuelve la cuenta creada en register
    public Acount getAcount() { return cuenta; }
    
    public BorderPane getGrid() {return borderPANE; }
    
    public void setAcount(Acount c) { cuenta = c; }
}
