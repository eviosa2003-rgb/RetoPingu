package Modelo;
/*
 * Hereda de la clase Jugador 
 * posee todos sus atributos y métodos.
 */
public class Foca extends Jugador{
	
	private boolean soborno;
	private int turnoBloqueado;
	
	//constructor
	public Foca(String nombre, String color, int posicion) {
		super(nombre, color, posicion);
		this.soborno = false;
		this.turnoBloqueado = 0;
	}
	
	//getters y setters 
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
	
	/*
	 * metodo que sirve para aplastar al jugador
	 * El pinguino pierde la mitad de los objetos
	 * de su inventario.
	 */
	public void aplastarJugador(Pinguino p) {
		//calcula la mitad de los objetos del inventario
		int mitad = p.getInv().totalObjetos() / 2;
		
		//mientras objetos sea mas grande que 0
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
			
			//si no hay nada para eliminar el ciclo termina 	
			}else {
				mitad = 0;
			}
		}
		//elimina objetos vacios de la lista item.
		p.getInv().eliminarVacios();
	}
}
