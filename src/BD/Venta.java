/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CDetalle_ventas;
import Controller.CItem;
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
    private final String SQL_INSERT = "INSERT INTO venta  values(NULL,?,CURRENT_TIMESTAMP,?,'Realizada',?)";
    private final String SQL_SELECT="SELECT * FROM VENTA"; 
    private final String SQL_DELETE="UPDATE venta set  estado = 'Cancelada' where idventa = ?"; 
    private final String SQL_SELECT_VENTA ="SELECT * FROM detalle_venta where idventa = ?";
     private final String SQL_EMPRESA = "SELECT * FROM  Empresa";
    

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
    
    public void eliminarVenta(String id) throws SQLException{
         
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE);
           stmt.setString(1,id);
             System.out.println("Ejecutando query:" + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
            
            
           
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
     }
        }
    
     public  ArrayList<CDetalle_ventas> obtenerDetalles(String id){
         
         ArrayList<CDetalle_ventas> ListarDetalleVentas= new ArrayList<CDetalle_ventas>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            
           
            stmt = conn.prepareStatement(SQL_SELECT_VENTA);
            stmt.setString(1,id);
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
    
     
      
    public ArrayList<String> ObtenerHeader() {
        ArrayList<String> Header = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EMPRESA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idempresa");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono= rs.getString("telefono");
                String correo= rs.getString("correo");
                String rfc= rs.getString("rfc");

         
      
                
              
                Header.add(id);
                Header.add(nombre);
                Header.add(direccion);
                Header.add(telefono);
                Header.add(correo);
                Header.add(rfc);

                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return Header;
    }
    
        public ArrayList<CItem> ObtenerDetalles(int idventa) {
        ArrayList<CItem> detalles = new ArrayList<CItem>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement("select a.nombre,dv.costo,dv.cantidad from detalle_venta dv inner JOIN articulo a on a.idarticulo  = dv.idarticulo where idventa="+idventa+"");
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String costo = rs.getString("costo");
                String cantidad = rs.getString("cantidad");
                
                CItem items = new CItem(nombre, cantidad, costo);
                detalles.add(items);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return detalles;
    }
     
        
         public ArrayList<String> ObtenerVenta(int idventa) {
        ArrayList<String> venta = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement("select  * from venta where idventa="+idventa+"");
            rs = stmt.executeQuery();

            while (rs.next()) {
                String total = rs.getString("total_venta");
                venta.add(total);
             
                
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return venta;
    }


}
