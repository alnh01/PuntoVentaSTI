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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import jxl.write.DateTime;

/**
 *
 * @author najera10
 */
public class Venta {
    
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO venta  values(NULL,?,CURRENT_TIMESTAMP,?,'ACTIVO',?)";
    private final String SQL_SELECT="SELECT * FROM VENTA"; 

    public Venta(Connection conn) {
        this.userConn = conn;
    }

    public Venta() {
       
    }
 
    
    public ArrayList<CVenta> ObtenerVentas() {
        ArrayList<CVenta> ListarVentas = new ArrayList<CVenta>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idventa");
                int idcliente = rs.getInt("idcliente");
                Timestamp fecha = rs.getTimestamp("fecha_hora");
                double total_venta =rs.getDouble("total_venta");
                String estado = rs.getString("estado");
                String idusuario= rs.getString("idusuario");
      
                CVenta ven = new CVenta(id,idcliente,fecha,total_venta,estado,idusuario);
                ListarVentas.add(ven);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarVentas;
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
           
            stmt = conn.prepareStatement("select  top 1 *  from venta  order  by   idventa  desc");
            rs = stmt.executeQuery();
           
            while (rs.next()) {
              lastVal = rs.getLong("IDVENTA");
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
