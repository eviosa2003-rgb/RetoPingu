package Controlador;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class main extends Application {

	// Declaramos el reproductor fuera para que no se detenga
	private MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage) {
		try {
			// 1. INICIAR MÚSICA
			encenderMusica();

			// 2. CARGAR PANTALLA
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/recursos/PantallaMenu.fxml"));
			Parent root = loader.load();	
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Reto Pingu");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void encenderMusica() {
		try {
			// Esta forma busca el archivo en la raíz en la carpeta del proyecto
			File file = new File("src/recursos/musica/fondo.mp3");
			if (!file.exists()) {
				System.out.println(" El archivo no existe en: " + file.getAbsolutePath());
				return;
			}
			String ruta = file.toURI().toString();
			Media musicFile = new Media(ruta);
			mediaPlayer = new MediaPlayer(musicFile);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.setVolume(0.3);
			mediaPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
