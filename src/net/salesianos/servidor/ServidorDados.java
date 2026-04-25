package net.salesianos.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorDados {

    private static final int PUERTO = 5000;

    public static void main(String[] args) {

        GameManager gameManager = new GameManager();

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {

            System.out.println("Servidor iniciado en puerto " + PUERTO);

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Nueva conexión desde: "
                        + clientSocket.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(clientSocket, gameManager);
                handler.start();
            }

        } catch (IOException e) {
            System.err.println("Error al iniciar servidor: " + e.getMessage());
        }
    }
}
