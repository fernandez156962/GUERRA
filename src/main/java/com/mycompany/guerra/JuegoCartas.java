/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guerra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alumno
 */
public class JuegoCartas {
    
    private List<Carta> baraja;
    
    public JuegoCartas() {
        baraja = new ArrayList<>();
        for (PaloEspanol palo : PaloEspanol.values()) {
            for (int valor = 1; valor <= 12; valor++) {
                baraja.add(new Carta(valor, palo));
            }
        }
        Collections.shuffle(baraja);
    }
    
    
    public boolean barajaVacia() {
        return baraja.isEmpty();
    }
    
    
}
