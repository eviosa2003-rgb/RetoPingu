package Modelo;

import java.io.Serializable; // Importante para guardar en BD
import java.util.ArrayList;
import java.util.Random;

public class Tablero implements Serializable { // Añadido Serializable

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

	// --- CORRECCIÓN DE ERROR DE COMPILACIÓN ---
	// Antes tenías "getcasilla" con 'c' minúscula, pero GestorPartida busca
	// "getCasilla"
	public Casilla getCasilla(int posicion) {
		if (posicion >= 0 && posicion < casillas.size()) {
			return casillas.get(posicion);
		}
		return null;
	}

	// --- MÉTODOS PARA EL GESTOR BBDD ---
	// Estos métodos son los que te daban error en GestorBBDD.java
	public String serializar() {
		// Crea una representación en texto del tablero para guardarlo
		StringBuilder sb = new StringBuilder();
		for (Casilla c : casillas) {
			sb.append(c.getClass().getSimpleName()).append(",");
		}
		return sb.toString();
	}

	public void deserializar(String datos) {
		// Aquí reconstruirías el tablero desde el texto de la BD (puedes dejarlo vacío
		// por ahora para que compile)
	}

	// --- LÓGICA DE GENERACIÓN ---
	public void generarCasillasAleatorias() {
		casillas.clear(); // Limpiamos por si acaso
		casillas.add(new Normal(0));
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
		return posicionActual; // Si no hay más, se queda donde está
	}

	public int buscarAgujeroAnterior(int posicionActual) {
		for (int i = posicionActual - 1; i >= 0; i--) {
			if (casillas.get(i) instanceof Agujero) {
				return i;
			}
		}
		return 0; // Al inicio si no hay agujeros antes
	}
}
