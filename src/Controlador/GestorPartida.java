package Controlador;

import java.util.Random;

public class GestorPartida {
	private Partida partida;
	private GestorTablero gestorTablero;
	private GestorBBDD gestorBBDD;
	private Random random;
	private Jugador jugador;
	
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
    	
    	if (jugadorActual.tieneTurnoBloqueado()) {
    		System.out.println(jugadorActual.getNombre() + "pierde el turno.");
    		jugadorActual.desbloquearTurno();
    		siguienteTurno();
    		return;
    	}
    	procesarturnoJugador(jugadorActual);
    	
    	actualizarEstadoTablero();
    	
    	guardarPartida();
    	
    	siguienteTurno();
    	
    	
    }

    
    
    
    
    public void procesarTurnoJugador(Jugador jugador) {
		
    	System.out.println("Turno de: " + jugador.getNombre());
    	
    	Dado dado = new Dado();
    	
    	if (jugador.getInventario().tieneDado()) {
    		dado = jugador.getInventario().usarDado();
    		System.out.println(jugador.getNombre() + "usa un dado especial. ");
    		
    	}
    	int resultado = tirarDado (jugador, dado);
    	
    	int posicion = jugador.getPosicion();
    	
    	Casilla casilla = this.partida.getTablero().getCasilla(posicion);
    	
    	gestorTablero.ejecutarCasilla(this.partida, jugador.getPinguino(), casilla);
   
    }

    
    public void actualizarEstadoTablero() {
    	
    	gestorTablero.comprobarFinTurno(this.partida);
    
    }

    public void siguienteTurno() {

    this.partida.pasarAlSiguienteJugador();
    Jugador siguiente = this.partida.getJugadorActual();
    
    System.out.println("Ahora le toca a: " + siguiente.getNombre());
    
    gestorBBDD.guardarTurnoActual(this.partida);
    
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void guardarPartida() {
    	
    	if (this.partida !=null) {
    		gestorBBDD.guardarPartida(this.partida);
    		System.out.println("Partida guardda correctamente. ");
    	}
    	else {
    		System.out.println("No hay partida para guardar. ");
    	}
    
    }

    public void cargarPartida(int id) {
        this.partida = gestorBBDD.cargarPartida(id);
        
        if(this.partida != null) {
        	System.out.println("Partida cargada correctamente. ");
        	
        }
        else {
        	System.out.println("No se ha encontrado ninguna partida con ese ID.");
        }
    }
}

