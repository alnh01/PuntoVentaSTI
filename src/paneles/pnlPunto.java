/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import Alerts.ErrorAlert;
import Alerts.Information;
import BD.Clientes;
import BD.CorteDia;
import BD.CortedelDia;
import BD.DetalleVenta;
import BD.Producto;
import BD.Proveedores;
import BD.Usuarios;
import BD.Venta;
import BD.login;
import Conexion.Conexion;
import Controller.CClientes;
import Controller.CCortedia;
import Controller.CDetalle_ventas;
import Controller.CItem;
import Controller.CProductos;
import Controller.CUsuarios;
import Controller.CVenta;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import principal.Principal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import modal.Ayuda;
import modal.Cortedia;
import modal.Productos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author RojeruSan
 */
public class pnlPunto extends javax.swing.JPanel {
  
    public static String buscar;
    Timer timer;
    Clientes cliente = new Clientes();
    private Connection userConn;
    DefaultComboBoxModel <CClientes> modeloClientes  = new DefaultComboBoxModel<CClientes>();
    public static  DefaultTableModel modeloTabla;
    DefaultListModel<CProductos> modeloListaProductos= new DefaultListModel<CProductos>();
    Producto producto = new Producto();
    Venta ventas = new Venta();
    DetalleVenta dventa = new DetalleVenta();
    public static boolean estado = false;
    
    public pnlPunto() {
       
        initComponents();
//     cargarListenerModeloTabla();
        cargarModeloCli();
        modeloTabla = new DefaultTableModel(){
            
           public boolean isCellEditable(int filas, int columnas){
            if(columnas==3){
                return true;
            }else{
                return false;
            }
    }
        };
        modeloTabla.addColumn("");
        modeloTabla.addColumn("Cod Barras");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio Venta");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Importe");
        tblventas.setModel(modeloTabla);
        cargarListenerModeloTabla();
        if(cmbclientes.getItemCount() != 0){
            
            cmbclientes.setSelectedIndex(0);

        }else{
            System.out.println("no tiene datos");
        }
         TableColumnModel columna=tblventas.getColumnModel();
      columna.getColumn(0).setPreferredWidth(-2);
      columna.getColumn(0).setMaxWidth(-2);
      columna.getColumn(0).setMinWidth(-2);

      columna.getColumn(2).setPreferredWidth(700);
       addEventKey();
    }
    
       
    
       private void addEventKey() {
           
              KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action deleteAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
              quitarproducto();
            }
        };
        
         tblventas.getInputMap(tblventas.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(delete, "DELETE");
           tblventas.getActionMap().put("DELETE", deleteAction);
        
        
        
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
                txtbuscar.requestFocus();
                txtpago.setBackground(Color.white);
                
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
              nuevoCliente();
            }
        };  
        
           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f3, "F3");
        this.getActionMap().put("F3", f3Action);
        
           
                 KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false);
        Action f5Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
                  CorteDia cot  = new CorteDia();
      boolean  exist =   cot.ValidaExist();
       if(exist == true){
          
           ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("YA REALIZO UN CORTE");
            err.msj3.setText("");
            err.setVisible(true);
            
       }else{
           if(txtpago.getText().equals("")){
               txtpago.setBackground(Color.CYAN);
               txtpago.requestFocus();
           }else{
               ventaGuardar(); 
           }
           
       }
       }
        };  
        
           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f5, "F5");
        this.getActionMap().put("F5", f5Action);
        
        
        
         KeyStroke f7 = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, false);
        Action f7Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new Cortedia(new JFrame(), true).setVisible(true);
            }
        }; 
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f7, "F7");
        this.getActionMap().put("F7", f7Action);
        
        
        
         KeyStroke A = KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0, false);
        Action AAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Ayuda ayu = new Ayuda(new JFrame(),true);
                ayu.setVisible(true);
            }
        };  
           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(A, "A");
        this.getActionMap().put("A", AAction);

        
//         KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, false);
//        Action TabAction = new AbstractAction() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(tab);
//            }
//        };  
//        
//           this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(tab, "TAB");
//        this.getActionMap().put("TAB", TabAction);
        
        
       }
    
    
    
    
    
    //cargar en comboxcleintes
        private void cargarModeloCli(){
        ArrayList<CClientes> listaCategorias;
        listaCategorias = cliente.ObtenerUsuarios();
//        modeloClientes.addElement(new CClientes(0, "Seleciona una opcion", "", "", "", "", ""));

        for(CClientes categoria : listaCategorias){
            modeloClientes.addElement(categoria);
            cmbclientes.setSelectedItem("");
            AutoCompleteDecorator.decorate(cmbclientes);
        }
    }
//cargardatos modelo tabla          
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
//    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblimagen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblventas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblsumatoria = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtpago = new javax.swing.JTextField();
        rSLabelFecha1 = new rojeru_san.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbclientes = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtbuscar = new rojerusan.RSMetroTextFullPlaceHolder();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1231, 529));
        setPreferredSize(new java.awt.Dimension(1231, 529));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Punto de Venta");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 240, 50));

        tblventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblventasMouseClicked(evt);
            }
        });
        tblventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblventasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblventas);
        tblventas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1100, 390));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Buscar Producto");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 120, 29));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblsumatoria.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblsumatoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsumatoria.setText("0.00");
        jPanel1.add(lblsumatoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 260, 90));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("TOTAL:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("PAGO CON:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 90, 40));

        txtpago.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtpago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpagoMouseClicked(evt);
            }
        });
        txtpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpagoActionPerformed(evt);
            }
        });
        txtpago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpagoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpagoKeyTyped(evt);
            }
        });
        jPanel1.add(txtpago, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 210, 60));

        rSLabelFecha1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelFecha1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        jPanel1.add(rSLabelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 110, 20));

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelHora1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        jPanel1.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 120, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/calendario.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 1100, 90));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(null);
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, 80, 80));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Cliente :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 70, 30));

        cmbclientes.setModel(modeloClientes);
        add(cmbclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 220, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/customer_1.png"))); // NOI18N
        jButton1.setText("Nuevo (F3)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 140, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pregunta.png"))); // NOI18N
        jLabel6.setText("F8");
        jLabel6.setToolTipText("Ayuda");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, -1));

        txtbuscar.setBorderColor(new java.awt.Color(102, 102, 255));
        txtbuscar.setBotonColor(new java.awt.Color(102, 0, 255));
        txtbuscar.setMayusculas(true);
        txtbuscar.setPhColor(new java.awt.Color(0, 0, 0));
        txtbuscar.setPlaceholder("Buscar producto...");
        txtbuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbuscarMouseClicked(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
            }
        });
        add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 540, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagoActionPerformed

    private void tblventasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblventasKeyReleased
    if(evt.getKeyCode()==KeyEvent.VK_F2){
            int filaSeleccionada = tblventas.getSelectedRow();
            String cantidad = JOptionPane.showInputDialog("Modificar cantidad:");
          
            if (cantidad != null){
                   if (cantidad.equals("")) {
                
           cantidad = "1";
           
        }else{
        cantidad = cantidad;        
        }
            System.out.println("Code to do something if the user push OK");  
            String precioVenta = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
            double importe = Double.parseDouble(cantidad) * Double.parseDouble(precioVenta);
            String importeString = String.valueOf(importe);
            modeloTabla.setValueAt(cantidad, filaSeleccionada, 4);
            modeloTabla.setValueAt(importeString, filaSeleccionada, 5);
         
            }else{
            
            System.out.println("(Code to do something if the user push Cancel)");

              }
       
           
        }
  
    }//GEN-LAST:event_tblventasKeyReleased

    private void CancelarVenta(){
          int cantidadFilas = modeloTabla.getRowCount();
        
        if (cantidadFilas > 0 ) {
       int opcion =  JOptionPane.showConfirmDialog(this, "¿Esta seguro de cancelar la venta? ");
        
        if (opcion == 0) {   
            for (int i = cantidadFilas-1; i >= 0 ; i--) {
            modeloTabla.removeRow(i);
             txtpago.setText("");
                txtbuscar.setText("");
                lblimagen.setIcon(null);
                limpiarListaProductos();
        }     
        }
       }else{
              ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO HAY UN PRODUCTO SELECCIONADO");
            err.msj3.setText("");
            err.setVisible(true);
        }
    }
    
    private void ventaGuardar(){
     int opcion = cmbclientes.getSelectedIndex();

if(this.tblventas.getRowCount()==0 && this.tblventas.getSelectedRow()==-1){
//JOptionPane.showMessageDialog(null, "NO AGREGO NINGUN PRODUCTO");  
  ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO AGREGO ALGUN PRODUCTO");
            err.msj3.setText("");
            err.setVisible(true);
}else if (opcion ==-1){
//JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO UN CLIENTE");
  ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO HA SELECCIONADO UN CLIENTE");
            err.msj3.setText("");
            err.setVisible(true);
}else if (txtpago.getText().equals("")){
//JOptionPane.showMessageDialog(null, "Porfavor agrege la cantidad de pago");
              ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO AGREGO CANTIDAD DE PAGO");
            err.msj3.setText("");
            err.setVisible(true);
}else{
double sumat = Double.parseDouble(lblsumatoria.getText());
double pago = Double.parseDouble(txtpago.getText());
   if(pago < sumat ){
         //  JOptionPane.showMessageDialog(null, "La catidad con la que pago no puede ser menor");
               ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("LA CANTIDAD DE PAGO ES INCORRECTA");
            err.msj3.setText("");
            err.setVisible(true);
   }else{
    try {  
         
        long   id = guardarventa();
        imprimirTicket( (int)id );

              

                
    
    } catch (JRException ex) {
        Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
    }
    txtbuscar.setText("");
    limpiarListaProductos(); 
   }

}
}
    private void txtpagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpagoKeyTyped
   char validar = evt.getKeyChar();
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresa Solo numeros");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagoKeyTyped

    private void txtpagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpagoKeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
      int opcion = cmbclientes.getSelectedIndex();
       
    if(this.tblventas.getRowCount()==0 && this.tblventas.getSelectedRow()==-1){
  ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO AGREGO NINGUN PRODUCTO");
            err.msj3.setText("");
            err.setVisible(true);
}else if (opcion ==-1){
  ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO AGREGO NINGUN PRODUCTO");
            err.msj3.setText("");
            err.setVisible(true);
}else if (txtpago.getText().equals("")){
  ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("NO AGREGO CANTIDAD DE PAGO");
            err.msj3.setText("");
            err.setVisible(true);

}else{
    
double sumat = Double.parseDouble(lblsumatoria.getText());
double pago = Double.parseDouble(txtpago.getText());
   if(pago < sumat){
             ErrorAlert err = new ErrorAlert(new JFrame(),true);
            
            err.msj1.setText("HAY UN PLOBLEMA ");
            err.msj2.setText("LA CANTIDAD DE PAGO ES ERRONEA");
            err.msj3.setText("");
            err.setVisible(true);
 
   }else{
    try {  
   long id =  guardarventa();
        imprimirTicket((int)id);
        txtpago.setBackground(Color.white);
    } catch (JRException ex) {
        Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
    }
txtbuscar.setText("");
limpiarListaProductos(); 
   }

}
    
    
          }        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     nuevoCliente(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
private void nuevoCliente(){
     JTextField nombre = new JTextField("");
    JTextField telefono = new JTextField("");
    JTextField  email  = new JTextField ("");
    JTextArea direccion = new JTextArea("");
    JPanel panel = new JPanel(new GridLayout(0, 1));
    Border bordejpanel = new TitledBorder(new EtchedBorder(), "Datos Personales");
    
    panel.add(new JLabel("Nombre:"));
    panel.add(nombre);
    panel.add(new JLabel("Telefono:"));
    panel.add(telefono);
    panel.add(new JLabel("email:"));
    panel.add(email);
    panel.add(new JLabel("direccion:"));
    panel.add(direccion);
    
   int result = JOptionPane.showConfirmDialog(null, panel, "Registro de Cliente",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        
   
      try {
          CClientes cli = new CClientes(0,nombre.getText().toUpperCase(),"","",direccion.getText().toUpperCase(),telefono.getText(),email.getText().toUpperCase());
          cliente.insert(cli);
          cmbclientes.removeAllItems();
          cargarModeloCli();
      } catch (SQLException ex) {
          Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
      }
       
        
    } else {
        System.out.println("Cancelled");
    }    
      
}                    
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
      Ayuda ay = new Ayuda (new JFrame(),true);
      ay.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed
if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            
            if(txtbuscar.getText().equals("")){
            
             
            
            
            }else{
              Productos productoListado;
                try {
                    estado = true;
                     productoListado = new Productos(new JFrame(),true);
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
                  
                anadirProductoAVenta(prod);
                txtbuscar.setText("");
//                }
                
            } catch (SQLException ex) {
                Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
            
            
           
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyPressed

    private void txtpagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpagoMouseClicked
txtpago.setBackground(Color.CYAN);        // TODO add your handling code here:
    }//GEN-LAST:event_txtpagoMouseClicked

    private void txtbuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscarMouseClicked
  txtpago.setBackground(Color.white);        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarMouseClicked

    private void tblventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblventasMouseClicked
     txtpago.setBackground(Color.white);        // TODO add your handling code here:
     
    }//GEN-LAST:event_tblventasMouseClicked

    
       public void anadirProductoAVenta(CProductos prod){
       ArrayList Lista = new ArrayList();
        String claveProd =Integer.toString(prod.getIdProducto());
        String barras = prod.getCodigo();
        String nombreProd = prod.getNomProducto();
        String precioVenta = String.valueOf(prod.getPrecioVentaProducto());
        String cantidad = "1";
        
       String importe = String.valueOf(prod.getPrecioVentaProducto());
       
         if(tblventas.getRowCount() ==0){
             
        String [] datosProducto = {claveProd,barras, nombreProd, precioVenta,cantidad,importe};
        modeloTabla.addRow(datosProducto);
    
             
         }else{
             
     boolean encontrado = false;
      int index = 0;
      String cantidadEXT="";
      
      for (int i = 0; i < pnlPunto.tblventas.getRowCount() ; i++) {
          
      Lista.add(pnlPunto.tblventas.getValueAt(i, 0).toString());

                    if( Lista.get(i).equals(claveProd)){
                         encontrado  =true ;
                         cantidadEXT  =  pnlPunto.tblventas.getValueAt(i, 4).toString();
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
    
    
    
    private void desplegarFoto(CProductos prod){
       
   
        try {
            /*obtener imagen*/
            Blob is = producto.buscarFoto(prod);
            byte[]recuperar = is.getBytes(1, (int) is.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image images = img.getScaledInstance(lblimagen.getWidth(), lblimagen.getHeight(), Image.SCALE_SMOOTH);
            lblimagen.setIcon(new ImageIcon(images));
        } catch (SQLException ex) {
            Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
        }
                
          
    }
    
    private void limpiarListaProductos(){
        modeloListaProductos.clear();
    }
    
    public long guardarventa() throws JRException{
         Long idVenta = null;
        try {
            ArrayList<CDetalle_ventas> detalles = new ArrayList<CDetalle_ventas>();    
            //Obtenemos la sumatoria de la venta
    
            String sumatoriaStr = lblsumatoria.getText();
            double montoVenta = Double.parseDouble(sumatoriaStr);
            String pagoConStr = txtpago.getText();
            //combobox
            CClientes clien = (CClientes)cmbclientes.getSelectedItem();
            int idcliente =  clien.getIdcliente();
            double cambio = 0;
            if(!pagoConStr.isEmpty()){
                double montoPago = Double.parseDouble(pagoConStr);
                cambio = montoPago - montoVenta;
            }
            //Obtenemos la fecha actual y creamos un objeto Date Sql
            Calendar calendarioLocal = Calendar.getInstance();
            java.util.Date fechaActual = calendarioLocal.getTime();
            long fechaMilisegundos = fechaActual.getTime();
            java.sql.Date fecha = new Date(fechaMilisegundos);
            String idprin=Principal.txtcodigo.getText();
            CVenta venta = new CVenta(0,idcliente,null,montoVenta," ",idprin);
           
             idVenta = ventas.insertarVenta(venta);
            System.out.println("id dela ultima venta: "+idVenta);
            int numRows = modeloTabla.getRowCount();
            
            for (int i = 0; i < numRows; i++) {
                String idProducto = (String) modeloTabla.getValueAt(i, 0);
                String cantidadStr = (String) modeloTabla.getValueAt(i, 4);
                String costoxpStr = (String) modeloTabla.getValueAt(i, 3);
                double cantidad = Double.parseDouble(cantidadStr);
                double costoxp = Double.parseDouble(costoxpStr);
                double costototalx = cantidad * costoxp;
                CDetalle_ventas detalle = new CDetalle_ventas(0,idVenta, idProducto, cantidad,costototalx);
                ArrayList<Double> Stocks =  producto.Obtener_stock(idProducto);
                for (int j = 0; j < Stocks.size(); j++) {
                    
                   String cadena = Stocks.get(j).toString();
                   Double stk =  Double.parseDouble(cadena);
                   
                   double canTotal = stk - cantidad;
                   
                   producto.Rest_Stock(idProducto, canTotal);
                }
                dventa.insertarDetalleVenta(detalle);   
                detalles.add(detalle);
              
            }
        userConn=Conexion.getConnection();
       
            for (int i = numRows-1; i >= 0; i--) {
                modeloTabla.removeRow(i);
            }
            lblsumatoria.setText("0.0");
            if(!pagoConStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "<html><h1 style='font-size:200 px; color:blue'>"+cambio+"</h1></html>", "Usted debe dar este cambio:", 1);
                txtpago.setText("");
                txtpago.setText("");
                txtbuscar.setText("");
                lblimagen.setIcon(null);
            }
//            reportedeticket(idVenta);
            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(pnlPunto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idVenta;
    }
    
    private void quitarproducto(){
       int filaSeleccionada  = tblventas.getSelectedRow();
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
    
    public void reportedeticket(Long idventa) throws JRException, SQLException{
            userConn=Conexion.getConnection();
            Map parametro = new HashMap();
            parametro.put("idventa",idventa);
            String path = System.getProperty("user.dir")+"/src/reportes/ticket.jasper";
            JasperPrint jprint = JasperFillManager.fillReport(path, parametro,userConn);
            JasperPrintManager.printReport(jprint,false);
//            JasperViewer viewer = new JasperViewer(jprint,false);
//            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            viewer.setVisible(true);
    }
    
    public void limpiartabla(){
         while (modeloTabla.getRowCount() > 0)
            {   
             modeloTabla.removeRow(0);
        }
    }
    
     public void imprimirTicket( int idventa){
    
        Calendar cal = Calendar.getInstance();
         int año = cal.get(Calendar.YEAR);
         int mes = cal.get(Calendar.MONTH);
         int dia = cal.get(Calendar.DAY_OF_MONTH);
         int hora = cal.get(Calendar.HOUR_OF_DAY);
         int minuto = cal.get(Calendar.MINUTE);
         int segundo = cal.get(Calendar.SECOND);
         String fechaActual = dia +"/"+(mes+1)+"/"+año;
         String horaActual = hora+":"+minuto+":"+segundo;
         String cadena = "";



              ArrayList<String>ListarEncabezado = ventas.ObtenerHeader();
                   cadena += ""
                    + " "+ListarEncabezado.get(1)+"\n"
                       +"\n"
                    + "==============================\n"
                    + "direccion\n"
                    + ""+ListarEncabezado.get(2)+"\n"
                    + "===============================\n"
                    + "Telefono: "+ListarEncabezado.get(3)+" \n \n"
                    + ""+fechaActual+"              "+horaActual+"\n"
                     +"================================\n"
                    + "Descripcion              Importe\n"
                    + "===============================\n";       
                 ArrayList<CItem>ListarDetalles = ventas.ObtenerDetalles(idventa);    
                 int numeroDetalles = ListarDetalles.size();

                 for (int i = 0; i < numeroDetalles; i++) {
                     CItem item= ListarDetalles.get(i);

                     cadena += ""
                     +" "+item.getNombre()+"       "+item.getCantidad()+"         "+Double.parseDouble(item.getCosto())+"\n";

                    }

                   ArrayList<String>ObtenerVentas = ventas.ObtenerVenta(idventa);

                      cadena +="================================\n"
                    + "Total: "+ObtenerVentas.get(0)+"\n"
                    + "\n"
                    + "\n"
                    + "================================\n"
                    + "GRACIAS POR SU PREFERENCIA..\n"
                    + "***********:::::::::************"
                    + "\n           "
                    + "\n           "
                    + "\n           "
                    + "\n           "
                    + "\n           "
                    + "\n           ";



             DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        //Aqui selecciona tu impresora, el ejemplo tomará la impresora predeterminada.
                PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                DocPrintJob pj = service.createPrintJob();
                byte[] bytes = cadena.getBytes();
                Doc doc = new SimpleDoc(bytes, flavor, null);
                try {
                    pj.print(doc, null);

                } catch (PrintException e) {
                }


 
 
 }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbclientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblimagen;
    public static javax.swing.JLabel lblsumatoria;
    private rojeru_san.RSLabelFecha rSLabelFecha1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    public static javax.swing.JTable tblventas;
    public static rojerusan.RSMetroTextFullPlaceHolder txtbuscar;
    public static javax.swing.JTextField txtpago;
    // End of variables declaration//GEN-END:variables
}
