/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxmls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VacioPrincipalController implements Initializable {

    @FXML
    private BarChart<?, ?> graficoBarras;
    @FXML
    private LineChart<?, ?> graficoTotalPorMeses;
    @FXML
    private NumberAxis ejeYGraficoMeses;
    @FXML
    private CategoryAxis ejeXGraficoMeses;
    @FXML
    private PieChart bolaGrafico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
