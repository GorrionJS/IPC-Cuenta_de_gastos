/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Acount;
import model.AcountDAOException;
/**
 * FXML Controller class
 *
 * @author elgor
 */
public class Mi_perfilUsuarioController implements Initializable {


    @FXML
    private ImageView imagen_de_perfil_de_usuario;
    @FXML
    private Text nombre_de_usuario;
    @FXML
    private TextField campo_de_texto_del_nickname_NO_MODIFICAR;
    @FXML
    private PasswordField campo_de_texto_de_la_contraseña;
    @FXML
    private PasswordField campo_de_texto_del_email;
    @FXML
    private ToggleButton boton_visualizar_contraseña;
    @FXML
    private ImageView imagen_de_ver_y_no_ver_la_contraseña;
    @FXML
    private DatePicker datePicker_fecha_de_nacimiento;
    
    /**
     * VAMOS A USAR ESTAS VARIABLES PARA SABER SI LO INTRODUCIDO
     * ES CORRECTO O NO
     */
    private Acount cuenta;
    private PrimeraPantallaController principal;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            cuenta = Acount.getInstance();
        } catch (AcountDAOException | IOException ex) {
            //Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombre_usuario = cuenta.getLoggedUser().getName();
        String apellidos_usuario = cuenta.getLoggedUser().getSurname();
        String nickname = cuenta.getLoggedUser().getNickName();
        String email = cuenta.getLoggedUser().getEmail();
        String password = cuenta.getLoggedUser().getPassword();
        //campo_de_texto_del_nickname_NO_MODIFICAR.setText();
        
        
    }    

    //public init(A)
    public void init (PrimeraPantallaController prin, Acount usuario){
        principal = prin;
        this.cuenta = usuario;
    }
    
    @FXML
    private void boton_cambiar_foto(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_nombre_de_usuario(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_contraseña(ActionEvent event) {
    }

    @FXML
    private void boton_guardar_cambios_contraseña(ActionEvent event) {
    }

    @FXML
    private void boton_visualizar_contraseña_pulsado(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_email(ActionEvent event) {
    }

    @FXML
    private void boton_guardar_cambios_email(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_fecha_nacimiento(ActionEvent event) {
    }

    @FXML
    private void boton_guardar_cambios_fecha(ActionEvent event) {
    }

}
