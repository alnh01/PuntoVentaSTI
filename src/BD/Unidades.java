/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CUnidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Unidades {
    
    private Connection userConn;
    private final String SQL_SELECT = "SELECT * FROM unidad_medida";
     private final String SQL_INSERT = "INSERT INTO unidad_medida values(NULL,?)";
    
    public Unidades(Connection conn) {
        this.userConn = conn;
    }

    public Unidades() {
       
    }
 
 
 
 
  public ArrayList<CUnidades> obtenerUnidad() {
        ArrayList<CUnidades> ListarUnidades = new ArrayList<CUnidades>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idunidad");
                String nombre = rs.getString("nombre");
               
         
      
                
                CUnidades cate = new CUnidades(id, nombre);
                ListarUnidades.add(cate);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarUnidades;
    }
  
  
  
    public  int   insert(CUnidades unidad) throws SQLException  {
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
           stmt.setString(1,unidad.getNombre());
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
}
