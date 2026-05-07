package vista;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

public class PantallaMenu {

	@FXML
	private Button btnJugar;

	// --- EL MÉTODO PRINCIPAL ---
	@FXML
	private void handleJugar(ActionEvent event) {
		// 1. Elegir número de pingüinos (Humano)
		List<Integer> opciones = Arrays.asList(1, 2, 3, 4);
		ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, opciones);
		dialog.setTitle("Configuración de Partida");
		dialog.setHeaderText("¿Cuántos pingüinos van a jugar?");
		dialog.setContentText("Selecciona jugadores humanos:");

		Optional<Integer> result = dialog.showAndWait();

		result.ifPresent(numHumanos -> {
			// 2. Preguntar si se quiere jugar contra la Foca (IA)
			Alert alertIA = new Alert(Alert.AlertType.CONFIRMATION);
			alertIA.setTitle("Oponente IA");
			alertIA.setHeaderText("¿Quieres añadir a la Foca (IA) a la partida?");
			alertIA.setContentText("La Foca jugará como un oponente adicional.");

			ButtonType btnSi = new ButtonType("Sí, con Foca");
			ButtonType btnNo = new ButtonType("No, solo humanos");
			alertIA.getButtonTypes().setAll(btnSi, btnNo);

			Optional<ButtonType> resultIA = alertIA.showAndWait();
			boolean conFoca = (resultIA.isPresent() && resultIA.get() == btnSi);

			cargarPantallaJuego(numHumanos, conFoca);
		});
	}
	// --- MÉTODOS QUE FALTABAN Y DABAN ERROR (onAction en FXML) ---

	@FXML
	private void handleTrofeu(ActionEvent event) {
		System.out.println("Trofeos...");
	}

	@FXML
	private void handleOpcions(ActionEvent event) {
		System.out.println("Opciones...");
	}

	@FXML
	private void handleAjuda(ActionEvent event) {
		System.out.println("Ayuda...");
	}

	@FXML
	private void handlePersonatges(ActionEvent event) {
		System.out.println("Personajes...");
	}

	@FXML
	private void handleRanquing(ActionEvent event) {
		System.out.println("Ranking...");
	}

	@FXML
	private void handleBotiga(ActionEvent event) {
		System.out.println("Tienda...");
	}

	@FXML
	private void handleCompartir(ActionEvent event) {
		System.out.println("Compartiendo...");
	}

	// Métodos del Menú de Archivo
	@FXML
	private void handleNewGame(ActionEvent event) {
		System.out.println("Nuevo Juego...");
	}

	@FXML
	private void handleSaveGame(ActionEvent event) {
		System.out.println("Guardando...");
	}

	@FXML
	private void handleLoadGame(ActionEvent event) {
		System.out.println("Cargando...");
	}

	@FXML
	private void handleQuitGame(ActionEvent event) {
		System.exit(0);
	}

	private void cargarPantallaJuego(int humanos, boolean conFoca) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/recursos/PantallaJuego.fxml"));
			Parent root = loader.load();

			PantallaJuego controller = loader.getController();
			// Nuevo método que acepta ambos parámetros
			controller.configurarPartidaCompleta(humanos, conFoca);

			Stage stage = (Stage) btnJugar.getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
