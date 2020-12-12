/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CPermisos;
import Controller.CUsuario_Permiso;
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
  private final String SQL_SELECT_USP = "SELECT * FROM usuario_permiso";

 
 
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
    
    
     
      public ArrayList<CUsuario_Permiso> ObtenerPermisosGlobal() {
        ArrayList<CUsuario_Permiso> ListarPermisoUs = new ArrayList<CUsuario_Permiso>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_USP);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idusuario_permiso");
                int idusuario = rs.getInt("idusuario");
                int idpermiso = rs.getInt("idpermiso");
                
                CUsuario_Permiso permi = new CUsuario_Permiso(id, idusuario,idpermiso);
                ListarPermisoUs.add(permi);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarPermisoUs;
    }
    
}
  
 