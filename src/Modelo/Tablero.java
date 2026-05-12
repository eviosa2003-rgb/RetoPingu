package Modelo;

import java.io.Serializable; // Importante para guardar en BD
import java.util.ArrayList;
import java.util.Random;

public class Tablero implements Serializable { 

	private ArrayList<Casilla> casillas;//almacena todas las casillas del tablero
	 //constructor
	public Tablero() {
		this.casillas = new ArrayList<Casilla>();
	}
	
	//getter y setters
	public ArrayList<Casilla> getCasillas() {
		return casillas;
	}
	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}
	
	//devuelve la casilla concreta segun su posicion
	public Casilla getCasilla(int posicion) {
		if (posicion >= 0 && posicion < casillas.size()) {
			return casillas.get(posicion);
		}
		return null;
	}

	public String serializar() {
		// Crea una representación en texto del tablero para guardarlo
		StringBuilder sb = new StringBuilder();
		//recorre las casillas y guarda el tipo que es
		for (Casilla c : casillas) {
			sb.append(c.getClass().getSimpleName()).append(",");
		}
		return sb.toString();
	}

	public void deserializar(String datos) {
		// Aquí reconstruirías el tablero desde el texto de la BD
	}

	//genera un tablero aleatorio con distintos tipos de casillas 
	public void generarCasillasAleatorias() {
		casillas.clear(); // Limpiamos por si acaso
		casillas.add(new Normal(0));//primera casilla normal
		Random r = new Random();

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
			//añade casilla a tablero
			casillas.add(c);
		}
		//ultima casilla siempre normal 
		casillas.add(new Normal(49));
	}
	
	//busca la siguiente casilla de tipo Trineo apartir de la posicion actual
	public int buscarSiguienteTrineo(int posicionActual) {
		for (int i = posicionActual + 1; i < casillas.size(); i++) {
			if (casillas.get(i) instanceof Trineo) {
				return i; //devuelve la posicion de trineo encontraso
			}
		}
		return posicionActual; // Si no hay más, se queda donde está
	}

	// Busca el último agujero antes de la posición actual
	public int buscarAgujeroAnterior(int posicionActual) {
		for (int i = posicionActual - 1; i >= 0; i--) {
			if (casillas.get(i) instanceof Agujero) {
				return i;
			}
		}
		return 0; // Al inicio si no hay agujeros antes
	}
}
