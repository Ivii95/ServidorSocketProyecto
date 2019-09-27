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
public class Pista implements Serializable{
    public int id,num;
    public String tipo;
    public float precioHora;

    public Pista(int id, int num, String tipo, float precioHora) {
        this.id = id;
        this.num = num;
        this.tipo = tipo;
        this.precioHora = precioHora;
    }

    public Pista() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "Pistas: " + "id=" + id + ", num=" + num + ", tipo=" + tipo + ", precioHora=" + precioHora;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(float precioHora) {
        this.precioHora = precioHora;
    }
    
}
