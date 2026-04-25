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

	    public void gestionarBatalla(Pinguino p) {
	        int bolasPropias = inv.getCantidad("bola");
	        int bolasRival = p.getInv().getCantidad("bola");
	        
	        inv.gastarItem("bola", bolasPropias);
	        p.getInv().gastarItem("bola", bolasPropias);
	    	
	        if (bolasPropias > bolasRival) {
	        	p.moverPosicion(-(bolasPropias - bolasRival));
	        } else if (bolasRival > bolasPropias) {
	        	this.moverPosicion(-(bolasRival - bolasPropias));
	        }
	    }

	    public void usarItem(Item i) {
	    	return inv.gastarItem(i.getNombre(), 1);
	    }

	    public void añadirItem(Item i) {
	        String nombre = i.getNombre().toLowerCase();
	        int max = 1000;
	        
	        if (nombre.equals("rapida") || nombre.equals("lento") || nombre.equals("normal") ) {
	        	max = 3;
	        } else if (nombre.equals("pez")) {
	        	max = 2;
	        } else if (nombre.equals("bolas")) {
	        	max = 6;
	        }
	    }

	    public void quitarItem(Item i) {
	       
	    	inv.gastarItem( i.getNombre(), i.getCantidad());
	    	inv.eliminarVacios();
	    }
}
