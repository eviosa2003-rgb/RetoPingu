package Modelo;

public class Foca extends Jugador{
	
	private boolean soborno;
	private int turnoBloqueado;
	
	public Foca(String nombre, String color, int posicion) {
		super(nombre, color, posicion);
		this.soborno = false;
		this.turnoBloqueado = 0;
	}
	
	public boolean isSoborno() {
		return soborno;
	}
	
	public void setSoborno(boolean soborno) {
		this.soborno = soborno;
	}
	
	public void setTurnoBloqueado(int turnoBloqueado) {
		this.turnoBloqueado = turnoBloqueado;
	}
	
	public int getTurnoBloqueado() {
		return turnoBloqueado;
	}
	
	public void aplastarJugador(Pinguino p) {
		//implementar aplastar jugador
		int mitad = p.getInv().totalObjetos() / 2;
		
		while(mitad > 0) {
			if(p.getInv().gastarItem("bola", 1)) {
				mitad--;
			}else if (p.getInv().gastarItem("pez", 1)) {
				mitad--;
			}else if (p.getInv().gastarItem("rapido", 1)) {
				mitad--;
			}else if (p.getInv().gastarItem("lento", 1)) {
				mitad--;
			}else if (p.getInv().gastarItem("normal", 1)) {
				mitad--;
			}else {
				mitad = 0;
			}
		}
		p.getInv().eliminarVacios();
	}
}
