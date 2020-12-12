
package BD;

import Conexion.Conexion;
import Controller.CCategorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import paneles.pnlCategorias;


public class Categorias {
    
private Connection userConn;
 private final String SQL_SELECT = "SELECT * FROM categoria";
 private final String SQL_INSERT = "INSERT INTO categoria values(NULL,?,?,1)";
 private final String SQL_UPDATE = "UPDATE categoria SET nombre = ? , descripcion=?  where idcategoria=?";
 private final String SQL_DELETE = "DELETE categoria WHERE idcategoria=?";
    pnlCategorias pnlCategorias;

 
 
   public Categorias(Connection conn) {
        this.userConn = conn;
    }

    public Categorias() {
       
    }
 
 
 
 
  public ArrayList<CCategorias> ObtenerCategorias() {
        ArrayList<CCategorias> ListarCategorias = new ArrayList<CCategorias>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idcategoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int condicion= rs.getInt("condicion");
         
      
                
                CCategorias cate = new CCategorias(id, nombre,descripcion,condicion);
                ListarCategorias.add(cate);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarCategorias;
    }
  
   public  int   insert(CCategorias categoria) throws SQLException  {
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
           stmt.setString(1,categoria.getNombre());
           stmt.setString(2,categoria.getDescripcion());
    
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
   
   
    public int actualizarCategoria(CCategorias categoria,String nombre, String descripcion) throws SQLException{
        
          Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" +SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1,nombre);
            stmt.setString(2,descripcion);
     
            stmt.setString(3, categoria.getIdcategoria());
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
    
     
     public int eliminarCatedoria(CCategorias categoria) throws  SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE);
           stmt.setString(1,categoria.getIdcategoria());
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
