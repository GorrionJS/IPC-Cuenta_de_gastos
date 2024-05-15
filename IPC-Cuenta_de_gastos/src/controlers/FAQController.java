/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FAQController implements Initializable {

    @FXML
    private Button BtnInicio;
    @FXML
    private Button btnAddGasto;
    @FXML
    private Button btnVerGasto;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnAyuda;
    @FXML
    private Button btnCerrarSesion;

    
    private PrimeraPantallaController principal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //METODO QUE PROPAGA LA PANTALLA PRINCIPAL
    public void init(PrimeraPantallaController princ){
        principal = princ;
    }
    
    // método que se encarga de desactivar las funciones 
    public void desactivar(boolean parametro){
        boolean orden = parametro;
        BtnInicio.setDisable(orden);
        btnAddGasto.setDisable(orden);
        btnAyuda.setDisable(orden);
        btnCerrarSesion.setDisable(orden);
        btnExportar.setDisable(orden);
        btnPerfil.setDisable(orden);
        btnVerGasto.setDisable(orden);
    }

    
    // una de las funciones que añadiremos poco a  poco
    @FXML
    private void addGastoMethod(ActionEvent event) throws IOException {
       
        FXMLLoader fxmlAdd = new FXMLLoader(getClass().getResource("/fxmls/añadirCargo.fxml"));
        
        AnchorPane es = fxmlAdd.load();      
        AñadirCargoController controlAdd =fxmlAdd.getController();
        controlAdd.init(principal);
        
        principal.getAnchorPane().getChildren().setAll(es);
    }
    
}
