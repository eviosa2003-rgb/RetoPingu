package Controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class main extends Application {

<<<<<<< Updated upstream
		@Override
		public void start(Stage primaryStage) throws Exception {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("/PantallaMenu.fxml"));
		    Parent root = loader.load();
=======
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/PantallaMenu.fxml"));
			Parent root = loader.load();
>>>>>>> Stashed changes

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setTitle("El Juego del Pingüino");
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("Error al cargar la pantalla");
			e.printStackTrace();
		}
	}

<<<<<<< Updated upstream
=======
	public static void main(String[] args) {
		launch(args);
	}
}
>>>>>>> Stashed changes
