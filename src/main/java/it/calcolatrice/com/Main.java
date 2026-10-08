package it.calcolatrice.com;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Primo numero: ");
        double n1 = scanner.nextDouble();

        System.out.print("Operatore (+, -, *, /): ");
        String operatore = scanner.next();

        System.out.print("Secondo numero: ");
        double n2 = scanner.nextDouble();

        Socket socket = new Socket("localhost", 12345);

        PrintWriter output = new PrintWriter(
            socket.getOutputStream(), true
        );
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );

        output.println(n1 + " " + operatore + " " + n2);

        System.out.println(input.readLine());

        socket.close();
        scanner.close();
    }
}
