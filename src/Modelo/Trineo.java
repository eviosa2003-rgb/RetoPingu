package Modelo;

public class Trineo extends Casilla {
	public Trineo(int posicion) {
		super(posicion);
	}

	@Override
	public void realizarAccion(Pinguino p, Partida partida) {
		for (int i = p.getPosicion() + 1; i < 50; i++) {
			if (partida.getTablero().getCasillas().get(i) instanceof Trineo ) {
				p.setPosicion(i);
				break;
			}
		}
	}
	
	
}
