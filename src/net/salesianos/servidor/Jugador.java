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

}