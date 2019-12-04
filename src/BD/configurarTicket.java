/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CCTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Najera10
 */
public class configurarTicket {
 private Connection userConn;
 private final String SQL_SELECT = "SELECT * FROM empresa";
private  final String SQL_UPDATE  ="UPDATE empresa  SET nombre = ? ,direccion = ?, telefono = ? , correo = ? WHERE idempresa = ? ";
    public configurarTicket(Connection conn) {
        this.userConn = conn;
    }

    public configurarTicket() {
       
    }
 

    public ArrayList<CCTicket> ObtenerDatos() {
        ArrayList<CCTicket> Listardatos = new ArrayList<CCTicket>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            rs.next();
                int idempre = rs.getInt("idempresa");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo= rs.getString("correo");
         
      
                
                CCTicket cate = new CCTicket(idempre,nombre, direccion,telefono,correo);
                Listardatos.add(cate);
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return Listardatos;
    }
    
     public int actualizarTicket(String idemp,String nombre, String direccion,String telefono , String correo) throws SQLException{
        
          Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" +SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1,nombre);
            stmt.setString(2,direccion);
            stmt.setString(3,telefono);
            stmt.setString(4,correo);
            stmt.setString(5,idemp);
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
    
    
    
    
}