/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
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
public class LogInController implements Initializable {
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private static final String NEXT = "/fxmls/Usuario_login_Marco";
    private Acount cuenta;
    private BooleanProperty validNick;
    private BooleanProperty validPass;
    private PrimeraPantallaController principal;
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private Label wrongUserText;
    @FXML
    private Label wrongPassText;
    @FXML
    private AnchorPane main;
    @FXML
    private TextField inputNick;
    @FXML
    private PasswordField inputPass;
    @FXML
    private Button acceptButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        validNick = new SimpleBooleanProperty();
        validPass = new SimpleBooleanProperty();
        
        validNick.setValue(Boolean.FALSE);
        validPass.setValue(Boolean.FALSE);
        
        wrongPassText.setVisible(false);
        wrongUserText.setVisible(false);
        
        inputNick.focusedProperty().addListener((obj, oldV, newV) -> { if(!newV) {evaluateNick();} });
        inputNick.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)) {inputPass.requestFocus(); }});
        
        inputPass.setOnKeyPressed(event -> { if(event.getCode().equals(KeyCode.ENTER)) try {
            acceptar(null);
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AcountDAOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        } });
        
        acceptButton.disableProperty().bind(validNick.not());
    }
    
    ///////////////////////////////////////////////////////
    // INIT Y BYPASS
    ///////////////////////////////////////////////////////
    public void init(PrimeraPantallaController princ){
        this.principal = princ;
    }
    
    public void byPass() throws IOException, AcountDAOException { 
        this.inputNick.setText("admin");
        this.inputPass.setText("admin");
        acceptar(new ActionEvent());
    }
    
    ///////////////////////////////////////////////////////
    // METODOS DE LA CUENTA
    ///////////////////////////////////////////////////////
    public void setAccount(Acount c) { cuenta = c; }
    
    private void evaluateNick() {
        if(cuenta != null && cuenta.existsLogin(inputNick.getText())) { 
            wrongUserText.setVisible(false);
            validNick.setValue(Boolean.TRUE);
        }
        else { 
            wrongUserText.setVisible(true);
            validNick.setValue(Boolean.FALSE);
        }
    }

    ///////////////////////////////////////////////////////
    // METODOS DE LOS BOTONES ACEPTAR Y CANCELAR
    ///////////////////////////////////////////////////////
    @FXML
    private void acceptar(ActionEvent event) throws IOException, AcountDAOException {
        String log = inputNick.getText();
        String con = inputPass.getText();
        
        if(cuenta.logInUserByCredentials(log, con)){
            FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource(NEXT + ".fxml"));
            Parent root = fxmlMain.load();
            Scene scene = new Scene(root, javafxmlapplication.JavaFXMLApplication.MIN_WIDTH, javafxmlapplication.JavaFXMLApplication.MIN_HEIGHT);
            
            MiPerfilController controller = fxmlMain.getController();
            controller.init(principal, cuenta);
            
            Stage stage = (Stage) main.getScene().getWindow();
            Stage newStage = new Stage();
            
            newStage.setScene(scene);
            stage.close();
            newStage.show();
        } else {
            wrongPassText.setText("Contraseña incorrecta");
            wrongPassText.setVisible(true);
        }
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        ButtonType ok = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        Alert alert = new Alert(Alert.AlertType.WARNING, "Está a punto de elminiar todos los datos rellenados.",
        ok, no);
        
        alert.setContentText("¿Está seguro de que quiere eliminar todos los datos?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ok) { 
                inputNick.clear();
                inputPass.clear();
                principal.clear();
            }
    }
    
    ///////////////////////////////////////////////////////
    // BOTONES
    ///////////////////////////////////////////////////////
    
    @FXML
    private void aceptarM(MouseEvent event) {
    }

    @FXML
    private void cancelarM(MouseEvent event) {
    }

    
    
}
