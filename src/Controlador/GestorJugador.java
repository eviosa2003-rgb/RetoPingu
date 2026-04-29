package Controlador;

import java.util.Random;

import Modelo.Foca;
import Modelo.Item;
import Modelo.Jugador;
import Modelo.Pez;
import Modelo.Pinguino;
import Modelo.Tablero;

public class GestorJugador {
	
	Random r = new Random();

	
	public void jugadorUsaItem(String nombreItem) {
	
	
	}
	
	public void jugadorSeMueve(Jugador j, int pasos, Tablero t) {
		int nuevaPos = j.getPosicion()+pasos;
	
		if(nuevaPos > 49) {
			nuevaPos = 49;
		}
		if (nuevaPos < 0 ) {
			nuevaPos = 0;
		}
		j.setPosicion(nuevaPos);
	
	
	}
	
	public void jugadorFinalizaTurno(Jugador j) {	
		System.out.println(j.getNombre() + " termina turno");
	}
	
	public void pinguinoEvento(Pinguino p) {
		p.añadirItem(new Pez("pez", 1));
	}
	
	public void pingüinoGuerra(Pinguino p1, Pinguino p2) {
		p1.gestionarBatalla(p2);
	}
	
	public void focaInteractua(Pinguino p, Foca f) {
		boolean tienePez = false;
		
		for(Item i : p.getInv().getLista()) {
			if(i.getNombre().equalsIgnoreCase("pez")) {
				tienePez = true;
				p.quitarItem(new Pez("pez", 1));
				break;
			}
		}
		if(!tienePez) {
			f.aplastarJugador(p);
			f.golpearJugador(p);	
		}
	}
	
}
