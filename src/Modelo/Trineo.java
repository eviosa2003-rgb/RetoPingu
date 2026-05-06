package Modelo;

public class Trineo extends Casilla {
	public Trineo(int posicion) {
		super(posicion);
	}

	@Override
<<<<<<< Updated upstream
	public void realizarAccion(Jugador jugador, Tablero tablero) {
		
		
=======
	public String getTipo() {
		return "TRINEU";
	}

	@Override
	public void realizarAccion(Jugador jugador) {
	
>>>>>>> Stashed changes
	}
	
	
}
