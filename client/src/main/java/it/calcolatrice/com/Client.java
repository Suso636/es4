package it.calcolatrice.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int porta = 12345;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Primo numero: ");
            String primo = scanner.nextLine().trim();

            System.out.print("Operatore (+, -, *, /): ");
            String operatore = scanner.nextLine().trim();

            System.out.print("Secondo numero: ");
            String secondo = scanner.nextLine().trim();

            String richiesta = primo + " " + operatore + " " + secondo;

            try (Socket socket = new Socket(host, porta);
                 PrintWriter output = new PrintWriter(
                         socket.getOutputStream(), true);
                 BufferedReader input = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {

                output.println(richiesta);

                String risposta = input.readLine();
                System.out.println("Risposta del server: " + risposta);

            } catch (Exception e) {
                System.err.println("Errore di connessione: " + e.getMessage());
            }
        }
    }
}
