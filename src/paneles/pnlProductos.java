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
import BD.Categorias;
import BD.Producto;
import BD.Unidades;

import Controller.CProductos;
import Controller.CUnidades;
import Controller.CCategorias;
import java.awt.Frame;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author najera10
 */
public class pnlProductos extends javax.swing.JInternalFrame {

    DefaultComboBoxModel <CCategorias> modeloCategorias;
    DefaultComboBoxModel <CUnidades> modeloUnidades;
    CProductos   productoseleccionado = null ;
     private TableRowSorter trsFiltro;
    DefaultTableModel modeloTablaProductos = new DefaultTableModel(){
           public boolean isCellEditable(int filas, int columnas){
            if(columnas==3){
                return true;
            }else{
                return false;
            }
    }
        
        
        
    };
    Categorias categoria = new Categorias();
    Unidades unidad = new Unidades();
    Producto product = new Producto();
   
    
    
    public pnlProductos() {
         modeloCategorias = new DefaultComboBoxModel<CCategorias>();
         modeloUnidades = new DefaultComboBoxModel<CUnidades>(); 
        initComponents();
        cargarModeloCat();
        cargarModeloUni();
        CargarColunmas();
       if( pnlAlmacen.click){
         CargarModeloTabla();
         } 
        CargarModeloTabla();
         TableColumnModel columna=tblProdutos.getColumnModel();
       columna.getColumn(0).setPreferredWidth(700);
        
  
        
    }
    
    
    
      public void cargarModeloCat(){
            ArrayList<CCategorias> listaCategorias;
        listaCategorias = categoria.ObtenerCategorias();
        modeloCategorias.addElement(new CCategorias("0", "Selecciona opcion","",1));

        for(CCategorias categoria : listaCategorias){
            modeloCategorias.addElement(categoria);
        }
    }
      
       public void cargarModeloUni(){
            ArrayList<CUnidades> listarUnidades;
        listarUnidades = unidad.obtenerUnidad();
        modeloUnidades.addElement(new CUnidades(0, "Selecciona opcion"));
        for(CUnidades unidades : listarUnidades){
            modeloUnidades.addElement(unidades);
    }
       }
       
        private void cargarProducto(ImageIcon icono){
        
        //Redimensión de imagen para ajustarla al tamaño del JLabel.
        Image imgProd = icono.getImage();
        int anchoEtiqueta = lblImagenArticulo.getWidth(); //Obtiene ancho de la imagen
        int altoEtiqueta = lblImagenArticulo.getHeight(); //Obtiene alto de la imagen
                
        //Se crea un nuevo objeto Image con la imagen redimensionada.
        Image imgRedimensionada = imgProd.getScaledInstance(anchoEtiqueta, altoEtiqueta, Image.SCALE_DEFAULT);
               
        //Se crea un nuevo objeto ImageIcon a partir de la imagen redimensionada.
        ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);
        
        lblImagenArticulo.setIcon(iconRedimensionado);
//        String clave = producto.getIdProducto();
//        String nombre = producto.getNomProducto();
//        String descripcion = producto.getDescProducto();
//        double stockRequerido = producto.getStockProducto();
//        String unidad = producto.getUnidadProducto();
//        double precioCompra = producto.getPrecioCompraProducto();
//        double precioVenta = producto.getPrecioVentaProducto();
        
//        campoClave.setText(clave);
//        campoNombre.setText(nombre);
//        campoDesc.setText(descripcion);
//        campoStock.setText(String.valueOf(stockRequerido));
//        comboUnidades.setSelectedItem(unidad);
//        campoPrecioCompra.setText(String.valueOf(precioCompra));
//        campoPrecioVenta.setText(String.valueOf(precioVenta));
//        
//        campoClave.setEnabled(false);
//        campoNombre.setEnabled(false);
    }
        
     private  void CargarColunmas(){
        modeloTablaProductos.addColumn("Nombre");
        modeloTablaProductos.addColumn("Stock");
        modeloTablaProductos.addColumn("Codigo");
        }
        
         public void CargarModeloTabla(){
      
//    limpiarTabla();
       ArrayList<CProductos>ListarProductos = product.ObtenerCategorias();
      int numerosProductos = ListarProductos.size();
      modeloTablaProductos.setNumRows(numerosProductos);
        for (int i = 0; i < numerosProductos; i++) {
            CProductos productos = ListarProductos.get(i);
            String nombre = productos.getNomProducto();
            String desc = productos.getDescProducto();
            String codigo = productos.getCodigo();
            double stock = productos.getStockProducto();
            
            
            modeloTablaProductos.setValueAt(productos, i, 0);
            modeloTablaProductos.setValueAt(stock, i, 1);
            modeloTablaProductos.setValueAt(codigo, i, 2);
        
           

            
        }
         
    }    
        public void limpiar(){
        txtnombre.setText("");
        txtdescripcion.setText("");
        txtstock.setText("0.0");
        txtpreciocompra.setText("0.0");
        txtprecioventa.setText("0.0");
        txtcodigo.setText("");
        cmbcategorias.setSelectedIndex(0);
        cmbunidades.setSelectedIndex(0);
         tblProdutos.clearSelection ();
         }
        public  void limpiarTabla(){
        int numFilas = modeloTablaProductos.getRowCount();
        if(numFilas > 0){
            for(int i = numFilas - 1; i >= 0; i--){
                modeloTablaProductos.removeRow(i);
            }
        }
    }
         public void filtroBusqueda(JTextField txt) {
        trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanelRound1 = new LIB.JPanelRound();
        Nunidad = new javax.swing.JButton();
        Ncategoria = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblImagenArticulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbcategorias = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbunidades = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btneliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtnombre = new rojeru_san.rsfield.RSTextFull();
        txtstock = new rojeru_san.rsfield.RSTextFull();
        txtpreciocompra = new rojeru_san.rsfield.RSTextFull();
        txtprecioventa = new rojeru_san.rsfield.RSTextFull();
        txtcodigo = new rojeru_san.rsfield.RSTextFull();
        txtdescripcion = new rojeru_san.rsfield.RSTextFull();
        txtbuscar = new rojerusan.RSMetroTextFullPlaceHolder();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProdutos.setModel(modeloTablaProductos);
        tblProdutos.setRowHeight(25);
        tblProdutos.setRowMargin(0);
        tblProdutos.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tblProdutos.getSelectionModel().addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event){
                    if(!event.getValueIsAdjusting() && (tblProdutos.getSelectedRow()>= 0) ){
                        int filaseleccionada = tblProdutos.getSelectedRow();
                        CProductos producto = (CProductos) modeloTablaProductos.getValueAt(filaseleccionada, 0);

                        String cadena = String.valueOf(producto.getPrecioCompraProducto());
                        String cadena1 = String.valueOf(producto.getPrecioVentaProducto());
                        String stok = String.valueOf(producto.getStockProducto());
                        txtnombre.setText(producto.getNomProducto());
                        txtdescripcion.setText(producto.getDescProducto());
                        txtpreciocompra.setText(cadena);
                        txtprecioventa.setText(cadena1);
                        txtcodigo.setText(producto.getCodigo());
                        txtstock.setText(stok);
                        modeloCategorias.setSelectedItem(producto.getCategorra());
                        modeloUnidades.setSelectedItem(producto.getUnidad());
                        productoseleccionado = producto;

                    }
                }
            }   
        );
        jScrollPane1.setViewportView(tblProdutos);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 550, 260));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Listado de Productos");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, 34));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(128, 128, 131));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/taxes.png"))); // NOI18N
        jLabel12.setText("Articulos");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 550, 130));

        jPanelRound1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRound1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
        jPanelRound1.setOpaque(true);
        jPanelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nunidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Nunidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/box.png"))); // NOI18N
        Nunidad.setText("Nueva Unidad");
        Nunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NunidadActionPerformed(evt);
            }
        });
        jPanelRound1.add(Nunidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        Ncategoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Ncategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/list.png"))); // NOI18N
        Ncategoria.setText("Nueva Categoria");
        Ncategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NcategoriaActionPerformed(evt);
            }
        });
        jPanelRound1.add(Ncategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagenArticulo.setBackground(new java.awt.Color(153, 153, 153));
        lblImagenArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImagenArticulo.setMaximumSize(new java.awt.Dimension(50, 50));
        lblImagenArticulo.setMinimumSize(new java.awt.Dimension(50, 50));
        lblImagenArticulo.setName(""); // NOI18N
        lblImagenArticulo.setPreferredSize(new java.awt.Dimension(50, 50));
        lblImagenArticulo.setVerifyInputWhenFocusTarget(false);
        lblImagenArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImagenArticuloMousePressed(evt);
            }
        });
        jPanel2.add(lblImagenArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        jPanelRound1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombre del Articulo *");
        jPanelRound1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 170, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Categoria:");
        jPanelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 90, 30));

        cmbcategorias.setModel(modeloCategorias);
        jPanelRound1.add(cmbcategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 160, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Stock *");
        jPanelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 50, 30));

        jLabel11.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Foto");
        jPanelRound1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 130, 20));

        cmbunidades.setModel(modeloUnidades);
        jPanelRound1.add(cmbunidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 130, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Precio Venta *");
        jPanelRound1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Unidad:");
        jPanelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 70, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Precio Compra *");
        jPanelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 120, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Descripcion *");
        jPanelRound1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 110, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Código *");
        jPanelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 60, 30));

        btneliminar.setBackground(new java.awt.Color(247, 94, 24));
        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/garbage.png"))); // NOI18N
        btneliminar.setMnemonic('e');
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        jPanelRound1.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 140, 40));

        jButton2.setBackground(new java.awt.Color(81, 161, 194));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/brush.png"))); // NOI18N
        jButton2.setMnemonic('c');
        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelRound1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 140, 40));

        jButton1.setBackground(new java.awt.Color(102, 166, 54));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/diskette.png"))); // NOI18N
        jButton1.setMnemonic('g');
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelRound1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 130, 40));

        txtnombre.setForeground(new java.awt.Color(0, 0, 0));
        txtnombre.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtnombre.setBotonColor(new java.awt.Color(255, 102, 102));
        txtnombre.setMayusculas(true);
        txtnombre.setPlaceholder("Nombre");
        jPanelRound1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 320, -1));

        txtstock.setForeground(new java.awt.Color(0, 0, 0));
        txtstock.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtstock.setBotonColor(new java.awt.Color(255, 102, 102));
        txtstock.setDoubleBuffered(true);
        txtstock.setPlaceholder("0.0");
        txtstock.setSoloNumeros(true);
        jPanelRound1.add(txtstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 140, -1));

        txtpreciocompra.setForeground(new java.awt.Color(0, 0, 0));
        txtpreciocompra.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtpreciocompra.setBotonColor(new java.awt.Color(255, 102, 102));
        txtpreciocompra.setPlaceholder("0.0");
        txtpreciocompra.setSoloNumeros(true);
        jPanelRound1.add(txtpreciocompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 130, -1));

        txtprecioventa.setForeground(new java.awt.Color(0, 0, 0));
        txtprecioventa.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtprecioventa.setBotonColor(new java.awt.Color(255, 102, 102));
        txtprecioventa.setPlaceholder("0.0");
        txtprecioventa.setSoloNumeros(true);
        jPanelRound1.add(txtprecioventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 160, -1));

        txtcodigo.setForeground(new java.awt.Color(0, 0, 0));
        txtcodigo.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtcodigo.setBotonColor(new java.awt.Color(255, 102, 102));
        txtcodigo.setPlaceholder("Codigo");
        jPanelRound1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 150, -1));

        txtdescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtdescripcion.setBordeColorFocus(new java.awt.Color(153, 153, 255));
        txtdescripcion.setBotonColor(new java.awt.Color(255, 102, 102));
        txtdescripcion.setMayusculas(true);
        txtdescripcion.setPlaceholder("Descripcion");
        jPanelRound1.add(txtdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 310, -1));

        jPanel3.add(jPanelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 490, 540));

        txtbuscar.setForeground(new java.awt.Color(0, 0, 0));
        txtbuscar.setBorderColor(new java.awt.Color(102, 102, 255));
        txtbuscar.setBotonColor(new java.awt.Color(102, 0, 255));
        txtbuscar.setMayusculas(true);
        txtbuscar.setPlaceholder("Ingresa busqueda ...");
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });
        jPanel3.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 430, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1080, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
            double stock ;
            double previo_venta ;
            double precio_compra;
        try {
            
            
            if (txtprecioventa.getText().isEmpty()){
            previo_venta=0.0;
            }else{
             previo_venta = Double.parseDouble(txtprecioventa.getText());
            }
          if (txtstock.getText().isEmpty()){
            stock =0.0;
            }else{
             stock =Double.parseDouble(txtstock.getText());
            } if (txtpreciocompra.getText().isEmpty()){
            precio_compra=0.0;
            }else{
             precio_compra = Double.parseDouble(txtpreciocompra.getText());
                
            }
         
            String codigo = txtcodigo.getText();
            String descripcion  = txtdescripcion.getText();
            String nombre = txtnombre.getText();
            int cuentaFilasSeleccionadas = tblProdutos.getSelectedRowCount();
            String opcionc = cmbcategorias.getSelectedItem().toString();
            String opcionu = cmbunidades.getSelectedItem().toString();
            
          
            
//                      
        if (cuentaFilasSeleccionadas == 1) {
            
            String categoriaselaccionada =  productoseleccionado.getCategorra();
            String categorianueva  = cmbcategorias.getSelectedItem().toString();
            String unidadseccionada = productoseleccionado.getUnidad();
            String unidadnueva = cmbunidades.getSelectedItem().toString();
            
            int categoriaid = 0;
            int unidadaid = 0;
            if (categoriaselaccionada.equals(categorianueva)) {
              categoriaid = productoseleccionado.getIdCategoria();
            }else{
                CCategorias categoria = (CCategorias)cmbcategorias.getSelectedItem();
                 categoriaid = Integer.parseInt(categoria.getIdcategoria());
            }
                  if (unidadseccionada.equals(unidadnueva)) {
              unidadaid = productoseleccionado.getIdunidadProducto();
            }else{
                CUnidades unidad = (CUnidades)cmbunidades.getSelectedItem();
                 unidadaid = unidad.getIdunidad();
            }
         
         //   System.out.println(productoseleccionado.getCategorra());
    //    CCategorias categor = new CCategorias("",, descripcion, WIDTH)
        product.actualizar(productoseleccionado, nombre, descripcion, stock, imgArticleFile, unidadaid, precio_compra, previo_venta,categoriaid, codigo);
            limpiar();
            CargarModeloTabla(); 
            SuccessAlert suc = new SuccessAlert(new JFrame(),true );
                       suc.msj1.setText("!HECHO¡");
                       suc.msj2.setText("SE HAN ACTUALIZADO LOS DATOS");
                       suc.msj3.setText("");
                       suc.setVisible(true);
           
        }else{
         
        CCategorias categoria = (CCategorias)cmbcategorias.getSelectedItem();
            CUnidades unidad = (CUnidades)cmbunidades.getSelectedItem();
             int idcategoria = Integer.parseInt(categoria.getIdcategoria());
            
            if(opcionc.equals("Selecciona opcion") || opcionu.equals("Selecciona opcion") ){
                
                ErrorAlert e = new ErrorAlert(new JFrame(), true);
                    e.msj1.setText("HAY UN PLOBLEMA ");
                    e.msj2.setText("LE FALTA SELECCIONAR UNA CATEGORIA \n O UNIDAD");
                    e.msj3.setText("");
                    e.setVisible(true);

                
            }else if(opcionc.equals("Selecciona opcion")){
             ErrorAlert e = new ErrorAlert(new JFrame(), true);
                    e.msj1.setText("HAY UN PLOBLEMA ");
                    e.msj2.setText("LE FALTA  SELECCIONAR UNA CATEGORIA \n O UNIDAD");
                    e.msj3.setText("");
                    e.setVisible(true);
            }else if(opcionu.equals("Selecciona opcion")){
          //  JOptionPane.showMessageDialog(null, "Debe seleccionar Unidad o Alguna fila para editar");  
                  ErrorAlert e = new ErrorAlert(new JFrame(), true);
                    e.msj1.setText("HAY UN PLOBLEMA ");
                    e.msj2.setText("LE FALTA  SELECCIONAR UNA CATEGORIA \n O UNIDAD");
                    e.msj3.setText("");
                    e.setVisible(true);
            }else if(nombre.equals("")){
          //  JOptionPane.showMessageDialog(rootPane, "El campo nombre no puede estar vacio");
                ErrorAlert e = new ErrorAlert(new JFrame(), true);
                    e.msj1.setText("HAY UN PLOBLEMA ");
                    e.msj2.setText("LE FALTA SELECCIONAR UNA CATEGORIA \n O UNIDAD");
                    e.msj3.setText("");
                    e.setVisible(true);
            }else{
            
            
          if (imgArticleFile == null){
//            JOptionPane.showMessageDialog(null, "No has eleccionada una imagen");
            File file = new File(System.getProperty("user.dir")+"/src/img1/nimages.png");
            CProductos productos = new CProductos( 0,  nombre, descripcion,  stock, file ,unidad.getIdunidad(),  precio_compra, previo_venta,idcategoria,codigo,"","","");
            product.insertar(productos);
            
            SuccessAlert suc = new SuccessAlert(new JFrame(),true );
                       suc.msj1.setText("!HECHO¡");
                       suc.msj2.setText("SE HAN GUARDADO LOS CAMBIOS");
                       suc.msj3.setText("");
                       suc.setVisible(true);
            
            limpiar();
            CargarModeloTabla();
            }else{
            CProductos productos = new CProductos( 0,  nombre, descripcion,  stock,  imgArticleFile,unidad.getIdunidad(),  precio_compra, previo_venta,idcategoria,codigo,"","","");
            product.insertar(productos);
            //JOptionPane.showMessageDialog(null, "Registro realizado");
            limpiar();
            CargarModeloTabla();
            
             SuccessAlert suc = new SuccessAlert(new JFrame(),true );
                       suc.msj1.setText("!HECHO¡");
                       suc.msj2.setText("SE HAN GUARDADO LOS CAMBIOS");
                       suc.msj3.setText("");
                       suc.setVisible(true);
            }  
            }
            
        }
            
           
            
            

        } catch (SQLException ex) {
            Logger.getLogger(pnlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
try {
            
             int cuentaFilasSeleccionadas = tblProdutos.getSelectedRowCount();
            if (cuentaFilasSeleccionadas == 0) {
                ErrorAlert e = new ErrorAlert(new JFrame(), true);
            e.msj1.setText("HAY UN PLOBLEMA ");
            e.msj2.setText("DEBE SELECCIONAR UNA FILA");
            e.msj3.setText("");
            e.setVisible(true);
            }else{
                     
                
                WarningAlert w = new WarningAlert(new JFrame(), true);
                w.msj1.setText("ESTAS SEGURO°°°");
                w.msj2.setText("¡DESEAS ELIMINARLO?");
                w.msj3.setText("");
                w.setVisible(true);

            if (w.hecho) {
 ///---->   
                product.eliminar(productoseleccionado);
///limpiar 
                 limpiar();///->>>
                 ///cargardatos a tabla
                CargarModeloTabla();///->>>
                SuccessAlert succ  = new SuccessAlert(new Frame(), true);
                succ.msj1.setText("CORRECTO !!!");
                succ.msj2.setText("SE HA ELIMINADO");
                succ.msj3.setText("");
                succ.setVisible(true);
            }
            
                
                
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(pnlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }//GEN-LAST:event_btneliminarActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MousePressed
File imgArticleFile;
    private void lblImagenArticuloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenArticuloMousePressed
        
       if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
           
           JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos de imagen jpg, gif o png", "jpg", "gif", "png");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            int anchoImagen = lblImagenArticulo.getWidth();
            int altoImagen = lblImagenArticulo.getHeight();

            imgArticleFile = chooser.getSelectedFile();
           
            ImageIcon icono = new ImageIcon(imgArticleFile.getAbsolutePath());
            Image imagen = icono.getImage();
            Image imagenRedimensionada = imagen.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_DEFAULT);

            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

            lblImagenArticulo.setIcon(iconoRedimensionado);
        }
         }else{
           imgArticleFile = null;
       }
        
    }//GEN-LAST:event_lblImagenArticuloMousePressed

    private void NcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NcategoriaActionPerformed
    JTextField nombre = new JTextField("");
    JTextArea descripcion = new JTextArea("");
    JPanel panel = new JPanel(new GridLayout(0, 1));
    Border bordejpanel = new TitledBorder(new EtchedBorder(), "Datos Categoria");
    panel.add(new JLabel("Nombre:"));
    panel.add(nombre);
    panel.add(new JLabel("Descripción:"));
    panel.add(descripcion);
    
   int result = JOptionPane.showConfirmDialog(null, panel, "Registro de Categoria",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        
        try {
              if (nombre.getText().equals("") || descripcion.getText().equals("") ){
                ErrorAlert e = new ErrorAlert(new JFrame(), true);
                    e.msj1.setText("HAY UN PLOBLEMA ");
                    e.msj2.setText("LOS CAMPOS NO PUEDEN ESTAR VACIOS");
                    e.msj3.setText("");
                    e.setVisible(true);     
            JOptionPane.showConfirmDialog(null, panel, "Registro de Categoria",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            }else{
            CCategorias catego = new CCategorias("0",nombre.getText().toUpperCase(),descripcion.getText().toUpperCase(),1);
            categoria.insert(catego);
            cmbcategorias.removeAllItems();
            cargarModeloCat();
              }
        } catch (SQLException ex) {
            Logger.getLogger(pnlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } else {
        System.out.println("Cancelled");
    }   
    
    }//GEN-LAST:event_NcategoriaActionPerformed
public void  limpiarProveedor(){
    cmbcategorias.removeAllItems();
}
    private void NunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NunidadActionPerformed
        JTextField nombre = new JTextField("");
  
    JPanel panel = new JPanel(new GridLayout(0, 1));
    Border bordejpanel = new TitledBorder(new EtchedBorder(), "Datos Unidad");
    panel.add(new JLabel("Nombre:"));
    panel.add(nombre);
    
    
   int result = JOptionPane.showConfirmDialog(null, panel, "Registro de Unidad",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        
        try {
             if (nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "el campo no puede estar vacio");
            JOptionPane.showConfirmDialog(null, panel, "Registro de Unidad",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
             }else{
            CUnidades unida = new CUnidades(0,nombre.getText().toUpperCase());
           unidad.insert(unida);
           cmbunidades.removeAllItems();
           cargarModeloUni();
        }
        } catch (SQLException ex) {
            Logger.getLogger(pnlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } else {
        System.out.println("Cancelled");
    }    
    }//GEN-LAST:event_NunidadActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    limpiar();   
   
    
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// txtbuscar.addKeyListener(new KeyAdapter() {
//            public void keyReleased(final KeyEvent e) {
//                String cadena = (txtbuscar.getText());
//                txtbuscar.setText(cadena);
//                repaint();
//                filtroBusqueda(txtbuscar);
//            }
//        });
//        trsFiltro = new TableRowSorter(tblProdutos.getModel());
//        tblProdutos.setRowSorter(trsFiltro);        // TODO add your handling code here:
        
    String cadena  = txtbuscar.getText();
    
    ArrayList<CProductos> ListarProductos = product.ObtenerCategoriasCriterio(cadena);
    int numeroProductos = ListarProductos.size();
    modeloTablaProductos.setNumRows(numeroProductos);
    
        for (int i = 0; i < numeroProductos ; i++) {
          CProductos producto = ListarProductos.get(i); 
            
            String nombre = producto.getNomProducto();
            String desc = producto.getDescProducto();
            String codigo = producto.getCodigo();
            double stock = producto.getStockProducto();
            modeloCategorias.setSelectedItem(producto.getCategorra());
                modeloUnidades.setSelectedItem(producto.getUnidad());
            
            modeloTablaProductos.setValueAt(producto, i, 0);
            modeloTablaProductos.setValueAt(stock, i, 1);
            modeloTablaProductos.setValueAt(codigo, i, 2);
          
        }
    
        
    
    
    }//GEN-LAST:event_txtbuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ncategoria;
    private javax.swing.JButton Nunidad;
    private javax.swing.JButton btneliminar;
    private javax.swing.JComboBox cmbcategorias;
    private javax.swing.JComboBox cmbunidades;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private LIB.JPanelRound jPanelRound1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagenArticulo;
    private javax.swing.JTable tblProdutos;
    private rojerusan.RSMetroTextFullPlaceHolder txtbuscar;
    private rojeru_san.rsfield.RSTextFull txtcodigo;
    private rojeru_san.rsfield.RSTextFull txtdescripcion;
    private rojeru_san.rsfield.RSTextFull txtnombre;
    private rojeru_san.rsfield.RSTextFull txtpreciocompra;
    private rojeru_san.rsfield.RSTextFull txtprecioventa;
    private rojeru_san.rsfield.RSTextFull txtstock;
    // End of variables declaration//GEN-END:variables
}
