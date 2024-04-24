/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guerra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alumno
 */
public class Jugador {
    
    private String nombre;
    private List<Carta> mano;  //cada jugador tiene una lista

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public Carta jugarCarta() {
        return mano.remove(0);
    }
    
    public boolean verificarCartaMano(Carta c){
        if(mano.contains(c)){
            return true;
        }
        else{
            return false;
        }
    }

    public List<Carta> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
}
