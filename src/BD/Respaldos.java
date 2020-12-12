/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.message.DbException;
import org.h2.tools.Recover;
import org.h2.tools.Restore;

/**
 *
 * @author Najera
 */
public class Respaldos {
    
 private Connection userConn;
     
   public Respaldos(Connection conn) {
        this.userConn = conn;
    }

    public Respaldos() {
       
    }
    
    
      public void respaldo() {
    
       try{
     Class.forName("org.h2.Driver");
       Connection con = DriverManager.getConnection("jdbc:h2:file:./base/PuntoVenta", "sa", "admin" );
       Statement stmt = con.createStatement();
       con.prepareStatement("BACKUP TO 'backup.zip'").executeUpdate();

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
      
      public  void execute() {
        
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//          
//          
//      try
//      {
//         System.out.println("Restoring test");
//         Restore.execute("D:\\Archivos\\STI\\RespaldoDefinitivo\\Dashboard_1\\backup.zip", "memFS:./base/PuntoVenta;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE", "PuntoVenta");
//
//         JdbcDataSource datasource = new JdbcDataSource();
//         datasource.setUrl("jdbc:h2:memFS:./base/PuntoVenta;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//         conn = datasource.getConnection("sa", "admin");
//         stmt =  conn.createStatement();
          Connection conn = null;
      Statement stmt = null;

      try
      {
         System.out.println("Restoring test");
         Restore.execute("D:\\Archivos\\STI\\RespaldoDefinitivo\\Dashboard_1\\PuntoVenta.mv.db", "memFS:PuntoVenta;DB_CLOSE_DELAY=-1", null);

         JdbcDataSource datasource = new JdbcDataSource();
         datasource.setUrl("jdbc:h2:file:D:\\Archivos\\STI\\RespaldoDefinitivo\\Dashboard_1\\src\\base\\PuntoVenta");
         conn = datasource.getConnection("sa", "admin");
         stmt = conn.createStatement();




     
      }catch(Exception e){
      
      e.printStackTrace();
      
      

          
      }
          
          

      }

    
    
    
    
}
