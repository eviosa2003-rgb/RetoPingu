package Modelo;

import java.util.ArrayList;
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

}
