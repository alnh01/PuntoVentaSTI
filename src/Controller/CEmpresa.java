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
public class CEmpresa {
    
    private int idempresa;
    private String empresa;
    private String direccion;
    private String telefono;
    private String correo;
    private String rfc;

    public CEmpresa(int idempresa, String empresa, String direccion, String telefono, String correo, String rfc) {
        this.idempresa = idempresa;
        this.empresa = empresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.rfc = rfc;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
