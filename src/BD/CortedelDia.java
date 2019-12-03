/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CCortedia;
import Controller.CDetalle_ingresos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CortedelDia {
     private Connection userConn;
    private final String SQL_SELECT = "select ar.nombre as articulo , ar.descripcion , dv.cantidad,dv.costo  from detalle_venta dv INNER JOIN venta v  ON dv.idventa = v.idventa INNER JOIN articulo ar on dv.idarticulo = ar.idarticulo	 where CONVERT(date,v.fecha_hora)=Convert(date,GETDATE())";

    public CortedelDia(Connection conn) {
        this.userConn = conn;
    }

    public CortedelDia() {
       
    }
    
     public ArrayList<CCortedia> ObtenerCorte() {  
        
    
        ArrayList<CCortedia> ListarCorte = new ArrayList<CCortedia>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("articulo");
                String descripcion = rs.getString("descripcion");
                Double cantidad = rs.getDouble("cantidad");
                Double costo= rs.getDouble("costo");
         
                System.out.println(costo);
                
                CCortedia cate = new CCortedia(nombre, descripcion,cantidad,costo);
                ListarCorte.add(cate);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarCorte;
    }

  
    
}



