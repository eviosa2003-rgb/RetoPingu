package Controlador;

import java.util.Random;

import Modelo.Casilla;
import Modelo.Dado;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Pinguino;

public class GestorPartida {

	private Partida partida;
	private GestorTablero gestorTablero;
	private GestorJugador gestorJugador;
	private GestorBBDD gestorBBDD;
	private Random random;
	
	public GestorPartida() {
		this.partida = null;
		this.gestorTablero = new GestorTablero();
		this.gestorJugador = new GestorJugador();
		this.gestorBBDD = new GestorBBDD();
		this.random = new Random();
	}

	public void nuevaPartida() {
		this.partida = new Partida();
		System.out.println("¡Nueva partida creada!");
	}

	public int tirarDado(Jugador j, Dado dado) {
		int resultado = dado.tirar(random);
		System.out.println(j.getNombre() + " ha sacado un " + resultado);
		gestorJugador.jugadorSeMueve(j, resultado, this.partida.getTablero());
		return resultado;
	}

	/** Ejecuta el turno completo del jugador que toca jugar */
	public void ejecutarTurnoCompleto() {
		if (this.partida == null) {
			System.out.println(" No hay ninguna partida activa. ");
			return;
		}
		Jugador jugadorActual = this.partida.getJugadorActual();

		if (jugadorActual.tieneTurnoBloqueado()) {
			System.out.println(jugadorActual.getNombre() + " pierde el turno.");
			jugadorActual.desbloquearTurno();
			siguienteTurno();
			return;
		}
		procesarTurnoJugador(jugadorActual);
		actualizarEstadoTablero();
		guardarPartida();
		siguienteTurno();
	}

	public void procesarTurnoJugador(Jugador jugador) {
		System.out.println("Turno de: " + jugador.getNombre());

		Dado dado = new Dado("normal", 1, 1, 6);

		if (jugador.getInventario().tieneDado()) {
			dado = jugador.getInventario().usarDado();
			System.out.println(jugador.getNombre() + " usa un dado especial.");
		}

		int resultado = tirarDado(jugador, dado);
		int posicion = jugador.getPosicion();
		Casilla casilla = this.partida.getTablero().getCasilla(posicion);

		if (jugador instanceof Pinguino) {
			gestorTablero.ejecutarCasilla(this.partida, (Pinguino) jugador, casilla);
		}
	}

	public void actualizarEstadoTablero() {
		gestorTablero.comprobarFinTurno(this.partida);
	}

	public void siguienteTurno() {
		this.partida.pasarAlSiguienteJugador();
		Jugador siguiente = this.partida.getJugadorActual();
		if (siguiente != null) {
			System.out.println("Ahora le toca a: " + siguiente.getNombre());
		}
		gestorBBDD.guardarTurnoActual(this.partida);
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void guardarPartida() {
		if (this.partida != null) {
			gestorBBDD.guardarPartida(this.partida);
			System.out.println("Partida guardada correctamente.");
		} else {
			System.out.println("No hay partida para guardar.");
		}
	}

	public void cargarPartida(int id) {
		this.partida = gestorBBDD.cargarPartida(id);
		if (this.partida != null) {
			System.out.println("Partida cargada correctamente.");
		} else {
			System.out.println("No se ha encontrado ninguna partida con ese ID.");
		}
	}
}
