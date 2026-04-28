package Modelo;

import java.util.Random;

public class Dado extends Item{
	
	private int max;
	private int min;
	private Random r;
	
	public Dado(String tipo) {
		super(tipo, 1);
		r = new Random();
		
		if (tipo.equalsIgnoreCase("rapida")) {
			min = 5;
			max = 10;
		} else if(tipo.equalsIgnoreCase("lento")) {
			min = 1;
			max = 3;
		}else {
			min = 1;
			max = 6;
		}
	}
	

	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	public int getMin() {
		return min;
	}
	
	public int tirar(Random r) {
		return r.nextInt(max-min + 1) + min;
	}
}
