package Modelo;

import java.util.ArrayList;
public class Tablero {
	private ArrayList<Casilla> casillas;
	
	public Tablero() {
		this.casillas = new ArrayList<Casilla>();
	}
	
	public ArrayList<Casilla> getCasillas(){
		return casillas;
	}
	
	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}
	
	public void actualizarTablero() {
		//implementar actualizacion de tablero
	}
}
