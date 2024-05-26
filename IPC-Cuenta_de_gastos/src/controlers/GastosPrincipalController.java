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
import model.User;

/**
 * FXML Controller class
 *
 * @author elgor
 */

public class GastosPrincipalController implements Initializable {

    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private Acount cuenta;
    private User user;
    private MiPerfilController principal;
    private List<Charge> cargosTotales;
    private Charge cargoActual;
    private List<Category> categoriasUsuario;
    private Map<String, Double> tablaHash;
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
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
            // Cargamos y lanzamos los graficos
            try {
                graficoDiaADia();
                graficoMes();
                categoriasGrafico();
            } catch (AcountDAOException ex) {
                Logger.getLogger(GastosPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (AcountDAOException ex) {
            Logger.getLogger(GastosPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GastosPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    ///////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////
    public void init (MiPerfilController prin, Acount cuent) throws IOException{
        this.principal = prin;
        cuenta = cuent;
        // Obtenemos la cuenta
        user = cuenta.getLoggedUser();
    }
    
    ///////////////////////////////////////////////////////
    // LANZAMOS GRAFICO BARRAS (DIA A DIA)
    ///////////////////////////////////////////////////////
    private void graficoDiaADia() throws AcountDAOException {
       // Crear una serie de datos
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Gastos día a día");
        
        // Obtenemos todos los cargos
        cargosTotales = cuenta.getUserCharges();
        
        double[] dias = new double[32];
        LocalDate fechaAc = LocalDate.now();
        Month mesAct = fechaAc.getMonth();
        // Tenemos que comprobar todos aquellos que sean de este mes y del día
        for (int i = 0; i < cargosTotales.size(); i++) {
            cargoActual = cargosTotales.get(i);
            LocalDate fechaCargo = cargoActual.getDate();
            Month mes = fechaCargo.getMonth();
            if (mesAct.equals(mes)) {
                int diaFecha = fechaCargo.getDayOfMonth();
                // Utilizar un switch para cada día del mes
                if(diaFecha < dias.length)
                    dias[diaFecha] += cargoActual.getCost();
                else System.out.println("Valor fuera de rango ?!");
            }// Cerramos if
        } // Cerramos for
        
        // Añadir los datos a la serie
        for(Integer i = 1; i <= 31; i++) {
            series.getData().add(new XYChart.Data(i.toString(), dias[i]));
        }
        
        graficoBarras.getData().setAll(series);
    }
    
    ///////////////////////////////////////////////////////
    // LANZAMOS GRAFICO PIECHART
    ///////////////////////////////////////////////////////
    private void categoriasGrafico() throws AcountDAOException{
        categoriasUsuario = cuenta.getUserCategories();
        cargosTotales = cuenta.getUserCharges();
        tablaHash = new HashMap<>(categoriasUsuario.size());
        
        /*
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
        
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        for (Map.Entry<String, Double> entry : tablaHash.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }        
        
        bolaGrafico.getData().addAll(pieChartData);
*/  
    }// Cerramos metodo
    
    ///////////////////////////////////////////////////////
    // LANZAMOS GRAFICO LINEAL (MES A MES)
    ///////////////////////////////////////////////////////
    private void graficoMes() throws AcountDAOException {
        /*double eneroC = 0.0;
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
        double diciembreC = 0.0;*/
        
        double month[] = new double[13];
        
        // Crear una serie de datos
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Gastos por mes");
        
            cargosTotales = cuenta.getUserCharges();
            for (int i = 0; i < cargosTotales.size(); i++) {
                cargoActual = cargosTotales.get(i);
                // Ya tenemos obtenido el cargo, vamos a ver en qué mes lo metemos
                
                LocalDate fechaCargo = cargoActual.getDate();
                Month mes = fechaCargo.getMonth();
                
                month[mes.getValue()] += cargoActual.getCost();
            } // Cierre del for

            for(int i = 1; i <= 12; i++) {
                series.getData().add(new XYChart.Data(Month.of(i).toString(), month[i]));
            }
            
            graficoTotalPorMeses.getData().setAll(series);
    }
}
