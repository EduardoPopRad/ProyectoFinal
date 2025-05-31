package org.proyecto;

import org.proyecto.ui.VentanaRegistro;
import org.proyecto.util.GestorFicheroConfiguracion;

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
			if(GestorFicheroConfiguracion.obtenerRuta("recordar").equals("false")) {
				GestorFicheroConfiguracion.actualizarValor("usuario", "");
				GestorFicheroConfiguracion.actualizarValor("password", "");
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
