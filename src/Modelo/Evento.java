package Modelo;

import java.util.Random;

public class Evento extends Casilla{ 
	
	private String[] eventos = {"pez", "bolas", "rapido", "lento", "pierdeTurno", "pierdeItem", "motos"};
	
	public Evento(int posicion) {
		super(posicion);
	}
	
	public void realizarAccion() {
		Random rand = new Random();
		String evento = eventos[rand.nextInt(6)];
		
		if(evento.equals("pez")) {
			
		}else if (evento.equals("bolas")) {
			
		}else if (evento.equals("rapido")) {
			
		}else if (evento.equals("lento")) {
			
		}else if (evento.equals("pierdeTurno")) {
			
		}else if (evento.equals("pierdeItems")) {
			
		}else if (evento.equals("motos")) {
			
		}
	}
}
