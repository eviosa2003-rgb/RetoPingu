package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/*
 *implementamos serializable para poder guardar y cargar el inventario desde base de datos 
 */
public class Inventario implements Serializable {
	//la lista que almacena todos los items 
	private ArrayList<Item> lista;

	//constructor
	public Inventario() {
		this.lista = new ArrayList<Item>();
	}
	
	//getter y setter
	public ArrayList<Item> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Item> lista) {
		this.lista = lista;
	}
	
	/* 
	 * Busca un item por su nombre.
	 *pide por parametro  nombre del item a buscar
	 * retorna el item encontrado y null si no existe
	 */
	public Item buscarPorNombre(String nombre) {
		//recorre todos los items de lista 
		for (Item item : lista) {
			//comprueba sin importar mayusculas ni minusculas 
			if (item.getNombre().equalsIgnoreCase(nombre)) {
				//devuelve el item si es encontrado en lista 
				return item;
			}
		}
		//devuelve null si no se encontro.
		return null;
	}
	
	/*
	 * devuelve la cantidad de un item en especifico
	 * pide por parametro nombre del item
	 * retorna la cantidad del item disponible y si no hay 0
	 */
	public int getCantidad(String nombre) {
		//recorre todos los items de lista
		for (Item i : lista) {
			//comprueba que coincidan los nombres 
			if (i.getNombre().equalsIgnoreCase(nombre)) {
				//devuelve la cantidad del item buscado
				return i.getCantidad();	
			}
		}
		//si no existe dvuelve 0
		return 0;
	}
	
	/*
	 * añade un item
	 * si el item ya existe le suma cantidades 
	 * si suppera el maximo permitido se ajusta al maximo 
	 * pide por parametros el nuevo item que se quiere añadir 
	 * y la maxima cantidad permitida 
	 */
	public void añadirItem(Item nuevo, int maximo) {
		//busca si el item ya existe 
		Item stock = buscarPorNombre(nuevo.getNombre());
		
		//si no existe se añade en el inventario
		if (stock == null) {
			int cantidad = nuevo.getCantidad();
			
			//controla que no supere el maximo
			if (cantidad > maximo) {
				cantidad = maximo;
			}
			//actualiza la cantidad final
			nuevo.setCantidad(cantidad);
			//añade el item a la lista 
			lista.add(nuevo);
			
		//si ya existe
		} else {
			//suma cantidades y controla que no supere el maximo
			int total = stock.getCantidad() + nuevo.getCantidad();
			if (total > maximo) {
				total = maximo;
			}
			//actualiza la cantidad del item 
			stock.setCantidad(total);
		}
	}
	
	/*
	 *gasta una cantidad especifica de item
	 *pide por parametro el nombre y la cantidad de item a gastar 
	 *devuelve verdadero si se pudo gastar y negativo si no se pudo   
	 */
	public boolean gastarItem(String nombre, int cantidad) {
		//recorre todos items del inventario y lo busca poe el nombre 
		for (Item i : lista) {
			if (i.getNombre().equalsIgnoreCase(nombre)) {
				//verifica si hay suficiente cantidad 
				if (i.getCantidad() >= cantidad) {
					//se resta la cantidad soicitada 
					i.restarCantidad(cantidad);
					return true;
				}
				//devuelve si no hay sufuiciente cantidad 
				return false;
			}
		}
		//el item no existe 
		return false;
	}
	/*
	 * calcula el total de objetos del inventario
	 * devuelve la suma de todos los items
	 */
	public int totalObjetos() {
		int total = 0;
		//recorre la lista y suma el total de cada item
		for (Item item : lista) {
			total += item.getCantidad();
		}
		return total;
	}
	/*
	 * elimina los items cuya cantidad sea menor o igual a 0
	 */
	public void eliminarVacios() {
		//removeIF elimina automaticamente los elementos
		lista.removeIf(i -> i.getCantidad() <= 0);
	}

}
