package modal;

import BD.LimpiarTablas;
import Conexion.Conexion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rojeru San
 */
public class Restore {


 
    
    public static boolean restoreDB(String source) throws FileNotFoundException {
        
     boolean bandera = new LimpiarTablas().ObtenerTablas();

     if(bandera){
         
          try {
     
            File origen = new File(source);
            
            String cadena;
            FileReader f = new FileReader(source);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
               new LimpiarTablas().insert(cadena);
            }
            b.close();
        } catch (IOException ex) {
            Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
       
        return true;
    }


        
    }
    
        
   

