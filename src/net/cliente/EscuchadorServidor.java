package net.cliente;

import java.io.BufferedReader;

public class EscuchadorServidor extends Thread {

    private final BufferedReader in;

    private volatile boolean partidaTerminada = false;
    private volatile boolean esMiTurno = false;

    public EscuchadorServidor(BufferedReader in) {
        this.in = in;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                procesarMensaje(mensaje);
            }
        } catch (java.io.IOException e) {
            if (!partidaTerminada) {
                System.out.println("Conexión con servidor perdida");
            }
        }
        partidaTerminada = true;
    }

    private void procesarMensaje(String mensaje) {

        if (mensaje.startsWith("ESPERANDO:")) {
            System.out.println("Esperando jugadores... ("
                    + mensaje.substring(10) + ")");

        } else if (mensaje.startsWith("INICIO:")) {
            System.out.println("\n¡Partida iniciada! Jugadores: "
                    + mensaje.substring(7));

        } else if (mensaje.startsWith("RONDA:")) {
            System.out.println("\n── RONDA " + mensaje.substring(6) + " ──");

        } else if (mensaje.startsWith("TURNO:")) {
            System.out.println("Turno de: " + mensaje.substring(6));

        } else if (mensaje.equals("ES_TU_TURNO")) {
            esMiTurno = true;
            System.out.println("¡ES TU TURNO! Pulsa ENTER para lanzar...");

        } else if (mensaje.startsWith("DADOS:")) {
            System.out.println("Dados: [" + mensaje.substring(6) + "]");

        } else if (mensaje.startsWith("COMBINACION:")) {
            String[] partes = mensaje.substring(12).split(":", 2);
            System.out.println(partes[0] + " → " + partes[1]);

        } else if (mensaje.startsWith("PUNTOS:")) {
            String[] partes = mensaje.substring(7).split(":");
            System.out.println(partes[0] + " obtiene " + partes[1] + " pts");

        } else if (mensaje.startsWith("MARCADOR:")) {
            System.out.println("Marcador: " + mensaje.substring(9));

        } else if (mensaje.startsWith("FIN:")) {
            System.out.println("\n=== FIN DE PARTIDA ===");
            String[] jugadores = mensaje.substring(4).split(",");
            String[] medallas = { "1.", "2.", "3.", "4." };
            for (int i = 0; i < jugadores.length; i++) {
                String[] kv = jugadores[i].split(":");
                System.out.println(medallas[i] + " "
                        + kv[0] + ": " + kv[1] + " pts");
            }
            partidaTerminada = true;

        } else if (mensaje.startsWith("DESCONECTADO:")) {
            System.out.println(mensaje.substring(13) + " se desconectó");

        } else if (mensaje.startsWith("ERROR:")) {
            System.out.println("Error: " + mensaje.substring(6));
        }
    }
}
