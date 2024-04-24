/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guerra;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;


public class Cliente {
    private static final String SERVIDOR_IP = "127.0.0.1";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVIDOR_IP, PUERTO);
            System.out.println("Conectado al servidor.");

            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            Jugador jugador = (Jugador) entrada.readObject();
            System.out.println("¡Bienvenido, " + jugador.getNombre() + "!");

            // Recibir cartas del servidor
            List<Carta> mano = (List<Carta>) entrada.readObject();
            System.out.println("Tus cartas:");
            for (Carta carta : mano) {
                System.out.println(carta);
            }

            // Cerrar conexiones
            entrada.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

