

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.guerra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alumno
 */
public class ServidorXabi {

    private static final int PORT = 1234;
    private static final int NUM_PLAYERS = 4;
    private static final int NUM_ROUNDS = 10;
    private static final String[] cards = {"As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Jota", "Reina", "Rey"};
    private static final Map<Integer, List<String>> cardsPerRound = new HashMap<>();
    private static List<Socket> playerSockets = new ArrayList<>();

    // Método synchronized para evitar problemas de concurrencia
    public static synchronized void addCard(int round, String card) {
        List<String> roundCards = cardsPerRound.computeIfAbsent(round, k -> new ArrayList<>());
        roundCards.add(card);
        if (roundCards.size() == NUM_PLAYERS) {
            determineHighestCard(round);
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado. Esperando jugadores...");
            for (int i = 0; i < NUM_PLAYERS; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Jugador conectado: " + clientSocket);
                playerSockets.add(clientSocket);
            }
            for (int i = 1; i <= NUM_ROUNDS; i++) {
                System.out.println("Ronda " + i + ":");
                cardsPerRound.clear();
                final int j = i;
                playerSockets.forEach(socket -> {
                    handleClient(socket, j);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los sockets de los jugadores y el socket del servidor al finalizar el juego
            playerSockets.forEach(socket -> {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void handleClient(Socket clientSocket, int round) {
    try {
        if (!clientSocket.isClosed()) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String card = getRandomCard();
                addCard(round, card);
                out.println("Tu carta para la ronda " + round + " es: " + card);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private static void determineHighestCard(int round) {
        List<String> roundCards = cardsPerRound.get(round);
        String highestCard = roundCards.stream().max(Comparator.comparing(ServidorXabi::getCardValue)).orElse(null);
        System.out.println("La carta más alta en la ronda " + round + " es: " + highestCard);
    }

    private static int getCardValue(String card) {
        return Arrays.asList(cards).indexOf(card);
    }

    private static String getRandomCard() {
        int index = (int) (Math.random() * cards.length);
        return cards[index];
    }   
}
