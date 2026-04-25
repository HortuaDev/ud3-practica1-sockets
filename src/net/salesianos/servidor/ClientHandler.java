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

    @Override
    public void run() {
        try {
            jugador = new Jugador("?", socket);

            String primerMensaje = jugador.getIn().readLine();

            if (primerMensaje == null || !primerMensaje.startsWith("NOMBRE:")) {
                jugador.enviar("ERROR:Se esperaba NOMBRE:<nombre>");
                jugador.cerrar();
                return;
            }

            String nombre = primerMensaje.substring(7).trim();

            jugador = new Jugador(nombre, socket);

            if (!gameManager.agregarJugador(jugador)) {
                jugador.enviar("ERROR:Sala llena o partida en curso");
                jugador.cerrar();
                return;
            }

            System.out.println(nombre + " se conectó");

        } catch (Exception e) {
            System.out.println("Error en registro: " + e.getMessage());
        }
    }

}
