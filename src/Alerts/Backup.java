package Alerts;


import static Conexion.Conexion.getConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rojeru San
 */
public class Backup {

    private String obtenerFecha() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String Fehca = dia + "-" + (mes + 1) + "-" + año;
        return Fehca;
    }

    public void exportDB(String nombreDB) {

        String path = "respaldos/" + nombreDB + "_" + obtenerFecha() + ".zip";
        File existe = new File(path);
        if (existe.exists()) {
            WarningAlert w = new WarningAlert(new JFrame(), true);
            w.msj1.setText("Ya existe un respaldo con ese nombre");
            w.msj2.setText("¿Deseas reemplazarlo?");
            w.msj3.setText("");
            w.setVisible(true);

            if (w.hecho) {
                generaBackup(path);
            }

        } else {
                 generaBackup(path);
        }
    }

    private void generaBackup(String respaldo) {
        
        try {
            new CargandoBackup(new JFrame(), true).setVisible(true);
            
            File origen = new File("./base/PuntoVenta.mv.db");
            File destino = new File(respaldo);
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:file:./base/PuntoVenta", "sa", "admin" );
            Statement stmt = con.createStatement();
            con.prepareStatement("BACKUP TO '"+destino+"'").executeUpdate();
            
            SuccessAlert s = new SuccessAlert(new JFrame(), true);
            s.msj1.setText("Respaldo generado con éxito.");
            s.msj2.setText("");
            s.msj3.setText("");
            s.setVisible(true);
            
            
            // update isSuccessful (or maybe execute the statement in try/catch)
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
        



        
        
        
    }
}
