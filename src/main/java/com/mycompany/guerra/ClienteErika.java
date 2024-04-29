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
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class ClienteErika {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        final String IP = "127.0.0.1"; //nose cual poner
        final int PUERTO = 12345;
        
        try{
            Socket sc = new Socket(IP, PUERTO);
            
            ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el nombre del jugador");
            String nombre = scanner.nextLine();
            Jugador j = new Jugador(nombre);
            j.getMano();
            oos.writeObject(j);
            
            while (true) {
                System.out.println("1. Envia carta");
                System.out.println("2. Salir");
                System.out.print("Elija una opcion: ");

                String opcion = teclado.readLine();

                if ("1".equals(opcion)) {
                    Carta carta = (Carta) ois.readObject();
                    System.out.println("La carta del jugador "+ j.getNombre()+ " es "+ carta);
                    //ois.writeObject(carta);
                    //String mensaje = teclado.readLine();
                    //salidaSocket.println(mensaje);
                } else if ("2".equals(opcion)) {
                    //salidaSocket.println("/quit");//mandamos esto porque nos queremos ir
                    break; //nos vamos
                } else {
                    System.out.println("Opcion incorrecta. Intentalo de nuevo...");
                }
            }
                  
                    
        }catch(IOException ex){
            System.err.println("IOException Mensaje "+ ex.getMessage());
            ex.printStackTrace(System.err);
            System.exit(1);
        }
        
    }
    
}
