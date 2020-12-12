/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Date;
import java.sql.Timestamp;
import jxl.write.DateTime;

/**
 *
 * @author najera10
 */
public class CVenta {
    private int idVenta;
    private int idcliente;
    private Timestamp fecha_hora;
    private double montoVenta;
    private String estado;
    private String idusuario;

    public CVenta(int idVenta, int idcliente, Timestamp fecha_hora, double montoVenta, String estado, String idusuario) {
        this.idVenta = idVenta;
        this.idcliente = idcliente;
        this.fecha_hora = fecha_hora;
        this.montoVenta = montoVenta;
        this.estado = estado;
        this.idusuario = idusuario;
    }
    
    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    }
    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }


    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }



     public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }
    
    
    
}
