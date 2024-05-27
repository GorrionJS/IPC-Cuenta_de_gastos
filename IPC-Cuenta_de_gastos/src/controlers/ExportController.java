/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author joanb
 */
public class ExportController implements Initializable {
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private static final String EXPORTAR = "/fxmls/Exportar";
    private PrinterJob printer;
    private Acount cuenta;
    private MiPerfilController main;
    private PrintRequestAttributeSet aset;
    
    private String rutaArchivo = "";
    private String nombreArchivo = "";
    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private Button routeButton;
    @FXML
    private Button okButton;
    @FXML
    private Label rutaArchivoLabel;
    @FXML
    private Label nombreArchivoLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    ///////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////
    public void init(Acount c, MiPerfilController p) {
        cuenta = c;
        main = p;
    }
    
    ///////////////////////////////////////////////////////
    // ACEPTAR MÉTODO
    ///////////////////////////////////////////////////////
    @FXML
    private void accept(ActionEvent event) throws IOException, AcountDAOException, PrinterException, PrintException {
        // Creamos la alerta
        ButtonType ok = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirme su elección", ok, no);
        alert.setTitle("Confirmar impresión");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que quiere imprimir el archivo?");
        
        // Obtenemos el valor
        Optional<ButtonType> result = alert.showAndWait();
        
        // Chekeamos
        if(result.isPresent() && result.get() == ok) {
            /*ExportWorker controller = new ExportWorker();
            controller.setAccount(cuenta);
            
            controller.createFile(); 
            controller.writeFile();
            File data = controller.returnFile();
            FileInputStream text = new FileInputStream(data);
            
            DocFlavor psInformat = DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
            Doc myDoc = new SimpleDoc(text, psInformat, null);
            
            // https://docs.oracle.com/javase/8/docs/technotes/guides/jps/spec/printing2d.fm1.html
            // Step 1: Set up initial print settings.
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            // Step 2: Obtain a print job.
            PrinterJob pj = PrinterJob.getPrinterJob();
            // Step 3: Find print services.
            PrintService []services = PrinterJob.lookupPrintServices();
            
            if (services.length > 0) {
                System.out.println("selected printer: " + services[0]);
                try {
                    pj.setPrintService(services[0]); 
                    // Step 2: Pass the settings to a page dialog and print dialog.
                    pj.pageDialog(aset);
                    
                    if (pj.printDialog(aset)) {
                    //    DocPrintJob job = services[].createPrintJob();
                    // Step 4: Update the settings made by the user in the dialogs.
                    // Step 5: Pass the final settings into the print request.
                    //    job.print(myDoc, aset);
                    }
                } catch (PrinterException pe) {
                    System.err.println(pe);
                }
            }*/
            try {
            // Setup and export file
            ExportWorker controller = new ExportWorker();
            controller.setAccount(cuenta);
            // Se va a crear el archivo
            controller.createFile(rutaArchivo, nombreArchivo);
            // Se va a escribir en el archivo
            controller.writeFile();
            File data = controller.returnFile();
            FileInputStream text = new FileInputStream(data);

            // Create a Doc object to hold the file content
            DocFlavor psInformat = DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
            Doc myDoc = new SimpleDoc(text, psInformat, null);
            
            // Alerta avisando de que se ha hecho bien el traspaso de información
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado correctamente");
            alerta.setHeaderText(null);
            alerta.setContentText("Se ha guardado correctamente el archivo en formato TXT.");
            alerta.show();

            // Step 1: Set up initial print settings.
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

            // Step 2: Obtain a print job.
            PrinterJob pj = PrinterJob.getPrinterJob();

            // Step 3: Find print services.
            PrintService[] services = PrintServiceLookup.lookupPrintServices(psInformat, aset);

            if (services.length > 0) {
                System.out.println("Selected printer: " + services[0].getName());
                try {
                    pj.setPrintService(services[0]);

                    // Step 4: Show print dialog and get user settings
                    if (pj.printDialog(aset)) {
                        // Create a print job from the selected service
                        DocPrintJob job = services[0].createPrintJob();

                        // Step 5: Pass the final settings into the print request.
                        job.print(myDoc, aset);
                    }
                } catch (PrinterException | PrintException pe) {
                    System.err.println(pe);
                } finally {
                    text.close();
                }
            } else {
                System.out.println("No suitable print service found.");
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void seleccionRuta(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccione directorio");

        // Obtenemos el directorio y lo guardamos
        File selectedDirectory = directoryChooser.showDialog(
                ((Node)event.getSource()).getScene().getWindow()
        );
        rutaArchivo = selectedDirectory.getAbsolutePath();
        rutaArchivoLabel.setText(rutaArchivo);
    }

    @FXML
    private void seleccionarNombrePulsado(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Nombre del archivo");
        dialog.setTitle("Elegir nombre del archivo");
        dialog.setHeaderText(null);
        dialog.setContentText("Introduzca un nuevo nombre para el archivo:");
        
        Optional<String> result = dialog.showAndWait();
        nombreArchivoLabel.setText(result.get());
        nombreArchivo = result.get();
    }
}
