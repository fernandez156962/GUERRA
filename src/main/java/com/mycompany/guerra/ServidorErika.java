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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class ServidorErika {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PUERTO = 12345;
        final int NUM_CLIENTES = 4;
        
        try{
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones...");
            
            int numClientes = 0;
            ArrayList<Jugador> jugadores = new ArrayList<>();
            
            while(numClientes< NUM_CLIENTES){
                Socket cliente = servidor.accept();
                System.out.println("Cliente " + (numClientes + 1) + " conectado");
                
                /*PrintWriter out = new PrintWriter(cliente.getOutputStream());
                 //dice algo el cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Cliente dice: " + inputLine);
                    out.println(inputLine);
                }*/
                
                ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            
                Jugador j = (Jugador) ois.readObject();
                System.out.println("jugador recibido" + j);
                jugadores.add(j);
                cliente.close(); //para cerrar el socket, puerto que hemos conectado
            }
        }catch(IOException ex){
            System.err.println("IOException Mensaje "+ ex.getMessage());
            ex.printStackTrace(System.err);
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorErika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
