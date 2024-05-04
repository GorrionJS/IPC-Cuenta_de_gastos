/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXMLApplication extends Application {
       
    // Modificar si se cambia el nombre del archivo
    private final String archivo = "BaseInicial_BETA";
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // Modificado el archivo FXML para que concuerde con el nombre
        FXMLLoader loader= new  FXMLLoader(getClass().getResource(archivo + ".fxml"));
        Parent root = loader.load();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene scene = new Scene(root);
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        stage.setScene(scene);
        // Modificado para que cambie el titulo de arriba (antes ponia proyecto inicial)
        stage.setTitle("IPC-ENTREGA");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
