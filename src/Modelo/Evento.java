package Modelo;

<<<<<<< Updated upstream
import java.util.Random;

public class Evento extends Casilla{ 
=======
public class Evento extends Casilla {
>>>>>>> Stashed changes
	
	private String[] eventos = {"pez", "bolas", "rapido", "lento", "pierdeTurno", "pierdeItem", "motos"};
	
	public Evento(int posicion) {
		super(posicion);
	}
<<<<<<< Updated upstream
	
	public void realizarAccion(Jugador jugador, Tablero tablero) {
		Random rand = new Random();
		String evento = eventos[rand.nextInt(6)];
=======

	@Override
	public String getTipo() {
		return "INTERROGANT";
	}

	@Override
	public void realizarAccion(Jugador jugador) {
>>>>>>> Stashed changes
		
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
