import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Inserisci il primo numero: ");
            String primo = scanner.nextLine().trim();

            System.out.print("Inserisci l'operatore (+, -, *, /): ");
            String operatore = scanner.nextLine().trim();

            System.out.print("Inserisci il secondo numero: ");
            String secondo = scanner.nextLine().trim();

            try (
                Socket socket = new Socket(HOST, PORTA);
                PrintWriter out = new PrintWriter(
                    socket.getOutputStream(),
                    true,
                    StandardCharsets.UTF_8
                );
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream(),
                        StandardCharsets.UTF_8
                    )
                )
            ) {
                out.println(primo + " " + operatore + " " + secondo);

                String risposta = in.readLine();
                System.out.println("Risposta del server: " + risposta);
            }
        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
        }
    }
}


