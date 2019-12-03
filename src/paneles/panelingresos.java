/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import Alerts.Information;
import Controller.CProveedores;
import BD.DetalleIngreso;
import BD.Ingreso;
import BD.Producto;
import BD.Proveedores;
import Controller.CDetalle_ingresos;
import Controller.CDetalle_ventas;
import Controller.CIngreso;
import Controller.CProductos;
import Controller.CProveedores;
import app.bolivia.swing.JCTextField;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author najera10
 */
public class panelingresos extends javax.swing.JInternalFrame  {
       Proveedores  bdprv = new Proveedores();
       DefaultTableModel modeloTabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
     
     Proveedores proveedor = new Proveedores();
     DefaultComboBoxModel <CProveedores> modeloProveedor;
     DefaultListModel<CProductos> modeloListaProductos= new DefaultListModel<CProductos>();
      Producto producto = new Producto();
      Ingreso ingresos = new Ingreso();
         DetalleIngreso  dingreso = new DetalleIngreso();
    
    public panelingresos() {
       cargarColumnasTabla();
          modeloProveedor = new DefaultComboBoxModel<CProveedores>();
        initComponents();
        cargarModeloProv();

       SimpleDateFormat formateador = new SimpleDateFormat(
   "dd 'de' MMMM 'de' yyyy", new Locale("ES"));
   Date fechaDate = new Date();
   String fecha = formateador.format(fechaDate);
   txtfecha.setText(fecha);

   cargarListenerModeloTabla();

    }
    
        private void cargarListenerModeloTabla(){
        
        modeloTabla.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
             int numFilas = modeloTabla.getRowCount();
             double sumatoria  = 0;
                for (int i = 0; i < numFilas; i++) {
                    String importe = (String) modeloTabla.getValueAt(i, 4);
                    sumatoria += Double.parseDouble(importe);
                }
                lblsumatoria.setText(String.valueOf(sumatoria));
            }
        });
    }
      
    
     private void cargarColumnasTabla(){
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio Compra");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Importe");

    
    }
   
     
    
     public void cargarModeloProv(){
            ArrayList<CProveedores> ListarProveedores;
        ListarProveedores = proveedor.ObtenerProveedores();
//        modeloProveedor.addElement(new CCategorias("0", "Selecciona opcion","",1));
       
        for(CProveedores prov : ListarProveedores){
            modeloProveedor.addElement(prov);
            
             cmbproveedor.setSelectedItem("");
            AutoCompleteDecorator.decorate(cmbproveedor);
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
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblingresos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmbproveedor = new javax.swing.JComboBox();
        lblsumatoria = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnprov = new javax.swing.JButton();
        txtfecha = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtbuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 480, 27));

        tblingresos.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        tblingresos.setModel(modeloTabla);
        tblingresos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblingresosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblingresos);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 480, 130));

        jButton2.setBackground(new java.awt.Color(247, 94, 24));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/sale-label.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, 140, 40));

        jButton1.setBackground(new java.awt.Color(102, 166, 54));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/diskette.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 130, 40));

        jList1.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        jList1.setModel(modeloListaProductos);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 480, 95));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Buscar Producto:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(128, 128, 131));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/profit_1.png"))); // NOI18N
        jLabel7.setText("INGRESOS");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 120));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbproveedor.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        cmbproveedor.setModel(modeloProveedor);
        jPanel1.add(cmbproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 330, 30));

        lblsumatoria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblsumatoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsumatoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lblsumatoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 330, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Proveedor :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Sumatoria de Compra :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        btnprov.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnprov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/provider.png"))); // NOI18N
        btnprov.setText("Nuevo");
        btnprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprovActionPerformed(evt);
            }
        });
        jPanel1.add(btnprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 130, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 450, 270));

        txtfecha.setForeground(new java.awt.Color(102, 102, 102));
        txtfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtfecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 200, 30));

        jButton3.setBackground(new java.awt.Color(250, 214, 42));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/delete.png"))); // NOI18N
        jButton3.setText("Quitar Producto");
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 190, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
 limpiarListaProductos();
        String cadenaBusqueda = txtbuscar.getText();
        if(cadenaBusqueda.isEmpty()){
            limpiarListaProductos();
        }
        else{
            try {
                ArrayList<CProductos> listaProductos = producto.obtenerProductosPorCriterio(cadenaBusqueda);
                
                for(CProductos prod:listaProductos){
                    modeloListaProductos.addElement(prod);
                }
            } // TODO add your handling code here:
            catch (SQLException ex) {
                Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void jList1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MousePressed
        JList list = (JList)evt.getSource();
        if(evt.getClickCount() == 2){
            int index = list.locationToIndex(evt.getPoint());
            CProductos prod = (CProductos)list.getSelectedValue();
            anadirProducto(prod);
            
        
        } 
    }//GEN-LAST:event_jList1MousePressed

    private void tblingresosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblingresosKeyReleased

     

         if(evt.getKeyCode()==KeyEvent.VK_F3){
            int filaSeleccionada = tblingresos.getSelectedRow();
            String cantidad = JOptionPane.showInputDialog("Modificar cantidad:");
         if (cantidad != null){
            if (cantidad.equals("")) {
                
           cantidad = "1";
           
        }else{
        cantidad = cantidad;        
        }
            System.out.println("Code to do something if the user push OK");  
            String precioCompra = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
            double importe = Double.parseDouble(cantidad) * Double.parseDouble(precioCompra);
            String importeString = String.valueOf(importe);
            modeloTabla.setValueAt(cantidad, filaSeleccionada, 3);
            modeloTabla.setValueAt(importeString, filaSeleccionada, 4);

    }
    //If cancel button is pressed
    else{

            System.out.println("(Code to do something if the user push Cancel)");

    }
            

           
        }
//        
    }//GEN-LAST:event_tblingresosKeyReleased

    private void btnprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprovActionPerformed
    JTextField nombre = new JTextField("");
    JTextField telefono = new JTextField("");
    JTextField  email  = new JTextField ("");
    JTextArea direccion = new JTextArea("");
    JPanel panel = new JPanel(new GridLayout(0, 1));
    Border bordejpanel = new TitledBorder(new EtchedBorder(), "Datos Personales");
    panel.add(new JLabel("Registrar Nuevo Proveedor "));
    panel.add(new JLabel("Nombre:"));
    panel.add(nombre);
    panel.add(new JLabel("Telefono:"));
    panel.add(telefono);
    panel.add(new JLabel("email:"));
    panel.add(email);
    panel.add(new JLabel("direccion:"));
    panel.add(direccion);
    
   int result = JOptionPane.showConfirmDialog(null, panel, "Registro de proveedor",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        
        try {
            CProveedores prove = new CProveedores(0,nombre.getText(),"","",direccion.getText(),telefono.getText(),email.getText());
            bdprv.registrar_Proveedor(prove);
            cargarModeloProv();
        } catch (SQLException ex) {
            Logger.getLogger(panelingresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } else {
        System.out.println("Cancelled");
    }    
   
          
    }//GEN-LAST:event_btnprovActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
            
    

        try {
       
            
            ArrayList<CDetalle_ingresos> detalles = new ArrayList<CDetalle_ingresos>();
             
            
                  if(this.tblingresos.getRowCount()!=0 && tblingresos.getSelectedRow()!=-1){
                     
                      
          String sumatoriaStr = lblsumatoria.getText();
               double montoVenta = Double.parseDouble(sumatoriaStr);
               

         Calendar calendarioLocal = Calendar.getInstance();
            java.util.Date fechaActual = calendarioLocal.getTime();
            long fechaMilisegundos = fechaActual.getTime();
            java.sql.Date fecha = new java.sql.Date(fechaMilisegundos);



               
               CProveedores provedores = (CProveedores)cmbproveedor.getSelectedItem();
               
               int idpro = provedores.getIdproveedor();
               
               String  estado = "Estado";
              
               CIngreso ingreso = new CIngreso(0, idpro, fecha , montoVenta, estado);
              
               Long idingreso = ingresos.insertarIngreso(ingreso);
               
               
               System.out.println("id dela ultima venta: "+idingreso);
               
                    int numRows = modeloTabla.getRowCount();
            
            for (int i = 0; i < numRows; i++) {
                String idProducto = (String) modeloTabla.getValueAt(i, 0);
                String cantidadStr = (String) modeloTabla.getValueAt(i, 3);
                double cantidad = Double.parseDouble(cantidadStr);
                String precioC = (String)modeloTabla.getValueAt(i, 2);
                
                double precio_compra = Double.parseDouble(precioC);
               
                int idarticulo = Integer.parseInt(idProducto);
                CDetalle_ingresos detalle = new CDetalle_ingresos(idingreso, idarticulo, cantidad,precio_compra);
                dingreso.insertarDetalleIngreso(detalle);
                
                detalles.add(detalle);
            }
            
        for (int i = numRows-1; i >= 0; i--) {
                modeloTabla.removeRow(i);
            }
            
            lblsumatoria.setText("0.0");
                              
                  }else{
              Information info  = new Information(new Frame(), true);
                info.jLabel1.setText("Informacion !!!");
                info.textos.setText("Verifique los datos");
                info.setVisible(true);
             }
           } catch (SQLException ex) {
               Logger.getLogger(panelingresos.class.getName()).log(Level.SEVERE, null, ex);
           }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed
 
    private void limpiarListaProductos(){
                modeloListaProductos.clear();

    }
 
  private void anadirProducto(CProductos prod){
       String claveProd =Integer.toString(prod.getIdProducto());
        String nombreProd = prod.getNomProducto();
        String precioCompra = String.valueOf(prod.getPrecioCompraProducto());
        String cantidad = "1";
       String importe = String.valueOf(prod.getPrecioCompraProducto());
        
        String [] datosProducto = {claveProd, nombreProd,precioCompra,cantidad,importe};
        modeloTabla.addRow(datosProducto);
       
    }
  public void  limpiarCategoria(){
    cmbproveedor.removeAllItems();
}
 
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnprov;
    public javax.swing.JComboBox cmbproveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblsumatoria;
    private javax.swing.JTable tblingresos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JLabel txtfecha;
    // End of variables declaration//GEN-END:variables


}
