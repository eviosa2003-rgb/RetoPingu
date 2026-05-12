package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable {
	private ArrayList<Jugador> jugadores;//lista de jugadores
	private int jugadorActualIndice;//indice del jugador actual
	private Tablero tablero;//tablero
	private boolean finalizada;//indica si la partida finalizo

	//constructores
	public Partida() {
		this.tablero = new Tablero();
		this.tablero.generarCasillasAleatorias();//
		this.jugadores = new ArrayList<>();
		this.jugadorActualIndice = 0;
		this.finalizada = false;
	}

	//controla turnos, devuelve al jugador que le toca jugar 
	public Jugador getJugadorActual() {
		//verifica si la lista existe y no esta vacia 
		if (jugadores != null && !jugadores.isEmpty()) {
			//devuelve jugador actual usando su indice 
			return jugadores.get(jugadorActualIndice);
		}
		//si no hay jugadores devuelve null
		return null;
	}

	public Jugador getTurnoActual() {
		return getJugadorActual();
	}
	/*
	 * cambia el turno al siguiente jugador
	 * y cuando llegue el ultimo jugador vuelve al inicio
	 */
	public void siguienteTurno() {
		jugadorActualIndice = (jugadorActualIndice + 1) % jugadores.size();
	}

	// Getters y Setters 
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
	 }

	public void setLastEvent(String s) {
	}
}
