package Modelo;


public class SueloQuebradizo extends Casilla {
	public SueloQuebradizo(int posicion) {
		super(posicion);
	}

	@Override
	public void realizarAccion(Jugador jugador) {
		if(jugador instanceof Pinguino) {
			String resultado = aplicarEfecto((Pinguino) jugador);
			System.out.println(resultado);
		}
	}
	
	public String aplicarEfecto(Pinguino p) {
		int cantidad = p.getInv().totalObjetos();
		
		if(cantidad > 5) {
			p.setPosicion(0);
		}
	}
}
