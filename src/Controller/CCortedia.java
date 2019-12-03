/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Najera10
 */
public class CCortedia {
   
    
    
 private String articulo;
 private String descripcion;
 private Double cantidad;
 private Double precioporpro;

    public CCortedia(String articulo, String descripcion, Double cantidad, Double precioporpro) {
        this.articulo = articulo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioporpro = precioporpro;
    }



    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioporpro() {
        return precioporpro;
    }

    public void setPrecioporpro(Double precioporpro) {
        this.precioporpro = precioporpro;
    }
 
 
 
 
 
 
}
