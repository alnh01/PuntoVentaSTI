/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;


import Conexion.Conexion;
import Controller.CDetalle_ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author najera10
 */
public class DetalleVenta {
  
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO detalle_venta (idventa, idarticulo, cantidad,costo) values(?, ?, ?, ?)";

    public DetalleVenta(Connection conn) {
        this.userConn = conn;
    }

    public DetalleVenta() {
       
    }
    public void insertarDetalleVenta(CDetalle_ventas detalle){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            
        conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
           
            
         
            stmt.setLong(1, detalle.getIdVenta());
            stmt.setString(2, detalle.getIdProd());
            stmt.setDouble(3, detalle.getCantidadVendida());
            stmt.setDouble(4, detalle.getCostoxp());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
