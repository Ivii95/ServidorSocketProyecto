/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author DAM2
 */
public class Alquiler implements Serializable{
    public int id;
    public Pista p;
    public Usuario usu;
    public String horaInicio,horaFin;
    public String dia;
    public boolean ocupada;

    public Alquiler(int id, Pista p, Usuario usu, String horaInicio, String horaFin, String dia, boolean ocupada) {
        this.id = id;
        this.p = p;
        this.usu = usu;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
        this.ocupada = ocupada;
    }
    

    public Alquiler() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pista getP() {
        return p;
    }

    public void setP(Pista p) {
        this.p = p;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    
}
