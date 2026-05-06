package Modelo;

public class Normal extends Casilla {
	public Normal(int posicion) {
		super(posicion);
	}
<<<<<<< Updated upstream
	
	
	@Override
	public void realizarAccion(Jugador jugador, Tablero tablero) {
		
=======

	@Override
	public String getTipo() {
		return "NORMAL";
	}

	@Override
	public void realizarAccion(Jugador jugador) {
>>>>>>> Stashed changes
		
	}

}
