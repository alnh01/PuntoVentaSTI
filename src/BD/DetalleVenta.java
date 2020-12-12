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
import java.util.ArrayList;

/**
 *
 * @author najera10
 */
public class DetalleVenta {
  
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO detalle_venta values(NULL,?, ?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM  detalle_venta";
    
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
    
    public ArrayList<CDetalle_ventas> ObtenerDetalleVentas() {
        ArrayList<CDetalle_ventas> ListarDetalleVentas= new ArrayList<CDetalle_ventas>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int iddetalle = rs.getInt("iddetalle_venta");
                Long idventa = rs.getLong("idventa");
                String idarticulo = rs.getString("idarticulo");
                Double cantidad= rs.getDouble("cantidad");
                Double costo = rs.getDouble("costo");
               
             
                
                CDetalle_ventas emp = new CDetalle_ventas(iddetalle,idventa,idarticulo,cantidad,costo);
                ListarDetalleVentas.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarDetalleVentas;
    }
}
