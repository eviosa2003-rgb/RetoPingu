package Modelo;

import java.util.ArrayList;
import java.util.Random;

public class Partida {
	
	private Tablero tablero;
	private ArrayList<Jugador> jugadores;
	private int turnoActual;
	private boolean finalizada;
	
	
	public Partida() {
		this.jugadores = new ArrayList<Jugador>();
		this.turnoActual = 0;
		this.finalizada = false;
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
	
	public void añadirJugador(Jugador j) {
		if(jugadores.size() < 4) {
			jugadores.add(j);
		}
	}
	
	public Jugador getJugadorActual() {
	    if (jugadores == null || jugadores.isEmpty()) {
	        return null;
	    }
	    if (turnoActual < 0 || turnoActual >= jugadores.size()) {
	    	turnoActual = 0;
	    }
	    return jugadores.get(turnoActual);
	}
	
	public void ejecutarTurno() {
		Jugador j = jugadores.get(turnoActual);
		
		if (j instanceof Foca) {
			((Foca) j).actuar(this);
		}else {
			Pinguino p = (Pinguino) j;
			int tirada = new Random().nextInt(6) + 1;
			p.moverPosicion(tirada);
			
			Casilla c = tablero.getCasillas(p.getPosicion());
			c.realizarAccion(p, this);
			
			comprobarBatallas(p);
		}
		comprobarVictoria(j);
		siguienteTurno();
	}
	
	public void comprobarBatallas(Pinguino p) {
		for (Jugador j : jugadores) {
			if (j instanceof Pinguino && j != p) {
				if (j.getPosicion() == p.getPosicion()) {
					p.gestionarBatalla((Pinguino) j);
				}
			}
		}
	}
	
	public void comprobarVictoria(Jugador j) {
		if (j.getPosicion() >= 49) {
			finalizada = true;
			System.out.println("Ganador" + j.getNombre());
		}
	}
	
	public void siguienteTurno() {
		turnoActual = (turnoActual + 1) % jugadores.size();
	}
}
