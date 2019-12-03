/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;

/**
 *
 * @author najera10
 */
public class CProductos {
    
    private int idProducto;
    private String nomProducto;
    private String descProducto;
    private double stockProducto;
    private File fotoProducto;
    private int idunidadProducto;
    private double precioCompraProducto;
    private double precioVentaProducto;
    private int idCategoria;
    private String codigo;
  private String categorra;
    private String unidad;

    public CProductos(int idProducto, String nomProducto, String descProducto, double stockProducto, File fotoProducto, int idunidadProducto, double precioCompraProducto, double precioVentaProducto, int idCategoria, String codigo, String categorra, String unidad) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.descProducto = descProducto;
        this.stockProducto = stockProducto;
        this.fotoProducto = fotoProducto;
        this.idunidadProducto = idunidadProducto;
        this.precioCompraProducto = precioCompraProducto;
        this.precioVentaProducto = precioVentaProducto;
        this.idCategoria = idCategoria;
        this.codigo = codigo;
        this.categorra = categorra;
        this.unidad = unidad;
    }
 



   
    



    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public double getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(double stockProducto) {
        this.stockProducto = stockProducto;
    }

    public File getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(File fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public int getIdunidadProducto() {
        return idunidadProducto;
    }

    public void setIdunidadProducto(int idunidadProducto) {
        this.idunidadProducto = idunidadProducto;
    }

    public double getPrecioCompraProducto() {
        return precioCompraProducto;
    }

    public void setPrecioCompraProducto(double precioCompraProducto) {
        this.precioCompraProducto = precioCompraProducto;
    }

    public double getPrecioVentaProducto() {
        return precioVentaProducto;
    }

    public void setPrecioVentaProducto(double precioVentaProducto) {
        this.precioVentaProducto = precioVentaProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCategorra() {
        return categorra;
    }

    public void setCategorra(String categorra) {
        this.categorra = categorra;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }



    @Override
    public String toString() {
        return this.nomProducto;
    }
    
    
    
    
    
   
}
