package Modelo;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Partida {
	
	private Tablero tablero;
	private ArrayList<Jugador> jugadores;
	private int turnoActual;
	private int turnos;
	private boolean finalizada;
	private Jugador ganador;
	private String lastEvent;
	
	
	public Partida() {
		this.tablero = new Tablero();
		this.jugadores = new ArrayList<Jugador>();
		this.turnoActual = 0;
		this.turnos = 0;
		this.finalizada = false;
		this.ganador = null;
		this.lastEvent = "Que comience la partida";
		this.tablero.generarCasillasAleatorias();
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
		return turnoActual;
	}
	
	public void setJugadorActualIndice(int jugadorActual) {
		this.turnoActual = jugadorActual;
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
	
	public Jugador getTurnoActual() {
		if(jugadores == null || jugadores.isEmpty()) {
			return null;
		}
		
		if(turnoActual < 0 || turnoActual >= jugadores.size()) {
			turnoActual =0;
		}
		
		return jugadores.get(turnoActual);
	}
	
	public void siguienteTurno() {
		if(!jugadores.isEmpty()) {
			turnoActual = (turnoActual + 1) % jugadores.size();
			turnos++;
		}
	}
	
	public void comprobarGanador() {
		for(Jugador jugador : jugadores) {
			if(jugador.getPosicion() >= 49) {
				finalizada = true;
				ganador = jugador;
				lastEvent = jugador.getNombre() + " es el GANADOR!!";
				break;
			}
		}
	}
}
