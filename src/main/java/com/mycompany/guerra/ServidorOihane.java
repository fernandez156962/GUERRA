/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.guerra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alumno
 */
public class ServidorOihane {

    private static final int PUERTO = 12345;  //puerto desde el que se conectan
    private static final int NUMERO_DE_JUGADORES = 4;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            //PARA CADA CLIENTE
            for (int i = 0; i < NUMERO_DE_JUGADORES; i++) {
                Socket cliente = servidor.accept();  //METODO QUE SE QDA A LA ESPERA
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());  

                // Flujo de entrada y salida para comunicarse con el cliente
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

                // Pedir el nombre al cliente
                out.writeObject("Por favor, ingresa tu nombre:");
                String nombre = (String) in.readObject();
                
                System.out.println("Nombre recibido del cliente " + (i + 1) + ": " + nombre);
            }

            // Pedir una carta a cada cliente
            for (int i = 0; i < NUMERO_DE_JUGADORES; i++) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                // Flujo de entrada y salida para comunicarse con el cliente
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

                // Pedir una carta al cliente
                out.writeObject("Por favor, elige una carta (por ejemplo: As de OROS):");
                Carta carta = (Carta) in.readObject();
                System.out.println("Carta recibida del cliente " + (i + 1) + ": " + carta);
            }

            servidor.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


    

