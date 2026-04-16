package Controlador;

public class GestorJugador {
	public void jugadorUsaItem(String nombreItem) {
	
	}
	
	public void jugadorSeMueve(Jugador j, int pasos, Tablero t) {
		int nuevaPos = j.getPosicion()+pasos;
	
	
	if(nuevaPos > 49) {
		nuevaPos = 49;
	}
	if (nuevaPos < 0 ) {
		nuevaPos = 0;
	}
	j.setPosicion(nuevaPos);
	
	}
	public void jugadorFinalizaTurno(Jugador j) {
		
	}
	public void pinguinoEvento(Pinguino p) {
		
	}
	public void pingüinoGuerra(Pinguino p1, Pinguino p2) {
		
	}
	public void focaInteractua(Pinguino p, Foca F) {
		
	}
	
}
