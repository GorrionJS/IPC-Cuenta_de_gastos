/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxmls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author elgor
 */
public class Mi_perfilController implements Initializable {

    @FXML
    private ImageView imagen_de_perfil_de_usuario;
    @FXML
    private Text nombre_de_usuario;
    @FXML
    private Text apellido_de_usuario;
    @FXML
    private TextField campo_de_texto_del_nickname_NO_MODIFICAR;
    @FXML
    private PasswordField campo_de_texto_de_la_contraseña;
    @FXML
    private PasswordField campo_de_texto_del_email;
    @FXML
    private ToggleButton boton_visualizar_contraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //public init(A)
    
    @FXML
    private void boton_cambiar_foto(ActionEvent event) {
        // Vamos a cambiar la foto de perfil del usuario
        String url = "";
    }

    @FXML
    private void boton_cambiar_fecha_nacimiento(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_email(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_contraseña(ActionEvent event) {
        campo_de_texto_de_la_contraseña.setDisable(false);
        
    }

    @FXML
    private void boton_visualizar_contraseña_pulsado(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_nombre_de_usuario(ActionEvent event) {
    }

    @FXML
    private void boton_cambiar_mi_apellido(ActionEvent event) {
    }

    @FXML
    private void boton_guardar_cambios_contraseña(ActionEvent event) {
    }

    @FXML
    private void boton_guardar_cambios_email(ActionEvent event) {
    }

    @FXML
    private void boton_guardar_cambios_fecha(ActionEvent event) {
    }
    
}
