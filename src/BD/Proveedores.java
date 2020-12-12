/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CProveedores;
import Controller.CUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import paneles.panelingresos;

/**
 *
 * @author najera10
 */
public class Proveedores {
 
     private Connection userConn;
    
    private final String SQL_SELECT = "SELECT * FROM proveedores";
    private final String SQL_INSERT = "INSERT INTO proveedores  values(NULL,?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE proveedores  WHERE idproveedor=?";
    private final String SQL_UPDATE = "UPDATE proveedores set nombre=?,direccion=?,telefono=?,email=? WHERE idproveedor=?";
    
    
    
    public ArrayList<CProveedores> ObtenerProveedores() {
        ArrayList<CProveedores> ListarProveedores = new ArrayList<CProveedores>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idproveedor");
                String nombre = rs.getString("nombre");
                String tipo_documento = rs.getString("tipo_documento");
                String num_documento= rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
               
         
                
            CProveedores emp = new CProveedores(id, nombre,tipo_documento,num_documento,direccion,telefono,email);
            ListarProveedores.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarProveedores;
    }
    
    
    
    
    public int registrar_Proveedor(CProveedores proveedor  ) throws SQLException{
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
        
           stmt.setString(1,proveedor.getNombre());
           stmt.setString(2,proveedor.getTipo_documento());
           stmt.setString(3,proveedor.getNum_documento());
           stmt.setString(4,proveedor.getDireccion());
           stmt.setString(5,proveedor.getTelefono());
           stmt.setString(6,proveedor.getEmail());
           
             System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
             
           
            } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
        return rows;
    
    }
    
    public int actualizarProveedor(CProveedores proveedor,String nombre, String direccion,String telefono,String email) throws SQLException{
        
          Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" +SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1,nombre);
            stmt.setString(2,direccion);
            stmt.setString(3, telefono);
            stmt.setString(4, email);
            stmt.setInt(5, proveedor.getIdproveedor());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }
    
    
    public int EliminiarProveedor(CProveedores proveedor) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE);
           stmt.setInt(1,proveedor.getIdproveedor());
             System.out.println("Ejecutando query:" + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);

        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
     }
        
        return rows;
    }  
}
