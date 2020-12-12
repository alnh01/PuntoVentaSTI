/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Timestamp;
import jxl.write.DateTime;

/**
 *
 * @author Najera
 */
public class CCorte {
    
    
 private int idcorte;
 private Timestamp fecha;
 private int  idusuario;
 private Double  total;
 private String estado;

    public CCorte(int idcorte, Timestamp fecha, int idusuario, Double total, String estado) {
        this.idcorte = idcorte;
        this.fecha = fecha;
        this.idusuario = idusuario;
        this.total = total;
        this.estado = estado;
    }

    public int getIdcorte() {
        return idcorte;
    }

    public void setIdcorte(int idcorte) {
        this.idcorte = idcorte;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
 
 
 
 
 
}
