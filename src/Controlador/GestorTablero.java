package Controlador;

import java.util.Random;

import Modelo.Agujero;
import Modelo.BolaDeNieve;
import Modelo.Casilla;
import Modelo.Dado;
import Modelo.Evento;
import Modelo.Item;
import Modelo.Jugador;
import Modelo.Oso;
import Modelo.Partida;
import Modelo.Pez;
import Modelo.Pinguino;
import Modelo.SueloQuebradizo;
import Modelo.Trineo;

public class GestorTablero {
	

    public String ejecutarCasilla(Partida partida, Pinguino pinguino, Casilla casilla) {
    	
    String mensaje = "";
    	
    	if (casilla instanceof Oso) {
    		mensaje = "OSO_ATACA";
    		
    	}
    	else if (casilla instanceof Oso) {
    		int destino = partida.getTablero().buscarAgujeroAnterior(pinguino.getPosicion());
    		pinguino.setPosicion(destino);
    		mensaje = pinguino.getNombre() + "cae en un agujero y retroce a la casilla " + destino +".";
    		
    	}
    	else if (casilla instanceof Agujero) {
    		int destino = partida.getTablero().buscarAgujeroAnterior(pinguino.getPosicion());
    		pinguino.setPosicion(destino);
    		mensaje = pinguino.getNombre() + "cae en el último trineo y no avanza más.";
    		
    	}
    	else if (casilla instanceof Trineo) {
    		int destino = partida.getTablero().buscarAgujeroAnterior(pinguino.getPosicion());
    		if (destino != pinguino.getPosicion()) {
    			pinguino.setPosicion(destino);
    			mensaje = pinguino.getNombre() + "usa un trineo y avanza hasta la casilla " + destino + ".";
    			
    		}
    		else {
    			mensaje = pinguino.getNombre() + " cae el último trineo y no avanza más.";
    			
    		}
    	}
    	else if (casilla instanceof Evento) {
    		int evento = (int) (Math.random() * 6);
    		
    		switch (evento) {
    		case 0:
    			pinguino.añadirItem(new Pez("pez", 1));
    			mensaje = pinguino.getNombre() + " consigue 1 pez.";
    			break;
    		case 1:
    			int bolas = (int) (Math.random());
    			pinguino.añadirItem(new BolaDeNieve("bola", bolas));
    			mensaje = pinguino.getNombre() + "consigue " + bolas + "bolas de nieve.";
    			break;
    			
    		case 2:
    			pinguino.añadirItem(new Dado ("rapido", 1, 5, 10));
    			mensaje = pinguino.getNombre() + " consigue un dado rapido.";
    			break;
    			
    		case 3:
    			pinguino.añadirItem(new Dado ("lento", 1, 1, 3));
    			mensaje = pinguino.getNombre() + "consigue un dado lento";
    			break;
    		
    		case 4:
    			pinguino.perderTurno();
    			mensaje = pinguino.getNombre() + "pierde un turno";
    			break;
    			
    		default:
    			Item item = pinguino.getInv().buscarPorNombre("bola");
    			
    			if (item == null) {
    				item = pinguino.getInv().buscarPorNombre("pez");
    				
    			}
    			
    			if (item == null) {
    				item = pinguino.getInv().buscarPorNombre("rapido);");
    				
    			}
    			
    			if (item == null) {
    				item = pinguino.getInv().buscarPorNombre("lento");
    				
    			}
    			
    			if (item != null) {
    				item.restarCantidad(1);
    				pinguino.getInv().eliminarVacios();
    				mensaje = pinguino.getNombre() + "pierde 1 objeto del inventario.";
    				
    			}
    			else {
    				mensaje = pinguino.getNombre() + "no tenia objetos que perder.";
    				
    			}
    				
    			break;
    				
    			}
    		}
    	else if (casilla instanceof SueloQuebradizo) {
    		mensaje = ((SueloQuebradizo) casilla).aplicarEfecto(pinguino);
    		
    	}
    	else {
    		mensaje = pinguino.getNombre() + "cae en una casilla normal";
    		
    	}
    	partida.setLastEvent(mensaje);	
    	return mensaje;
    	}

    
}