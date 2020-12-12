/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import BD.Categorias;
import BD.CorteDia;
import BD.CortedelDia;
import BD.GenerarRespaldo;
import Controller.CCategorias;
import Controller.CCorte;
import Controller.CCortedia;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import principal.Principal;
import rsbuttom.AWTUtilities;

/**
 *
 * @author Najera10
 */
public class Cortedia extends javax.swing.JDialog {
  CortedelDia cortdd ; 
     Timer timer = null;
    TimerTask task;
    int i = 32;
      Categorias bdcat = new Categorias();
      CortedelDia corte = new CortedelDia();
      
      CorteDia cdia = new CorteDia();
      
        DefaultTableModel modelocorte = new DefaultTableModel(){
    public boolean isCellEditable(int filas, int columnas){
            if(columnas==3){
                return true;
            }else{
                return false;
            }
    }
    
    };
    public Cortedia(java.awt.Frame parent, boolean modal) {
            
        super(parent, modal);
        AWTUtilities.setOpaque(this, false);
        Ubicar(500);
        initComponents();
        CargarColunmas();
        CargarModeloTabla();
        addEventKey();
        
    }
  
     private  void CargarColunmas(){
        modelocorte.addColumn("Articulo");
        modelocorte.addColumn("Descripcion");
        modelocorte.addColumn("Costo");
        
    }
     
     
       private void addEventKey() {
           
         KeyStroke ESC = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
         Action ESCAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
                cerrar();
            
            }
        };  
        
      
        jLabel5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ESC, "ESC");
        jLabel5.getActionMap().put("ESC", ESCAction);

           
        
    }
       
       
       public void cerrar(){
            task = new TimerTask() {
            @Override
            public void run() {
                if (i == 0) {
                    Cerrar();
                } else {
                    Ubicar(i);
                    i -= 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 2);
           
       }
    
//          private void cargarModelotabla(){
//        CortedelDia cortdd = new CortedelDia();     
//        ArrayList<CCortedia>ListarCorte = cortdd.ObtenerCorte();
//         int numerosProveedpres = ListarCorte.size();
//         for (int i = 0; i < numerosProveedpres; i++) {
//             CCortedia corte = ListarCorte.get(i);
//            String nombre = corte.getArticulo();
//            String descripcion = corte.getDescripcion();
//            Double precioxa = corte.getPrecioporpro();
//            Double cantidad  =corte.getCantidad() ;        
//           
//        while (modelocorte.getRowCount() > 0) {
//            modelocorte.removeRow(0);
//        }
//            
//           modelocorte.setValueAt(nombre, i, 0);
//           modelocorte.setValueAt(descripcion, i, 1);
//           modelocorte.setValueAt(precioxa , i, 2);
//            
//        }
//         
//    }
//    
         public void CargarModeloTabla(){
      
         limpiarTabla();
       ArrayList<CCortedia>ListaCortes = corte.ObtenerCorte();
      int numeros = ListaCortes.size();
      modelocorte.setNumRows(numeros);
       double totalE = 0.0;
        for (int i = 0; i < numeros; i++) {
            CCortedia cortedias = ListaCortes.get(i);
            String nombre = cortedias.getArticulo();
            String descripcion = cortedias.getDescripcion();
            Double costo = cortedias.getPrecioporpro();
            Double cantidad = cortedias.getCantidad();
             
            
            modelocorte.setValueAt(nombre, i, 0);
            modelocorte.setValueAt(descripcion+" "+"Cant:  "+cantidad, i, 1);
            modelocorte.setValueAt(costo, i, 2);
            totalE = totalE + Double.parseDouble(modelocorte.getValueAt(i, 2).toString());
            System.out.println(totalE);
        }
         txtTotal.setText(""+totalE);
    }
         
  
         
//    public void filtroBusqueda(JTextField txt) {
//        trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
//    }
//
//   
//    public void filtroBusqueda(JTextField txt) {
//        trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
//    }
    
//    public void limpiar(){
//        txtnombre.setText("");
//        txtdescripcion.setText("");
//         }
        public  void limpiarTabla(){
        int numFilas = modelocorte.getRowCount();
        if(numFilas > 0){
            for(int i = numFilas - 1; i >= 0; i--){
                modelocorte.removeRow(i);
            }
        }
    } 
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcorte = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        fSButtonMD1 = new LIB.FSButtonMD();
        jPanel3 = new javax.swing.JPanel();

        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel4.setText("Corte del dia");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/icons8-multiplicar-50.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 60));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblcorte.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tblcorte.setModel(modelocorte);
        jScrollPane1.setViewportView(tblcorte);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 580, 210));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setText("Productos");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setText("TOTAL  :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 80, 40));

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(204, 204, 204));
        txtTotal.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setBorder(null);
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 120, 40));

        fSButtonMD1.setBackground(new java.awt.Color(102, 255, 102));
        fSButtonMD1.setForeground(new java.awt.Color(0, 0, 0));
        fSButtonMD1.setText("CORTE");
        fSButtonMD1.setColorNormal(new java.awt.Color(102, 102, 255));
        fSButtonMD1.setColorPressed(new java.awt.Color(204, 204, 255));
        fSButtonMD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD1ActionPerformed(evt);
            }
        });
        jPanel2.add(fSButtonMD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 320, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 660, 340));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
          task = new TimerTask() {
            @Override
            public void run() {
                if (i == 0) {
                    Cerrar();
                } else {
                    Ubicar(i);
                    i -= 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 2);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
  task = new TimerTask() {
            @Override
            public void run() {
                if (i == 352) {
                    timer.cancel();
                } else {
                    Ubicar(i);
                    i += 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 2);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
  setVisible(false);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void fSButtonMD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD1ActionPerformed
 try {
          String id = Principal.txtcodigo.getText();
          String idc = id.replace(" ","");
          int idusuario = Integer.parseInt(idc);
          double total = Double.parseDouble(txtTotal.getText());
          CCorte cortardia = new CCorte(0,null,idusuario,total,"");
          cdia.insert(cortardia);
          GenerarRespaldo  respaldo = new GenerarRespaldo();
          respaldo.Resp_Clientes();
          this.dispose();
      } catch (SQLException ex) {
          Logger.getLogger(Cortedia.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(Cortedia.class.getName()).log(Level.SEVERE, null, ex);
      }        // TODO add your handling code here:
    }//GEN-LAST:event_fSButtonMD1ActionPerformed


    
    
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cortedia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cortedia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cortedia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cortedia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 Cortedia dialog = new Cortedia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FSButtonMD fSButtonMD1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblcorte;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
private void Cerrar() {
        this.dispose();
        timer = null;
        task = null;
    }

    private void Trasparencia(float trasp) {
        AWTUtilities.setOpacity(this, trasp);
    }

    private void Ubicar(int y) {
        this.setLocation(590, y - 260);
    }   

}
