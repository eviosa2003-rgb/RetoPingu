package vista;

import java.io.File;
import java.util.List;

import Controlador.GestorPartida;
import Modelo.Dado;
import Modelo.Foca;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Pinguino;
import Modelo.Tablero;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaJuego {

	@FXML
	private GridPane tablero;
	@FXML
	private ImageView P1, P2, P3, P4;
	@FXML
	private Text dadoResultText, eventos, peces_t, nieve_t;
	@FXML
	private Button dado;

	private GestorPartida gestorPartida;
	private static final int COLUMNS = 5;
	private static final String TAG_CASILLA = "FONDO_HIELO";

	@FXML
	public void initialize() {
		gestorPartida = new GestorPartida();
		gestorPartida.nuevaPartida(1);
		cargarFotosFichas();
		dibujarEscenarioCompleto();
	}

	private void cargarFotosFichas() {
		String[] archivos = { "pinguino_rojo.png", "pinguino_azul.png", "pinguino_verde.png", "foca.png" };
		ImageView[] fichas = { P1, P2, P3, P4 };
		for (int i = 0; i < fichas.length; i++) {
			try {
				File f = new File("src/recursos/imagenes/" + archivos[i]);
				if (f.exists()) {
					fichas[i].setImage(new Image(f.toURI().toString()));
					fichas[i].setMouseTransparent(true);
					fichas[i].setFitWidth(35); // Un poco más pequeñas para que quepan 4
					fichas[i].setFitHeight(35);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void configurarPartidaCompleta(int numHumanos, boolean conFoca) {
		gestorPartida.nuevaPartida(numHumanos);
		P1.setVisible(numHumanos >= 1);
		P2.setVisible(numHumanos >= 2);
		P3.setVisible(numHumanos >= 3);
		P4.setVisible(conFoca || numHumanos >= 4);
		dibujarEscenarioCompleto();
	}

	private void dibujarEscenarioCompleto() {
		tablero.getChildren().removeIf(node -> TAG_CASILLA.equals(node.getUserData()));
		tablero.getChildren().removeAll(P1, P2, P3, P4);

		Tablero t = gestorPartida.getPartida().getTablero();

		for (int i = 0; i < t.getCasillas().size(); i++) {
			int row = i / COLUMNS, col = i % COLUMNS;
			String tipo = t.getCasilla(i).getClass().getSimpleName().toLowerCase();

			try {
				File f = new File("src/recursos/imagenes/" + tipo + ".png");
				if (f.exists()) {
					ImageView imgCasilla = new ImageView(new Image(f.toURI().toString()));
					imgCasilla.setFitWidth(65);
					imgCasilla.setFitHeight(65);
					imgCasilla.setPreserveRatio(true);
					imgCasilla.setUserData(TAG_CASILLA);
					GridPane.setHalignment(imgCasilla, HPos.CENTER);
					GridPane.setValignment(imgCasilla, VPos.CENTER);
					tablero.add(imgCasilla, col, row);
				}
			} catch (Exception e) {
			}

			Text num = new Text(String.valueOf(i));
			num.setUserData(TAG_CASILLA);
			num.getStyleClass().add("cell-type");
			GridPane.setHalignment(num, HPos.LEFT);
			GridPane.setValignment(num, VPos.TOP);
			tablero.add(num, col, row);
		}

		// --- POSICIONAR FICHAS CON DESFASE (Para que no se tapen) ---
		ImageView[] misFichas = { P1, P2, P3, P4 };
		List<Jugador> jugadores = gestorPartida.getPartida().getJugadores();

		for (int i = 0; i < jugadores.size(); i++) {
			if (i >= 4) {
				break;
			}
			ImageView ficha = misFichas[i];
			int p = jugadores.get(i).getPosicion();

			tablero.add(ficha, p % COLUMNS, p / COLUMNS);

			// Aplicamos una esquina diferente a cada uno
			aplicarOffsetFicha(ficha, i);

			ficha.toFront();
		}
		actualizarContadoresItems();
	}

	// MÉTODO NUEVO: Reparte a los pingüinos en las 4 esquinas de la casilla
	private void aplicarOffsetFicha(ImageView ficha, int indice) {
		switch (indice) {
		case 0:
			ficha.setTranslateX(-15);
			ficha.setTranslateY(-15);
			break; // Arriba-Izquierda
		case 1:
			ficha.setTranslateX(15);
			ficha.setTranslateY(-15);
			break; // Arriba-Derecha
		case 2:
			ficha.setTranslateX(-15);
			ficha.setTranslateY(15);
			break; // Abajo-Izquierda
		case 3:
			ficha.setTranslateX(15);
			ficha.setTranslateY(15);
			break; // Abajo-Derecha
		}
	}

	@FXML
	private void handleDado(ActionEvent event) {
		Partida p = gestorPartida.getPartida();
		Jugador actual = p.getJugadorActual();
		int posPrevia = actual.getPosicion();
		int indice = p.getJugadorActualIndice();

		if (actual instanceof Pinguino) {
			int r = gestorPartida.tirarDado((Pinguino) actual, new Dado("n", 1, 1, 6));
			dadoResultText.setText("Salió: " + r);
			animar(obtenerFichaVisual(indice), posPrevia, r, indice);
		} else {
			ejecutarIA();
		}
	}

	private void animar(ImageView f, int origen, int pasos, int indiceFicha) {
		dado.setDisable(true);
		f.toFront();
		SequentialTransition seq = new SequentialTransition();
		int temp = origen;

		for (int i = 0; i < Math.abs(pasos); i++) {
			final int act = temp;
			int sig = (pasos > 0) ? act + 1 : act - 1;
			if (sig < 0 || sig > 49) {
				break;
			}

			TranslateTransition mov = new TranslateTransition(Duration.millis(200), f);
			// El movimiento calcula la distancia entre centros de celdas
			mov.setByX(((sig % 5) - (act % 5)) * (tablero.getWidth() / 5));
			mov.setByY(((sig / 5) - (act / 5)) * (tablero.getHeight() / 10));
			seq.getChildren().add(mov);
			temp = sig;
		}

		final int dest = temp;
		seq.setOnFinished(e -> {
			// Al terminar, fijamos la celda y REAPLICAMOS su esquina
			GridPane.setColumnIndex(f, dest % 5);
			GridPane.setRowIndex(f, dest / 5);
			aplicarOffsetFicha(f, indiceFicha);
			dado.setDisable(false);
			f.toFront();
			actualizarContadoresItems();
		});
		seq.play();
	}

	private void ejecutarIA() {
		Partida p = gestorPartida.getPartida();
		int indiceIA = p.getJugadores().size() - 1;
		Foca f = (Foca) p.getJugadorActual();
		int prev = f.getPosicion();
		String m = gestorPartida.ejecutarTurnoIA();
		eventos.setText("IA: " + m);
		animar(P4, prev, f.getPosicion() - prev, indiceIA);
	}

	private ImageView obtenerFichaVisual(int i) {
		return switch (Math.min(i, 3)) {
		case 0 -> P1;
		case 1 -> P2;
		case 2 -> P3;
		default -> P4;
		};
	}

	private void actualizarContadoresItems() {
		Jugador j = gestorPartida.getPartida().getTurnoActual();
		if (j instanceof Pinguino p) {
			peces_t.setText("Peces: " + p.getPeces());
			nieve_t.setText("Bolas: " + p.getBolasNieve());
		}
	}

	@FXML
	private void handleQuitGame(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/recursos/PantallaMenu.fxml"));
			Stage s = (Stage) tablero.getScene().getWindow();
			s.setScene(new Scene(root));
		} catch (Exception e) {
			System.exit(0);
		}
	}

	@FXML
	private void handleSaveGame() {
		gestorPartida.guardarPartida();
	}

	@FXML
	private void handleLoadGame() {
		gestorPartida.cargarPartida(1);
		dibujarEscenarioCompleto();
	}

	@FXML
	private void handleNewGame() {
		configurarPartidaCompleta(1, true);
	}

	@FXML
	private void handlePeces() {
	}

	@FXML
	private void handleNieve() {
	}

	@FXML
	private void handleRapido() {
	}

	@FXML
	private void handleLento() {
	}
}
