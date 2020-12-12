
package Licencia;

//import conexion.ConexionBD;
import Alerts.Information;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import principal.Principal;
import principal.Session;


public class Ejecutar {

    public static String diasRestantes = "";

   
 
    public static void main(String[] args) throws IOException {
        

 try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
      boolean validar = new control().comprobar();
      
        if (validar) {    
            
        Information suc = new Information(new JFrame(),true );
        suc.jLabel1.setText("!HECHO¡");
        suc.textos.setText("SE HA TERMINADO LA PRUEBA");
        suc.setVisible(true);
        System.exit(0);
        
        } else {
            new Session().setVisible(true);
        }
    
  System.in.read();
           
  
}   
}
