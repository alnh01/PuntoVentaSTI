/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CDetalle_ingresos;
import Controller.CDetalle_ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Najera
 */
public class DetalleIngreso {
     private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO detalle_ingreso (idingreso, idarticulo, cantidad,precio_compra) values(?, ?, ? , ? )";

    public DetalleIngreso(Connection conn) {
        this.userConn = conn;
    }

    public DetalleIngreso() {
       
    }
    public void insertarDetalleIngreso(CDetalle_ingresos detalle){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            
        conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
           
            
         
            stmt.setLong(1, detalle.getIdingreso());
            stmt.setInt(2, detalle.getIdarticulo());
            stmt.setDouble(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecioCompra());
           

            
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