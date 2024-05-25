/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlers;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
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
    private static final String EXPORTAR = "/fxmls/Exportar";

    
    ///////////////////////////////////////////////////////
    // VARIABLES DEL NET BEANS
    ///////////////////////////////////////////////////////
    @FXML
    private Button routeButton;
    @FXML
    private Button okButton;

    private PrinterJob printer;
    
    private Acount cuenta;
    
    private MiPerfilController main;
    
    private PrintRequestAttributeSet aset;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void accept(ActionEvent event) throws IOException, AcountDAOException, PrinterException, PrintException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar impresión");
        alert.setContentText("Esta seguro que quiere imprimir el archivo");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get().equals(ButtonType.OK)) {
            ExportWorker controller = new ExportWorker();
            controller.setAccount(cuenta);
            
            controller.createFile(); controller.writeFile();
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
            }
    }
    }
    
    public void init(Acount c, MiPerfilController p) {
        cuenta = c;
        main = p;
    }
    
}
