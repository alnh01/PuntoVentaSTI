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
public class CUnidades {
    
 private int idunidad;
 private String nombre;

    public CUnidades(int idunidad, String nombre) {
        this.idunidad = idunidad;
        this.nombre = nombre;
    }
    
    public int getIdunidad() {
        return idunidad;
    }

    public void setIdunidad(int idunidad) {
        this.idunidad = idunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
 
 
 
    
    
    
    
}
