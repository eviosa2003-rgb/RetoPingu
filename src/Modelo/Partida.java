package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable {
	private ArrayList<Jugador> jugadores;
	private int jugadorActualIndice;
	private Tablero tablero;
	private boolean finalizada;

	public Partida() {
		this.tablero = new Tablero();
		this.tablero.generarCasillasAleatorias();
		this.jugadores = new ArrayList<>();
		this.jugadorActualIndice = 0;
		this.finalizada = false;
	}

	// --- MÉTODO QUE FALTA ---
	public Jugador getJugadorActual() {
		if (jugadores != null && !jugadores.isEmpty()) {
			return jugadores.get(jugadorActualIndice);
		}
		return null;
	}

	public Jugador getTurnoActual() {
		return getJugadorActual();
	}

	public void siguienteTurno() {
		jugadorActualIndice = (jugadorActualIndice + 1) % jugadores.size();
	}

	// Getters y Setters necesarios
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getJugadorActualIndice() {
		return jugadorActualIndice;
	}

	public void setJugadorActualIndice(int i) {
		this.jugadorActualIndice = i;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void comprobarGanador() {
		/* Lógica de meta */ }

	public void setLastEvent(String s) {
	}
}
