/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.Acount;
import model.AcountDAOException;
/**
 * FXML Controller class
 *
 * @author elgor
 */
public class Mi_perfilUsuarioController implements Initializable {

    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private Acount cuenta;
    private MiPerfilController principal;
    
    private Image imagenOjoAbierto;
    private Image imagenOjoCerrado;
    
    // PARA LOS LISTENERS
    // Propiedades del usuario
    private final StringProperty nombreUsuario = new SimpleStringProperty();
    private final StringProperty apellidosUsuario = new SimpleStringProperty();
    private final StringProperty emailUsuario = new SimpleStringProperty();
    private final StringProperty contraseñaUsuario = new SimpleStringProperty();
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private ImageView imagen_de_perfil_de_usuario;
    @FXML
    private TextField campo_de_texto_del_nickname_NO_MODIFICAR;
    @FXML
    private PasswordField campo_de_texto_de_la_contraseña;
    @FXML
    private TextField campo_de_texto_del_email;
    @FXML
    private ToggleButton boton_visualizar_contraseña;
    @FXML
    private ImageView imagen_de_ver_y_no_ver_la_contraseña;
    private DatePicker datePicker_fecha_de_nacimiento;
    @FXML
    private Label apellidos_usuario_label;
    @FXML
    private TextField campo_de_texto_de_contraseña_visible;
    @FXML
    private Label nombre_usuario_label;
    @FXML
    private TextField fechaRegistroLabel;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //imagenOjoAbierto = new Image(getClass().getResource("images/ojo.png").toString());
        //imagenOjoCerrado = new Image(getClass().getResource("/images/visible.png").toString());
        
        try {
            cuenta = Acount.getInstance();
        } catch (AcountDAOException | IOException ex) {
            //Logger.getLogger(PrimeraPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Obtenemos información del usuario
        String nombre_usuario = cuenta.getLoggedUser().getName();
        String apellidos_usuario = cuenta.getLoggedUser().getSurname();
        String nickname = cuenta.getLoggedUser().getNickName();
        String email = cuenta.getLoggedUser().getEmail();
        String password = cuenta.getLoggedUser().getPassword();
        Image imagen = cuenta.getLoggedUser().getImage();
        
        // Printeamos la información del usuario por los campos de texto
        //campo_de_texto_del_nickname_NO_MODIFICAR.setText(nombre_usuario);
        nombre_usuario_label.setText(nombre_usuario);
        apellidos_usuario_label.setText(apellidos_usuario);
        campo_de_texto_del_email.setText(email);
        campo_de_texto_de_la_contraseña.setText(password);
        campo_de_texto_de_contraseña_visible.textProperty().bind(
                campo_de_texto_de_la_contraseña.textProperty());
        campo_de_texto_del_nickname_NO_MODIFICAR.setText(nickname);
        imagen_de_perfil_de_usuario.setImage(imagen);
        
        LocalDate fechaRegistro = cuenta.getLoggedUser().getRegisterDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = fechaRegistro.format(formatter);
        fechaRegistroLabel.setText(formattedDate);
        
        /* CAMPOS DÓNDE AÑADIMOS LISTENERS 
        DEBE DE HABER UNO POR CADA BOTÓN
        1. el que cambia; 2º el cambiado */
        // Listeners para actualizar los Labels
        nombreUsuario.addListener((observable, oldValue, newValue) -> {
            nombre_usuario_label.setText(newValue);
        });

        apellidosUsuario.addListener((observable, oldValue, newValue) -> {
            apellidos_usuario_label.setText(newValue);
        });

        // Listeners para actualizar los TextFields
        emailUsuario.addListener((observable, oldValue, newValue) -> {
            campo_de_texto_del_email.setText(newValue);
        });

        contraseñaUsuario.addListener((observable, oldValue, newValue) -> {
            campo_de_texto_de_contraseña_visible.setText(newValue);
            campo_de_texto_de_la_contraseña.setText(newValue);
        });

        // Listeners para los TextFields (opcional, si deseas que al editar los campos se actualicen las propiedades)
        campo_de_texto_del_email.textProperty().addListener((observable, oldValue, newValue) -> {
            emailUsuario.set(newValue);
        });

        campo_de_texto_de_contraseña_visible.textProperty().addListener((observable, oldValue, newValue) -> {
            contraseñaUsuario.set(newValue);
        });

        campo_de_texto_de_la_contraseña.textProperty().addListener((observable, oldValue, newValue) -> {
            contraseñaUsuario.set(newValue);
        });
    }    

    ///////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////
    public void init (MiPerfilController prin) throws AcountDAOException {
        this.principal = prin;
    }
    
    ///////////////////////////////////////////////////////
    // METODOS
    ///////////////////////////////////////////////////////
    @FXML
    
    ///////////////////////////////////////////////////////
    // FOTO
    ///////////////////////////////////////////////////////
    private void boton_cambiar_foto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elija nueva imagen");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg")
        );
        
        File selectedFile = fileChooser.showOpenDialog(
                ((Node)event.getSource()).getScene().getWindow()
        );
        
        if (selectedFile != null) {
            Image imagen = new Image(selectedFile.toURI().toString());
            cuenta.getLoggedUser().setImage(imagen);
            imagen_de_perfil_de_usuario.setImage(imagen);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cambios en el perfil");
            alert.setHeaderText("Imagen de perfil cambiada.");
            alert.setContentText("Su imagen de perfil ha sido cambiada exitosamente.");
            alert.showAndWait();
        }
    }

    ///////////////////////////////////////////////////////
    // NOMBRE
    ///////////////////////////////////////////////////////
    @FXML
    private void boton_cambiar_nombre_de_usuario(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Nuevo nombre"); // Por defecto
        dialog.setTitle("Cambios en el perfil");
        dialog.setHeaderText("Cambios en el nombre de usuario");
        dialog.setContentText("Introduzca su nuevo nombre:");
        
        Optional<String> result = dialog.showAndWait();
        
        if (!result.get().equals(cuenta.getLoggedUser().getName())) {
            cuenta.getLoggedUser().setName(result.get());
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cambios en el perfil");
            alert.setHeaderText("Nombre del usuario cambiado.");
            alert.setContentText("Su nombre ha sido cambiado exitosamente.");
            alert.showAndWait();
            
            nombre_usuario_label.setText(result.get());
        }
    }
    
    ///////////////////////////////////////////////////////
    // APELLIDO
    ///////////////////////////////////////////////////////
    @FXML
    private void boton_cambiar_apellidos_de_usuario(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Nuevos apellidos"); // Por defecto
        dialog.setTitle("Cambios en el perfil");
        dialog.setHeaderText("Cambios en los apellidos de usuario");
        dialog.setContentText("Introduzca sus nuevos apellidos:");
        
        Optional<String> result = dialog.showAndWait();
        
        if (!result.get().equals(cuenta.getLoggedUser().getPassword())) {
            cuenta.getLoggedUser().setSurname(result.get());
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cambios en el perfil");
            alert.setHeaderText("Apellidos del usuario cambiados.");
            alert.setContentText("Sus apellidos han sido cambiados exitosamente.");
            alert.showAndWait();
            
            apellidos_usuario_label.setText(result.get());
        }
    }

    ///////////////////////////////////////////////////////
    // CONTRASEÑA
    ///////////////////////////////////////////////////////
    @FXML
    private void boton_cambiar_contraseña(ActionEvent event) {
        campo_de_texto_de_la_contraseña.setDisable(false);
        
        if (campo_de_texto_de_la_contraseña.isDisable()) {
            campo_de_texto_de_la_contraseña.setDisable(true);
        }
    }

    @FXML
    private void boton_guardar_cambios_contraseña(ActionEvent event) {
        String nuevaContraseña = campo_de_texto_de_la_contraseña.getText();
        if (!nuevaContraseña.equals(cuenta.getLoggedUser().getPassword())) {
            cuenta.getLoggedUser().setPassword(nuevaContraseña);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cambios en el perfil");
            alert.setHeaderText("Contraseña cambiada.");
            alert.setContentText("Su contraseña ha sido cambiada exitosamente.");
            alert.showAndWait();
        }
    }

    @FXML
    private void boton_visualizar_contraseña_pulsado(ActionEvent event) {
        if (campo_de_texto_de_la_contraseña.isVisible()){
            campo_de_texto_de_contraseña_visible.setVisible(true);
            campo_de_texto_de_la_contraseña.setVisible(false);
        } else {
            campo_de_texto_de_contraseña_visible.setVisible(false);
            campo_de_texto_de_la_contraseña.setVisible(true);
        }
        if (campo_de_texto_de_la_contraseña.isVisible()) {
            // Mostramos ojo abierto
            imagen_de_ver_y_no_ver_la_contraseña.setImage(imagenOjoAbierto);
        } else {
            // Mostramos ojo tachados
            imagen_de_ver_y_no_ver_la_contraseña.setImage(imagenOjoCerrado);
        }
    }

    ///////////////////////////////////////////////////////
    // EMAIL
    ///////////////////////////////////////////////////////
    @FXML
    private void boton_cambiar_email(ActionEvent event) {
        campo_de_texto_del_email.setDisable(false);
        
        if (campo_de_texto_del_email.isDisable()) {
            campo_de_texto_del_email.setDisable(true);
        }
    }

    @FXML
    private void boton_guardar_cambios_email(ActionEvent event) {
        String nuevoCorreo = campo_de_texto_del_email.getText();
        
        if (!nuevoCorreo.equals(cuenta.getLoggedUser().getEmail())){
            cuenta.getLoggedUser().setEmail(nuevoCorreo);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cambios en el perfil");
            alert.setHeaderText("Dirección de correo electrónico cambiada.");
            alert.setContentText("Su drección de correo electrónico ha sido cambiada exitosamente.");
            alert.showAndWait();
        }
        
    }

    ///////////////////////////////////////////////////////
    // SE PUEDE BORRAR
    ///////////////////////////////////////////////////////
    private void boton_cambiar_fecha_nacimiento(ActionEvent event) {
        datePicker_fecha_de_nacimiento.setDisable(false);
        
        if (datePicker_fecha_de_nacimiento.isDisable()) {
            datePicker_fecha_de_nacimiento.setDisable(true);
        }
    }

    private void boton_guardar_cambios_fecha(ActionEvent event) {
        if (datePicker_fecha_de_nacimiento.getValue() != cuenta.getLoggedUser().getRegisterDate()) {
        }
    }


}
