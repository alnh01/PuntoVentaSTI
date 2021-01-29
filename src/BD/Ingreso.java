/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CDetalle_ingresos;
import Controller.CIngreso;
import Controller.CUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Najera
 */
public class Ingreso {
    
    
    private Connection userConn;
 private final String SQL_INSERT = "INSERT INTO ingreso  values(NULL,?,?,?,?)";
 private final String SQL_SELECT ="SELECT * FROM ingreso";
 private final String SQL_DELETE="UPDATE ingreso set  estado = 'Cancelada' where idingreso = ?"; 
 private final String SQL_SELECT_INGRESO ="SELECT * FROM detalle_ingreso where idingreso = ?";
 private final String SQL_DELETE_DETALLE="DELETE  detalle_ingreso where idingreso = ?"; 
 private final  String SQL_TRUNCATE  = "TRUNCATE TABLE ingreso  RESTART IDENTITY";
 private final  String SQL_TRUNCATE_DETALLE  = "TRUNCATE TABLE detalle_ingreso  RESTART IDENTITY"; 
 
 
  public Ingreso(Connection conn) {
        this.userConn = conn;
    }

    public Ingreso() {
       
    }
    
    public ArrayList<CIngreso> ObtenerIngresos() {
        ArrayList<CIngreso> ListarIngresos = new ArrayList<CIngreso>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
              int idingreso = rs.getInt("idingreso");
              int idproveedor = rs.getInt("idproveedor");
              Date fecha_hora = rs.getDate("fecha_hora");
              double total_compra = rs.getDouble("total_compra");
              String estado = rs.getString("estado");
         
      
                
                CIngreso cate = new CIngreso(idingreso,idproveedor , (java.sql.Date) fecha_hora,total_compra,estado);
                ListarIngresos.add(cate);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarIngresos;
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
           
            stmt = conn.prepareStatement("select top 1  idingreso as ultimo from ingreso order by idingreso desc;");
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
     
     
     public void eliminarIngresos(String id) throws SQLException{
         
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
        
     
       
     public void eliminarDetalleIngresos(String id) throws SQLException{
         
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE_DETALLE);
           stmt.setString(1,id);
             System.out.println("Ejecutando query:" + SQL_DELETE_DETALLE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
            
            
           
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
     }
        }
        
     
     
     public  ArrayList<CDetalle_ingresos> obtenerDetalles(String id){
         
         ArrayList<CDetalle_ingresos> ListarDetalleIngresos= new ArrayList<CDetalle_ingresos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            
           
            stmt = conn.prepareStatement(SQL_SELECT_INGRESO);
            stmt.setString(1,id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int iddetalle = rs.getInt("iddetalle_ingreso");
                Long idingreso = rs.getLong("idingreso");
                int idarticulo = rs.getInt("idarticulo");
                Double cantidad= rs.getDouble("cantidad");
                Double costo = rs.getDouble("precio_compra");
          
             
                
                CDetalle_ingresos emp = new CDetalle_ingresos(iddetalle,idingreso,idarticulo,cantidad,costo);
                ListarDetalleIngresos.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarDetalleIngresos;
    }
    
       
  public void LimpiarTabla(){
     
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TRUNCATE);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }
  
  
    public void LimpiarTablaDetalle() {
     
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TRUNCATE_DETALLE);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }
     
     
         
         
     }
     
     
     
         
     
 

