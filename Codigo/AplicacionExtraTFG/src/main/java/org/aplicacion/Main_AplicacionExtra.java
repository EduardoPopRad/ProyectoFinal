package org.aplicacion;

import org.aplicacion.ui.VentanaRegistro;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main_AplicacionExtra extends Application {

	private VentanaRegistro ventanaRegistro;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image icon = new Image(getClass().getResourceAsStream("/imagenes/logo.png"));
		primaryStage.getIcons().add(icon);

		ventanaRegistro = new VentanaRegistro(primaryStage);
		primaryStage.setScene(ventanaRegistro.getScene());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
