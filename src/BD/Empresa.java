/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CEmpresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Najera
 */
public class Empresa {
        
    
    
    
    private Connection userConn;
 private final String SQL_SELECT = "SELECT * FROM empresa";


 
 
 public Empresa(Connection conn) {
        this.userConn = conn;
    }

    public Empresa() {
       
    }
    
     public ArrayList<CEmpresa> ObtenerEmpresa() {
        ArrayList<CEmpresa> ListarEmpresa = new ArrayList<CEmpresa>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
          
                int idempresa = rs.getInt("idempresa");
                String empresa = rs.getString("nombre");
                String direccion= rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String rfc = rs.getString("rfc");
                CEmpresa permi = new CEmpresa(idempresa, empresa, direccion, telefono,correo,rfc);
                ListarEmpresa.add(permi);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarEmpresa;
    }
}
