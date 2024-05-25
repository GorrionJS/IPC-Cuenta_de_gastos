/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author elgor
 */
public class FAQController implements Initializable {
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private VBox color1;
    @FXML
    private VBox color2;
    @FXML
    private VBox fila3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        color1.setVisible(false);
        color2.setVisible(false);
        fila3.setVisible(false);
        // Configurar evento para boton1
        boton1.setOnMouseEntered(event -> showVBox(color1));
        boton1.setOnMousePressed(event -> showVBox(color1));

        // Configurar evento para boton2
        boton2.setOnMouseEntered(event -> showVBox(color2));
        boton2.setOnMousePressed(event -> showVBox(color2));

        // Configurar evento para boton3
        boton3.setOnMouseEntered(event -> showVBox(fila3));
        boton3.setOnMousePressed(event -> showVBox(fila3));

        // Configurar evento para hacer clic fuera de los botones
        color1.getParent().setOnMousePressed(event -> hideAllVBoxes());        
    }    
    
    private void showVBox(VBox vboxToShow) {
        hideAllVBoxes();  // Ocultar todos los VBoxes primero
        vboxToShow.setVisible(true);  // Mostrar el VBox solicitado
    }

    private void hideAllVBoxes() {
        color1.setVisible(false);
        color2.setVisible(false);
        fila3.setVisible(false);
        boton1.setVisible(true);
    }   
}
