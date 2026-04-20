package Modelo;

import java.util.ArrayList;

public class SueloQuebradizo extends Casilla {
	public SueloQuebradizo(int posicion) {
		super(posicion);
	}

	@Override
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
	}
}
