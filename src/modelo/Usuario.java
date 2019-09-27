/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    private int id;
    private String usuario;
    private String contrasena;
    private int admin;
    private String correoRecuperacion;
    private String nombre;
    private String apellidos;
    private int tlf;
    private String sexo;
    private String nacimiento;
    private String pais;
    private String comunidadAutonoma;
    private String provincia;
    private String ciudad;
    private String domicilio;
    
    public Usuario(){
        
    }

    @Override
    public String toString() {
        return "Usuario:\n" + "id=" + id + "\n, usuario=" + usuario + "\n, contrase\u00f1a=" + contrasena + "\n, admin=" + admin + "\n"
                + ", correoRecuperacion=" + correoRecuperacion + "\n, nombre=" + nombre + "\n, apellidos=" + apellidos + "\n"
                + ", tlf=" + tlf + "\n, sexo=" + sexo + "\n, nacimiento=" + nacimiento + "\n, pais=" + pais + "\n"
                + ", comunidadAutonoma=" + comunidadAutonoma + "\n, provincia=" + provincia + "\n, ciudad=" + ciudad + "\n, domicilio=" + domicilio;
    }
    
    public Usuario(int id, String usuario, String contrasena, int admin, String correoRecuperacion, String nombre, String apellidos, int tlf, String sexo, String nacimiento, String pais, String comunidadAutonoma, String provincia, String ciudad, String domicilio) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.admin = admin;
        this.correoRecuperacion = correoRecuperacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tlf = tlf;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
        this.pais = pais;
        this.comunidadAutonoma = comunidadAutonoma;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getCorreoRecuperacion() {
        return correoRecuperacion;
    }

    public void setCorreoRecuperacion(String correoRecuperacion) {
        this.correoRecuperacion = correoRecuperacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
}
