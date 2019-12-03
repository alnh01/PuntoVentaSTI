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
import Controller.CVenta;

/**
 *
 * @author najera10
 */
public class Venta {
    
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO venta (idcliente,fecha_hora,total_venta,estado,idusuario) values(?,GETDATE(),?,'ACTIVO',?)";
    private final String SQL_VALIDAREXISTENCIAS="SELECT *"; 

 public Venta(Connection conn) {
        this.userConn = conn;
    }

    public Venta() {
       
    }
 
 
    
    public Long insertarVenta(CVenta venta) throws SQLException {
        Long lastVal = 0l;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {

            
         conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
           stmt.setInt(1, venta.getIdcliente());
           stmt.setDouble(2,venta.getMontoVenta());
          stmt.setString(3,venta.getIdusuario());

    
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
