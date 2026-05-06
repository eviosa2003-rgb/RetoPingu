package Modelo;

public class Foca extends Jugador {
	
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
	
<<<<<<< Updated upstream
	public void aplastarJugador(Pinguino p) {
		//implementar aplastar jugador
	}
	
	public void golpearJugador(Pinguino p) {
		//implementar golpear jugador
	}
	
	public boolean esSobornado() {
		return soborno;
=======
	public void setTurnoBloqueado(int turnoBloqueado) {
		this.turnoBloqueado = turnoBloqueado;
	}
	
	public int getTurnoBloqueado() {
		return turnoBloqueado;
	}
	
	/** Aplasta al jugador: le quita la mitad de sus objetos */
	public void aplastarJugador(Pinguino p) {
		int mitad = p.getInv().totalObjetos() / 2;
		
		while (mitad > 0) {
			if (p.getInv().gastarItem("bola", 1)) {
				mitad--;
			} else if (p.getInv().gastarItem("pez", 1)) {
				mitad--;
			} else if (p.getInv().gastarItem("rapido", 1)) {
				mitad--;
			} else if (p.getInv().gastarItem("lento", 1)) {
				mitad--;
			} else if (p.getInv().gastarItem("normal", 1)) {
				mitad--;
			} else {
				mitad = 0;
			}
		}
		p.getInv().eliminarVacios();
>>>>>>> Stashed changes
	}

	/** Golpea al jugador: lo retrocede 3 casillas */
	public void golpearJugador(Pinguino p) {
		p.moverPosicion(-3);
		System.out.println("La foca golpea a " + p.getNombre() + " y retrocede 3 casillas.");
	}
}
