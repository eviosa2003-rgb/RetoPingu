package Modelo;

import java.util.ArrayList;
import java.util.Random;

public class Partida {
	
	private Tablero tablero;
	private ArrayList<Jugador> jugadores;
	private int turnos;
	private int jugadorActual;
	private boolean finalizada;
	private Jugador ganador;
	private String lastEvent;
	
	public Partida() {
		this.jugadores = new ArrayList<Jugador>();
		this.turnos = 0;
		this.jugadorActual = 0;
		this.finalizada = false;
		this.ganador = null;
		
	}	
	public Tablero getTablero() {
		return tablero;
	}
	
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public int getTurnos() {
		return turnos;
	}
	
	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}
	
	public int getJugadorActualIndice() {
		return jugadorActual;
	}
	
	public void setJugadorActualIndice(int jugadorActual) {
		this.jugadorActual = jugadorActual;
	}
	
	public boolean isFinalizada() {
		return finalizada;
	}
	
	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	public Jugador getGanador() {
		return ganador;
	}
	
	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}
	
	public String getLastEvent() {
		return lastEvent;
	}
	
	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
	
	public Jugador getJugadorActual() {
	    if (jugadores == null || jugadores.isEmpty()) {
	        return null;
	    }
	    if (jugadorActual < 0 || jugadorActual >= jugadores.size()) {
	    	jugadorActual = 0;
	    }
	    return jugadores.get(jugadorActual);
	}
	
	public void siguienteTurno() {
		if (!jugadores.isEmpty()) {
			jugadorActual = (jugadorActual + 1) % jugadores.size();
			turnos++;
		}
	}
	
	public void comprobarGanador() {
		for(Jugador jugador : jugadores) {
			if (jugador.getPosicion() >= 49) {
				finalizada = true;
				ganador = jugador;
				setLastEvent(jugador.getNombre() + " GANADOR!!!");
				break;
			}
		}
	}
}
