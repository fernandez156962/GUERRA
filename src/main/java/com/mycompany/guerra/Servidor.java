/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.guerra;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Servidor {

    private static final int PUERTO = 12345;
    private static final int NUMERO_DE_JUGADORES = 4;
    private static List<ObjectOutputStream> flujosSalida = new ArrayList<>();
    private static List<Jugador> jugadores = new ArrayList<>();
    private static Baraja baraja;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Aceptar conexiones de los jugadores
            for (int i = 1; i <= NUMERO_DE_JUGADORES; i++) {
                Socket cliente = servidor.accept();  //esperamos a que acepteee se qda bloqueado
                System.out.println("Jugador " + i + " conectado desde: " + cliente.getInetAddress());
                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                flujosSalida.add(salida);
                Jugador jugador = new Jugador("Jugador " + i);
                jugadores.add(jugador);
                salida.writeObject(jugador);
                salida.flush();
            }

            // Iniciar el juego
            iniciarJuego();

            // Cerrar conexiones
            for (ObjectOutputStream salida : flujosSalida) {
                salida.close();
            }
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void iniciarJuego() {
        baraja = new Baraja();
        baraja.repartirCartas(jugadores.get(0), jugadores.get(1), jugadores.get(2), jugadores.get(3));

        /*Jugador ganadorRonda;
        int ronda = 1;

        // Jugar las cartas y determinar al ganador de cada ronda
        do {
            ganadorRonda = baraja.determinarGanador(jugadores.get(0), jugadores.get(1), jugadores.get(2), jugadores.get(3));
            //System.out.println("Ronda " + ronda + ": El ganador es " + ganadorRonda.getNombre());
            // Rotar la lista de jugadores para que el ganador juegue primero en la siguiente ronda
            //Collections.rotate(jugadores, -1);
            ronda++;
            
            //mientras haya mas jugadores con cartas se seguira jugando
        } while (cantidadJugadoresConCartas() > 1);

        Jugador ganadorFinal = jugadores.stream()
                .filter(jugador -> !jugador.getMano().isEmpty())
                .findFirst()
                .orElse(null);
        System.out.println("El ganador final del juego es " + ganadorFinal.getNombre());*/
    }

    //METODO QUE DEVUELVE CUANTOS JUGADORES TIENEN CARTAS
    private static int cantidadJugadoresConCartas() {
        return (int) jugadores.stream().filter(jugador -> !jugador.getMano().isEmpty()).count(); 
    }
}
