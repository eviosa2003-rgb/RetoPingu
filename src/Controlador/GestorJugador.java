package Controlador;

import java.util.Random;

public class GestorJugador {
	
	Random r = new Random();

<<<<<<< Updated upstream
	
	public void jugadorUsaItem(String nombreItem, Pinguino p) {
		if (nombreItem.equals("bols")) {
			
			if (p.getBolasNieve()>0) {
				p.setBolasNieve(p.getBolasNieve() -1);
				System.out.println("Has usado una bola de nieve");
			}
			else if(nombreItem.equals("pez")) {
				
				if (p.getPeces()>0) {
					p.setPeces(p.getPeces() -1);
					System.out.println("Has usado una bola de nieve");
					
				}
				else if (nombreItem.equals("dado")) {
					
					if (p.getDados()>0) {
						int tirada = r.nextInt(6) +1;
						p.setPosicion(p.getPosicion() + tirada);
						p.setDados(p.getDados() -1);
						System.out.println("Avanzas" + tirada);
					}
				}
			}
		}
	
=======
	public void jugadorUsaItem(String nombreItem) {
		// TODO: implementar uso de item por nombre
>>>>>>> Stashed changes
	}
	
	public void jugadorSeMueve(Jugador j, int pasos, Tablero t) {
		int nuevaPos = j.getPosicion() + pasos;
	
<<<<<<< Updated upstream
	
	if(nuevaPos > 49) {
		nuevaPos = 49;
	}
	if (nuevaPos < 0 ) {
		nuevaPos = 0;
	}
	j.setPosicion(nuevaPos);
	
	t.activarCasillas(j);
	
	}
	
	public void jugadorFinalizaTurno(Jugador j) {
		
		if (j.getTurnosPerdidos()> 0) {
			j.setTurnosPeridos(j.getTurnosPerdidos() -1);
			System.out.println("Pierdes turno");
			
		}
		else {
			System.out.println("Turno terminado");
		}
		
=======
		if (nuevaPos > 49) {
			nuevaPos = 49;
		}
		if (nuevaPos < 0) {
			nuevaPos = 0;
		}
		j.setPosicion(nuevaPos);
	}
	
	public void jugadorFinalizaTurno(Jugador j) {
		System.out.println(j.getNombre() + " termina turno");
>>>>>>> Stashed changes
	}
	
	public void pinguinoEvento(Pinguino p) {
		
		int num = r.nextInt(4); // 0-3
		if (num == 0) {
			p.setPeces(p.getPeces()+1);
			System.out.println("Ganas un pez");
		}
		
		if ( num == 1) {
			p.setBolasNieve(p.getBolasNieve()+2);
			System.out.println("Ganas bolas de nieve");
		}
		if (num == 2) {
			p.setPosicion(p.getPosicion()+3);
			System.out.println("Avanzas 3");
		}
		if (num == 3) {
			p.setTurnosPerdidos(1);
			System.out.println("Pierdes turno");
			}
	}
	
	
	
	public void pingüinoGuerra(Pinguino p1, Pinguino p2) {
	
		int n1 = r.nextInt(6) + 1;
		int n2 = r.nextInt(6) + 1;
		
		if (n1> n2) {
			p2.setPosicion(p2.getPosicion() -2);
			System.out.println("Ganan p1");
			
		} 
		else {
			p1.setPosicion(p1.getPosicion() - 2);
			System.out.println("Gana p2");
		}	
	}
	
	
	public void focaInteractua(Pinguino p, Foca F) {
		if (p.isProtegido()) {
			System.out.println("No pasa nada (protegido)");
			p.setProtegido(false);
		}
		else {
			p.setPosicion(0);
			System.out.println("La foca te manda al inicio");
		}
		
<<<<<<< Updated upstream
=======
		for (Item i : p.getInv().getLista()) {
			if (i.getNombre().equalsIgnoreCase("pez")) {
				tienePez = true;
				p.quitarItem(new Pez("pez", 1));
				break;
			}
		}
		if (!tienePez) {
			f.aplastarJugador(p);
			f.golpearJugador(p);
		}
>>>>>>> Stashed changes
	}
}
