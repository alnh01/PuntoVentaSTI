/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Alerts.ErrorAlert;
import Conexion.Conexion;
import Controller.CProductos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import static java.sql.JDBCType.BLOB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import paneles.pnlPunto;

public class Producto {
    
     private Connection userConn;

     private final String SQL_SELECT = "SELECT ar.*,ca.nombre as categoria ,un.nombre as unidad FROM  articulo ar INNER join categoria ca on ar.idcategoria = ca.idcategoria inner JOIN unidad_medida un  on un.idunidad = ar.idunidad";
     private final String SQL_INSERT = "INSERT INTO articulo values(NULL,?, ?, ?, ?,?, ?, ?, ?, ?, 1)";
     private final String SQL_UPDATE = "UPDATE articulo set idcategoria = ?, idunidad = ?, codigo = ?, nombre = ? ,stock = ?, precio_venta = ?,precio_compra = ?,descripcion = ?  where idarticulo = ? ";
     private final String SQL_DELETE = "DELETE articulo  WHERE idarticulo=?";
     private final String SQL_UPDATE_STOCK = "UPDATE articulo set STOCK = ?  WHERE idarticulo=? ";
     private final String SQL_SELECT_STOCK = "SELECT stock  FROM articulo where idarticulo = ?  ";
     private final String SQL_TRUNCATE = "TRUNCATE table  articulo  RESTART IDENTITY";
     private final String SQL_TRUNCATE_DVENTA = "TRUNCATE table  detalle_venta  RESTART IDENTITY";
     private final String SQL_TRUNCATE_VENTA = "TRUNCATE table  venta  RESTART IDENTITY";


     
     private final String SQL_BUSCAR_REPETIDO = "SELECT * FROM articulo WHERE codigo = ? ";
     
     
     
     public void insertar (CProductos producto) throws SQLException{
    
    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
             FileInputStream fis =null;
            File fileFoto = producto.getFotoProducto();
            if (fileFoto == null) {
                fileFoto = null;
            }else{
             fis = new FileInputStream(fileFoto);
            }
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           
           stmt = conn.prepareStatement(SQL_INSERT);
           
           stmt.setInt(1,producto.getIdCategoria());
           stmt.setInt(2,producto.getIdunidadProducto());
           stmt.setString(3,producto.getCodigo());
           stmt.setString(4,producto.getNomProducto());
           stmt.setDouble(5,producto.getStockProducto());
           stmt.setDouble(6,producto.getPrecioVentaProducto());
           stmt.setDouble(7,producto.getPrecioCompraProducto());
           stmt.setString(8,producto.getDescProducto());
           
           if(fis == null){
               stmt.setBinaryStream(9,null);
           }else{
              long tamanoFoto = fileFoto.length();
            stmt.setBinaryStream(9, fis, tamanoFoto);
           }
    
             System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
         
           
        } catch (FileNotFoundException ex) {
             Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
     }
     } 


 public ArrayList<CProductos> ObtenerCategorias() {
        ArrayList<CProductos> ListarProductos = new ArrayList<CProductos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                
     int idproducto =rs.getInt("idarticulo");
     String nomProducto =rs.getString("nombre");     
     String descProducto=rs.getString("descripcion");
     double stockProducto=rs.getDouble("stock");
     File fotoProducto;
     int idunidadProducto =rs.getInt("idunidad") ;
     double precioCompraProducto=rs.getDouble("precio_compra");
     double precioVentaProducto= rs.getDouble("precio_venta");
     int idCategoria = rs.getInt("idcategoria");
     String codigo = rs.getString("codigo");
     String categoria  = rs.getString("categoria");
     String unidad = rs.getString("unidad");
     String condicion = rs.getString("condicion");
                
     CProductos productos = new CProductos( idproducto,  nomProducto,  descProducto,  stockProducto,null, idunidadProducto,  precioCompraProducto,  precioVentaProducto,  idCategoria,  codigo,categoria,unidad,condicion);
      ListarProductos.add(productos);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarProductos;
    }

 
  public ArrayList<CProductos> obtenerProductosPorCriterio(String criterio) {
    
      
      ArrayList<CProductos> ListarProductos = new ArrayList<CProductos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
           
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
         
            stmt = conn.prepareStatement("SELECT  * FROM articulo WHERE idarticulo LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR codigo LIKE '%"+criterio+"%'");
            
            
             rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                 int id = rs.getInt("idarticulo");
                 System.out.println(id);
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double stock = rs.getDouble("stock");
                int unidad = rs.getInt("idunidad");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                String codigo = rs.getString("codigo");
                int idCategoria = rs.getInt("idcategoria");
                String condicion = rs.getString("condicion");
                
                CProductos producto = new CProductos(id,nombre,descripcion,stock, null,unidad, precioCompra,precioVenta,idCategoria,codigo,"","",condicion);
                ListarProductos.add(producto);                
            }
        } catch (SQLException ex) {
             Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
       return ListarProductos;
    }
  
  
  public ArrayList<CProductos> obtenerPorCodigo(String criterio){
    
      
      ArrayList<CProductos> ListarProductos = new ArrayList<CProductos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
           
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
  
            stmt = conn.prepareStatement("SELECT  * FROM articulo WHERE   codigo LIKE '%"+criterio+"%'");
            
            
             rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                 int id = rs.getInt("idarticulo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double stock = rs.getDouble("stock");
                int unidad = rs.getInt("idunidad");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                String codigo = rs.getString("codigo");
                int idCategoria = rs.getInt("idcategoria");
                String condicion = rs.getString("condicion");
                
                CProductos producto = new CProductos(id,nombre,descripcion,stock, null,unidad, precioCompra,precioVenta,idCategoria,codigo,"","",condicion);
                ListarProductos.add(producto);                
            }
        } catch (SQLException ex) {
             Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
       return ListarProductos;
    }
  
   public CProductos  obtenerporCodigoProdcuto(String criterio) throws SQLException{
    
      ArrayList<CProductos> ListarProductos = new ArrayList<CProductos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        CProductos producto = null;
       
        try {
           
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            
            stmt = conn.prepareStatement("SELECT  * FROM articulo WHERE  codigo = '"+criterio+"'");
            
            
             rs = stmt.executeQuery();
            
         
             if (!rs.next()) {
                 
                ErrorAlert e = new ErrorAlert(new JFrame(), true);
                e.msj1.setText("HAY UN PLOBLEMA ");
                e.msj2.setText("EL CODIGO INGRESADO NO EXITE!!");
                e.msj3.setText("");
                e.setVisible(true);
             }else{
//                    
                int id = rs.getInt("idarticulo");
                System.out.println(id);
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double stock = rs.getDouble("stock");
                int unidad = rs.getInt("idunidad");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                String codigo = rs.getString("codigo");
                int idCategoria = rs.getInt("idcategoria");
                String condicion = rs.getString("condicion");
                
                 producto = new CProductos(id,nombre,descripcion,stock, null,unidad, precioCompra,precioVenta,idCategoria,codigo,"","",condicion);
//                ListarProductos.add(producto);     
                
                  
//                pnlPunto pnlpn = new pnlPunto();
//                pnlpn.anadirProductoAVenta(producto);
                
      }
                            
//            
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
       return  producto;
    }
  
  
 public Blob buscarFoto(CProductos producto){
        InputStream streamFoto = null;
        
        Blob Fotos = null;
        ImageIcon li = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
          
            
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();  
            
            String sql = "SELECT imagen from articulo where idarticulo = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, producto.getIdProducto());
                      
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Fotos = rs.getBlob("imagen");
            }
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
        return Fotos;
    }
 
 // UPDATE articulo set idcategoria = ?, idunidad = ?, codigo = ?, nombre = ? ,stock = ?, precio_venta = ?,precio_compra = ?,descripcion = ? 
 public int actualizar(CProductos producto, String nomProducto, String descProducto, double stockProducto, File fotoProducto, int idunidadProducto, double precioCompraProducto, double precioVentaProducto, int idCategoria, String codigo) throws SQLException{
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           
            stmt = conn.prepareStatement(SQL_UPDATE);
            
              stmt.setInt(1,idCategoria);
              stmt.setInt(2,idunidadProducto);
              stmt.setString(3,codigo);
              stmt.setString(4,nomProducto);
              stmt.setDouble(5,stockProducto);
              stmt.setDouble(6,precioVentaProducto);
              stmt.setDouble(7,precioCompraProducto);
              stmt.setString(8,descProducto);
              stmt.setInt(9, producto.getIdProducto());
              



//            stmt.setString(3, categoria.getIdcategoria());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
 } 
 
 
 
 public int eliminar(CProductos producto) throws  SQLException{
     Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
          
        try {
           conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_DELETE);
           stmt.setInt(1,producto.getIdProducto());
       
            rows = stmt.executeUpdate();
            

        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
     }
        
        return rows; 
 }
 
 
 public ArrayList<Double> Obtener_stock(String idproducto) throws SQLException{
        ArrayList<Double> ids = new ArrayList<Double>(); 
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
  
        try {
           
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
          
            stmt = conn.prepareStatement(SQL_SELECT_STOCK);
            stmt.setString(1,idproducto);
            
             rs = stmt.executeQuery();
            
         
              while (rs.next()) {
                
             
              
                double stock = rs.getDouble("stock");
                
             
                
                
                ids.add(stock);                
            }

        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
     
     
     return ids;
     
 }
 
 public void Rest_Stock( String idproductos , double cantidad) throws SQLException{
     
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
           
            stmt = conn.prepareStatement(SQL_UPDATE_STOCK);
              int idpro = Integer.parseInt(idproductos);
              stmt.setDouble(1,cantidad);
              stmt.setInt(2,idpro);
            rows = stmt.executeUpdate();
          
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
     
     
 }
 
 
  public void LimpiarTabla() throws SQLException{
     
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TRUNCATE);
            rows = stmt.executeUpdate();
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
 }
  
  
  public void limpiarDetallesVenta(){
       
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TRUNCATE_DVENTA);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
             Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
  }
   public void limpiarVenta(){
       
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TRUNCATE_VENTA);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
             Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
  }
  
  
  
  public boolean VerificarCodigos(String codigo) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        boolean bandera =false;
        try {
           
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BUSCAR_REPETIDO);
             stmt.setString(1,codigo);
             rs = stmt.executeQuery();
                if (!rs.next()) {
                bandera = false; 
                }else{
                bandera = true;
                }
               
            
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
       return bandera;
    }
  
  public ArrayList<CProductos> ObtenerCategoriasCriterio(String criterio) {
        ArrayList<CProductos> ListarProductos = new ArrayList<CProductos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT ar.*,ca.nombre as categoria ,un.nombre as unidad FROM  articulo ar INNER join categoria ca on ar.idcategoria = ca.idcategoria inner JOIN unidad_medida un  on un.idunidad = ar.idunidad  WHERE ar.idarticulo LIKE '%"+criterio+"%' OR ar.nombre LIKE '%"+criterio+"%' OR ar.codigo LIKE '%"+criterio+"%'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                
     int idproducto =rs.getInt("idarticulo");
     String nomProducto =rs.getString("nombre");     
     String descProducto=rs.getString("descripcion");
     double stockProducto=rs.getDouble("stock");
     File fotoProducto;
     int idunidadProducto =rs.getInt("idunidad") ;
     double precioCompraProducto=rs.getDouble("precio_compra");
     double precioVentaProducto= rs.getDouble("precio_venta");
     int idCategoria = rs.getInt("idcategoria");
     String codigo = rs.getString("codigo");
     String categoria  = rs.getString("categoria");
     String unidad = rs.getString("unidad");
     String condicion = rs.getString("condicion");
                
     CProductos productos = new CProductos( idproducto,  nomProducto,  descProducto,  stockProducto,null, idunidadProducto,  precioCompraProducto,  precioVentaProducto,  idCategoria,  codigo,categoria,unidad,condicion);
      ListarProductos.add(productos);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return ListarProductos;
    }
  
    
     public CProductos  obtenerporCodigoDeProducto(String criterio) throws SQLException{
    
      ArrayList<CProductos> ListarProductos = new ArrayList<CProductos>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        CProductos producto = null;
       
        try {
           
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            
            stmt = conn.prepareStatement("SELECT  * FROM articulo WHERE  codigo = '"+criterio+"'");
            
            
             rs = stmt.executeQuery();
            
         
             if (!rs.next()) {
                 
                
                 
             }else{
//                    
                int id = rs.getInt("idarticulo");
                System.out.println(id);
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double stock = rs.getDouble("stock");
                int unidad = rs.getInt("idunidad");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                String codigo = rs.getString("codigo");
                int idCategoria = rs.getInt("idcategoria");
                String condicion = rs.getString("condicion");
                
                 producto = new CProductos(id,nombre,descripcion,stock, null,unidad, precioCompra,precioVenta,idCategoria,codigo,"","",condicion);
//                ListarProductos.add(producto);     
                
                  
//                pnlPunto pnlpn = new pnlPunto();
//                pnlpn.anadirProductoAVenta(producto);
                
      }
                            
//            
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
       return  producto;
    }
  
  
  
  
}
