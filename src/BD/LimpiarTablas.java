/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Najera
 */
public class LimpiarTablas {
    
    
     private Connection userConn;
     
      private final String SQL_LISTAR_TABLAS = "select table_name, table_type from  information_schema.tables where table_type = 'TABLE'";
     
     private final String SQL_TRUNCATE_ = "TRUNCATE table ";
  



     public boolean  ObtenerTablas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR_TABLAS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                System.out.println(rs.getString("table_name")); 
                limpiarBase(rs.getString("table_name"));
            }
                
    }    catch (SQLException ex) {
             Logger.getLogger(LimpiarTablas.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return true;
     }
          
     
     
          
     
     
       public void limpiarBase(String datos) {
     
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement("TRUNCATE table"+" " + datos);

            rows = stmt.executeUpdate();
            
        
            } catch (SQLException ex) {
             Logger.getLogger(LimpiarTablas.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
       }
     
  
        
     public  void insert(String sql){
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(sql);
           
            rows = stmt.executeUpdate();
           
        } catch (SQLException ex) {
             Logger.getLogger(LimpiarTablas.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
     }
        
 }