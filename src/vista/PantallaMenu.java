package vista;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class PantallaMenu {

    @FXML private Label lblJugador;
    @FXML private Label lblPunts;
    @FXML private Label lblTemps;
    @FXML private ProgressBar progress;

    private int segons = 10800; // 3h

    @FXML
    public void initialize() {
        iniciarComptador();
    }

    private void iniciarComptador() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                segons--;
                actualitzarTemps();
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void actualitzarTemps() {
        int h = segons / 3600;
        int m = (segons % 3600) / 60;
        int s = segons % 60;

        lblTemps.setText(String.format("%02d:%02d:%02d", h, m, s));
    }

    @FXML
    private void onJugar() {
        System.out.println("Jugar clicat!");
        // aquí canvies de pantalla
    }
}