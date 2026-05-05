package Modelo;

public class Pinguino extends Jugador {
	private Inventario inv;
	
	public Pinguino(String nombre, String color, int posicion, Inventario inv) {
		super(nombre, color, posicion);
		this.inv = inv;
	}
	public Inventario getInv() {
		return inv;
	}
	public void setInv(Inventario inv) {
		this.inv = inv;
	}
	public String gestionarBatalla(Pinguino p2) {
		int bolas1 = inv.getCantidad("bolas");
		int bolas2 = p2.getInv().getCantidad("bolas");
		
		inv.gastarItem("bolas", bolas1);
		p2.getInv().gastarItem("bolas", bolas2);
		
		String mensaje = "Batalla bola de nieve";
		if(bolas1 > bolas2) {
			p2.moverPosicion(-(bolas1 - bolas2));
			mensaje +="Gana " + p2.getNombre() + " avanza " + (bolas1 - bolas2); 
		}else if (bolas2 < bolas1) {
			this.moverPosicion(-(bolas2 - bolas1));
			mensaje +="Gana " + this.getNombre() + " avanza " + (bolas2 - bolas1);
		}else {
			mensaje += "Empate";
		}
		return mensaje;
	}
	
	public boolean usarItem(Item i) {
		return inv.gastarItem(i.getNombre(), 1);
	}
	
	public void añadirItem(Item i) {
		String nombre = i.getNombre().toLowerCase();
		int maximo = 1000;
		
		if(nombre.equals("normal") || nombre.equals("rapido") || nombre.equals("lento")) {
			maximo = 3;
		} else if (nombre.equals("pez")) {
			maximo = 2;
		} else if (nombre.equals("bola")) {
			maximo = 6;
		}
		
		inv.añadirItem(i, maximo);
	}
	
	public void quitarItem(Item i) {
		
		inv.gastarItem(i.getNombre(), getPosicion());
		inv.eliminarVacios();
	}
}
	