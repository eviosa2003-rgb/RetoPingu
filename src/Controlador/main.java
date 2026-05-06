package Controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class main extends Application {
 
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/recursos/PantallaMenu.fxml"));
            Parent root = loader.load();
 
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("El Juego del Pingüino");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la pantalla: " + e.getMessage());
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
 
	