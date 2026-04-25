package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;

public class Jugador {

    private String nombre;
    private int puntuacion;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Jugador(String nombre, Socket socket) throws IOException {
        this.nombre = nombre;
        this.puntuacion = 0;
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
    }

}