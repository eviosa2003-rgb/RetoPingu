package vista;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import controlador.GestorPartida;
import modelo.*;

public class PantallaJuego {

    // -------------------------------------------------------------------------
    // FXML
    // -------------------------------------------------------------------------

    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;

    // -------------------------------------------------------------------------
    // Estado interno
    // -------------------------------------------------------------------------

    private GestorPartida gestorPartida;
    private int p1Position = 0;
    private static final int COLUMNS = 5;
    private static final String TAG_CASILLA_TEXT = "CASILLA_TEXT";
    private final Random rand = new Random();

    // -------------------------------------------------------------------------
    // Inicialización
    // -------------------------------------------------------------------------

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");

        gestorPartida = new GestorPartida();

        ArrayList<Jugador> jugadores = new ArrayList<>();
        Inventario inventario = new Inventario();

        // Dado normal 1-6
        Dado dadoItem = new Dado("normal", 1, 1, 6);
        inventario.getLista().add(dadoItem);

        // Items de uso: Trineo (rápido), Pez, BolaDeNieve
        // Trineo es casilla, no item → los botones rápido/lento usan
        // directamente movimiento de posición
        Pez pezItem = new Pez("pez", 2);
        BolaDeNieve bolaItem = new BolaDeNieve("bola", 2);
        inventario.getLista().add(pezItem);
        inventario.getLista().add(bolaItem);

        jugadores.add(new Pinguino("Jugador1", "Azul", 0, inventario));

        gestorPartida.nuevaPartida();
        gestorPartida.getPartida().setJugadores(jugadores);

        mostrarTiposDeCasillasEnTablero(gestorPartida.getPartida().getTablero());
        actualizarContadoresItems();
    }

    // -------------------------------------------------------------------------
    // Tablero
    // -------------------------------------------------------------------------

    private void mostrarTiposDeCasillasEnTablero(Tablero t) {
        tablero.getChildren().removeIf(node -> TAG_CASILLA_TEXT.equals(node.getUserData()));

        for (int i = 0; i < t.getCasillas().size(); i++) {
            Casilla casilla = t.getCasillas().get(i);

            if (i > 0 && i < 49) {
                String tipo = casilla.getClass().getSimpleName();

                Text texto = new Text(tipo);
                texto.setUserData(TAG_CASILLA_TEXT);
                texto.getStyleClass().add("cell-type");

                int row = i / COLUMNS;
                int col = i % COLUMNS;

                GridPane.setRowIndex(texto, row);
                GridPane.setColumnIndex(texto, col);

                tablero.getChildren().add(texto);
            }
        }
    }

    // -------------------------------------------------------------------------
    // Menú
    // -------------------------------------------------------------------------

    @FXML
    private void handleNewGame(ActionEvent event) {
        System.out.println("Nueva partida.");

        // Reiniciar estado visual
        p1Position = 0;
        GridPane.setRowIndex(P1, 0);
        GridPane.setColumnIndex(P1, 0);
        P1.setTranslateX(0);
        P1.setTranslateY(0);

        // Reiniciar modelo
        gestorPartida.nuevaPartida();

        ArrayList<Jugador> jugadores = new ArrayList<>();
        Inventario inventario = new Inventario();
        inventario.getLista().add(new Dado("normal", 1, 1, 6));
        inventario.getLista().add(new Pez("pez", 2));
        inventario.getLista().add(new BolaDeNieve("bola", 2));
        jugadores.add(new Pinguino("Jugador1", "Azul", 0, inventario));
        gestorPartida.getPartida().setJugadores(jugadores);

        mostrarTiposDeCasillasEnTablero(gestorPartida.getPartida().getTablero());
        actualizarContadoresItems();
        eventos.setText("¡Nueva partida iniciada!");
        dadoResultText.setText("");
    }

    @FXML
    private void handleSaveGame() {
        System.out.println("Guardando partida...");
        gestorPartida.guardarPartida();
        mostrarInfo("Guardar", "Partida guardada correctamente.");
        eventos.setText("Partida guardada.");
    }

    @FXML
    private void handleLoadGame() {
        System.out.println("Cargando partida...");
        // Cargamos la partida con id=1 (la más reciente)
        gestorPartida.cargarPartida(1);

        if (gestorPartida.getPartida() != null) {
            // Sincronizar posición visual del jugador
            Jugador j = gestorPartida.getPartida().getJugadorActual();
            if (j != null) {
                p1Position = j.getPosicion();
                GridPane.setRowIndex(P1, p1Position / COLUMNS);
                GridPane.setColumnIndex(P1, p1Position % COLUMNS);
                P1.setTranslateX(0);
                P1.setTranslateY(0);
            }
            mostrarTiposDeCasillasEnTablero(gestorPartida.getPartida().getTablero());
            actualizarContadoresItems();
            eventos.setText("Partida cargada.");
        } else {
            mostrarError("Cargar", "No se ha encontrado ninguna partida guardada.");
        }
    }

    @FXML
    private void handleQuitGame(ActionEvent event) {
        System.out.println("Saliendo al menú...");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PantallaMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
        } catch (Exception e) {
            e.printStackTrace();
            // Si falla la navegación, cerramos directamente
            System.exit(0);
        }
    }

    // -------------------------------------------------------------------------
    // Dado
    // -------------------------------------------------------------------------

    @FXML
    private void handleDado(ActionEvent event) {
        Pinguino pingu = (Pinguino) gestorPartida.getPartida().getJugadores().get(0);
        Dado d = (Dado) pingu.getInv().getLista().get(0);

        System.out.println("Posición previa: " + pingu.getPosicion());

        int resultado = gestorPartida.tirarDado(pingu, d);

        System.out.println("Posición actual: " + pingu.getPosicion());

        dadoResultText.setText("Ha salido: " + resultado);

        // Comprobar si el jugador ha ganado (posición 49)
        if (pingu.getPosicion() >= 49) {
            eventos.setText("🎉 ¡" + pingu.getNombre() + " ha llegado a la meta!");
            dado.setDisable(true);
            deshabilitarBotonesItems();
        } else {
            String tipoCasilla = gestorPartida.getPartida().getTablero()
                    .getCasillas().get(pingu.getPosicion()).getClass().getSimpleName();
            eventos.setText(pingu.getNombre() + " avanza " + resultado
                    + " casillas → " + tipoCasilla);
        }

        moveP1(resultado);
    }

    // -------------------------------------------------------------------------
    // Movimiento animado del jugador
    // -------------------------------------------------------------------------

    private void moveP1(int steps) {
        dado.setDisable(true);

        int oldPosition = p1Position;
        p1Position += steps;

        if (p1Position >= 50) p1Position = 49;
        if (p1Position < 0)   p1Position = 0;

        int oldRow = oldPosition / COLUMNS;
        int oldCol = oldPosition % COLUMNS;
        int newRow = p1Position / COLUMNS;
        int newCol = p1Position % COLUMNS;

        double cellWidth  = tablero.getWidth()  / COLUMNS;
        double cellHeight = tablero.getHeight() / 10;

        double dx = (newCol - oldCol) * cellWidth;
        double dy = (newRow - oldRow) * cellHeight;

        TranslateTransition slide = new TranslateTransition(Duration.millis(350), P1);
        slide.setByX(dx);
        slide.setByY(dy);

        slide.setOnFinished(e -> {
            P1.setTranslateX(0);
            P1.setTranslateY(0);
            GridPane.setRowIndex(P1, newRow);
            GridPane.setColumnIndex(P1, newCol);
            dado.setDisable(false);
        });

        slide.play();
    }

    // -------------------------------------------------------------------------
    // Botones de items
    // -------------------------------------------------------------------------

    /**
     * RÁPIDO – mueve al jugador 3 casillas hacia adelante (efecto Trineo).
     * Coste: 0 items (es una acción especial de turno).
     */
    @FXML
    private void handleRapido() {
        Pinguino pingu = getPinguino();
        if (pingu == null) return;

        int pasos = 3;
        pingu.moverPosicion(pasos);
        if (pingu.getPosicion() > 49) pingu.setPosicion(49);

        eventos.setText(pingu.getNombre() + " usa TRINEO → avanza " + pasos + " casillas extra.");
        moveP1(pasos);
        System.out.println("Rápido: +" + pasos + " posición: " + pingu.getPosicion());
    }

    /**
     * LENTO – retrocede al jugador 2 casillas (efecto Foca).
     * Coste: 0 items.
     */
    @FXML
    private void handleLento() {
        Pinguino pingu = getPinguino();
        if (pingu == null) return;

        int pasos = -2;
        pingu.moverPosicion(pasos);
        if (pingu.getPosicion() < 0) pingu.setPosicion(0);

        eventos.setText(pingu.getNombre() + " es golpeado por una FOCA → retrocede 2 casillas.");
        moveP1(pasos);
        System.out.println("Lento: " + pasos + " posición: " + pingu.getPosicion());
    }

    /**
     * PECES – usa un Pez del inventario para avanzar 1 casilla extra.
     */
    @FXML
    private void handlePeces() {
        Pinguino pingu = getPinguino();
        if (pingu == null) return;

        // Buscar el item Pez en el inventario
        Item pezItem = buscarItem(pingu, "pez");

        if (pezItem == null || pezItem.getCantidad() <= 0) {
            eventos.setText("¡No tienes peces en el inventario!");
            mostrarError("Sin peces", "No tienes peces disponibles.");
            return;
        }

        // Gastar un pez
        pezItem.setCantidad(pezItem.getCantidad() - 1);

        int pasos = 1;
        pingu.moverPosicion(pasos);
        if (pingu.getPosicion() > 49) pingu.setPosicion(49);

        eventos.setText(pingu.getNombre() + " usa un PEZ → avanza 1 casilla. Peces restantes: " + pezItem.getCantidad());
        moveP1(pasos);
        actualizarContadoresItems();
        System.out.println("Pez usado. Quedan: " + pezItem.getCantidad());
    }

    /**
     * NIEVE – usa una BolaDeNieve para protegerse de la siguiente Foca.
     */
    @FXML
    private void handleNieve() {
        Pinguino pingu = getPinguino();
        if (pingu == null) return;

        Item bolaItem = buscarItem(pingu, "bola");

        if (bolaItem == null || bolaItem.getCantidad() <= 0) {
            eventos.setText("¡No tienes bolas de nieve en el inventario!");
            mostrarError("Sin bolas de nieve", "No tienes bolas de nieve disponibles.");
            return;
        }

        // Gastar una bola
        bolaItem.setCantidad(bolaItem.getCantidad() - 1);

        // TODO: activar flag protegido cuando Pinguino tenga isProtegido()
        eventos.setText(pingu.getNombre() + " usa una BOLA DE NIEVE → protegido de la siguiente Foca. Bolas restantes: " + bolaItem.getCantidad());
        actualizarContadoresItems();
        System.out.println("Bola de nieve usada. Quedan: " + bolaItem.getCantidad());
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Devuelve el primer Pinguino de la partida o null si no hay. */
    private Pinguino getPinguino() {
        if (gestorPartida.getPartida() == null
                || gestorPartida.getPartida().getJugadores().isEmpty()) {
            return null;
        }
        return (Pinguino) gestorPartida.getPartida().getJugadores().get(0);
    }

    /** Busca un Item en el inventario del pingüino por nombre (ignore case). */
    private Item buscarItem(Pinguino p, String nombre) {
        for (Item item : p.getInv().getLista()) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        return null;
    }

    /** Actualiza los textos que muestran la cantidad de cada item. */
    private void actualizarContadoresItems() {
        Pinguino pingu = getPinguino();
        if (pingu == null) return;

        Item pez  = buscarItem(pingu, "pez");
        Item bola = buscarItem(pingu, "bola");

        if (peces_t != null)  peces_t.setText("Peces: "         + (pez  != null ? pez.getCantidad()  : 0));
        if (nieve_t != null)  nieve_t.setText("Bolas de nieve: " + (bola != null ? bola.getCantidad() : 0));
    }

    private void deshabilitarBotonesItems() {
        rapido.setDisable(true);
        lento.setDisable(true);
        peces.setDisable(true);
        nieve.setDisable(true);
    }

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

    public void setGestorPartida(GestorPartida gestorPartida) {
        this.gestorPartida = gestorPartida;
    }
}