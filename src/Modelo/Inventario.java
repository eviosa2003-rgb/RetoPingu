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
	
	public Item buscarPorNombre(String nombre) {
		for(Item item : lista) {
			if(item.getNombre().equalsIgnoreCase(nombre)){
				return item;
			}
		}
		return null;
	}
	public int getCantidad(String nombre) {
		for (Item i : lista) {
			if( i.getNombre().equalsIgnoreCase(nombre)) {
				return i.getCantidad();
			}
		}
		return 0;
	}
	public void añadirItem(Item nuevo, int maximo) {
		Item stock = buscarPorNombre(nuevo.getNombre());
		if(stock == null) {
			int cantidad = nuevo.getCantidad();
			if(cantidad > maximo) {
				cantidad = maximo;
			}
			nuevo.setCantidad(cantidad);
			lista.add(nuevo);
		}else {
			int total = stock.getCantidad() + nuevo.getCantidad();
			if(total > maximo) {
				total = maximo;
			}
			stock.setCantidad(total);
		}
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
	
	public int totalObjetos() {
		int total = 0;
		for(Item item : lista) {
			total += item.getCantidad();
		}
		return total;
	}
	
	public void eliminarVacios() {
		lista.removeIf(i -> i.getCantidad() <= 0);
	}

}
