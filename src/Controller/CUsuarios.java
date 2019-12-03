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
public class CUsuarios {
 
    
    private int id_usuario;
    private String nombre;
    private String tipo_documento;
    private String num_documento;
    private String direccion;
    private String telefono;
    private String email;
    private String cargo;
    private String login;
    private String clave;
    private String imagen;
    private String codicion;

    public CUsuarios(int id_usuario, String nombre, String tipo_documento, String num_documento, String direccion, String telefono, String email, String cargo, String login, String clave, String imagen, String codicion) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.clave = clave;
        this.imagen = imagen;
        this.codicion = codicion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCodicion() {
        return codicion;
    }

    public void setCodicion(String codicion) {
        this.codicion = codicion;
    }

    @Override
    public String toString() {
        return this.nombre;
                
     }

    
    
    
    
}
