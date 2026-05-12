package Modelo;

/*
 * Es una clase abstracta, lo que significa que no se puede
 * crear directamente un objeto Item sino que debe ser
 * heredada por otras clases 
 */

public abstract class Item {
	
	private String nombre;//nombre item 
	private int cantidad;// cantidad disponible item
	
	//constructores
	public Item(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	//getters y setters
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	//suma una cantidad al total de items 
	public void sumarCantidad(int valor) {
		this.cantidad += valor;
	}
	
	/*
	 * resta una cantidad al total actual de items 
	 */
	public void restarCantidad(int valor) {
		this.cantidad -= valor;
		//evita cantidades negativos
		if (this.cantidad < 0) {
			this.cantidad = 0;
		}
	}
}
