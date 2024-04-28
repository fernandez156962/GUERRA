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
import java.net.Socket;


public class ClienteOihane {

    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Socket cliente = new Socket(HOST, PUERTO);

            // Flujo de entrada y salida para comunicarse con el servidor
            ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

            // Recibir solicitud de nombre del servidor
            String solicitudNombre = (String) in.readObject();
            System.out.println("Servidor dice: " + solicitudNombre);

            // Enviar nombre al servidor
            String nombre = "Ejemplo"; // Aquí puedes solicitar el nombre al usuario
            out.writeObject(nombre);

            // Recibir solicitud de carta del servidor
            Carta carta = (Carta) in.readObject();
            System.out.println("Servidor dice: " + carta);

            // Solicitar al usuario ingresar la carta
            

            

            // Enviar la carta al servidor
            out.writeObject(carta);

            cliente.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
}
