/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CCorte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class CorteDia {
    
    private String SQL_INSERT_SQL = "INSERT INTO cortedia values(NULL,CURRENT_TIMESTAMP,?,?,'ACTIVO')";
    private String SQL_SELECT = "SELECT * from cortedia  where fechahora = ? ";
    private String SQL_SELECT_RESP = "SELECT * from cortedia" ;
    private String SQL_SELECT_EXIST = "SELECT * FROM cortedia WHERE FORMATDATETIME(FECHAHORA,'yyyy-MM-dd') = CURRENT_DATE()";

    private Connection userConn;
    
    public CorteDia(Connection conn) {
        this.userConn = conn;
    }

    public CorteDia() {
       
    }
    
    
    
   public  void   insert(CCorte corte) throws SQLException  {
        
        Long lastVal = 0l;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT_SQL);
            
           
            stmt.setInt(1,corte.getIdusuario());
            stmt.setDouble(2,corte.getTotal());
          

             System.out.println("Ejecutando query:" + SQL_INSERT_SQL);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
            
             stmt.close();
           
            
            
           
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
       

    }
   
   
   
   
   public void ValidarExistencia(){
         
    
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }   
   }
   
     
    public ArrayList<CCorte> ObtenerCorte() {
        ArrayList<CCorte> ListarCorte = new ArrayList<CCorte>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_RESP);
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                int id = rs.getInt("idcorte");
                Timestamp fecha = rs.getTimestamp("fechahora");
                int  idusu =rs.getInt("idusuario");
                double total = rs.getDouble("total");
                String estado= rs.getString("estado");
      
              CCorte cort = new CCorte(id, fecha, idusu, total, estado);
                ListarCorte.add(cort);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarCorte;
    }
    
    
    
    
    public Boolean  ValidaExist(){
        
        boolean isExist =false; 
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EXIST);
            rs = stmt.executeQuery();

            if(rs.next()){
                
                isExist=true;
                
            }else{
                
               isExist = false;
            }
                
             

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
       
        return isExist;
    }
}
