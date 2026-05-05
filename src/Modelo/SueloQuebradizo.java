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
			return "Jugador: "+ p.getNombre() + " vuelve al inicio";
		} else if (cantidad > 0 && cantidad <= 5){
			p.perderTurno();
			return "Jugador: "+ p.getNombre() + " pierde el turno";
		}else {
			return "Jugador: "+ p.getNombre() + " pasa sin penalización.";
		}
	}
}
