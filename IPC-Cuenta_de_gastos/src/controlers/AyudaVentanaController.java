/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author elgor
 */
public class AyudaVentanaController implements Initializable {
    
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private MiPerfilController principal;
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private HBox titulo;
    @FXML
    private Label texto_como_añadir_gasto;
    @FXML
    private Label texto_ver_gastos;
    @FXML
    private Label texto_exportar;
    @FXML
    private Label texto_graficas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public void init(MiPerfilController principal) {
        this.principal = principal;
    }
}
