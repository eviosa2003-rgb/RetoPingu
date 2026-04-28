package Modelo;

import java.util.ArrayList;

public class SueloQuebradizo extends Casilla {
	public SueloQuebradizo(int posicion) {
		super(posicion);
	}

	@Override
	public void realizarAccion() {
		System.out.println("¡El suelo se rompe!");
	}
}
