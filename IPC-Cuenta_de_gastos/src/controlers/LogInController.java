/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    
    private Parent controller;

    private BooleanProperty validNick;
    
    private BooleanProperty validPass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validNick = new SimpleBooleanProperty();
        validPass = new SimpleBooleanProperty();
        
        validNick.setValue(Boolean.FALSE);
        validPass.setValue(Boolean.FALSE);
        
        wrongPassText.setVisible(false);
        wrongUserText.setVisible(false);
        
        inputNick.focusedProperty().addListener((obj, oldV, newV) -> { if(!newV) {evaluateNick();} });
        
        acceptButton.disableProperty().bind(validNick.not());
        
        
    }    

    @FXML
    private void cancelarM(MouseEvent event) {
        
        
    }

    @FXML
    private void aceptarM(MouseEvent event) throws AcountDAOException, IOException {
        
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
            }
    }

    @FXML
    private void acceptar(ActionEvent event) throws IOException, AcountDAOException {
        String log = inputNick.getText();
        String con = inputPass.getText();
        
        if(cuenta.logInUserByCredentials(log, con)){

            FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource("/fxmls/Main_Profile.fxml"));
            Parent root = fxmlMain.load();
            
            Scene scene = new Scene(root, javafxmlapplication.JavaFXMLApplication.MIN_WIDTH, javafxmlapplication.JavaFXMLApplication.MIN_HEIGHT);
           
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
}
