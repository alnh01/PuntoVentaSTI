/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Controller.CUsuarios;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import paneles.pnlUsuarios;

/**
 *
 * @author najera10
 */
public class Usuarios {
    
    private Connection userConn;
    
    private final String SQL_SELECT = "SELECT * FROM usuario ";
    private final String SQL_INSERT = "INSERT INTO usuario  values(NULL,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE usuario  WHERE idusuario=?";
    private final String SQL_PERMISOS = "SELECT * FROM usuario_permiso WHERE idusuario= ? ";
    private final String SQL_INSERT_PERMISOS ="INSERT  INTO usuario_permiso values(NULL,?,?)";
    private final String SQL_DELETE_PERMISOS = "DELETE usuario_permiso  WHERE idusuario=?";
    private final String SQL_CAMBIARCONTRASENA="UPDATE usuario SET clave = ? where idusuario = ? ";
    private final String SQL_CAMBIAR_USUARIO="UPDATE usuario SET login = ? where idusuario = ? ";
    private final String SQL_OBTENER_USUARIOP ="SELECT * FROM usuario_permiso";

    
    public Usuarios(Connection conn) {
        this.userConn = conn;
    }

    public Usuarios() {
       
    }
    
     public ArrayList<CUsuarios> ObtenerUsuarios() {
        ArrayList<CUsuarios> ListarUsuarios = new ArrayList<CUsuarios>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idusuario");
                String nombre = rs.getString("nombre");
                String tipo_documento = rs.getString("tipo_documento");
                String num_documento= rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");
                String usuario = rs.getString("login");
                String clave = rs.getString("clave");
                String imagen  = rs.getString("imagen");
                String condicion = rs.getString("condicion");
                
                CUsuarios emp = new CUsuarios(id, nombre,tipo_documento,num_documento,direccion,telefono,email,cargo, usuario,clave,imagen,condicion);
                ListarUsuarios.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarUsuarios;
    }
     
     public ArrayList<CUsuarios>ObtenerUsuariosPermiso(){
     
      ArrayList<CUsuarios> ListarPermisoUsuarios = new ArrayList<CUsuarios>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idusuario");
                String nombre = rs.getString("nombre");
                String tipo_documento = rs.getString("tipo_documento");
                String num_documento= rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");
                String usuario = rs.getString("login");
                String clave = rs.getString("clave");
                String imagen  = rs.getString("imagen");
                String condicion = rs.getString("condicion");
                
                CUsuarios emp = new CUsuarios(id, nombre,tipo_documento,num_documento,direccion,telefono,email,cargo, usuario,clave,imagen,condicion);
                ListarPermisoUsuarios.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarPermisoUsuarios;
     
     }
   
     
     public  Long   insert(CUsuarios usuario) throws SQLException  {
        
        Long lastVal = 0l;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
            
           
            stmt.setString(1,usuario.getNombre());
            stmt.setString(2,usuario.getTipo_documento());
            stmt.setString(3,usuario.getNum_documento());
            stmt.setString(4,usuario.getDireccion());
            stmt.setString(5,usuario.getTelefono());
            stmt.setString(6,usuario.getEmail());
            stmt.setString(7,usuario.getCargo());
            stmt.setString(8,usuario.getLogin());
            stmt.setString(9,usuario.getClave());
            stmt.setString(10,usuario.getImagen());
            stmt.setString(11,usuario.getCodicion());

             System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
            
             stmt.close();
           
            stmt = conn.prepareStatement("select top 1  idusuario as ultimo from usuario order by idusuario desc;");
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
     
     public int  actualizarUsuario(CUsuarios usuario,String nombre,String direccion,String telefono,String email,String login,String clave) throws SQLException{
         
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + "Actualizacion");
            stmt = conn.prepareStatement("update  usuario set nombre='"+nombre+"',direccion='"+direccion+"',telefono='"+telefono+"'"
                    + ",email='"+email+"',login='"+login+"',clave='"+clave+"' where idusuario='"+usuario.getId_usuario()+"' ");
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
     
     
     public int eliminarusuario(CUsuarios usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE);
           stmt.setInt(1,usuario.getId_usuario());
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
     
     
     public void reporte() throws SQLException, JRException{
            Connection conn = null;
            JasperReport reporte;
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            String path = System.getProperty("user.dir")+"/src/reportes/reporteusuarios.jasper";
            JasperPrint jprint = JasperFillManager.fillReport(path, null,conn);
            JasperViewer viewer = new JasperViewer(jprint,false);
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setVisible(true);
     }
     
     
     public  ArrayList<Object>  obtenerpermiosos(int idusuario) throws SQLException{
        ArrayList<Object> ListarPermisos = new ArrayList<Object>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SSQL = "SELECT * FROM usuario_permiso WHERE idusuario= '" + idusuario + "' ";
         
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SSQL);
            rs = stmt.executeQuery();
        
            
                    while (rs.next()) {
                        int id = rs.getInt("idpermiso");
                        ListarPermisos.add(id);
                        }
            
                    return ListarPermisos;
     
     
     }
     public void  guardarPermisos(ArrayList<Object> Permisos,Long iduser) throws SQLException{
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            for (int i = 0; i < Permisos.size(); i++) {
           stmt = conn.prepareStatement(SQL_INSERT_PERMISOS);
           stmt.setLong(1,iduser);
           stmt.setInt(2, (int) Permisos.get(i));
             System.out.println("Ejecutando query:" + SQL_INSERT_PERMISOS);
            rows = stmt.executeUpdate();
         }
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
     }
        public void eliminarPermisos(CUsuarios usuarios) throws SQLException{
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE_PERMISOS);
           stmt.setInt(1,usuarios.getId_usuario());
             System.out.println("Ejecutando query:" + SQL_DELETE_PERMISOS);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
  
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
     }
       
     }
      public void  actualizarPermisos(ArrayList<Object> Permisos,CUsuarios usuarios) throws SQLException{
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            for (int i = 0; i < Permisos.size(); i++) {
           stmt = conn.prepareStatement(SQL_INSERT_PERMISOS);
           stmt.setLong(1,usuarios.getId_usuario());
           stmt.setInt(2, (int) Permisos.get(i));
             System.out.println("Ejecutando query:" + SQL_INSERT_PERMISOS);
            rows = stmt.executeUpdate();
         }
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
     }
     
     public void cambiarcontraseña(String clave ,String  usuario) throws SQLException{
           Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection(); 
           stmt = conn.prepareStatement(SQL_CAMBIARCONTRASENA);
           stmt.setString(1,clave);
           stmt.setString(2,usuario);

             System.out.println("Ejecutando query:" + SQL_CAMBIARCONTRASENA);
            rows = stmt.executeUpdate();
         
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
     }
     
       public void cambiarusuario(String newusuario ,String  usuario) throws SQLException{
           Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection(); 
           stmt = conn.prepareStatement(SQL_CAMBIAR_USUARIO);
           stmt.setString(1,newusuario);
           stmt.setString(2,usuario);

             System.out.println("Ejecutando query:" + SQL_CAMBIAR_USUARIO);
            rows = stmt.executeUpdate();
         
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
       
     }
       
       
        public ArrayList<CUsuarios> ObtenerUsuariospotCriterio(String criterio) {
        ArrayList<CUsuarios> ListarUsuarios = new ArrayList<CUsuarios>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE idusuario LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%'OR login LIKE '%"+criterio+"%'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idusuario");
                String nombre = rs.getString("nombre");
                String tipo_documento = rs.getString("tipo_documento");
                String num_documento= rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");
                String usuario = rs.getString("login");
                String clave = rs.getString("clave");
                String imagen  = rs.getString("imagen");
                String condicion = rs.getString("condicion");
                
                CUsuarios emp = new CUsuarios(id, nombre,tipo_documento,num_documento,direccion,telefono,email,cargo, usuario,clave,imagen,condicion);
                ListarUsuarios.add(emp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarUsuarios;
    }
}