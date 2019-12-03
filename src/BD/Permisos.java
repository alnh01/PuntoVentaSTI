/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CPermisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Toshiba
 */
public class Permisos {
 
    private Connection userConn;
 private final String SQL_SELECT = "SELECT * FROM permiso";
 
 
 public Permisos(Connection conn) {
        this.userConn = conn;
    }

    public Permisos() {
       
    }
    
     public ArrayList<CPermisos> ObtenerPermisos() {
        ArrayList<CPermisos> ListarCategorias = new ArrayList<CPermisos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idpermiso");
                String nombre = rs.getString("nombre");
       
         
      
                
                CPermisos permi = new CPermisos(id, nombre);
                ListarCategorias.add(permi);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarCategorias;
    }
    
    
    
}
  
 