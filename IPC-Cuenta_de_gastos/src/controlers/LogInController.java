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
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LogInController implements Initializable {
    
    @FXML
    private Text wrongUserText;
    @FXML
    private Text wrongPassText;
    @FXML
    private AnchorPane main;
    @FXML
    private TextField inputNick;
    @FXML
    private PasswordField inputPass;
    @FXML
    private Button acceptButton;
    
    private Acount cuenta;

    private BooleanProperty validNick;
    
    private BooleanProperty validPass;
    
    private PrimeraPantallaController principal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
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
    
    public void setAccount(Acount c) { cuenta = c; }

    @FXML
    private void cancel(ActionEvent event) {
        ButtonType ok = new ButtonType("Acceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, "Esta a punto de elminiar todos los datos rellenados",
        ok, no);
        
        alert.setContentText("Esta seguro de que quiere eliminar todos los datos");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ok) { 
                inputNick.clear();
                inputPass.clear();
                principal.clear();
            }
    }

    @FXML
    private void acceptar(ActionEvent event) throws IOException, AcountDAOException {
        String log = inputNick.getText();
        String con = inputPass.getText();
        
        if(cuenta.logInUserByCredentials(log, con)){

            FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource("/fxmls/Usuario_login_Marco.fxml"));
            Parent root = fxmlMain.load();
            
            MiPerfilController controller = fxmlMain.getController();
            controller.init(principal, cuenta);
            
            BorderPane p = principal.getGrid();
            p.getChildren().setAll(root);
            
        } else {
            wrongPassText.setText("Contraseña incorrecta");
            wrongPassText.setVisible(true);
        }
    }
    
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
    
    public void init(PrimeraPantallaController princ){
        principal = princ;
    }
    
    public void byPass() throws IOException, AcountDAOException { 
        inputNick.setText("admin");
        inputPass.setText("admin");
        acceptar(new ActionEvent());
    }

    @FXML
    private void cancelarM(MouseEvent event) {
    }

    @FXML
    private void aceptarM(MouseEvent event) {
    }

    @FXML
    private void atras(ActionEvent event) {
        
    }
}
