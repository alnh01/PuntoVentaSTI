/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import BD.Producto;
import Controller.CProductos;
import Controller.CUnidades;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import paneles.pnlPunto;

/**
 *
 * @author Najera
 */
public class Productos extends javax.swing.JDialog {

    /**
     * Creates new form Productos
     */
    Producto product;
    DefaultTableModel tabla = new DefaultTableModel(){
        
        public boolean isCellEditable(int filas, int columnas){
            if(columnas==4){
                return true;
            }else{
                return false;
            }
        }
    };
     

    
    public Productos(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        product = new Producto();
         CargarColunmas();
     
        
      TableColumnModel columna=tblproductos.getColumnModel();
      
      columna.getColumn(1).setPreferredWidth(500);
      
       CargarModeloTabla();
        this.setLocationRelativeTo(this);
//       
        addEventKey();
    }

    
    
      
    
    private void addEventKey() {
           
         KeyStroke ENTER = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
         Action ENTERAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            
            int filaseleccionada = tblproductos.getSelectedRow();
             ArrayList Lista = new ArrayList();
            String codigo = tblproductos.getValueAt(filaseleccionada, 0).toString();
            String nombre = tblproductos.getValueAt(filaseleccionada, 1).toString();
            String precio = tblproductos.getValueAt(filaseleccionada, 2).toString();
            String  stock = tblproductos.getValueAt(filaseleccionada, 3).toString();
            String cantidad = "1";
            String importe = precio;
            
      if(pnlPunto.tblventas.getRowCount() ==0){
                 
            String [] datosProducto = {codigo, nombre, precio,cantidad,importe};
            
            pnlPunto.modeloTabla.addRow(datosProducto);
            pnlPunto.cargarListenerModeloTabla();
      }else{
          
      boolean encontrado = false;
      int index = 0;
      String cantidadEXT="";
      
      for (int i = 0; i < pnlPunto.tblventas.getRowCount() ; i++) {
          
      Lista.add(pnlPunto.tblventas.getValueAt(i, 0).toString());

                    if( Lista.get(i).equals(codigo)){
                         encontrado  =true ;
                         cantidadEXT  =  pnlPunto.tblventas.getValueAt(i, 3).toString();
                        index = i;
                        break;


                }  
      }
      
                  if (encontrado){

                    double  num = 1;
                    double cnt = Double.valueOf(cantidadEXT);
                    double cantAct = num + cnt ;
                    String cantidadActualizada = String.valueOf(cantAct);
                   pnlPunto.modeloTabla.setValueAt(cantidadActualizada, index, 3);

                    String precioVenta =pnlPunto.modeloTabla.getValueAt(index, 2).toString();
                    double importeEXT = Double.parseDouble(cantidadActualizada) * Double.parseDouble(precioVenta);
                    String importeString = String.valueOf(importeEXT);
                    //            modeloTablaPunto.setValueAt(cantidad, index, 3);
                    pnlPunto.modeloTabla.setValueAt(importeString, index, 4);

                }else{
               
           String [] datosProducto = {codigo, nombre, precio,cantidad,importe};
           
            pnlPunto.modeloTabla.addRow(datosProducto);
             pnlPunto.cargarListenerModeloTabla();

                }

          
    }
             
      }
        };  
        
           tblproductos.getInputMap(tblproductos.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(ENTER, "ENTER");
           tblproductos.getActionMap().put("ENTER", ENTERAction);
           
        
    }
    
    

     private  void CargarColunmas(){
        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Precio");
        tabla.addColumn("Stock");
       
        }
    
    
    public  void CargarModeloTabla() throws SQLException{
    
        
        String buscar = pnlPunto.txtbuscar.getText();
        System.out.println(buscar);
    
    ArrayList<CProductos>ListarProductos = product.obtenerProductosPorCriterio(buscar);
    
      
////
       int numerosProductos = ListarProductos.size();
       
       tabla.setNumRows(numerosProductos);
       
        
        for (int i = 0; i < numerosProductos; i++) {
            CProductos productos = ListarProductos.get(i);
            int id = productos.getIdProducto();
            double pv = productos.getPrecioVentaProducto();
            String descripcion = productos.getCodigo();
            double stock = productos.getStockProducto();

            tabla.setValueAt(id, i, 0);
            tabla.setValueAt(productos, i,1);
            tabla.setValueAt(pv, i, 2);
            tabla.setValueAt(stock, i, 3);
      
            

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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblproductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblproductos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblproductos.setModel(tabla);
        tblproductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblproductosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblproductos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ESCAPE && this != null){
     System.out.println("probando...");
     dispose();
}
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void tblproductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblproductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE && this != null){
            dispose();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tblproductosKeyReleased

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Productos dialog = null;
                try {
                    dialog = new Productos(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tblproductos;
    // End of variables declaration//GEN-END:variables
}
