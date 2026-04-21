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
	
	public void moverPosicion (int p) {
		//implementar movimiento
		this.posicion += p;
		
		if(this.posicion < 0) {
			this.posicion = 0;
		}
		
		if(this.posicion > 49) {
			this.posicion = 49;
		}
	}
}
