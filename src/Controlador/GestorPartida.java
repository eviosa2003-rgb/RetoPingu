package Controlador;

import java.util.Random;

public class GestorPartida {
	private Partida partida;
	private GestorTablero gestorTablero;
	private GestorBBDD gestorBBDD;
	private Random random;
	
	public GestorPartida() {
		this.partida = null;
		this.gestorTablero = new GestorTablero();
		this.gestorJugador = new GestorJugador();
		this.gestorBBDD = new Gestor BBDD();
		this.random = new Random();
	}
	public void nuevaPartida() {
		this.partida = new Partida();
		System.out.println("¡Nueva partida creada!");
		
	}

    public int tirarDado(Jugador j, Dado dado) {
    	int resultado = dado.tirar(random);
    	System.out.println(jugador.getNombre() + "ha sacado un" + resultado);
    	
    	gestorJugador.jugadorSeMueve(j, resultado, this.partida.getTablero());
    	
    	return resultado;
    }
   
    // Ejecuta el turno completo del jugador que toca jugar

    public void ejecutarTurnoCompleto() {
    	if (this.partida == null) {
    		System.out.println(" No hay ninguna partida activa. ");
    		return;
    	}
    	Jugador jugadorActual = this.partida.getJugadorActual();
    	
    	
    	
    	
    	
    	
    }

    public void procesarTurnoJugador(Jugador j) {
        // TODO: procesar turno de un jugador
    }

    public void actualizarEstadoTablero() {
        // TODO: actualizar estado del tablero
    }

    public void siguienteTurno() {
        // TODO: pasar al siguiente turno
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void guardarPartida() {
        // TODO: guardar la partida usando GestorBBDD
    }

    public void cargarPartida(int id) {
        // TODO: cargar partida desde BBDD
    }
}

