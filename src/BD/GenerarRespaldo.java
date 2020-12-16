/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Controller.CCategorias;
import Controller.CClientes;
import Controller.CCorte;
import Controller.CDetalle_ingresos;
import Controller.CDetalle_ventas;
import Controller.CEmpresa;
import Controller.CIngreso;
import Controller.CPermisos;
import Controller.CProductos;
import Controller.CProveedores;
import Controller.CUnidades;
import Controller.CUsuario_Permiso;
import Controller.CUsuarios;
import Controller.CVenta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import jxl.write.DateTime;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Najera10
 */
public class GenerarRespaldo {
    
    
    String RESPALDO = "INSERT INTO nomtab values";
    Clientes cl = new Clientes();
    Usuarios usr = new Usuarios();
    Proveedores prov = new Proveedores();
    Producto prod = new Producto();
    Categorias cat = new Categorias();
    DetalleIngreso deta_ing = new DetalleIngreso();
    DetalleVenta deta_ven = new DetalleVenta();
    Ingreso ingres = new Ingreso();
    Permisos permiso = new Permisos();
    Unidades unidad  = new Unidades();
    Venta venta = new Venta();
    Empresa empresa = new Empresa();
    CorteDia  corte = new CorteDia();
    
    
    public String Remplazar(String nombreT){
        String tex_insert =  RESPALDO.replace("nomtab", nombreT);
        return tex_insert;
    }
    
    
    public void  Resp_Clientes(File ruta) throws IOException{
        
       
      ArrayList<CClientes> clientes  =  cl.ObtenerUsuarios();
      ArrayList<CUsuarios> usuarios  =  usr.ObtenerUsuarios();
      ArrayList<CProveedores> proveedor  =  prov.ObtenerProveedores();
      ArrayList<CProductos> produc = prod.ObtenerCategorias();
      ArrayList<CCategorias> categoria  =cat.ObtenerCategorias();
      ArrayList<CDetalle_ingresos> detalle_ingreso  =deta_ing.ObtenerUsuarios();
      ArrayList<CDetalle_ventas> detalle_ventas  =deta_ven.ObtenerDetalleVentas();
      ArrayList<CIngreso> ingresos =ingres.ObtenerIngresos();
      ArrayList<CPermisos> permisos = permiso.ObtenerPermisos();
      ArrayList<CUnidades> unidades = unidad.obtenerUnidad();
      ArrayList<CVenta> ventas = venta.ObtenerVentas();
      ArrayList<CUsuario_Permiso> permisosu  = permiso.ObtenerPermisosGlobal();
      ArrayList<CEmpresa> emp  = empresa.ObtenerEmpresa();
      ArrayList<CCorte> CorteDia  = corte.ObtenerCorte();

      
              
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");
        String historial = hourdateFormat.format(date);

    
        
            File file = ruta;
            
//        System.out.println(file);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
//           clientes
          for (int i = 0; i < clientes.size(); i++) {
          int id =    clientes.get(i).getIdcliente();
          String nombre = clientes.get(i).getNombre();
          String tipo_documento = clientes.get(i).getTipo_documento();
          String num_documento= clientes.get(i).getNum_documento();
          tipo_documento = "NULL";
          num_documento ="NULL";
          String direccion = clientes.get(i).getDireccion();
          String telefono = clientes.get(i).getTelefono();
          String email = clientes.get(i).getEmail();
          String variable = this.Remplazar("clientes") +"("+ id+",'"+nombre+"',"+tipo_documento+","+num_documento+",'"+direccion+"','"+telefono+"','"+email+"');";
           
           bw.write(variable);
           bw.newLine();
         
          }
          
          bw.newLine();
//          usuario
         for (int i = 0; i < usuarios.size(); i++) {
         int id =    usuarios.get(i).getId_usuario();
          String nombre = usuarios.get(i).getNombre();
          String tipo_documento = usuarios.get(i).getTipo_documento();
          String num_documento= usuarios.get(i).getNum_documento();
          tipo_documento = "NULL";
          num_documento ="NULL";
          String direccion = usuarios.get(i).getDireccion();
          String telefono = usuarios.get(i).getTelefono();
          String email = usuarios.get(i).getEmail();
          String cargo = usuarios.get(i).getCargo();
          String login = usuarios.get(i).getLogin();
          String clave = usuarios.get(i).getClave();
          String imagen = usuarios.get(i).getImagen();
          String condicion = usuarios.get(i).getCodicion();
   

        
         String variable = this.Remplazar("usuario") +"("+ id+",'"+nombre+"','"+tipo_documento+"','"+num_documento+"','"+direccion+"','"+telefono+"','"+email+"'"+",'"+cargo+"','"+login+"','"+clave+"','"+imagen+"','"+condicion+"');";
         bw.write(variable);
         bw.newLine();
         }
         bw.newLine();
//         proveedores
              for (int i = 0; i < proveedor.size(); i++) {
         int id =    proveedor.get(i).getIdproveedor();
          String nombre = proveedor.get(i).getNombre();
          String tipo_documento = proveedor.get(i).getTipo_documento();
          String num_documento= proveedor.get(i).getNum_documento();
          tipo_documento = "NULL";
          num_documento ="NULL";
          String direccion = proveedor.get(i).getDireccion();
          String telefono = proveedor.get(i).getTelefono();
          String email = proveedor.get(i).getEmail();
          
         String variable = this.Remplazar("proveedores") +"("+ id+",'"+nombre+"',"+tipo_documento+","+num_documento+",'"+direccion+"','"+telefono+"','"+email+"');";
         bw.write(variable);
         bw.newLine();
         }
         bw.newLine();
//         articulos
          for (int i = 0; i < produc.size(); i++) {
         int id =    produc.get(i).getIdProducto();
         int idcat = produc.get(i).getIdCategoria();
         int unidad = produc.get(i).getIdunidadProducto();
         String codigo = produc.get(i).getCodigo();
         String nombre = produc.get(i).getNomProducto();
         double stock= produc.get(i).getStockProducto();
         double precio_venta = produc.get(i).getPrecioVentaProducto();
         double precio_compra = produc.get(i).getPrecioCompraProducto();
         String descripcion =produc.get(i).getDescProducto();
         File imagen = produc.get(i).getFotoProducto();
         String condicion = produc.get(i).getCondicion();

         String variable = this.Remplazar("articulo") +"("+ id+","+idcat+","+unidad+",'"+codigo+"','"+nombre+"',"+stock+","+precio_venta+",'"+precio_compra+"','"+descripcion+"',"+imagen+",'"+condicion+"');";
         bw.write(variable);
         bw.newLine();
         }
         
         bw.newLine();
//         categorias
         for (int i = 0; i < categoria.size(); i++) {
             
          String id =    categoria.get(i).getIdcategoria();
          String nombre = categoria.get(i).getNombre();
          String descripcion = categoria.get(i).getDescripcion();
          int condicion= categoria.get(i).getEstado();
          
         String variable = this.Remplazar("categoria") +"("+id+",'"+nombre+"','"+descripcion+"',"+condicion+");";
         bw.write(variable);
         bw.newLine();
         }
         
         bw.newLine();
         for (int i = 0; i < detalle_ingreso.size(); i++) {
             
          int iddetalle =detalle_ingreso.get(i).getIddetalle_ingreso();
          Long idingreso =detalle_ingreso.get(i).getIdingreso();
          int idarticulo= detalle_ingreso.get(i).getIdarticulo();
          double cantidad = detalle_ingreso.get(i).getCantidad();
          double precio_compra = detalle_ingreso.get(i).getPrecioCompra();
          
         String variable = this.Remplazar("detalle_ingreso") +"("+iddetalle+","+idingreso+","+idarticulo+","+cantidad+","+precio_compra+");";
         bw.write(variable);
         bw.newLine();
         }
//         detalle_venta
          bw.newLine();
         for (int i = 0; i < detalle_ventas.size(); i++) {
             
          int iddetalle =detalle_ventas.get(i).getIdDetalleVenta();
          Long idventa =detalle_ventas.get(i).getIdVenta();
          String idarticulo= detalle_ventas.get(i).getIdProd();
          double cantidad = detalle_ventas.get(i).getCantidadVendida();
          double costo = detalle_ventas.get(i).getCostoxp();
          
         String variable = this.Remplazar("detalle_venta") +"("+iddetalle+","+idventa+","+idarticulo+","+cantidad+","+costo+");";
         bw.write(variable);
         bw.newLine();
         }
         
         
//         ingreso
          bw.newLine();
         for (int i = 0; i < ingresos.size(); i++) {
             
          int idingreso =ingresos.get(i).getIdingreso();
          int idproveedor =ingresos.get(i).getIdprovvedor();
          Date Fecha= ingresos.get(i).getFecha_hora();
          double total_compra = ingresos.get(i).getTotal_compra();
          String estado = ingresos.get(i).getEstado();
          
         String variable = this.Remplazar("ingreso") +"("+idingreso+","+idproveedor+","+Fecha+","+total_compra+","+estado+");";
         bw.write(variable);
         bw.newLine();
         }
         
//         permiso
           bw.newLine();
         for (int i = 0; i < permisos.size(); i++) {
             
          int idpermiso =permisos.get(i).getIdpermiso();
          String nombre =permisos.get(i).getNombre();
          
          
         String variable = this.Remplazar("permiso") +"("+idpermiso+",'"+nombre+"');";
         bw.write(variable);
         bw.newLine();
         }
//         unidad
              bw.newLine();
         for (int i = 0; i < unidades.size(); i++) {
             
          int idunidad =unidades.get(i).getIdunidad();
          String nombre =unidades.get(i).getNombre();
         String variable = this.Remplazar("unidad_medida") +"("+idunidad+",'"+nombre+"');";
         bw.write(variable);
         bw.newLine();
         }
         
        bw.newLine();
        for (int i = 0; i < ventas.size(); i++) {
             
          int idVenta =ventas.get(i).getIdVenta();
          int idcliente = ventas.get(i).getIdcliente();
          Timestamp fecha_hora = ventas.get(i).getFecha_hora();
          double montoVenta = ventas.get(i).getMontoVenta();
          String estado = ventas.get(i).getEstado();
          String idusuario = ventas.get(i).getIdusuario();
          
        String variable = this.Remplazar("venta") +"("+idVenta+","+idcliente+",'"+fecha_hora+"',"+montoVenta+",'"+estado+"',"+idusuario+");";
        bw.write(variable);
        bw.newLine();
         }
         bw.newLine();
         //USUARIO PERMISO 
         for (int i = 0; i < permisosu.size(); i++) {
             
          int iddetallep =permisosu.get(i).getId_usuario_permiso();
          int idusuario = permisosu.get(i).getId_usuario();
          int permiso = permisosu.get(i).getId_permiso();
             
             
             
        String variable = this.Remplazar("usuario_permiso") +"("+iddetallep+","+idusuario+",'"+permiso+"');";
        bw.write(variable);
        bw.newLine();
         }
         
         
         bw.newLine();
         
         for (int i = 0; i < emp.size(); i++) {
             
                int idempresa = emp.get(i).getIdempresa();
                String nombre = emp.get(i).getEmpresa();
                String direccion= emp.get(i).getDireccion();
                String telefono = emp.get(i).getTelefono();
                String correo = emp.get(i).getCorreo();
                String rfc = emp.get(i).getCorreo();
             
             
             
        String variable = this.Remplazar("empresa") +"("+idempresa+",'"+nombre+"','"+direccion+"','"+telefono+"','"+correo+"','"+rfc+"');";
        bw.write(variable);
        bw.newLine();
         }
         
          bw.newLine();
         
         for (int i = 0; i < CorteDia.size(); i++) {
             
             int id = CorteDia.get(i).getIdcorte();
             Timestamp fechahora = CorteDia.get(i).getFecha();
             int idusuario = CorteDia.get(i).getIdusuario();
             double total = CorteDia.get(i).getTotal();
             String estado = CorteDia.get(i).getEstado();
            
             String variable = this.Remplazar("cortedia") +"("+id+",'"+fechahora+"',"+idusuario+","+total+",'"+estado+"');";
             bw.write(variable);
             bw.newLine();
         }
         
          bw.close();
      }
}
    

