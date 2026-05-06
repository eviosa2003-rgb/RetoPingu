package Modelo;

public abstract class Jugador {
	
	private int posicion;
	private String nombre;
	private String color;
	
	public Jugador(String nombre, String color, int posicion) {
		this.nombre = nombre;
		this.color = color;
		this.posicion = posicion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public int getPosicion() {
		return posicion;
	}
	
<<<<<<< Updated upstream
	public void moverPosicion (int p) {
		//implementar movimiento
=======
	public int getTurnoPerdido() {
		return turnoPerdido;
	}

	public Inventario getInventario() {
		return inventario;
	}
	
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	
	public void setTurnoPerdido(int turnoPerdido) {
		this.turnoPerdido = turnoPerdido;
	}
	
	public void perderTurno() {
		this.turnoPerdido++;
	}

	/** Alias de perderTurno() usado en GestorTablero */
	public void bloquearTurno() {
		this.turnoPerdido++;
	}
	
	public boolean saltarTurno() {
		return turnoPerdido > 0;
	}

	/** Indica si el jugador tiene el turno bloqueado */
	public boolean tieneTurnoBloqueado() {
		return turnoPerdido > 0;
	}
	
	public void consumirTurnoPerdido() {
		if (turnoPerdido > 0) {
			turnoPerdido--;
		}
	}

	/** Alias de consumirTurnoPerdido() usado en GestorPartida */
	public void desbloquearTurno() {
		if (turnoPerdido > 0) {
			turnoPerdido--;
		}
	}
	
	public void moverPosicion(int p) {
>>>>>>> Stashed changes
		this.posicion += p;
		
		if (this.posicion < 0) {
			this.posicion = 0;
		}
		
		if (this.posicion > 49) {
			this.posicion = 49;
		}
	}
}
