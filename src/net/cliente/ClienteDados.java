package net.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDados {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(HOST, PUERTO)) {

            System.out.println("Conectado al servidor");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            System.out.print("Tu nombre: ");
            String nombre = scanner.nextLine().trim();
            out.println("NOMBRE:" + nombre);

            EscuchadorServidor escuchador = new EscuchadorServidor(in);
            escuchador.start();

            while (!escuchador.isPartidaTerminada()) {
                if (escuchador.isEsMiTurno()) {
                    scanner.nextLine();
                    escuchador.setEsMiTurno(false);
                    out.println("LANZAR");
                } else {
                    Thread.sleep(100);
                }
            }

            System.out.println("Partida terminada. Hasta luego.");

        } catch (IOException e) {
            System.err.println("No se pudo conectar: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scanner.close();
        }
    }

}
