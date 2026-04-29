package Modelo;

public class Trineo extends Casilla {
	public Trineo(int posicion) {
		super(posicion);
	}

	public void realizarAccion(Partida partida, Jugador jugador) {
		
		for(int i = posicion + 1; i < partida.getTablero().getCasillas().size(); i++) {
			Casilla c = partida.getTablero().getCasillas().get(i);
			
			if(c instanceof Trineo) {
				jugador.setPosicion(i);
				partida.setLastEvent("Avanzas al siguiente trineo.");
				return;
			}
		}
		partida.setLastEvent("No hay mas trineos");
	}
}
