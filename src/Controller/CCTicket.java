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
public class CCTicket {
    
    private int idempre;
    private String nombre;
    private String direccion;
    private String telefono;
    private String  correo;
    private String rfc;

     public CCTicket(int idempre, String nombre, String direccion, String telefono, String correo, String rfc) {
        this.idempre = idempre;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.rfc = rfc;
    }

    public int getIdempre() {
        return idempre;
    }

    public void setIdempre(int idempre) {
        this.idempre = idempre;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
    
    
    
}
