package Modelo;

import java.util.ArrayList;
public class Inventario {
	
	private ArrayList<Item> lista;
	
	public Inventario() {
		this.lista = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getLista() {
		return lista;
	}
	
	public void setLista(ArrayList<Item> lista) {
		this.lista = lista;
	}
	
	public int getCantidad(String nombre) {
		for (Item i : lista) {
			if( i.getNombre().equalsIgnoreCase(nombre)) {
				return i.getCantidad();
			}
		}
		return 0;
	}
	
	public void añadirItem(Item item) {
		for (Item i : lista) {
			if (i.getNombre().equalsIgnoreCase(item.getNombre())) {
				i.sumarCantidad(item.getCantidad());
				return;
			}
		}
		lista.add(item);
	}
	
	public boolean gastarItem(String nombre, int cantidad) {
		for(Item i : lista) {
			if (i.getNombre().equalsIgnoreCase(nombre)) {
				if (i.getCantidad() >= cantidad) {
					i.restarCantidad(cantidad);
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public void eliminarVacios() {
		lista.removeIf(i -> i.getCantidad() <= 0);
	}

}
