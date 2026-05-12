package Controlador;

import java.util.ArrayList;
import java.util.Random;

import Modelo.Casilla;
import Modelo.Dado;
import Modelo.Foca;
import Modelo.Inventario;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Pinguino;

public class GestorPartida {
	private Partida partida;
	private GestorTablero gestorTablero;
	private GestorBBDD gestorBBDD;
	private Random random;
	private GestorJugador gestorJugador;

	// 
	
	public GestorPartida() {
		this.partida = null;
		this.gestorTablero = new GestorTablero();
		this.gestorJugador = new GestorJugador();
		this.gestorBBDD = new GestorBBDD();
		this.random = new Random();
	}

	public void nuevaPartida(int numHumanos) {
		this.partida = new Partida();
		ArrayList<Jugador> jugadores = new ArrayList<>();
		String[] colores = { "Rojo", "Amarillo", "Verde", "Azul" };

		for (int i = 0; i < numHumanos && i < 4; i++) {
			Inventario inv = new Inventario();
			inv.añadirItem(new Dado("normal", 1, 1, 6), 1);
			jugadores.add(new Pinguino("Jugador " + (i + 1), colores[i], 0, inv));
		}

		// Añadimos siempre a la Foca como IA
		jugadores.add(new Foca("IA Foca", "Gris", 0));

		this.partida.setJugadores(jugadores);
		this.partida.setJugadorActualIndice(0);
	}

	public int tirarDado(Pinguino p, Dado d) {
		int resultado = d.tirar(random);
		p.moverPosicion(resultado);

		// Comprobar casilla de destino
		Casilla c = partida.getTablero().getCasilla(p.getPosicion());
		gestorTablero.ejecutarCasilla(partida, p, c);

		partida.comprobarGanador();
		if (!partida.isFinalizada()) {
			partida.siguienteTurno();
		}
		return resultado;
	}

	public String ejecutarTurnoIA() {
		Jugador actual = partida.getJugadorActual();
		if (!(actual instanceof Foca)) {
			return "No es turno de IA";
		}

		Foca f = (Foca) actual;
		int dado = new Random().nextInt(6) + 1;
		f.moverPosicion(dado);

		String msj = f.getNombre() + " ha movido " + dado;

		if (!partida.isFinalizada()) {
			partida.siguienteTurno();
		}
		return msj;
	}

	// --- MÉTODOS DE BBDD ---
	public void guardarPartida() {
		if (partida != null) {
			gestorBBDD.guardarBBDD(partida);
		}
	}

	public void cargarPartida(int id) {
		Partida cargada = gestorBBDD.cargarBBDD(id);
		if (cargada != null) {
			this.partida = cargada;
		}
	}

	public Partida getPartida() {
		return partida;
	}

}
