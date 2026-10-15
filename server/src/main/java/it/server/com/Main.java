package it.server.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Server in ascolto sulla porta " + porta);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
                     PrintWriter output = new PrintWriter(
                             socket.getOutputStream(), true)) {

                    String richiesta = input.readLine();
                    String risposta;

                    if (richiesta == null) {
                        risposta = "ERRORE: richiesta vuota.";
                    } else {
                        String[] parti = richiesta.trim().split("\\s+");

                        if (parti.length != 3) {
                            risposta = "ERRORE: formato non valido. Usa: numero operatore numero";
                        } else {
                            try {
                                double primo = Double.parseDouble(parti[0]);
                                String operatore = parti[1];
                                double secondo = Double.parseDouble(parti[2]);

                                switch (operatore) {
                                    case "+":
                                        risposta = "Risultato: " + (primo + secondo);
                                        break;
                                    case "-":
                                        risposta = "Risultato: " + (primo - secondo);
                                        break;
                                    case "*":
                                        risposta = "Risultato: " + (primo * secondo);
                                        break;
                                    case "/":
                                        risposta = secondo == 0.0
                                                ? "ERRORE: divisione per zero."
                                                : "Risultato: " + (primo / secondo);
                                        break;
                                    default:
                                        risposta = "ERRORE: operatore non riconosciuto.";
                                }
                            } catch (NumberFormatException e) {
                                risposta = "ERRORE: operandi non validi.";
                            }
                        }
                    }

                    output.println(risposta);

                } catch (Exception e) {
                    System.err.println("Errore con il client: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Errore del server: " + e.getMessage());
        }
    }
}

