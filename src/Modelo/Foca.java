package Modelo;

public class Foca extends Jugador{
	
	private boolean soborno;
	
	public Foca(String nombre, String color, int posicion) {
		super(nombre, color, posicion);
		this.soborno = false;
	}
	
	public boolean isSoborno() {
		return soborno;
	}
	
	public void setSoborno(boolean soborno) {
		this.soborno = soborno;
	}
	
	public void aplastarJugador(Pinguino p) {
		//implementar aplastar jugador
		
		for(Item i : p.getInv().getLista()) {
			int mitad = i.getCantidad() / 2;
			i.restarCantidad(mitad);
		}
		p.getInv().eliminarVacios();
	}
	
	public void golpearJugador(Pinguino p, Partida partida) {
		
		for(int i = p.getPosicion() - 1; i >= 0; i--) {
			Casilla c = partida.getTablero().getCasillas().get(i);
			
			if(c instanceof SueloQuebradizo) {
				p.setPosicion(i);
				return;
			}
		}
		p.setPosicion(0);
	}
	
	public boolean esSobornado() {
		return soborno;
	}
}
