package Modelo;

import java.io.Serializable;

public abstract class Jugador implements Serializable {
	private int posicion; 
	private String nombre;
	private String color;
	private Inventario inventario;//inventario del jugador 
	private int turnoPerdido;//cantidad de turnos perdidios 
	
	//constructor
	public Jugador(String nombre, String color, int posicion) {
		this.nombre = nombre;
		this.color = color;
		this.posicion = posicion;
		this.inventario = new Inventario();
		this.turnoPerdido = 0;
	}

	// Getters y Setters 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	// Métodos para evitar errores en GestorBBDD y GestorPartida
	public int getTurnosPerdidos() {
		return turnoPerdido;
	}

	public void setTurnosPerdidos(int t) {
		this.turnoPerdido = t;
	}
	// Conexión con Inventario
	public int getPeces() {
		return 0;
	} 

	public void setPeces(int n) {
	}

	public int getBolasNieve() {
		return 0;
	}

	public void setBolasNieve(int n) {
	}

	public boolean hasDadoRapido() {
		return false;
	}

	public void setDadoRapido(boolean b) {
	}

	public boolean hasDadoLento() {
		return false;
	}

	public void setDadoLento(boolean b) {
	}

	
	public void perderTurno() {
		this.turnoPerdido++;
	}
	
	//indica si el jugador debe saltarse el turno 
	public boolean saltarTurno() {
		return turnoPerdido > 0;
	}
	
	public void consumirTurnoPerdido() {
		if (turnoPerdido > 0) {
			turnoPerdido--;
		}
	}
	
	/*
	 * mueve al jugador una cantidad de posiciones 
	 * controla el tablero desde 0 a 49
	 */
	public void moverPosicion(int p) {
		//suma el movimiento a la posicion actual 
		this.posicion += p;
		//evita posiciones negativas
		if (this.posicion < 0) {
			this.posicion = 0;
		}
		//evita superar el final del tablero
		if (this.posicion > 49) {
			this.posicion = 49;
		}
	}
}
