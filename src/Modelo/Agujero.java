package Modelo;

public class Agujero extends Casilla {
	public Agujero(int posicion) {
		super(posicion);
	}
	
	@Override
	public void realizarAccion(Pinguino p, Partida partida) {
		p.setPosicion(p.getPosicion() - 3);
	}
}
