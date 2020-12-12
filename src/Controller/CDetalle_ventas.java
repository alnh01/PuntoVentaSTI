/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author najera10
 */
public class CDetalle_ventas {
     private int idDetalleVenta;
    private Long idVenta;
    private String idProd;
    private double cantidadVendida;
    private double costoxp;

    public CDetalle_ventas(int idDetalleVenta, Long idVenta, String idProd, double cantidadVendida, double costoxp) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProd = idProd;
        this.cantidadVendida = cantidadVendida;
        this.costoxp = costoxp;
    }

   

    public double getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(double cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public double getCostoxp() {
        return costoxp;
    }

    public void setCostoxp(double costoxp) {
        this.costoxp = costoxp;
    }
    
    

}
