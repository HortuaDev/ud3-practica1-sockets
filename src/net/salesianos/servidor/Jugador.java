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
                new InputStreamReader(socket.getInputStream()));
    }

    public void enviar(String mensaje) {
        out.println(mensaje);
    }

    public void sumarPuntos(int puntos) {
        this.puntuacion += puntos;
    }

    public void cerrar() {
        try {
            if (!socket.isClosed())
                socket.close();
        } catch (IOException e) {
            System.err.println("Error cerrando socket: " + e.getMessage());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

}