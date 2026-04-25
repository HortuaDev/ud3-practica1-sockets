package net.salesianos.servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    public static final int MIN_JUGADORES = 2;
    public static final int MAX_JUGADORES = 4;
    public static final int TOTAL_RONDAS = 5;

    private final List<Jugador> jugadores = new ArrayList<>();

    private int rondaActual = 0;
    private int turnoActual = 0;
    private boolean partidaEnCurso = false;

    private final Random random = new Random();

}