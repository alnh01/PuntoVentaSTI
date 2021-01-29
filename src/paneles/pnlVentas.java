/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import Alerts.ErrorAlert;
import Alerts.Information;
import Alerts.Success;
import Alerts.SuccessAlert;
import Alerts.Warning;
import Alerts.WarningAlert;
import BD.Clientes;
import BD.GenerarRespaldo;
import Controller.CClientes;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
//import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class pnlVentas extends javax.swing.JPanel {
 private TableRowSorter trsFiltro;
    DefaultTableModel modeloTablaClientes = new DefaultTableModel(){
      public boolean isCellEditable(int filas, int columnas){
            if(columnas==4){
                return true;
            }else{
                return false;
            }
    }
    
    
    };
    Clientes  bdclt = new Clientes();
    CClientes  clienteseleccionado = null ;
     GenerarRespaldo respl = new GenerarRespaldo();
           

       
    public pnlVentas() throws IOException {
        initComponents();
        cargarColumnas();
        cargarModeloTabla();
//        btneliminar.setVisible(false);

        
    }

    public void cargarColumnas(){
        modeloTablaClientes.addColumn("Nombre");
        modeloTablaClientes.addColumn("Dirección");
        modeloTablaClientes.addColumn("Télefono");
        modeloTablaClientes.addColumn("Email");
    }
    
     public void filtroBusqueda(JTextField txt) {
        trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
    }
    
    public void cargarModeloTabla (){
         limpiarTabla();
       ArrayList<CClientes>ListarClientes = bdclt.ObtenerUsuarios();
      int numerosUsusarios = ListarClientes.size();
      modeloTablaClientes.setNumRows(numerosUsusarios);
        for (int i = 0; i < numerosUsusarios; i++) {
            CClientes cliente = ListarClientes.get(i);
            String nombre = cliente.getNombre();
            String direccion = cliente.getDireccion();
            String telefono = cliente.getTelefono();
            String email = cliente.getEmail();
            
            
            modeloTablaClientes.setValueAt(cliente, i, 0);
            modeloTablaClientes.setValueAt(direccion, i, 1);
            modeloTablaClientes.setValueAt(telefono, i, 2);
            modeloTablaClientes.setValueAt(email, i, 3);
        }
    }
    
       public  void limpiarTabla(){
        int numFilas = modeloTablaClientes.getRowCount();
        if(numFilas > 0){
            for(int i = numFilas - 1; i >= 0; i--){
                modeloTablaClientes.removeRow(i);
            }
        }
    }
       
       public void limpiardatos(){
           txtnombre.setText("");
           txtdireccion.setText("");
           txtemail.setText("");
           txttelefono.setText("");
          
       }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelRound1 = new LIB.JPanelRound();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtnombre = new rojeru_san.rsfield.RSTextFull();
        txttelefono = new rojeru_san.rsfield.RSTextFull();
        txtemail = new rojeru_san.rsfield.RSTextFull();
        txtdireccion = new rojeru_san.rsfield.RSTextFull();
        jLabel7 = new javax.swing.JLabel();
        txtbuscarclientes = new rojeru_san.rsfield.RSTextFull();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaClientes.setModel(modeloTablaClientes);
        tablaClientes.setRowHeight(25);
        tablaClientes.setRowMargin(0);
        tablaClientes.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tablaClientes.getSelectionModel().addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event){
                    if(!event.getValueIsAdjusting() && (tablaClientes.getSelectedRow()>= 0) ){
                        int filaseleccionada = tablaClientes.getSelectedRow();
                        CClientes cliente = (CClientes) modeloTablaClientes.getValueAt(filaseleccionada, 0);
                        txtnombre.setText(cliente.getNombre());
                        txtdireccion.setText(cliente.getDireccion());
                        txtemail.setText(cliente.getEmail());
                        txttelefono.setText(cliente.getTelefono());
                        clienteseleccionado = cliente;

                    }
                }
            }   
        );
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        tablaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaClientesKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1080, 330));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Listado Clientes");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 219, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Buscar:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, 30));

        jPanelRound1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRound1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 6, true));
        jPanelRound1.setOpaque(true);
        jPanelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Control Clientes");
        jPanelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 219, 24));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 27));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Telefono :");
        jPanelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Email :");
        jPanelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 40, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Dirección:");
        jPanelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 30));

        btnguardar.setBackground(new java.awt.Color(102, 166, 54));
        btnguardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/diskette.png"))); // NOI18N
        btnguardar.setMnemonic('g');
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanelRound1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 120, 40));

        btneliminar.setBackground(new java.awt.Color(247, 94, 24));
        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/garbage.png"))); // NOI18N
        btneliminar.setMnemonic('e');
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        jPanelRound1.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 120, 40));

        jButton1.setBackground(new java.awt.Color(81, 161, 194));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/brush.png"))); // NOI18N
        jButton1.setMnemonic('c');
        jButton1.setText(" Limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelRound1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 130, -1));

        txtnombre.setForeground(new java.awt.Color(0, 0, 0));
        txtnombre.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtnombre.setBotonColor(new java.awt.Color(255, 102, 102));
        txtnombre.setMayusculas(true);
        txtnombre.setPlaceholder("Cliente");
        jPanelRound1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 350, 40));

        txttelefono.setForeground(new java.awt.Color(0, 0, 0));
        txttelefono.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txttelefono.setBotonColor(new java.awt.Color(255, 102, 102));
        txttelefono.setPlaceholder("Telefono");
        txttelefono.setSoloNumeros(true);
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        jPanelRound1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 170, 40));

        txtemail.setForeground(new java.awt.Color(0, 0, 0));
        txtemail.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtemail.setBotonColor(new java.awt.Color(255, 102, 102));
        txtemail.setMayusculas(true);
        txtemail.setPlaceholder("Email");
        jPanelRound1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 140, -1));

        txtdireccion.setForeground(new java.awt.Color(0, 0, 0));
        txtdireccion.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtdireccion.setBotonColor(new java.awt.Color(255, 102, 102));
        txtdireccion.setMayusculas(true);
        txtdireccion.setPlaceholder("Direccion");
        jPanelRound1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 350, -1));

        add(jPanelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 450, 270));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(128, 128, 131));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/customer.png"))); // NOI18N
        jLabel7.setText("Clientes");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 390, -1));

        txtbuscarclientes.setForeground(new java.awt.Color(0, 0, 0));
        txtbuscarclientes.setMayusculas(true);
        txtbuscarclientes.setPlaceholder("Buscar");
        txtbuscarclientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarclientesKeyReleased(evt);
            }
        });
        add(txtbuscarclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       
        try {
            String nombre = txtnombre.getText();
            String direccion = txtdireccion.getText();
            String telefono = txttelefono.getText();
            String email = txtemail.getText();
            
            
               int cuentaFilasSeleccionadas = tablaClientes.getSelectedRowCount(); 
           

               
               
        if (cuentaFilasSeleccionadas == 1) {
             bdclt.actualizarProveedor(clienteseleccionado, nombre, direccion, telefono, email);
                        SuccessAlert suc = new SuccessAlert(new JFrame(),true );
                       suc.msj1.setText("!HECHO¡");
                       suc.msj2.setText("SE HAN ACTUALIZADO LOS DATOS");
                       suc.msj3.setText("");
                       suc.setVisible(true);
            cargarModeloTabla();
            limpiardatos();
            
        }else if(cuentaFilasSeleccionadas == 0){
             CClientes clientes = new CClientes(0,nombre,"","",direccion,telefono,email);
            
             if (txtnombre.getText().isEmpty()) {
            ErrorAlert e = new ErrorAlert(new JFrame(), true);
            e.msj1.setText("HAY UN PLOBLEMA ");
            e.msj2.setText("EL NOMBRE NO PUEDE ESTAR VACIO");
            e.msj3.setText("");
            e.setVisible(true);
            
            }else if(txtdireccion.getText().isEmpty()){
            ErrorAlert e = new ErrorAlert(new JFrame(), true);
            e.msj1.setText("HAY UN PLOBLEMA ");
            e.msj2.setText("LA DIRECCION NO PUEDE ESTAR VACIA");
            e.msj3.setText("");
            e.setVisible(true);
            }else if(txtemail.getText().isEmpty()){
            ErrorAlert e = new ErrorAlert(new JFrame(), true);
            e.msj1.setText("HAY UN PLOBLEMA ");
            e.msj2.setText("EMAIL VACIO");
            e.msj3.setText("");
            e.setVisible(true);
            }else{
             
             bdclt.insert(clientes);
               
            SuccessAlert suc = new SuccessAlert(new JFrame(),true );
                       suc.msj1.setText("!HECHO¡");
                       suc.msj2.setText("SE HAN GUARDADO LOS CAMBIOS");
                       suc.msj3.setText("");
                       suc.setVisible(true);
                       
            cargarModeloTabla();
            limpiardatos();
                    }
        }
        } catch (SQLException ex) {
            Logger.getLogger(pnlVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
//       int cuentaFilasSeleccionadas = tablaClientes.getSelectedRowCount(); 
//        System.out.println("Hay seleccionadas: " + cuentaFilasSeleccionadas + " filas");
//
//        if (cuentaFilasSeleccionadas == 1) { 
//            btnguardar.setText("Editar");
//            btnguardar.setBackground(new java.awt.Color(255,252,51));
//            btnguardar.setSize(69, 23);
//            btneliminar.setVisible(true);
//
//            int indiceFilaSeleccionada = tablaClientes.getSelectedRow(); 
//            System.out.println("Fila seleccionada: " + indiceFilaSeleccionada);
//        } 
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        try {
            
               int cuentaFilasSeleccionadas = tablaClientes.getSelectedRowCount();
            if (cuentaFilasSeleccionadas == 0) {
            ErrorAlert e = new ErrorAlert(new JFrame(), true);
            e.msj1.setText("HAY UN PLOBLEMA ");
            e.msj2.setText("DEBE SELECCIONAR UN FILA");
            e.msj3.setText("");
            e.setVisible(true);
            }else{
                    WarningAlert w = new WarningAlert(new JFrame(), true);
                w.msj1.setText("ESTAS SEGURO°°°");
                w.msj2.setText("¡DESEAS ELIMINARLO?");
                w.msj3.setText("");
                w.setVisible(true);
                      
                 if (w.hecho) {
            bdclt.eliminarcliente(clienteseleccionado);
            cargarModeloTabla();
            limpiardatos();
            SuccessAlert succ  = new SuccessAlert(new Frame(), true);
                succ.msj1.setText("CORRECTO !!!");
                succ.msj2.setText("SE HA ELIMINADO");
                succ.msj3.setText("");
                succ.setVisible(true);
                        }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(pnlVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void tablaClientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClientesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaClientesKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
limpiardatos();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txtbuscarclientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarclientesKeyReleased
     limpiarTabla();
         String criterio = txtbuscarclientes.getText();
       ArrayList<CClientes>ListarClientes = bdclt.ObtenerClientesporCriterio(criterio);
      int numerosUsusarios = ListarClientes.size();
      modeloTablaClientes.setNumRows(numerosUsusarios);
        for (int i = 0; i < numerosUsusarios; i++) {
            CClientes cliente = ListarClientes.get(i);
            String nombre = cliente.getNombre();
            String direccion = cliente.getDireccion();
            String telefono = cliente.getTelefono();
            String email = cliente.getEmail();
            
            
            modeloTablaClientes.setValueAt(cliente, i, 0);
            modeloTablaClientes.setValueAt(direccion, i, 1);
            modeloTablaClientes.setValueAt(telefono, i, 2);
            modeloTablaClientes.setValueAt(email, i, 3);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarclientesKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private LIB.JPanelRound jPanelRound1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private rojeru_san.rsfield.RSTextFull txtbuscarclientes;
    private rojeru_san.rsfield.RSTextFull txtdireccion;
    private rojeru_san.rsfield.RSTextFull txtemail;
    private rojeru_san.rsfield.RSTextFull txtnombre;
    private rojeru_san.rsfield.RSTextFull txttelefono;
    // End of variables declaration//GEN-END:variables
}
