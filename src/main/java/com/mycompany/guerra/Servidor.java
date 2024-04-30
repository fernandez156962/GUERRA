/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectopcdguerra;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Servidor {

    private static final int PUERTO = 44441;
    private static final int NUMERO_DE_JUGADORES = 4;
    private static final int NUMERO_DE_RONDAS = 10;

    private static List<Jugador> jugadores = new ArrayList<>();


    private static final List<ObjectOutputStream> outputStreams = new ArrayList<>();

    public static void main(String[] args) {
       
            
    }

}
