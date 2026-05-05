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
import Modelo.Pinguino;
import Modelo.SueloQuebradizo;
import Modelo.Trineo;

public class GestorTablero {
	

    public String ejecutarCasilla(Partida partida, Pinguino pinguino, Casilla casilla) {
    	
    String mensaje = "";
    	
    	if (casilla instanceof Oso) {
    		mensaje = "OSO_ATAQUE";
    		
    	}
    	else if (casilla instanceof Oso) {
    		int destino = partida.getTablero().buscarAgujeroAnterior(jugador.getPosicion());
    		jugador.setPosicion(destino);
    		mensaje = jugador.getNombre() + "cae en un agujero y retroce a la casilla " + destino +".";
    		
    	}
    	else if (casilla instanceof Agujero) {
    		int destino = partida.getTablero().buscarAgujeroAnterior(jugador.getPosicion());
    		jugador.setPosicion(destino);
    		mensaje = jugador.getNombre() + "cae en el último trineo y no avanza más.";
    		
    	}
    	else if (casilla instanceof Trineo) {
    		int destino = partida.getTablero().buscarAgujeroAnterior(jugador.getPosicion);
    		if (destino != jugador.getPosicion()) {
    			jugador.setPosicion(destino);
    			mensaje = jugador.getNombre() + "usa un trineo y avanza hasta la casilla " + destino + ".";
    			
    		}
    		else {
    			mensaje = jugador.getNombre() + " cae el último trineo y no avanza más.";
    			
    		}
    	}
    	else if (casilla instanceof Evento) {
    		int evento = (int) (Math.random() * 6);
    		
    		switch (evento) {
    		case 0:
    			jugador.añadirItem(newPez("pez", 1));
    			mensaje = jugador.getNombre() + " consigue 1 pez.";
    			break;
    		case 1:
    			int bolas = (int) (Math.random());
    			jugador.añadirItem(new BolaDeNieve("bola", bolas));
    			mensaje = jugador.getNombre() + "consigue " + bolas + "bolas de nieve.";
    			break;
    			
    		case 2:
    			jugador.añadirItem(new Dado ("rapido", 1, 5, 10));
    			mensaje = jugador.gwNombre() + " consigue un dado rapido.";
    			break;
    			
    		case 3:
    			jugador.añadirItem(new Dado ("lento", 1, 1, 3));
    			mensaje = jugador.getNombre() + "consigue un dado lento";
    			break;
    		
    		case 4:
    			jugador.perderTurno();
    			mensaje = jugador.getNombre() + "pierde un turno";
    			break;
    			
    		default:
    			Item item = jugador.getInv().buscarPorNombre("bola");
    			
    			if (item == null) {
    				item = jugador.getInv().buscarPorNombre("pez");
    				
    			}
    			
    			if (item == null) {
    				item = jugador.getInv().buscarPorNombre("rapido);");
    				
    			}
    			
    			if (item == null) {
    				item = jugador.getInv().buscarPorNombre("lento");
    				
    			}
    			
    			if (item != null) {
    				item.restarCantidad(1);
    				jugador.getInv().eliminarVacios();
    				mensaje = jugador.getNombre() + "pierde 1 objeto del inventario.";
    				
    			}
    			else {
    				mensaje = jugador.getNombre() + "no tenia objetos que perder.";
    				
    			}
    				
    			break;
    				
    			}
    		}
    	else if (casilla instanceof SueloQuebradizo) {
    		mensaje = ((SueloQuebradizo) casilla).aplicarEfecto(jugador);
    		
    	}
    	else {
    		mensaje = jugador.getNombre() + "cae en una casilla normal";
    		
    	}
    	partida.setLastEvent(mensaje);	
    	return mensaje;
    	}
    
}