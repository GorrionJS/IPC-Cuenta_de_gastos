/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import model.Acount;
import model.AcountDAOException;
import model.Charge;
import model.User;

/**
 *
 * @author joanb
 */
public class ExportWorker{
    ///////////////////////////////////////////////////////
    // VARIABLES GLOBALES
    ///////////////////////////////////////////////////////
    private File file;
    private Acount cuenta = null;
    private User loggeduser = null;
    
    ///////////////////////////////////////////////////////
    // METODOS
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    // METODOS DE ARCHIVOS
    ///////////////////////////////////////////////////////
    public void createFile(String directory, String fileName) throws IOException {
        if (directory.equals("") || fileName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al configurar el archivo");
            alert.setHeaderText("Compruebe los datos del archivo");
            alert.setContentText("No puede dejar los campos en blanco.");
            alert.showAndWait();
        } else {
            file = new File(directory, fileName + ".txt");
        }
        
    }
    
    public void writeFile() throws IOException, AcountDAOException {
        FileWriter writer = new FileWriter(file);
        writer.write("Nombre: " + loggeduser.getName() + " Apellidos: " + loggeduser.getSurname() + "\n");
        writer.write("Nick: " + loggeduser.getNickName() + " Email: " + loggeduser.getEmail() 
                + " Fecha de registro: " + loggeduser.getRegisterDate() + "\n");
        String res = "Cargos: \n";
        res += "Nº. \t Categoria \t Nombre \t Id \t Precio \t Fecha \t Ticket \n";
        List<Charge> charges = cuenta.getUserCharges();
        List<Charge> aux = charges;
        for(int i = 0; i < aux.size(); i++) {
            Charge str = aux.get(i);
            res += i + ". " +
                    chargeToString(aux.get(i)) + "\n";
        }
        writer.write(res);
        writer.close();
    }
    
    public File returnFile() { return this.file; }
    
    ///////////////////////////////////////////////////////
    // METODOS DE LA CUENTA
    ///////////////////////////////////////////////////////
    public void setAccount(Acount c) {
        this.cuenta = c;
        this.loggeduser = c.getLoggedUser();   
    }
    
    private String chargeToString(Charge c) {
        String res = "";
        
        /*res += c.getCategory().getName() + " \t ";
        res += c.getName() + "\t ";
        res += c.getId() + "\t ";
        res += c.getCost() + "\t ";
        res += c.getDate() + "\t ";*/
        res += c.getCategory().getName() + " \t " + c.getName() + "\t " + c.getId() + "\t " + c.getCost() + "\t " + c.getDate() + "\t ";
        
        String aux = "sin ticket";
        
        if (c.getImageScan() != null) aux = "con ticket";
        res += aux + ".";
       
        return res;
    }
}
