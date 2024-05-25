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
    public void createFile() throws IOException {
        file = File.createTempFile("impr", null);
        System.out.println(file.getAbsolutePath());  
    }
    
    public void writeFile() throws IOException, AcountDAOException {
        FileWriter writer = new FileWriter(file);
        writer.write("Nombre: " + loggeduser.getName() + " Apellidos: " + loggeduser.getSurname() + "\n");
        writer.write("Nick: " + loggeduser.getNickName() + " Email: " + loggeduser.getEmail() 
                + " Fecha de registro: " + loggeduser.getRegisterDate() + "\n");
        String res = "Cargos: \n";
        res += "Nº. | Categoria | Nombre | Id | Precio | Fecha | Ticket \n";
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
        
        res += c.getCategory().getName() + " | ";
        res += c.getName() + ", ";
        res += c.getId() + ", ";
        res += c.getCost() + ", ";
        res += c.getDate() + ", ";
        
        String aux = "sin ticket";
        
        if (c.getImageScan() != null) aux = "con ticket";
        res += aux + ".";
       
        return res;
    }
    
    
    
}
