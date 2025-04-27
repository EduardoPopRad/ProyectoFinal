package org.proyecto;

import org.proyecto.ui.VentanaRegistro;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main_ProyectoFinal extends Application {

	private VentanaRegistro ventanaRegistro;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image icon = new Image(getClass().getResourceAsStream("/recursos/images/logo.png"));
		primaryStage.getIcons().add(icon); //Ponemos el logo como icono

		ventanaRegistro = new VentanaRegistro(primaryStage);
		primaryStage.setScene(ventanaRegistro.getScene());
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(event -> { // Cuando se cierra la ventana
			System.out.println("Se ha cerrado la aplicacion.");
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
