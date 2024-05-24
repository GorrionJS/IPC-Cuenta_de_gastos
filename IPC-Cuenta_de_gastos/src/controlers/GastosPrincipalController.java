/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Charge;

/**
 * FXML Controller class
 *
 * @author elgor
 */

public class GastosPrincipalController implements Initializable {

    private Acount cuenta;
    private MiPerfilController principal;
    private List<Charge> cargosTotales;
    private Charge cargoActual;
    
    @FXML
    private LineChart<String, Double> graficoTotalPorMeses;
        @FXML
    private NumberAxis ejeYGraficoMeses;
    @FXML
    private CategoryAxis ejeXGraficoMeses;
    @FXML
    private PieChart bolaGrafico;
    @FXML
    private BarChart<Integer, Double> graficoBarras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cuenta = Acount.getInstance();
        } catch (AcountDAOException ex) { 
            Logger.getLogger(GastosPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GastosPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        
        
    }    
    
    public void init (MiPerfilController prin) throws IOException{
        this.principal = prin;
        //this.cuenta = cuenta;
    }
    
    private void graficoDiaADia() throws AcountDAOException {
       // Crear una serie de datos
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Gastos día a día");
        
        // Obtenemos todos los cargos
        cargosTotales = cuenta.getUserCharges();
    }
    
    private void graficoMes() throws AcountDAOException {
        double eneroC = 0.0;
        double febreroC = 0.0;
        double marzoC = 0.0;
        double abrilC = 0.0;
        double mayoC = 0.0;
        double junioC = 0.0;
        double julioC = 0.0;
        double agostoC = 0.0;
        double septiembreC = 0.0;
        double octubreC = 0.0;
        double noviembreC = 0.0;
        double diciembreC = 0.0;
        
        // Crear una serie de datos
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Gastos por mes");
        
            cargosTotales = cuenta.getUserCharges();
            for (int i = 0; i < cargosTotales.size(); i++) {
                cargoActual = cargosTotales.get(i);
                // Ya tenemos obtenido el cargo, vamos a ver en qué mes lo metemos
                
                LocalDate fechaCargo = cargoActual.getDate();
                Month mes = fechaCargo.getMonth();
                int unidades = cargoActual.getUnits();
                
                while (unidades > 0) {
                    switch (mes) {
                    case JANUARY:
                        eneroC += cargoActual.getCost();
                        break;
                    case FEBRUARY:
                          febreroC += cargoActual.getCost();
                        break;
                    case MARCH:
                          marzoC += cargoActual.getCost();
                        break;
                    case APRIL:
                          abrilC += cargoActual.getCost();
                        break;
                    case MAY:
                          mayoC += cargoActual.getCost();
                        break;
                    case JUNE:
                          junioC += cargoActual.getCost();
                        break;
                    case JULY:
                          julioC += cargoActual.getCost();
                        break;
                    case AUGUST:
                          agostoC += cargoActual.getCost();
                        break;
                    case SEPTEMBER:
                          septiembreC += cargoActual.getCost();
                        break;
                    case OCTOBER:
                          octubreC += cargoActual.getCost();
                        break;
                    case NOVEMBER:
                          noviembreC += cargoActual.getCost();
                        break;
                    case DECEMBER:
                          diciembreC += cargoActual.getCost();
                        break;
                    default:
                        break;
                    } // Ciere del switch
                } // Cierre del while
            } // Cierre del for
            
            series.getData().add(new XYChart.Data("Enero", eneroC));
            series.getData().add(new XYChart.Data("Febrero", febreroC));
            series.getData().add(new XYChart.Data("Marzo", marzoC));
            series.getData().add(new XYChart.Data("Abril", abrilC));
            series.getData().add(new XYChart.Data("Mayo", mayoC));
            series.getData().add(new XYChart.Data("Junio", junioC));
            series.getData().add(new XYChart.Data("Julio", julioC));
            series.getData().add(new XYChart.Data("Agosto", agostoC));
            series.getData().add(new XYChart.Data("Septiembre", septiembreC));
            series.getData().add(new XYChart.Data("Octubre", octubreC));
            series.getData().add(new XYChart.Data("Noviembre", noviembreC));
            series.getData().add(new XYChart.Data("Diciembre", diciembreC));
            
            graficoTotalPorMeses.getData().setAll(series);
    }
    
}
