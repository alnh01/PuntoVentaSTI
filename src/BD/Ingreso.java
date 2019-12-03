/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CIngreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Najera
 */
public class Ingreso {
    
    
    private Connection userConn;
 private final String SQL_INSERT = "INSERT INTO ingreso (idproveedor, fecha_hora,total_compra,estado) values(?,?,?,?)";
 
 
  public Ingreso(Connection conn) {
        this.userConn = conn;
    }

    public Ingreso() {
       
    }
    
     public Long insertarIngreso(CIngreso ingreso) throws SQLException {
        Long lastVal = 0l;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {

            
         conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
         
           stmt.setInt(1,ingreso.getIdprovvedor());
           stmt.setDate(2,ingreso.getFecha_hora());
           stmt.setDouble(3,ingreso.getTotal_compra());
           stmt.setString(4,ingreso.getEstado());

    
             System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
            
           stmt.close();
           
            stmt = conn.prepareStatement("SELECT @@IDENTITY  AS ultimo");
            rs = stmt.executeQuery();
           
            while (rs.next()) {
              lastVal = rs.getLong("ultimo");
           }
            
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
     
        
        return lastVal;
    }
 
}
