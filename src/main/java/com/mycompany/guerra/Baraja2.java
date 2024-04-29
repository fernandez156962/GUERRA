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
public class Baraja2 {
    
    private List<Integer> baraja2;
    
    
    public Baraja2() {
        baraja2 = new ArrayList<>();
        // Crear las cartas de la baraja española
        for (int i = 1; i<41; i++) {
                baraja2.add(i);
        }
        // Barajar la baraja
        Collections.shuffle(baraja);
    }
    
    public void repartirCartas(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        for (int i = 0; i < baraja2.size(); i += 4) {
            j1.recibirCarta(baraja2.get(i));
            j2.recibirCarta(baraja2.get(i + 1));
            j3.recibirCarta(baraja2.get(i + 2));
            j4.recibirCarta(baraja2.get(i + 3));
        }

    }
}
