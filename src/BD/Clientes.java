/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.CClientes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author najera10
 */
public class Clientes {
    
        private Connection userConn;
    
    private final String SQL_SELECT = "SELECT * FROM clientes ";
    private final String SQL_INSERT = "INSERT INTO clientes  values(NULL,?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE clientes  WHERE idcliente=?";
    private final String SQL_UPDATE = "UPDATE clientes set nombre=?,direccion=?,telefono=?,email=? WHERE idcliente=?";
     String RESPALDO = "INSERT INTO clientes values  ";
   
    public Clientes(Connection conn) {
        this.userConn = conn;
    }

    public Clientes() {
       
    }
    
     public ArrayList<CClientes> ObtenerUsuarios() {
        ArrayList<CClientes> ListarClientes = new ArrayList<CClientes>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String tipo_documento = rs.getString("tipo_documento");
                String num_documento= rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

      
                
                CClientes clien = new CClientes(id, nombre,tipo_documento,num_documento,direccion,telefono,email);
                ListarClientes.add(clien);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarClientes;
    }
    
     public  int   insert(CClientes cliente) throws SQLException  {
       Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_INSERT);
           stmt.setString(1,cliente.getNombre());
           stmt.setString(2,cliente.getTipo_documento());
           stmt.setString(3,cliente.getNum_documento());
           stmt.setString(4,cliente.getDireccion());
           stmt.setString(5,cliente.getTelefono());
           stmt.setString(6,cliente.getEmail());
           
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
     
     
     public int actualizarProveedor(CClientes cliente,String nombre, String direccion,String telefono,String email) throws SQLException{
        
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
            stmt.setInt(5, cliente.getIdcliente());
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
     
     public int eliminarcliente(CClientes cliente) throws  SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE);
           stmt.setInt(1,cliente.getIdcliente());
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
     
     
      public void separar () throws IOException{
          
       ArrayList<CClientes> clientes  =  ObtenerUsuarios();
      
       
       String ruta = "D:\\OneDrive\\Documentos\\Escritorio\\ejemplo.txt";
           
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
           ;
          for (int i = 0; i < clientes.size(); i++) {
          int id =    clientes.get(i).getIdcliente();
          String nombre = clientes.get(i).getNombre();
          String tipo_documento = clientes.get(i).getTipo_documento();
          String num_documento= clientes.get(i).getNum_documento();
          tipo_documento = "NULL";
          num_documento ="NULL";
          
          
          String direccion = clientes.get(i).getDireccion();
          String telefono = clientes.get(i).getTelefono();
          String email = clientes.get(i).getEmail();
          
          String variable = RESPALDO +"("+ id+",'"+nombre+"',"+tipo_documento+","+num_documento+",'"+direccion+"','"+telefono+"','"+email+"')";
                
          System.out.println(variable);
           bw.write(variable);
           bw.newLine();
          }
          
          
          
          
          bw.close();
      }
 
     
}
