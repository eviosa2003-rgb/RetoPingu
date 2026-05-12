package Modelo;

public class Pinguino extends Jugador {
	private Inventario inv;//inventario propio
	
	//constructor
	public Pinguino(String nombre, String color, int posicion, Inventario inv) {
		super(nombre, color, posicion);
		this.inv = inv;
	}
	
	//getters y setters
	public Inventario getInv() {
		return inv;
	}
	public void setInv(Inventario inv) {
		this.inv = inv;
	}
	
	/*
	 * gastiona la batalla de bola de nieve entre el pinguino y el otro
	 * devuelve el resultado 
	 */
	public String gestionarBatalla(Pinguino p2) {
		//cantidad de bolas de ambos rivales
		int bolas1 = inv.getCantidad("bolas");
		int bolas2 = p2.getInv().getCantidad("bolas");
		
		//ambos jugadores gastn todas sus bolas 
		inv.gastarItem("bolas", bolas1);
		p2.getInv().gastarItem("bolas", bolas2);
		
		//mensaje inicial de batalla
		String mensaje = "Batalla bola de nieve";
		
		//si el jugador ctual tiene mas bolas 
		if(bolas1 > bolas2) {	
			//rival retrocede posiciones
			p2.moverPosicion(-(bolas1 - bolas2));
			mensaje +="Gana " + p2.getNombre() + " avanza " + (bolas1 - bolas2); 
		
		//si el rival tiene mas bolas 
		}else if (bolas2 > bolas1) {
			//jugador actual retrocede
			this.moverPosicion(-(bolas2 - bolas1));
			mensaje +="Gana " + this.getNombre() + " avanza " + (bolas2 - bolas1);
		
			//si tienen la misma cantidad empate y no pasa nada
		}else {
			mensaje += "Empate";
		}
		return mensaje;
	}
	
	/*
	 * usa el item del inventario
	 */
	public boolean usarItem(Item i) {
		return inv.gastarItem(i.getNombre(), 1);
	}
	
	/*
	 * añade un item al inventario dependiendo del tipo se pone un maximo o no 
	 */
	public void añadirItem(Item i) {
		//pasa el nombre a minuscula 
		String nombre = i.getNombre().toLowerCase();
		int maximo = 1000; // un maximo creado 
		
		//se establecen limites
		if(nombre.equals("normal") || nombre.equals("rapido") || nombre.equals("lento")) {
			maximo = 3;
		} else if (nombre.equals("pez")) {
			maximo = 2;
		} else if (nombre.equals("bola")) {
			maximo = 6;
		}
		//añade el item al inventario
		inv.añadirItem(i, maximo);
	}
	/*
	 * elimina item del del inventario
	 */
	public void quitarItem(Item i) {
		
		inv.gastarItem(i.getNombre(), 1);//gasta una cantidad de item 
		inv.eliminarVacios();//elimina items vacios del inventario
	}
}
	