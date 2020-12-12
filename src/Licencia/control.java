/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Licencia;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class control {

    //Fichero TMP
    public File archivo = new File("system.tmp");
    //Tiempo en que se actualiza el fichero TMP
    private int segundos = 20;

    public control() {
    }

    public boolean comprobar() {
        if (archivo.exists()) {
            ocultar(archivo);
             Date fechaActual = new Date();
         SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
         String fechaSistema = formateador.format(fechaActual);
            String tiempo = leer();
            String fechaD = Licencia.mD5.getDesencriptar(tiempo);
            
            long fechaL = Long.valueOf(fechaD);
            
            
            String fecha1 = getDate( fechaL, "dd/MM/yyyy");
            
           boolean valor =  compararfechas(fechaSistema,fecha1);
                System.out.println(valor);
            if (valor) {
                return false;
            } else {
//                programa_tarea();
                return true;
            }
        } else {
            crearTMP();
           
            return false;
        }
    }
    
    public boolean compararfechas(String fecha11, String fecha12) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fecha1 = sdf.parse(fecha11, new ParsePosition(0));
        java.util.Date fecha2 = sdf.parse(fecha12, new ParsePosition(0));
        if (fecha1.before(fecha2)) {
            return true;
        } else {
            return false;
        }
    }
    
      public static String getDate(long milliSeconds, String dateFormat) 
{ 
    // Create a DateFormatter object for displaying date in specified format. 
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat); 

    // Create a calendar object that will convert the date and time value in milliseconds to date. 
    Calendar calendar = Calendar.getInstance(); 
    calendar.setTimeInMillis(milliSeconds); 
    return formatter.format(calendar.getTime()); 
} 
    private String leer() {
        String linea = "0";
        BufferedReader fr;

        try {
            fr = new BufferedReader(new FileReader(archivo));
            while (fr.ready()) {
                linea = fr.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
        }

        return linea;
    }

    public static Date sumarRestarDiasFecha(Date fecha, int dias){

      Calendar calendar = Calendar.getInstance();	
      calendar.setTime(fecha); // Configuramos la fecha que se recibe	
      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0	
      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos	
 } 
    

     public static String ConvertiraFecha(long milliSeconds, String dateFormat) 
{ 
    // Create a DateFormatter object for displaying date in specified format. 
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat); 

    // Create a calendar object that will convert the date and time value in milliseconds to date. 
    Calendar calendar = Calendar.getInstance(); 
    calendar.setTimeInMillis(milliSeconds); 
    return formatter.format(calendar.getTime()); 
}
    private void crearTMP() {
        Date fecha = new Date();
        Date fecha1 = sumarRestarDiasFecha(fecha, 30);

        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
            wr.write(Licencia.mD5.getEncriptar(String.valueOf(fecha1.getTime())));
            wr.close();
            ocultar(archivo);
        } catch (IOException ex) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarApp() {
       
        if (archivo.exists()) {
            archivo.delete();
        }
        System.exit(0);
    }
 
    private static void ocultar(File archivo) {
        try {
            // win32 command line variant
            Process p = Runtime.getRuntime().exec("attrib +h " + archivo.getPath());
            p.waitFor(); // p.waitFor() important, so that the file really appears as hidden immediately after function exit.
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
