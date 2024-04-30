/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectopcdguerra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Jugador implements Serializable{
    private String nombre;
    private List<Integer> mano;
    private int partidasGanadas;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();
    }

    public void recibirCarta(Integer carta) {
        mano.add(carta);
    }

    public Integer jugarCarta() {
        return mano.remove(0);
        
    }

    public boolean verificarCartaMano(Integer c) {
        if (mano.contains(c)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Integer> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int numeroCartas() {
        return mano.size();
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }
    
    

}
