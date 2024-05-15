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
    private AnchorPane screen;
    private AnchorPane sideScreen;
   
    //cuenta que se crea solo una vez al crear la ventana principal y se va propagando a las demás clases
    private Acount miCuenta;
    //el controler del FAQ será la que nos ayude con la navegación en el programa, se activa cuando se logea el usuario
    //es un parametro al que se podrá acceder en las demás clases con el método getRightPane
    private FAQController controlFAQ;
    @FXML
    private VBox border;
    @FXML
    private VBox derechaPane;
    
    public void setDisplay(String dir, AnchorPane pan) {
        try {
            VBox newFXML = FXMLLoader.load((getClass().getResource(dir + ".fxml")));
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
        //setDisplay("/fxmls/Novedades", screen);
        //setDisplay("/fxmls/FAQ", sideScreen);
        
        
        // los siguientes metodos abilitan la parte centro y derecha del programa,
        // ******novedadesRediensionable habilita la parte de las novedades
        // ******desactivar habilita la parte de las funciones pero no se puede acceder hasta que el usuario se logea
        novedadesRedimensionable();
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
             FXMLLoader cargadorReg= new FXMLLoader(getClass().getResource("/fxmls/register.fxml"));
             VBox reg = cargadorReg.load();
             RegisterController register = cargadorReg.getController();
             
             // el metodo init ayuda a propagar el this the esta clase, es la que guarda la cuenta logeada para no realizar comprobaciones en cada ventana
             register.init(this);
             borderPANE.setCenter(reg);
    }
    

    @FXML
    private void login(MouseEvent event) throws IOException {
             
             FXMLLoader log= new FXMLLoader(getClass().getResource("/fxmls/Log_In.fxml"));
             VBox login = log.load();
             LogInController loge = log.getController();
             // el init está en todas las ventanas del anchorPane
             loge.init(this);
             borderPANE.setCenter(login);
    }
    
    //metodo que ayuda a obtener la cuenta de la ventana principal
    public Acount getAcount(){
        return miCuenta;
    }
    //metodo que permite abrir un FXML en el centro, con el respectivo setCenter(VBOX)
    public BorderPane getBorderPane(){
        return borderPANE;
    }
    
    //metodo que desabilita los botones hasta que el usuario se logee
    private void desactivar() {
        try{
        VBox root;
        FXMLLoader fxmlFAQ = new FXMLLoader(getClass().getResource("/fxmls/FAQ.fxml"));
        
        root = fxmlFAQ.load();      
        controlFAQ =fxmlFAQ.getController();
        controlFAQ.init(this);
        controlFAQ.desactivar(true);
        borderPANE.setRight(root);
        }catch( IOException e){
            System.err.println("Error al acceder a la ventana de opciones. Error " + e);
        }
        
    }
    
    //devuelve el controller de la clase que controla las funciones del programa
    // lo usa el login también para volver a activar las funciones una vez el usuario se ha logeado
    public FAQController getRightPaneController(){
        return controlFAQ;
    }
    
    //método que abre la ventana de novedades de forma redimensionable
    private void novedadesRedimensionable() {
        try{
        VBox root;
        FXMLLoader fxmlNov = new FXMLLoader(getClass().getResource("/fxmls/Novedades.fxml"));
        root= fxmlNov.load();
        borderPANE.setCenter(root);}
        catch(IOException e){
            System.err.println("Error al acceder a la ventana de novedades. Error " + e);
        }
    }
}
