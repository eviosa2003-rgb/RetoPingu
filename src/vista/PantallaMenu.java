package vista;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class PantallaMenu {

    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private TextField userField;
    @FXML private PasswordField passField;

    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void initialize() {
        System.out.println("PantallaMenu initialized");
    }

    // -------------------------------------------------------------------------
    // Navegación a PantallaJuego reutilizando el mismo método que handleLogin
    // -------------------------------------------------------------------------
    private void irAPantallaJuego(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PantallaJuego.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Pantalla de Juego");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "No se ha podido abrir la pantalla de juego.");
        }
    }

    // -------------------------------------------------------------------------
    // Menú
    // -------------------------------------------------------------------------

    @FXML
    private void handleNewGame(ActionEvent event) {
        System.out.println("New Game clicked");
        irAPantallaJuego(event);
    }

    @FXML
    private void handleSaveGame() {
        // No hay partida activa en el menú principal → aviso al usuario
        mostrarInfo("Guardar partida", "No hay ninguna partida activa para guardar.\nInicia una partida primero.");
    }

    @FXML
    private void handleLoadGame(ActionEvent event) {
        System.out.println("Load Game clicked");
        // Cargamos la última partida (id = 1) y abrimos la pantalla de juego
        irAPantallaJuego(event);
    }

    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game clicked");
        System.exit(0);
    }

    // -------------------------------------------------------------------------
    // Login
    // -------------------------------------------------------------------------

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Login pressed: " + username + " / " + password);

        if (!username.isEmpty() && !password.isEmpty()) {
            irAPantallaJuego(event);
        } else {
            mostrarError("Campos vacíos", "Por favor, introduce usuario y contraseña.");
        }
    }

    // -------------------------------------------------------------------------
    // Registro
    // -------------------------------------------------------------------------

    @FXML
    private void handleRegister() {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Register pressed: " + username);

        if (username.isEmpty() || password.isEmpty()) {
            mostrarError("Campos vacíos", "Por favor, introduce usuario y contraseña para registrarte.");
            return;
        }

        // TODO: conectar con GestorBBDD para guardar el usuario en la base de datos
        // Por ahora mostramos un mensaje de éxito
        mostrarInfo("Registro", "Usuario '" + username + "' registrado correctamente.\nYa puedes iniciar sesión.");
        System.out.println("Usuario registrado: " + username);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}