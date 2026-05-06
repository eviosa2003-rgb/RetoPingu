package Modelo;

<<<<<<< Updated upstream
import java.util.ArrayList;

=======
>>>>>>> Stashed changes
public class SueloQuebradizo extends Casilla {
	public SueloQuebradizo(int posicion) {
		super(posicion);
	}

	@Override
<<<<<<< Updated upstream
	public void realizarAccion(Jugador jugador, Tablero tablero) {
		
		 int posActual = jugador.getPosicion();
		  ArrayList<Casilla> casillas = tablero.getCasillas();
		  
		  for(int i = posActual -1; i >= 0; i--) {
			  if(casillas.get(i) instanceof SueloQuebradizo) {
				  jugador.setPosicion(i);
				  return;
			  }
		  }
		  jugador.setPosicion(0);
=======
	public String getTipo() {
		return "TERRA_TRENCAT";
	}

	@Override
	public void realizarAccion(Jugador jugador) {
		if (jugador instanceof Pinguino) {
			String resultado = aplicarEfecto((Pinguino) jugador);
			System.out.println(resultado);
		}
	}
	
	public String aplicarEfecto(Pinguino p) {
		int cantidad = p.getInv().totalObjetos();
		
		if (cantidad > 5) {
			p.setPosicion(0);
			return "Jugador: " + p.getNombre() + " vuelve al inicio";
		} else if (cantidad > 0 && cantidad <= 5) {
			p.perderTurno();
			return "Jugador: " + p.getNombre() + " pierde el turno";
		} else {
			return "Jugador: " + p.getNombre() + " pasa sin penalización.";
		}
>>>>>>> Stashed changes
	}
}
