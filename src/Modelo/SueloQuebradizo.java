package Modelo;


public class SueloQuebradizo extends Casilla {
	
	//constructor
	public SueloQuebradizo(int posicion) {
		super(posicion);
	}

	@Override
	public void realizarAccion(Jugador jugador) {
		
		//verifica si jugador es un pinguino
		if(jugador instanceof Pinguino) {
			
			//convierte jugador a tipo pinguino
			String resultado = aplicarEfecto((Pinguino) jugador);
			System.out.println(resultado);
		}
	}
	
	public String aplicarEfecto(Pinguino p) {
		//obtiene la cantidad de objetos 
		int cantidad = p.getInv().totalObjetos();
		
		//si es mas de 5 vuelve al inicio
		if(cantidad > 5) {
			p.setPosicion(0);
			return "Jugador: "+ p.getNombre() + " vuelve al inicio";
		//si tiene entre 1 y 5 pierde el turno
		} else if (cantidad > 0 && cantidad <= 5){
			p.perderTurno();
			return "Jugador: "+ p.getNombre() + " pierde el turno";
		//si no tiene objetos no recibe ninguna penalizacion
		}else {
			return "Jugador: "+ p.getNombre() + " pasa sin penalización.";
		}
	}
}
