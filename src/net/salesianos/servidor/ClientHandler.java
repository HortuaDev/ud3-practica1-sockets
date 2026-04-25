package net.salesianos.servidor;

import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket socket;
    private final GameManager gameManager;
    private Jugador jugador;

    public ClientHandler(Socket socket, GameManager gameManager) {
        this.socket = socket;
        this.gameManager = gameManager;
    }

}
