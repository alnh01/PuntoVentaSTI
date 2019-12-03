/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Date;

/**
 *
 * @author Najera
 */
public class CIngreso {
    
    private int idingreso;
    private int idprovvedor;
    private Date fecha_hora;
    private  double total_compra;
    private  String estado;

    public CIngreso(int idingreso, int idprovvedor, Date fecha_hora, double total_compra, String estado) {
        this.idingreso = idingreso;
        this.idprovvedor = idprovvedor;
        this.fecha_hora = fecha_hora;
        this.total_compra = total_compra;
        this.estado = estado;
    }

    public int getIdingreso() {
        return idingreso;
    }

    public void setIdingreso(int idingreso) {
        this.idingreso = idingreso;
    }

    public int getIdprovvedor() {
        return idprovvedor;
    }

    public void setIdprovvedor(int idprovvedor) {
        this.idprovvedor = idprovvedor;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(double total_compra) {
        this.total_compra = total_compra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
    
    
}
