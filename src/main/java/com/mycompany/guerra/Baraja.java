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
public class Baraja {
    
        private List<Carta> baraja;

    public Baraja() {
        baraja = new ArrayList<>();
        // Crear las cartas de la baraja española
        for (Palo palo : Palo.values()) {
            for (int valor = 1; valor <= 12; valor++) {
                baraja.add(new Carta(valor, palo));
            }
        }
        // Barajar la baraja
        Collections.shuffle(baraja);
    }

    public void repartirCartas(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        for (int i = 0; i < baraja.size(); i += 4) {
            j1.recibirCarta(baraja.get(i));
            j2.recibirCarta(baraja.get(i + 1));
            j3.recibirCarta(baraja.get(i + 2));
            j4.recibirCarta(baraja.get(i + 3));
        }

    }

    public Jugador determinarGanador(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {

        //CADA JUGADOR SACA LA CARTA DE ARRIBA DE LA LISTA
        Carta c1 = j1.jugarCarta();
        Carta c2 = j2.jugarCarta();
        Carta c3 = j3.jugarCarta();
        Carta c4 = j4.jugarCarta();

        System.out.println(j1.getNombre() + "jugo la carta" + c1.getValor());
        System.out.println(j2.getNombre() + "jugo la carta" + c2.getValor());
        System.out.println(j3.getNombre() + "jugo la carta" + c3.getValor());
        System.out.println(j4.getNombre() + "jugo la carta" + c4.getValor());

        List<Carta> cartas = new ArrayList<>();
        cartas.add(c1);
        cartas.add(c2);
        cartas.add(c3);
        cartas.add(c4);
        // Determinar el valor máximo de las cartas jugadas
        int maxValor = Math.max(Math.max(c1.getValor(), c2.getValor()), Math.max(c3.getValor(), c4.getValor()));

        // Verificar si hay empate
        List<Jugador> jugadoresEmpatados = new ArrayList<>();  //creo arraylist vacio

        //se meten los maximos en jugadoresEMpatados si solo hay uno entonces no se entra en el while y ya se sale
        if (c1.getValor() == maxValor) {
            jugadoresEmpatados.add(j1);
        }
        if (c2.getValor() == maxValor) {
            jugadoresEmpatados.add(j2);
        }
        if (c3.getValor() == maxValor) {
            jugadoresEmpatados.add(j3);
        }
        if (c4.getValor() == maxValor) {
            jugadoresEmpatados.add(j4);
        }

        // Si hay empate, se sigue jugando hasta que no haya empate
        while (jugadoresEmpatados.size() > 1) {
            System.out.println("¡Empate! Se vuelve a jugar una carta...");
            for (Jugador jugador : jugadoresEmpatados) {
                Carta cartaNueva = jugador.jugarCarta();
                cartas.add(cartaNueva);
                System.out.println(jugador.getNombre() + " volvió a jugar la carta: " + cartaNueva.getValor());
                if (cartaNueva.getValor() > maxValor) {
                    maxValor = cartaNueva.getValor();
                    jugadoresEmpatados.clear();
                    jugadoresEmpatados.add(jugador);
                } else if (cartaNueva.getValor() == maxValor) {
                    jugadoresEmpatados.add(jugador);
                }

            }
        }

        // Devolver al jugador ganador 
        Jugador ganador = jugadoresEmpatados.get(0);

        //para cada jugador
        for (Carta c : cartas) {

            //si no es el ganador
            ganador.recibirCarta(c);

        }
        return ganador;

    }
    
    
}
