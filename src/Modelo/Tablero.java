package Modelo;

import java.util.ArrayList;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
public class Tablero {
	
	private ArrayList<Casilla> casillas;
	
	public Tablero() {
		this.casillas = new ArrayList<Casilla>();
	}
	
	public ArrayList<Casilla> getCasillas() {
		return casillas;
	}
	
	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}
	
	public void actualizarTablero() {
		// implementar actualizacion de tablero
	}
<<<<<<< Updated upstream
=======
	
	public void generarCasillasAleatorias() {
		casillas.clear();
		casillas.add(new Normal(0));
		java.util.Random r = new java.util.Random();
		
		for (int i = 1; i < 49; i++) {
			int tipo = r.nextInt(6);
			Casilla c;
			
			switch (tipo) {
			case 0:
				c = new Oso(i);
				break;
			case 1:
				c = new Trineo(i);
				break;
			case 2:
				c = new Agujero(i);
				break;
			case 3:
				c = new Evento(i);
				break;
			case 4:
				c = new SueloQuebradizo(i);
				break;
			default:
				c = new Normal(i);
			}
			casillas.add(c);
		}
		casillas.add(new Normal(49));
	}
	
	public int buscarSiguienteTrineo(int posicionActual) {
		for (int i = posicionActual + 1; i < casillas.size(); i++) {
			if (casillas.get(i) instanceof Trineo) {
				return i;
			}
		}
		return posicionActual; // si no hay trineo, queda donde está
	}
	
	public int buscarAgujeroAnterior(int posicionActual) {
		for (int i = posicionActual - 1; i >= 0; i--) {
			if (casillas.get(i) instanceof Agujero) {
				return i;
			}
		}
		return 0;
	}

	/** Devuelve el total de casillas del tablero */
	public int getTotalCasillas() {
		return casillas.size();
	}

	/** Devuelve la casilla en la posición indicada */
	public Casilla getCasilla(int posicion) {
		if (posicion < 0) posicion = 0;
		if (posicion >= casillas.size()) posicion = casillas.size() - 1;
		return casillas.get(posicion);
	}
>>>>>>> Stashed changes
}
