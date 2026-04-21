package Controlador;

import java.util.Random;

public class GestorTablero {
	
	private Random random;
	
	public GestorTablero() {
		this.random = new Random();
		
	}
	

    public void ejecutarCasilla(Partida partida, Pinguino pinguino, Casilla casilla) {
    	
    	Jugador jugador = partida.getJugadorDEPinguino(pinguino);
    	
    	System.out.println(jugador.getNombre() + " ha caído en una casilla: " + casilla.getTipo);
    	
    	switch (casilla.getTipo()) {
    	 
    	case "OSO":
    		// Si el jugador tiene peces, puede sobornar al oso
            if (jugador.getInventario().tienePez()) {
                jugador.getInventario().usarPez();
                System.out.println(jugador.getNombre() + " ha sobornado al oso con un pez. ¡Se salva!");
            } else {
                // Sin peces, vuelve al inicio
                pinguino.setPosicion(0);
                System.out.println(jugador.getNombre() + " ha sido atacado por el oso y vuelve al inicio.");
            }
            break;

        case "FORAT_GEL":
            // Busca el forat de gel anterior y manda al jugador allí
            int posicionActual = pinguino.getPosicion();
            int posicionAnterior = buscarForatGelAnterior(partida.getTablero(), posicionActual);
            pinguino.setPosicion(posicionAnterior);
            System.out.println(jugador.getNombre() + " cae en el forat de gel y va a la casilla " + posicionAnterior);
            break;

        case "TRINEU":
            // Avanza hasta el siguiente trineo del tablero
            int posicionTrineu = buscarSiguienteTrineu(partida.getTablero(), pinguino.getPosicion());
            pinguino.setPosicion(posicionTrineu);
            System.out.println(jugador.getNombre() + " sube al trineo y avanza hasta la casilla " + posicionTrineu);
            break;

        case "INTERROGANT":
            // Activa un evento aleatorio
            ejecutarEventoAleatorio(partida, jugador);
            break;

        case "TERRA_TRENCAT":
            // El efecto depende de cuántos objetos tiene el jugador
            int numObjetos = jugador.getInventario().contarObjetos();

            if (numObjetos > 5) {
                // Más de 5 objetos: vuelve al inicio
                pinguino.setPosicion(0);
                System.out.println(jugador.getNombre() + " cae por el suelo roto y vuelve al inicio (tenía " + numObjetos + " objetos).");
            } else if (numObjetos > 0) {
                // Entre 1 y 5 objetos: pierde un turno
                jugador.bloquearTurno();
                System.out.println(jugador.getNombre() + " pierde un turno por el suelo roto.");
            } else {
                // Sin objetos: pasa sin penalización
                System.out.println(jugador.getNombre() + " pasa por el suelo roto sin problemas (no lleva objetos).");
            }
            break;

        default:
            // Casilla normal, no pasa nada
            System.out.println("Casilla normal, no ocurre nada.");
            break;
    	}
    }
    private void ejecutarEventoAleatorio(Partida partida, Jugador jugador) {
        // Generamos un número del 0 al 5 para escoger el evento
        int evento = random.nextInt(6);

        switch (evento) {
            case 0:
                // Obtener un pez (si cabe en el inventario)
                if (jugador.getInventario().puedeAnadirPez()) {
                    jugador.getInventario().anadirPez();
                    System.out.println("¡Evento! " + jugador.getNombre() + " obtiene un pez.");
                } else {
                    System.out.println("¡Evento! " + jugador.getNombre() + " iba a obtener un pez pero el inventario está lleno.");
                }
                break;

            case 1:
                // Obtener entre 1 y 3 bolas de nieve
                int bolas = random.nextInt(3) + 1;
                int bolasAnadidas = 0;
                for (int i = 0; i < bolas; i++) {
                    if (jugador.getInventario().puedeAnadirBola()) {
                        jugador.getInventario().anadirBola();
                        bolasAnadidas++;
                    }
                }
                System.out.println("¡Evento! " + jugador.getNombre() + " obtiene " + bolasAnadidas + " bola/s de nieve.");
                break;

            case 2:
                // Obtener un dado rápido (poca probabilidad, avanza 5-10 casillas)
                if (jugador.getInventario().puedeAnadirDado()) {
                    jugador.getInventario().anadirDadoRapido();
                    System.out.println("¡Evento! " + jugador.getNombre() + " obtiene un dado rápido (avanza 5-10).");
                } else {
                    System.out.println("¡Evento! " + jugador.getNombre() + " iba a obtener un dado rápido pero el inventario está lleno.");
                }
                break;

            case 3:
                // Obtener un dado lento (valores entre 1 y 3)
                if (jugador.getInventario().puedeAnadirDado()) {
                    jugador.getInventario().anadirDadoLento();
                    System.out.println("¡Evento! " + jugador.getNombre() + " obtiene un dado lento (valores 1-3).");
                } else {
                    System.out.println("¡Evento! " + jugador.getNombre() + " iba a obtener un dado lento pero el inventario está lleno.");
                }
                break;

            case 4:
                // Perder un turno
                jugador.bloquearTurno();
                System.out.println("¡Evento! " + jugador.getNombre() + " pierde un turno.");
                break;

            case 5:
                // Perder un objeto aleatorio del inventario
                String objetoPerdido = jugador.getInventario().perderObjetoAleatorio(random);
                if (objetoPerdido != null) {
                    System.out.println("¡Evento! " + jugador.getNombre() + " pierde un objeto: " + objetoPerdido);
                } else {
                    System.out.println("¡Evento! " + jugador.getNombre() + " iba a perder un objeto pero no tiene ninguno.");
                }
                break;
        }
    }
    public void comprobarFinTurno(Partida partida) {
    	for (Jugador jugador : partida.getJugadores()) {
    		int posicion = jugador.getPinguino().getPosicion();
    		int totalCasillas = partida.getTablero().getTotalCasillas();
    		
    		if (posicion >= totalCasillas - 1) {
    			
    			partida.setGanador(jugador);
    			System.out.println("¡" + jugador.getNombre() + "ha ganado la partida!");
    			return;
    		}
    	}
    	System.out.println("Fin del turno. Nadie ha ganado todavia. ");
    }
    
}