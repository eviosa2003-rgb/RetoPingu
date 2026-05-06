package Modelo;

import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.Random;
=======
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
		
	}	
=======
		this.lastEvent = "Que comience la partida";
		this.tablero.generarCasillasAleatorias();
	}

>>>>>>> Stashed changes
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
		this.finalizada = true;
	}
	
	public String getLastEvent() {
		return lastEvent;
	}
	
	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
	
<<<<<<< Updated upstream
	public Jugador getJugadorActual() {
	    if (jugadores == null || jugadores.isEmpty()) {
	        return null;
	    }
	    if (jugadorActual < 0 || jugadorActual >= jugadores.size()) {
	    	jugadorActual = 0;
	    }
	    return jugadores.get(jugadorActual);
=======
	/** Devuelve el jugador cuyo turno es ahora */
	public Jugador getTurnoActual() {
		if (jugadores == null || jugadores.isEmpty()) {
			return null;
		}
		if (turnoActual < 0 || turnoActual >= jugadores.size()) {
			turnoActual = 0;
		}
		return jugadores.get(turnoActual);
>>>>>>> Stashed changes
	}

	/** Alias de getTurnoActual() */
	public Jugador getJugadorActual() {
		return getTurnoActual();
	}
	
	public void siguienteTurno() {
		if (!jugadores.isEmpty()) {
<<<<<<< Updated upstream
			jugadorActual = (jugadorActual + 1) % jugadores.size();
=======
			turnoActual = (turnoActual + 1) % jugadores.size();
>>>>>>> Stashed changes
			turnos++;
		}
	}

	/** Alias de siguienteTurno() */
	public void pasarAlSiguienteJugador() {
		siguienteTurno();
	}
	
	public void comprobarGanador() {
<<<<<<< Updated upstream
		for(Jugador jugador : jugadores) {
=======
		for (Jugador jugador : jugadores) {
>>>>>>> Stashed changes
			if (jugador.getPosicion() >= 49) {
				finalizada = true;
				ganador = jugador;
				setLastEvent(jugador.getNombre() + " GANADOR!!!");
				break;
			}
		}
	}

	/**
	 * Busca el jugador que controla el pingüino dado.
	 * Como Pinguino extiende Jugador, compara por referencia directa.
	 */
	public Jugador getJugadorDEPinguino(Pinguino pinguino) {
		for (Jugador j : jugadores) {
			if (j == pinguino) {
				return j;
			}
		}
		return null;
	}
}
