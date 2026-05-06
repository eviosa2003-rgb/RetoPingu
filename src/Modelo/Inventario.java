package Modelo;

import java.util.ArrayList;
import java.util.Random;

public class Inventario {
	
	private ArrayList<Item> lista;
	private static final int MaxBolasNieve = 6;
	private static final int MaxPeces = 2;
	private static final int MaxDados = 3;
	
	public Inventario() {
		this.lista = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getLista() {
		return lista;
	}
	
	public void setLista(ArrayList<Item> lista) {
		this.lista = lista;
	}
<<<<<<< Updated upstream
=======
	
	public Item buscarPorNombre(String nombre) {
		for (Item item : lista) {
			if (item.getNombre().equalsIgnoreCase(nombre)) {
				return item;
			}
		}
		return null;
	}

	public int getCantidad(String nombre) {
		for (Item i : lista) {
			if (i.getNombre().equalsIgnoreCase(nombre)) {
				return i.getCantidad();
			}
		}
		return 0;
	}

	public void añadirItem(Item nuevo, int maximo) {
		Item stock = buscarPorNombre(nuevo.getNombre());
		if (stock == null) {
			int cantidad = nuevo.getCantidad();
			if (cantidad > maximo) {
				cantidad = maximo;
			}
			nuevo.setCantidad(cantidad);
			lista.add(nuevo);
		} else {
			int total = stock.getCantidad() + nuevo.getCantidad();
			if (total > maximo) {
				total = maximo;
			}
			stock.setCantidad(total);
		}
	}
	
	public boolean gastarItem(String nombre, int cantidad) {
		for (Item i : lista) {
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
		for (Item item : lista) {
			total += item.getCantidad();
		}
		return total;
	}

	/** Alias de totalObjetos() */
	public int contarObjetos() {
		return totalObjetos();
	}
	
	public void eliminarVacios() {
		lista.removeIf(i -> i.getCantidad() <= 0);
	}
>>>>>>> Stashed changes

	// Métodos de ayuda para peces
	

	public boolean tienePez() {
		return getCantidad("pez") > 0;
	}

	public void usarPez() {
		gastarItem("pez", 1);
		eliminarVacios();
	}

	public boolean puedeAnadirPez() {
		return getCantidad("pez") < 2;
	}

	public void anadirPez() {
		añadirItem(new Pez("pez", 1), 2);
	}

	// Métodos de ayuda para bolas de nieve

	public boolean puedeAnadirBola() {
		return getCantidad("bola") < 6;
	}

	public void anadirBola() {
		añadirItem(new BolaDeNieve("bola", 1), 6);
	}

	// Métodos de ayuda para dados

	public boolean tieneDado() {
		return getCantidad("rapido") > 0 || getCantidad("lento") > 0;
	}

	/** Devuelve el primer dado especial (rápido o lento) del inventario. */
	public Dado usarDado() {
		Item rapido = buscarPorNombre("rapido");
		if (rapido != null && rapido.getCantidad() > 0) {
			gastarItem("rapido", 1);
			eliminarVacios();
			return new Dado("rapido", 1, 5, 10);
		}
		Item lento = buscarPorNombre("lento");
		if (lento != null && lento.getCantidad() > 0) {
			gastarItem("lento", 1);
			eliminarVacios();
			return new Dado("lento", 1, 1, 3);
		}
		// Fallback: dado normal
		return new Dado("normal", 1, 1, 6);
	}

	public boolean puedeAnadirDado() {
		int total = getCantidad("rapido") + getCantidad("lento") + getCantidad("normal");
		return total < 3;
	}

	public void anadirDadoRapido() {
		añadirItem(new Dado("rapido", 1, 5, 10), 3);
	}

	public void anadirDadoLento() {
		añadirItem(new Dado("lento", 1, 1, 3), 3);
	}

	// Perder objeto aleatorio

	/** Quita 1 unidad de un item aleatorio del inventario. Devuelve el nombre o null. */
	public String perderObjetoAleatorio(Random random) {
		eliminarVacios();
		if (lista.isEmpty()) return null;
		int idx = random.nextInt(lista.size());
		Item item = lista.get(idx);
		item.restarCantidad(1);
		String nombre = item.getNombre();
		eliminarVacios();
		return nombre;
	}
}
