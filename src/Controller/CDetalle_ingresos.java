/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Najera
 */
public class CDetalle_ingresos {
    
    
    private int iddetalle_ingreso;
    private Long idingreso;
    private int idarticulo;
    private double cantidad;
    private double precioCompra;

    public CDetalle_ingresos(int iddetalle_ingreso, Long idingreso, int idarticulo, double cantidad, double precioCompra) {
        this.iddetalle_ingreso = iddetalle_ingreso;
        this.idingreso = idingreso;
        this.idarticulo = idarticulo;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }


   
    public int getIddetalle_ingreso() {
        return iddetalle_ingreso;
    }

    public void setIddetalle_ingreso(int iddetalle_ingreso) {
        this.iddetalle_ingreso = iddetalle_ingreso;
    }

    public Long getIdingreso() {
        return idingreso;
    }

    public void setIdingreso(Long idingreso) {
        this.idingreso = idingreso;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }


    
}
