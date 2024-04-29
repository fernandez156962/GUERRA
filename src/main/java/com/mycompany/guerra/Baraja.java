/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guerra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Baraja {

    private List<Carta> baraja;
    
    //esta cambiada solo hay oros y va del 1 al 40
    public Baraja() {
        baraja = new ArrayList<>();
        // Crear las cartas de la baraja española
        for (Palo palo : Palo.values()) {
            for (int valor = 1; valor <= 40; valor++) {
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

    public Jugador determinarGanador(HashMap<Jugador, ArrayList<Carta>> juego) {
        //MIENTRAS QUEDE MAS DE UN JUGADOR 
        ArrayList<Carta> cartasPerdedores = new ArrayList<>();
        while (juego.size() > 1) {
            int maximo = 0;  //PONEMOS EL MAXIMO A 0 
            ArrayList<Jugador> ganadores = new ArrayList<>();  //CREAMOS ARRAYLIST DE JUGADORES
            for (Jugador j : juego.keySet()) {  //PARA CADA JUGADOR
                ArrayList<Carta> listacartas = juego.get(j);     //COGEMOS SU ARRATLIST
                Carta c = j.jugarCarta();//JUGAMOS SU CARTA
                System.out.println(j.getNombre() + "jugo la carta" + c.getValor());
                listacartas.add(c);   //LO AÑADIMOS A SU LISTA DE JUEGO
                cartasPerdedores.add(c); //lo añadimos a la lista de todas las cartas
                juego.put(j, listacartas);  //AÑADIMOS A ESA PERSONA EN EL HASHMPA QUE SE SOBREPONE
                maximo = Math.max(c.getValor(), maximo);  //BUSCAMOS EN EL MAXIMO
            }

            for (Jugador j : juego.keySet()) {
                Carta c = juego.get(j).get(juego.size() - 1);  //coge la ultima carta 
                if (c.getValor() == maximo) {  //SI ES EL MAXIMO
                    ganadores.add(j);   //LO METEMOS EN GANADIRES

                }
            }
            if (ganadores.size() == 1) {
                Jugador ganador = ganadores.get(0);
                System.out.println("No hay empate ");
                System.out.println("El jugador " + ganador.getNombre() + " es el ganador y se lleva las cartas.");
                for(Carta c : cartasPerdedores){
                    ganador.recibirCarta(c);
                }
                juego.clear();
                //juego.keySet().removeIf(j -> !j.equals(ganador));
            } else {
                System.out.println("Hay empate. Empieza la guerra");
                juego.keySet().removeIf(jugador -> !ganadores.contains(jugador));
                ganadores.clear();
                for (Jugador j : juego.keySet()) {
                    if(j.numeroCartas()>1){    
                        Carta c = j.jugarCarta();
                        cartasPerdedores.add(c);
                        ganadores.add(j);
                    }
                    else if(j.numeroCartas()==1){
                        Carta c = j.jugarCarta();
                        cartasPerdedores.add(c);
                        System.err.println("El jugador"+ j.getNombre()+ " ha perdido porque se ha quedado sin cartas");
                    }else{
                        System.err.println("El jugador"+ j.getNombre()+ " ha perdido porque se ha quedado sin cartas");
                    }
                    juego.keySet().removeIf(jugador -> !ganadores.contains(jugador));
                }
                
            }

        }

    }
}
