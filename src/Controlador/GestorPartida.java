package Controlador;

import java.util.ArrayList;
import java.util.Random;

import Modelo.Agujero;
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
	private Jugador jugador;
	private GestorJugador gestorJugador;
	
	public GestorPartida() {
		this.partida = null;
		this.gestorTablero = new GestorTablero();
		this.gestorJugador = new GestorJugador();
		this.gestorBBDD = new GestorBBDD();
		this.random = new Random();
	}
	public void nuevaPartida(int numHumanos) {
		this.partida = new Partida();
		ArrayList<Jugador> Jugaodres = new ArrayList<Jugador>();
		System.out.println("¡Nueva partida creada!");
		
		String[] colores = {"Azul", "Rojo", "Verde", "Amarillo"};
		
		for (int i= 0; i < numHumanos && i < 4; i ++) {
			Inventario inv = new Inventario();
			
			inv.actualizar(new Dado("normal", 1, 1, 6),3);
			jugadores.add(new Pinguino("Jugador " + (i + 1, colores[i], inv)));
		}
		
		Foca cpu = new Foca ("IA Foca", "Gris", 0);
		jugadores.add(cpu);
		
		this.partida.setJugadores(jugadores);
		this.partida.setJugadorActualIndice(0);
		this.partida.setUltimoEvento("Partida preparada. Turno de  " + jugadores.get(0).getNombre());
		
	}
	
	public String jugarTurnoHumano(Dado dado) {
		if (partida == null || partida.isFinalizada()) return "Partida no disponible.";
		
		Jugador actual = partida.getJugadorActual();
		
		if (actual.saltarTruno()) {
			actual.consumirTurnoPerdido();
			
			String msj = actual.getNombre() + "Pierde su turno. ";
			
			partida.lastEvent(msj);
			siguienteTurno();
			return msj;
		}
		
		int resultado = dado.tirar(random);
		gestorJugador.jugadorSeMueve(actual, resultado, partida.getTablero());
		String mensaje = actual.getNombre() + " avanza " + resultado + " casillas.";
		
		if (actual instanceof Pinguino) {
			Casilla casilla = partida.getTablero().getCasillas(actual.getPosicion());
			
			String msjCasilla = gestorTablero.ejecutarCasilla(partida, (Pinguino) actual, casilla);
			
			if (msjCasilla != null) mensaje += " " + msjCasilla;
			String choques = comprobarChoquesJugadores();
			
			if (!choques.isEmpty()) {
				mensaje += " | " + choques;
			}
		
		}
		
		partida.comprobarGanador();
		
		if (!partida.isFinalizada()){
			siguienteTurno();
		}
		
		return mensaje;
		
	}
	
	public String ejecutarTurnoIA() {
		Jugadoor actual = partida.getJugadorActual();
		if (!(actual instanceof Foca)) return null;
		
		Foca f = (Foca) actual;
		String mensaje;
		
		if (f.getTurnoBloqueado > 0) {
			f.setTurnoBloqueado(f.getTurnosBloqueada() - 1);
			mensaje = "La foca esta comiendo pescado, esta distrahida en ello.";
			
		}
		else {
			int posicionAnterior = f.getPosicion();
			int pasos = random.nextInt(6) + 1;
			gestorJugador.jugadorSeMueve(f, pasos, partida.getTablero());
			int posicionNeva = f.getPosicion();
			Casilla casillaDestino = partida.getTablero().getCasillas(posicionNueva);
			
			if (casillaDestino instanceof Agujero) {
				int destinoA = partida.getTablero().buscarAgujeroAnterior(posicionNueva);
				f.setPosicion(destinoA);
				mensaje = "La foca (IA) cae en un agujerp y retrocede.";
			
			}
			else if (casillaDestino instanceof Oso){
				f.setPosicion(0);
				mensaje = "La foca (IA) se asusta del oso y huye al inicio.";
				
			}
			else {
				mensaje = "La foca (IA) se mueve " + pasos + " casillas";
				
			}
			
			for (Jugador j : partida.getJugadores()) {
				if (j instanceof Pinguino) {
					if (j.getPosicion() > posicionAnterior && j.getPosicion() < posicionNueva) {
						f.aplastarJugador((Pinguino) j);
						partida.setLastEvent("La foca pasó por encima de " + j.getNombre() + "y pierde el turno. ");
					}
				}
			}
			
			String choques = comprobarChoquesJugadores();
			if (!choques.isEmpty()) {
				mensaje += " | " + choques;
			}
			
		}
		
	partida.setUltimoEvento(mensaje);
	siguienteTurno();
	return mensaje;
		
	}
	
	private String comprobarChoquesJugadores() {
		StringBuldier sb = new StringBuilder();
		for (int i = 0; i < partida.getJugadores().size(); i++) {
			for (int j= i + 1; j < partida.getJugadores().size(); j++) {
				Jugador j1 = partida.getJugadres().get(i);
				
				if (j1.getPosicion() > 0 && j1.getPosicion() == j2.gwtPosicion()) {
					sb.append(gestorJugador.pingüinoGuerra((Pinguino) j1, (Pinguino) j2)).append(" ");
					
				}
				else if (j1 instanceof Pinguino && j2 instanceof Foca) {
					sb.append(gestorJugador.focaInteractua(Pinguino) j1, (Foca) j2, partida.getTablero())).append(" ");
					
				}
				else if ( j1 instanceof Foca && j2 instanceof Pinguino) {
					sb.append(gestorJugador.focaInteractua(Pinguino) j2, (Foca) j1, partida.getTablero())).append(" ");
				}
			}
		}
		return sb.toString().trim();
	}
	public void siguienteTurno() {
		partida.siguienteTurno();
	}
	
	public Partida getPartida() { 
		return this.partida;
	}
	public void guardarPartida() {
		gestorBBDD.guardarBBDD(partida);
	}
	public void cargarPartida(int id) {
		Partida cargada = gestorBBDD.guardarBBDD(partida);
		if (cargada != null) this.partida = cargada;
	}
	
	
	
	
	
	
	
}

