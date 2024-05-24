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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Category;
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
    private List<Category> categoriasUsuario;
    private Map<String, Double> tablaHash;
    
    @FXML
    private LineChart<String, Double> graficoTotalPorMeses;
    @FXML
    private NumberAxis ejeYGraficoMeses;
    @FXML
    private CategoryAxis ejeXGraficoMeses;
    @FXML
    private PieChart bolaGrafico;
    @FXML
    private BarChart<String, Double> graficoBarras;

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
        
        try {
            graficoDiaADia();
            graficoMes();
            categoriasGrafico();
        } catch (AcountDAOException ex) {
            Logger.getLogger(GastosPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void init (MiPerfilController prin) throws IOException{
        this.principal = prin;
    }
    
    private void categoriasGrafico() throws AcountDAOException{
        categoriasUsuario = cuenta.getUserCategories();
        cargosTotales = cuenta.getUserCharges();
        tablaHash = new HashMap<>(categoriasUsuario.size());
        
        
        for (int i = 0; i < cargosTotales.size(); i++) {
            for (int j = 0; i < categoriasUsuario.size(); i++) {
                // Vamos comprobando cada lista a ver si lo tenemos en X categoría
                Charge cargoAct = cargosTotales.get(i);
                Category categoriaCargo = cargoAct.getCategory();
                String nombreCargo = categoriaCargo.getName();
                
                Category categoriaAct = categoriasUsuario.get(j);
                String nombreCategoria = categoriaAct.getName();
                
                // Si pertenecen a la misma categoria, vamos a querer meterlos en la tablahash
                if (nombreCategoria.equals(nombreCargo)){
                    boolean hasKey = tablaHash.containsKey(nombreCategoria);
                    // True (si está la clave), si no (la añadimos)
                    if (hasKey) {
                        Double costeTotal = tablaHash.get(nombreCategoria);
                        costeTotal += cargoAct.getCost();
                        tablaHash.put(nombreCategoria, costeTotal);
                    }
                    // La añadimos
                    tablaHash.put(nombreCategoria, cargoAct.getCost());
                }// Cerramos if
            }// Cerramos for j
        }// Cerramos for i
        
        tablaHash.put("Food", 250.0);
        tablaHash.put("Transporte", 100.0);
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        for (Map.Entry<String, Double> entry : tablaHash.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }        
        
        bolaGrafico.getData().addAll(pieChartData);
    }// Cerramos metodo
    
    
    private void graficoDiaADia() throws AcountDAOException {
       // Crear una serie de datos
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Gastos día a día");
        
        // Obtenemos todos los cargos
        cargosTotales = cuenta.getUserCharges();
        
        double d1 = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 0.0;
        double d6 = 0.0;
        double d7 = 0.0;
        double d8 = 0.0;
        double d9 = 0.0;
        double d10 = 0.0;
        double d11 = 0.0;
        double d12 = 0.0;
        double d13 = 0.0;
        double d14 = 0.0;
        double d15 = 0.0;
        double d16 = 0.0;
        double d17 = 0.0;
        double d18 = 0.0;
        double d19 = 0.0;
        double d20 = 0.0;
        double d21 = 0.0;
        double d22 = 0.0;
        double d23 = 0.0;
        double d24 = 0.0;
        double d25 = 0.0;
        double d26 = 0.0;
        double d27 = 0.0;
        double d28 = 0.0;
        double d29 = 0.0;
        double d30 = 0.0;
        
        // Tenemos que comprobar todos aquellos que sean de este mes y del día
        for (int i = 0; i < cargosTotales.size(); i++) {
            cargoActual = cargosTotales.get(i);
            LocalDate fechaCargo = cargoActual.getDate();
            LocalDate fechaAc = LocalDate.now();
            Month mesAct = fechaAc.getMonth();
            Month mes = fechaCargo.getMonth();
            if (mesAct.equals(mes)) {
                int diaFecha = fechaCargo.getDayOfMonth();
                // Utilizar un switch para cada día del mes
                switch (diaFecha) {
                    case 1:
                        // Acciones para el día 1
                        d1 += cargoActual.getCost();
                        break;
                    case 2:
                        // Acciones para el día 2
                        d2 += cargoActual.getCost();
                        break;
                    case 3:
                        // Acciones para el día 3
                        d3 += cargoActual.getCost();
                        break;
                    case 4:
                        // Acciones para el día 4
                        d4 += cargoActual.getCost();
                        break;
                    case 5:
                        // Acciones para el día 5
                        d5 += cargoActual.getCost();
                        break;
                    case 6:
                        // Acciones para el día 6
                        d6 += cargoActual.getCost();
                        break;
                    case 7:
                        // Acciones para el día 7
                        d7 += cargoActual.getCost();
                        break;
                    case 8:
                        // Acciones para el día 8
                        d8 += cargoActual.getCost();
                        break;
                    case 9:
                        // Acciones para el día 9
                        d9 += cargoActual.getCost();
                        break;
                    case 10:
                        // Acciones para el día 10
                        d10 += cargoActual.getCost();
                        break;
                    case 11:
                        // Acciones para el día 11
                        d11 += cargoActual.getCost();
                        break;
                    case 12:
                        // Acciones para el día 12
                        d12 += cargoActual.getCost();
                        break;
                    case 13:
                        // Acciones para el día 13
                        d13 += cargoActual.getCost();
                        break;
                    case 14:
                        // Acciones para el día 14
                        d14 += cargoActual.getCost();
                        break;
                    case 15:
                        // Acciones para el día 15
                        d15 += cargoActual.getCost();
                        break;
                    case 16:
                        // Acciones para el día 16
                        d16 += cargoActual.getCost();
                        break;
                    case 17:
                        // Acciones para el día 17
                        d17 += cargoActual.getCost();
                        break;
                    case 18:
                        // Acciones para el día 18
                        d18 += cargoActual.getCost();
                        break;
                    case 19:
                        // Acciones para el día 19
                        d19 += cargoActual.getCost();
                        break;
                    case 20:
                        // Acciones para el día 20
                        d20 += cargoActual.getCost();
                        break;
                    case 21:
                        // Acciones para el día 21
                        d21 += cargoActual.getCost();
                        break;
                    case 22:
                        // Acciones para el día 22
                        d22 += cargoActual.getCost();
                        break;
                    case 23:
                        // Acciones para el día 23
                        d23 += cargoActual.getCost();
                        break;
                    case 24:
                        // Acciones para el día 24
                        d24 += cargoActual.getCost();
                        break;
                    case 25:
                        // Acciones para el día 25
                        d25 += cargoActual.getCost();
                        break;
                    case 26:
                        // Acciones para el día 26
                        d26 += cargoActual.getCost();
                        break;
                    case 27:
                        // Acciones para el día 27
                        d27 += cargoActual.getCost();
                        break;
                    case 28:
                        // Acciones para el día 28
                        d28 += cargoActual.getCost();
                        break;
                    case 29:
                        // Acciones para el día 29
                        d29 += cargoActual.getCost();
                        break;
                    case 30:
                        // Acciones para el día 30
                        d30 += cargoActual.getCost();
                        break;
                    default:
                        // Acciones para un valor de día inesperado
                        break;
                }// Cerramos switch
            }// Cerramos if
        } // Cerramos for
        
        // Añadir los datos a la serie
        series.getData().add(new XYChart.Data("1", d1));
        series.getData().add(new XYChart.Data("2", d2));
        series.getData().add(new XYChart.Data("3", d3));
        series.getData().add(new XYChart.Data("4", d4));
        series.getData().add(new XYChart.Data("5", d5));
        series.getData().add(new XYChart.Data("6", d6));
        series.getData().add(new XYChart.Data("7", d7));
        series.getData().add(new XYChart.Data("8", d8));
        series.getData().add(new XYChart.Data("9", d9));
        series.getData().add(new XYChart.Data("10", d10));
        series.getData().add(new XYChart.Data("11", d11));
        series.getData().add(new XYChart.Data("12", d12));
        series.getData().add(new XYChart.Data("13", d13));
        series.getData().add(new XYChart.Data("14", d14));
        series.getData().add(new XYChart.Data("15", d15));
        series.getData().add(new XYChart.Data("16", d16));
        series.getData().add(new XYChart.Data("17", d17));
        series.getData().add(new XYChart.Data("18", d18));
        series.getData().add(new XYChart.Data("19", d19));
        series.getData().add(new XYChart.Data("20", d20));
        series.getData().add(new XYChart.Data("21", d21));
        series.getData().add(new XYChart.Data("22", d22));
        series.getData().add(new XYChart.Data("23", d23));
        series.getData().add(new XYChart.Data("24", d24));
        series.getData().add(new XYChart.Data("25", d25));
        series.getData().add(new XYChart.Data("26", d26));
        series.getData().add(new XYChart.Data("27", d27));
        series.getData().add(new XYChart.Data("28", d28));
        series.getData().add(new XYChart.Data("29", d29));
        series.getData().add(new XYChart.Data("30", d30));
        
        graficoBarras.getData().setAll(series);
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
            
            series.getData().add(new XYChart.Data<>("Enero", 500.0));
            series.getData().add(new XYChart.Data<>("Febrero", 480.0));
            series.getData().add(new XYChart.Data<>("Marzo", 520.0));
            series.getData().add(new XYChart.Data<>("Abril", 510.0));
            series.getData().add(new XYChart.Data<>("Mayo", 490.0));
            
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
