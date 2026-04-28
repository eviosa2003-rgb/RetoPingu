package Modelo;

public class Pinguino extends Jugador {
	
	private Inventario inv;
	private int bolas;
	
	public Pinguino(String nombre, String color, int posicion, Inventario inv) {
		super(nombre, color, posicion);
		this.inv = inv;
		this.bolas = 0;
	}
	 public Inventario getInv() {
	        return inv;
	    }

	    public void setInv(Inventario inv) {
	        this.inv = inv;
	    }

	    public void gestionarBatalla(Pinguino otro) {
	        int bolas1 = this.bolas;
	        int bolas2 = otro.bolas;
	        
	        this.bolas = 0;
	        otro.bolas = 0;
	        
	        if (bolas1 > bolas2) {
	        	otro.posicon -= (bolas1 - bolas2);
	        }else if (bolas2 > bolas1) {
	        	this.posicion -= (bolas2 - bolas1);
	        }
	    }

	    public boolean usarItem(Item i) {
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
	        
	        int actual = inv.getCantidad(nombre);
	        
	        if ( actual < max) {
	        	inv.añadirItem(i);
	        }
	    }

	    public void quitarItem(Item i) {
	       
	    	inv.gastarItem( i.getNombre(), i.getCantidad());
	    	inv.eliminarVacios();
	    }
}
