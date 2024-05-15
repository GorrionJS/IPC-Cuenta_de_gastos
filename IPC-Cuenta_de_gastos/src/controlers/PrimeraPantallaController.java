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
   
    //cuenta que se crea solo una vez al crear la ventana principal y se va propagando a las demás clases
    private Acount miCuenta;
    //el controler del FAQ será la que nos ayude con la navegación en el programa, se activa cuando se logea el usuario
    //es un parametro al que se podrá acceder en las demás clases con el método getRightPane
    private FAQController controlFAQ;
    
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
        //setDisplay("/fxmls/FAQ", sideScreen);
        
        //como necesitamos darle valor al parametro del sideScreen, lo centramos con el siguiente método 
        //pero como todavia no se ha logeado entonces se mantienen desactivadas las opciones del cliente
        desactivar();
        
        
        try {
            inicializaCuenta();
        } catch (AcountDAOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    // metodo que crea la cuenta, se hace fuera del inicialice para evitar problemas con la creación del usuario y demás
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
    private void signup(ActionEvent event) throws IOException {
        // Direccion del FXML asociado al registro
        //setDisplay("/fxmls/Register", screen);
            FXMLLoader log= new FXMLLoader(getClass().getResource("/fxmls/Register.fxml"));
             AnchorPane login = log.load();
             RegisterController register = log.getController();
             
             // el metodo init ayuda a propagar el this the esta clase, es la que guarda la cuenta logeada para no realizar comprobaciones en cada ventana
             register.init(this);
             screen.getChildren().setAll(login);
    }
    

    @FXML
    private void login(MouseEvent event) throws IOException {
             
             FXMLLoader log= new FXMLLoader(getClass().getResource("/fxmls/Log_In.fxml"));
             AnchorPane login = log.load();
             LogInController loge = log.getController();
             // el init está en todas las ventanas del anchorPane
             loge.init(this);
             screen.getChildren().setAll(login);
    }
    
    //metodo que ayuda a obtener la cuenta de la ventana principal
    public Acount getAcount(){
        return miCuenta;
    }
    //metodo que permite abrir la ventana en el anchorPane, ya que necesitamos obtener el controller de cada FXML
    public AnchorPane getAnchorPane(){
        return screen;
    }
    
    //metodo que desabilita los botones hasta que el usuario se logee
    private void desactivar() {
        try{
        AnchorPane root;
        FXMLLoader fxmlFAQ = new FXMLLoader(getClass().getResource("/fxmls/FAQ.fxml"));
        
        root = fxmlFAQ.load();      
        controlFAQ =fxmlFAQ.getController();
        controlFAQ.init(this);
        controlFAQ.desactivar(true);
        sideScreen.getChildren().setAll(root);}catch( IOException e){
            System.err.println("Error al acceder a las novedades. Error " + e);
        }
        
    }
    
    //devuelve el controller de la clase que controla las funciones del programa
    // lo usa el login también para volver a activar las funciones una vez el usuario se ha logeado
    public FAQController getRightPaneController(){
        return controlFAQ;
    }
}
