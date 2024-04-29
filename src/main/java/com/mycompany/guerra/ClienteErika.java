/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.guerra;

import java.io.IOException;
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
    public static void main(String[] args) {
        final String IP = "127.0.0.1"; //nose cual poner
        final int PUERTO = 12345;
        
        try{
            Socket sc = new Socket(IP, PUERTO);
            
            ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el nombre del jugador");
            String nombre = scanner.nextLine();
            Jugador j = new Jugador(nombre);
            j.getMano();
            oos.writeObject(j);
                  
                    
        }catch(IOException ex){
            System.err.println("IOException Mensaje "+ ex.getMessage());
            ex.printStackTrace(System.err);
            System.exit(1);
        }
        
    }
    
}
