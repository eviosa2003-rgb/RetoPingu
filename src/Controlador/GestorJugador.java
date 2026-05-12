package Controlador;

import java.util.Random;

import Modelo.BolaDeNieve;
import Modelo.Dado;
import Modelo.Foca;
import Modelo.Item;
import Modelo.Jugador;
import Modelo.Pez;
import Modelo.Pinguino;
import Modelo.Tablero;

public class GestorJugador {
	
	private Random random = new Random();
	
	// hace qie use un item del inventario
	
	public boolean jugadorUsaItem(Pinguino jugador, String nombreItem) {
		return jugador.getInv().gastarItem(nombreItem, 1);
	}

	
	// mueve el jugador por el tablero
	
	public void jugadorSeMueve(Jugador j, int pasos, Tablero t) {
		int nuevaPos = j.getPosicion() +pasos;
	
		if(nuevaPos > 49) {
			nuevaPos = 49;
		}
		if (nuevaPos < 0 ) {
			nuevaPos = 0;
		}
		j.setPosicion(nuevaPos);
		
		//actualiza la casilla del jugador
	
	}
	
	public void jugadorFinalizaTurno(Jugador j) {	
		if (j.saltarTurno()) {
			j.consumirTurnoPerdido();
		}
	}
	
	
	
	public void pinguinoEvento(Pinguino p) {
		
		int evento = random.nextInt(6);
		switch (evento) {
		case 0:
			p.añadirItem(new Pez("pez", 1));
			break;
		
		case 1:
			p.añadirItem(new BolaDeNieve ("bola", random.nextInt(3) + 1));
			break;
			
		case 2:
			p.añadirItem(new Dado("rapidp", 1, 5, 10));
			break;
			
		case 3:
			p.añadirItem(new Dado("lento", 1, 1, 3));
			break;
			
		case 4: 
			p.perderTurno();
			break;
		
		default:
			Item item = p.getInv().buscarPorNombre("bola");
			if (item == null) {
				item = p.getInv().buscarPorNombre("pez");
			}
			if (item != null) {
				item.restarCantidad(1);
				p.getInv().eliminarVacios();
			}
			break;
		}
	}
		
	
	// Gestiona una batalla entre dos pinguinos
	
	public String pingüinoGuerra(Pinguino p1, Pinguino p2) {
		
	return p1.gestionarBatalla(p2);
	}
	
	public String focaInteractua(Pinguino p, Foca f, Tablero t) {
		if (p.getInv().gastarItem("pez", 1)) {
			f.setTurnoBloqueado(2);
			f.setSoborno(true);
			return "Foca_Interactua: " + p.getNombre() + "soborno a la foca con un pez.";
		}
		else {
			int agujeroAnterior = t.buscarAgujeroAnterior(p.getPosicion());
			p.setPosicion(agujeroAnterior);
			return "Foca_Interactua: ¡La foca empuja a " + p.getNombre() + " a un agujero!";
		}
	}
	
	
	
}
