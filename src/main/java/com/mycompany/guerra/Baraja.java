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

    public void determinarGanador2(HashMap<Jugador, ArrayList<Carta>> juego) {
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

            }
        }

        // Devolver al jugador ganador 
        Jugador ganador = jugadoresEmpatados.get(0);

        //para cada jugador
        for (Carta c : cartas) {
            boolean verificado = ganador.verificarCartaMano(c);
            if (!verificado) {
                ganador.recibirCarta(c);
            }

        }
        return ganador;

    }

    public void simularPartida(HashMap<Jugador, ArrayList<Carta>> juego) {
        // Mientras haya más de un jugador en el juego
        while (juego.size() > 1) {
            int maximo = 0;
            ArrayList<Jugador> ganadores = new ArrayList<>();

            // Cada jugador juega una carta
            for (Jugador jugador : juego.keySet()) {
                ArrayList<Carta> listaCartas = juego.get(jugador);
                Carta carta = jugador.jugarCarta();
                listaCartas.add(carta);
                juego.put(jugador, listaCartas);
                maximo = Math.max(carta.getValor(), maximo);
            }

            // Determinar los ganadores de esta ronda
            for (Jugador jugador : juego.keySet()) {
                Carta ultimaCarta = juego.get(jugador).get(juego.get(jugador).size() - 1);
                if (ultimaCarta.getValor() == maximo) {
                    ganadores.add(jugador);
                }
            }

            // Si hay un único ganador, transferir cartas y eliminar perdedores
            if (ganadores.size() == 1) {
                Jugador ganador = ganadores.get(0);
                System.out.println("No hay empate");
                System.out.println("El jugador " + ganador.getNombre() + " es el ganador y se lleva las cartas.");
                for (Jugador jugador : juego.keySet()) {
                    if (!jugador.equals(ganador)) {
                        juego.get(jugador).addAll(juego.get(ganador));
                    }
                }
                juego.keySet().removeIf(jugador -> !jugador.equals(ganador));
            } else {
                // En caso de empate, realizar una guerra
                System.out.println("Hay empate. Empieza la guerra");
                guerra(juego, ganadores);
                break; // Terminar la simulación después de una ronda en caso de empate
            }
        }
    }

    private void guerra(HashMap<Jugador, ArrayList<Carta>> juego, ArrayList<Jugador> empate) {
        // Cada jugador en empate juega una carta boca abajo y otra boca arriba
        HashMap<Jugador, Carta> cartasBocaArriba = new HashMap<>();
        for (Jugador jugador : empate) {
            ArrayList<Carta> listaCartas = juego.get(jugador);
            if (listaCartas.size() < 2) {
                // Si un jugador no tiene suficientes cartas para la guerra, lo eliminamos
                juego.remove(jugador);
                //esas cartas las tenemos que guardar
            } else {
                // Juega una carta boca abajo
                Carta cartaBocaAbajo = listaCartas.remove(listaCartas.size() - 1);
                // Juega una carta boca arriba
                Carta cartaBocaArriba = listaCartas.remove(listaCartas.size() - 1);
                System.out.println("El jugador " + jugador.getNombre() + " juega una carta boca abajo y una carta boca arriba.");

                // Guardamos la carta boca arriba para compararla después
                cartasBocaArriba.put(jugador, cartaBocaArriba);
            }
        }

        // Buscamos la carta de mayor valor entre las jugadas boca arriba
        int maximo = 0;
        for (Carta carta : cartasBocaArriba.values()) {
            maximo = Math.max(carta.getValor(), maximo);
        }

        // Identificamos los ganadores de esta ronda de guerra
        ArrayList<Jugador> ganadoresGuerra = new ArrayList<>();
        for (Map.Entry<Jugador, Carta> entry : cartasBocaArriba.entrySet()) {
            if (entry.getValue().getValor() == maximo) {
                ganadoresGuerra.add(entry.getKey());
            }
        }

        // Si hay un único ganador de la guerra, transferimos todas las cartas a su mazo
        if (ganadoresGuerra.size() == 1) {
            Jugador ganadorGuerra = ganadoresGuerra.get(0);
            System.out.println("El jugador " + ganadorGuerra.getNombre() + " gana la guerra y se lleva todas las cartas.");

            // Transferimos todas las cartas de la guerra al ganador
            for (Jugador jugador : juego.keySet()) {
                if (!jugador.equals(ganadorGuerra)) {
                    juego.get(ganadorGuerra).addAll(juego.get(jugador));
                }
            }

            // Eliminamos a los perdedores de la guerra
            juego.keySet().removeIf(jugador -> !jugador.equals(ganadorGuerra));
        } else {
            // Si hay empate nuevamente, continuamos con otra guerra recursivamente
            System.out.println("Empate en la guerra. Se inicia otra guerra.");
            guerra(juego, ganadoresGuerra);
        }
    }

}
