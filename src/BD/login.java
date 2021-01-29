/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Alerts.ErrorAlert;
import Conexion.Conexion;
import Controller.CUsuarios;
import ds.desktop.notify.DesktopNotify;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import paneles.pnlPunto;

import principal.Principal;
import principal.Session;

public class login {
    public int id_usuario;
    
  Usuarios usr = new Usuarios();
    private Connection userConn;
     Principal prin;
     
     public login() {

    }
    
    public login(Connection conn) {
        this.userConn = conn;
    }
    
       public int  validar_ingreso(String usuario, String pass) {
         
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int resultado = 0;
        String SSQL = "SELECT idusuario,nombre,login,clave FROM usuario WHERE login='" + usuario + "' AND clave='" + pass + "' ";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SSQL);
            rs = stmt.executeQuery();
            if (rs.next() == false) {
              ErrorAlert e = new ErrorAlert(new JFrame(), true);
              e.msj1.setText("HAY UN PLOBLEMA ");
              e.msj2.setText("USUARIO NO EXISTE");
              e.msj3.setText("");
              e.setVisible(true);
            Session usl = new Session();
            usl.setVisible(true);
            } else {
                
                     id_usuario = rs.getInt("idusuario");
                    
                    String nombre = rs.getString("nombre");
                    String login = rs.getString("login");
                    String clave = rs.getString("clave");
                    DesktopNotify.showDesktopMessage("Bienvenido", nombre, DesktopNotify.INFORMATION, 3000);
                CUsuarios usuarios = new CUsuarios( id_usuario,  nombre, "", "", "", "", " ","",  login,  clave, "","");           
                
                
                prin = new Principal();
               
                  prin.txtusuario.setText("" +usuarios.getLogin());
                  prin.txtcodigo.setText(" " + usuarios.getId_usuario());
                  pnlPunto.txtbuscar.requestFocus();
                 int idusuario=id_usuario;
        //obtener permisos
              
                 ArrayList<Object> listarPermisos;
                 listarPermisos = usr.obtenerpermiosos(idusuario);
      
                System.out.println(listarPermisos);
                    
                if (listarPermisos.contains(1)) {
                    prin.uno.setEnabled(true);
                }else{
                    prin.uno.setEnabled(false);
                }
                  if (listarPermisos.contains(2)) {
                    prin.dos.setEnabled(true);
                }else{
                    prin.dos.setEnabled(false);
                }
                    if (listarPermisos.contains(3)) {
                    prin.tres.setEnabled(true);
                }else{
                    prin.tres.setEnabled(false);
                }
                    
                if (listarPermisos.contains(4)) {
                    prin.cuatro.setEnabled(true);
                }else{
                    prin.cuatro.setEnabled(false);
                }
               if(listarPermisos.contains(5)){
                    prin.cinco.setEnabled(true);
              }else{
                         
                        prin.cinco.setEnabled(false);
              }
                     if(listarPermisos.contains(6)){
                    prin.siete.setEnabled(true);
              }else{
                       
                        prin.siete.setEnabled(false);
              }
                            
               
                 
                prin.setVisible(true);    
                
            }
        } catch (SQLException ex) {
             ErrorAlert e = new ErrorAlert(new JFrame(), true);
              e.msj1.setText("YA HAY UNA APLICACION DEL MISMO PROGRAMA.");
              e.msj2.setText("POR FAVOR CIERRELA ");
              e.msj3.setText("Y VUELVA A INTENTAR");
              e.setVisible(true);
            
            System.exit(0);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return id_usuario;
       
       }
}