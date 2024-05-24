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

/**
 * FXML Controller class
 *
 * @author elgor
 */
public class AyudaVentanaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private MiPerfilController principal;
    @FXML
    private Label titulo;
    @FXML
    private Label texto_como_añadir_gasto;
    @FXML
    private Label texto_ver_gastos;
    @FXML
    private Label texto_exportar;
    @FXML
    private Label texto_graficas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titulo.setText("¿Necesitas ayuda?");
        
        texto_como_añadir_gasto.setText("Si quieres añadir un gasto, "
                + "pulsa sobre el botón \"Añadir gasto\"."
        + "\n"
        + "¡Introduce el gasto que desees, y listo!"
        );
        
        texto_ver_gastos.setText("Si quieres añadir un gasto, "
                + "pulsa sobre el botón \"Añadir gasto\"."
        + "\n"
        + "¡Ahí podrás ver todos los gastos que llevas hasta el momento!"
        );
        
        texto_exportar.setText("Así es, es posible."
        + "\n"
        + "¡Únicamente pulsa sobre el botón \"Exportar\", "
        + "elige el formato que quieras, y expórtalo!"
        );
        
        texto_graficas.setText("Sin embargo, si prefieres un método más visual,"
                + "también puedes ver tus gastos en gráficas."
        + "\n"
        + "¡Al pulsar sobre el botón \"Inicio\", "
        + "podrás ver hasta tres gráficas con toda tu información!"
        + "\n"
        + "¡La información está repartida en:\n"
                + "1. A lo largo del mes actual.\n"
                + "2. Según las categorías que añadas."
                + "3. Durante todo el año."
        );
    }    
    
    public void init(MiPerfilController principal) {
        this.principal = principal;
    }
    
}
