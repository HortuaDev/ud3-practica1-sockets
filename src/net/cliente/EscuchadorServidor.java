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

}
