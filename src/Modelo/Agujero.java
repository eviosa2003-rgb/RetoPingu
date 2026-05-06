package Modelo;

public class Agujero extends Casilla {
	public Agujero(int posicion) {
		super(posicion);
	}

	@Override
<<<<<<< Updated upstream
	public void realizarAccion(Jugador jugador, Tablero tablero) {
		// TODO Auto-generated method stub
=======
	public String getTipo() {
		return "FORAT_GEL";
	}

	@Override
	public void realizarAccion(Jugador jugador) {
>>>>>>> Stashed changes
		
	}
}
