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
import Conexion.Conexion;
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
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import modal.Ingresos;
import modal.Productos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author najera10
 */
public class panelingresos extends javax.swing.JInternalFrame  {
       Proveedores  bdprv = new Proveedores();
           public static  DefaultTableModel modeloTabla;
    
    private Connection userConn;
    Proveedores proveedor = new Proveedores();
    DefaultComboBoxModel <CProveedores> modeloProveedor;
    DefaultListModel<CProductos> modeloListaProductos= new DefaultListModel<CProductos>();
    Producto producto = new Producto();
    Ingreso ingresos = new Ingreso();
    DetalleIngreso  dingreso = new DetalleIngreso();
    
    public panelingresos() {
       
        modeloProveedor = new DefaultComboBoxModel<CProveedores>();
          
          modeloTabla = new DefaultTableModel(){
            
           public boolean isCellEditable(int filas, int columnas){
            if(columnas==3){
                return true;
            }else{
                return false;
            }
    }
        };
        initComponents();
            this.txtbuscar.requestFocus();
        modeloTabla.addColumn("");
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio Compra");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Importe");
        tblingresos.setModel(modeloTabla);
      TableColumnModel columna=tblingresos.getColumnModel();
      columna.getColumn(0).setPreferredWidth(-2);
      columna.getColumn(0).setMaxWidth(-2);
      columna.getColumn(0).setMinWidth(-2);

      columna.getColumn(2).setPreferredWidth(700);
       SimpleDateFormat formateador = new SimpleDateFormat(
   "dd 'de' MMMM 'de' yyyy", new Locale("ES"));
   Date fechaDate = new Date();
   String fecha = formateador.format(fechaDate);
   txtfecha.setText(fecha);
   if(cmbproveedor.getItemCount() != 0){
            
            cmbproveedor.setSelectedIndex(0);

        }else{
            System.out.println("no tiene datos");
        }
   
   cargarListenerModeloTabla();
   addEventKey();
   

    }
    
      
       private void addEventKey() {
           
              KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action deleteAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
              quitarproducto();
            }
        };
        
         tblingresos.getInputMap(tblingresos.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(delete, "DELETE");
           tblingresos.getActionMap().put("DELETE", deleteAction);
        
        
        
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
                txtbuscar.requestFocus();
                
            }
        };  
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1, "F1");
        this.getActionMap().put("F1", f1Action);
           
        
        
        
          KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false);
        Action f4Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
              CancelarVenta();
            }
        };  
        
           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f4, "F4");
        this.getActionMap().put("F4", f4Action);
           
        
         KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false);
        Action f3Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
              nuevo_proovedor();
            }
        };  
        
           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);
        
        
        
              
                 KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false);
        Action f5Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                    
             if(tblingresos.getRowCount() > 0){
                   
                     Long  id = guardarCompra();
                    if(id != null){
                      reporteCompra(id);
                    }else{
                    System.out.println("Ocurrio un error"); 
                    }
                    
                   
                    
                }else{
             System.out.println("tabla vacia");
                }
                
                }
        };  
        
           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f5, "F5");
        this.getActionMap().put("F5", f5Action);
        
       }
    
    
        public static  void cargarListenerModeloTabla(){
        
        modeloTabla.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
             int numFilas = modeloTabla.getRowCount();
             double sumatoria  = 0;
                for (int i = 0; i < numFilas; i++) {
                    String importe = (String) modeloTabla.getValueAt(i, 5);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblingresos = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JLabel();
        bntQuitar = new javax.swing.JButton();
        txtbuscar = new rojerusan.RSMetroTextFullPlaceHolder();
        jLabel3 = new javax.swing.JLabel();
        cmbproveedor = new javax.swing.JComboBox();
        btnprov = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblsumatoria = new javax.swing.JLabel();

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

        tblingresos.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        tblingresos.setModel(modeloTabla);
        tblingresos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblingresosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblingresos);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 1060, 210));

        btnCancelar.setBackground(new java.awt.Color(247, 94, 24));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/sale-label.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 140, 30));

        btnGuardar.setBackground(new java.awt.Color(102, 166, 54));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/diskette.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(128, 128, 131));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Compras");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 50));

        txtfecha.setForeground(new java.awt.Color(102, 102, 102));
        txtfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtfecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 200, 30));

        bntQuitar.setBackground(new java.awt.Color(250, 214, 42));
        bntQuitar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/delete.png"))); // NOI18N
        bntQuitar.setText("Quitar Producto");
        bntQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntQuitarActionPerformed(evt);
            }
        });
        jPanel2.add(bntQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 180, 30));

        txtbuscar.setForeground(new java.awt.Color(0, 0, 0));
        txtbuscar.setBorderColor(new java.awt.Color(102, 102, 255));
        txtbuscar.setBotonColor(new java.awt.Color(102, 0, 255));
        txtbuscar.setMayusculas(true);
        txtbuscar.setPhColor(new java.awt.Color(0, 0, 0));
        txtbuscar.setPlaceholder("Buscar producto ...");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
            }
        });
        jPanel2.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 490, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Proveedor :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, -1, -1));

        cmbproveedor.setFont(new java.awt.Font("Yu Gothic UI", 0, 11)); // NOI18N
        cmbproveedor.setModel(modeloProveedor);
        jPanel2.add(cmbproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 300, 30));

        btnprov.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnprov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/provider.png"))); // NOI18N
        btnprov.setText("Nuevo");
        btnprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprovActionPerformed(evt);
            }
        });
        jPanel2.add(btnprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Sumatoria de Compra :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        lblsumatoria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblsumatoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsumatoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(lblsumatoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 330, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblingresosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblingresosKeyReleased
         int seleccionaopcion = tblingresos.getSelectedRow();
             if(seleccionaopcion < 0){
                 System.out.println("no hay");
             }else{
     

         if(evt.getKeyCode()==KeyEvent.VK_F2){
            int filaSeleccionada = tblingresos.getSelectedRow();
            String cantidad = JOptionPane.showInputDialog("Modificar cantidad:");
         if (cantidad != null){
            if (cantidad.equals("")) {
                
           cantidad = "1";
           
        }else{
        cantidad = cantidad;        
        }
            System.out.println("Code to do something if the user push OK");  
            String precioCompra = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
            double importe = Double.parseDouble(cantidad) * Double.parseDouble(precioCompra);
            String importeString = String.valueOf(importe);
            modeloTabla.setValueAt(cantidad, filaSeleccionada, 4);
            modeloTabla.setValueAt(importeString, filaSeleccionada, 5);

    }
    //If cancel button is pressed
    else{

            System.out.println("(Code to do something if the user push Cancel)");

    }
            

           
        }
       }
//        
    }//GEN-LAST:event_tblingresosKeyReleased

    private void btnprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprovActionPerformed
    
          nuevo_proovedor();
    }//GEN-LAST:event_btnprovActionPerformed
    public void nuevo_proovedor(){
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
            CProveedores prove = new CProveedores(0,nombre.getText().toUpperCase(),"","",direccion.getText().toUpperCase(),telefono.getText(),email.getText().toUpperCase());
            bdprv.registrar_Proveedor(prove);
            cargarModeloProv();
        } catch (SQLException ex) {
            Logger.getLogger(panelingresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } else {
        System.out.println("Cancelled");
    }    
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(tblingresos.getRowCount() > 0){
      
      
    Long  id = guardarCompra();
        
       if(id != null){
            
           reporteCompra(id);
           
       }else{
           System.out.println("Ocurrio un error"); 
       }
        }else{
            System.out.println("tabla vacia");
        }
       
    }//GEN-LAST:event_btnGuardarActionPerformed

    public Long  guardarCompra(){
        
        Long idingreso = null;

         try {
       
            ArrayList<CDetalle_ingresos> detalles = new ArrayList<CDetalle_ingresos>();
             
            
                  if(this.tblingresos.getRowCount() > 0 ){
                     
                      
          String sumatoriaStr = lblsumatoria.getText();
               double montoVenta = Double.parseDouble(sumatoriaStr);
               

         Calendar calendarioLocal = Calendar.getInstance();
            java.util.Date fechaActual = calendarioLocal.getTime();
            long fechaMilisegundos = fechaActual.getTime();
            java.sql.Date fecha = new java.sql.Date(fechaMilisegundos);



               
               CProveedores provedores = (CProveedores)cmbproveedor.getSelectedItem();
               
               int idpro = provedores.getIdproveedor();
               
               String  estado = "ACTIVO";
              
               CIngreso ingreso = new CIngreso(0, idpro, fecha , montoVenta, estado);
              
                idingreso = ingresos.insertarIngreso(ingreso);
               
               
      
               
                    int numRows = modeloTabla.getRowCount();
            
            for (int i = 0; i < numRows; i++) {
                String idProducto = (String) modeloTabla.getValueAt(i, 0);
                String cantidadStr = (String) modeloTabla.getValueAt(i, 4);
                double cantidad = Double.parseDouble(cantidadStr);
                String precioC = (String)modeloTabla.getValueAt(i, 3);
                
                double precio_compra = Double.parseDouble(precioC);
               
                int idarticulo = Integer.parseInt(idProducto);
                CDetalle_ingresos detalle = new CDetalle_ingresos(0,idingreso, idarticulo, cantidad,precio_compra);
               ArrayList<Double> Stocks =  producto.Obtener_stock(idProducto);
               
                for (int j = 0; j < Stocks.size(); j++) {
                    
                   String cadena = Stocks.get(j).toString();
                   Double stk =  Double.parseDouble(cadena);
                   
                   double canTotal = stk + cantidad;
                    System.out.println(canTotal+"dsfsdf");
                   producto.Rest_Stock(idProducto, canTotal);
                   
                }
               
               
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
         return idingreso;
    }
    
    public void reporteCompra(Long id){
       
           try {
               userConn=Conexion.getConnection();
               Map parametro = new HashMap();
               parametro.put("idingreso",id);
               String path = System.getProperty("user.dir")+"/src/reportes/IngresosDetalles.jasper";
               JasperPrint jprint = JasperFillManager.fillReport(path, parametro,userConn);
               JasperViewer viewer = new JasperViewer(jprint,false);
               viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
               viewer.setVisible(true);
           } catch (SQLException ex) {
               Logger.getLogger(panelingresos.class.getName()).log(Level.SEVERE, null, ex);
           } catch (JRException ex) {
               Logger.getLogger(panelingresos.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed
 if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            
            if(txtbuscar.getText().equals("")){
            
             
            
            
            }else{
              Ingresos productoListado;
                try {
//                    estado = true;
                     productoListado = new Ingresos(new JFrame(),true);
                     productoListado.setVisible(true);  
                } catch (SQLException ex) {
                    Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
 
  if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            try {
              
                String cadenabusqueda = txtbuscar.getText();
                CProductos prod = producto.obtenerporCodigoProdcuto(cadenabusqueda);
                
                
//                if(prod == null){
//                
//                JOptionPane.showMessageDialog(null, "El Codigo No Existe", "", JOptionPane.ERROR_MESSAGE);
//                txtbuscar.setText("");
//
//                }else{
                  
                anadirProducto(prod);
                txtbuscar.setText("");
//                }
                
            } catch (SQLException ex) {
                Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
            }
           
 
  }

// TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    CancelarVenta();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void bntQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntQuitarActionPerformed
            quitarproducto();        // TODO add your handling code here:
    }//GEN-LAST:event_bntQuitarActionPerformed
 
    private void limpiarListaProductos(){
                modeloListaProductos.clear();

    }
 
  private void anadirProducto(CProductos prod){
       ArrayList Lista = new ArrayList();
        String claveProd =Integer.toString(prod.getIdProducto());
        String barras = prod.getCodigo();
        String nombreProd = prod.getNomProducto();
        String precioVenta = String.valueOf(prod.getPrecioVentaProducto());
        String cantidad = "1";
        
       String importe = String.valueOf(prod.getPrecioVentaProducto());
       
         if(tblingresos.getRowCount() ==0){
             
        String [] datosProducto = {claveProd,barras, nombreProd, precioVenta,cantidad,importe};
        modeloTabla.addRow(datosProducto);
    
             
         }else{
             
     boolean encontrado = false;
      int index = 0;
      String cantidadEXT="";
      
      for (int i = 0; i < tblingresos.getRowCount() ; i++) {
          
      Lista.add(tblingresos.getValueAt(i, 0).toString());

                    if( Lista.get(i).equals(claveProd)){
                         encontrado  =true ;
                         cantidadEXT  =  tblingresos.getValueAt(i, 4).toString();
                        index = i;
                        break;


                }  
      }
      
                  if (encontrado){

                    double  num = 1;
                    double cnt = Double.valueOf(cantidadEXT);
                    double cantAct = num + cnt ;
                    String cantidadActualizada = String.valueOf(cantAct);
                     modeloTabla.setValueAt(cantidadActualizada, index, 4);

                    String pv = modeloTabla.getValueAt(index, 3).toString();
                    double importeEXT = Double.parseDouble(cantidadActualizada) * Double.parseDouble(pv);
                    String importeString = String.valueOf(importeEXT);
                    //            modeloTablaPunto.setValueAt(cantidad, index, 3);
                    modeloTabla.setValueAt(importeString, index, 5);

                }else{
               
          String [] datosProducto = {claveProd, barras,nombreProd, precioVenta,cantidad,importe};
        modeloTabla.addRow(datosProducto);
    
                }
             
             
         }
    
    }
  public void  limpiarCategoria(){
    cmbproveedor.removeAllItems();
}
  
  private void CancelarVenta(){
          int cantidadFilas = modeloTabla.getRowCount();
        
        if (cantidadFilas > 0 ) {
       int opcion =  JOptionPane.showConfirmDialog(this, "¿Esta seguro de cancelar la venta? ");
        
        if (opcion == 0) {   
            for (int i = cantidadFilas-1; i >= 0 ; i--) {
            modeloTabla.removeRow(i);
            
                txtbuscar.setText("");
               
                limpiarListaProductos();
        }     
        }
       }else{
            Information info  = new Information(new Frame(), true);
                info.jLabel1.setText("iformacion !!!");
                info.textos.setText("NO HAY NINIGUN PRODUCTO");
                info.setVisible(true);
        }
    }
   private void quitarproducto(){
    int filaSeleccionada  = tblingresos.getSelectedRow();
 int cantidadFilas = modeloTabla.getRowCount();
     if (cantidadFilas > 0 ) {
         
     int opcion = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de borrar el producto?");
     if (opcion == 0) {
         if(filaSeleccionada == -1){
          JOptionPane.showMessageDialog(null, "No hay producto seleccionado", "", JOptionPane.ERROR_MESSAGE);
             
         }else{
        
         modeloTabla.removeRow(filaSeleccionada);

         }
     
     }
     }
 }
    
 
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntQuitar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnprov;
    public javax.swing.JComboBox cmbproveedor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    public static javax.swing.JLabel lblsumatoria;
    public static javax.swing.JTable tblingresos;
    public static rojerusan.RSMetroTextFullPlaceHolder txtbuscar;
    private javax.swing.JLabel txtfecha;
    // End of variables declaration//GEN-END:variables


}
