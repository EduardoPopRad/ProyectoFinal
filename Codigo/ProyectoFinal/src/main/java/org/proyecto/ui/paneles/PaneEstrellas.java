package org.proyecto.ui.paneles;

import org.proyecto.controler.EstrellasController;
import org.proyecto.controler.ObraController;
import org.proyecto.vo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PaneEstrellas {

	private VBox panel;
	private HBox estre;
	private Button[] estrellas;
	private Button enviar;

	// Tiene que ser un VBox que contiene arriba el HBox de estrellas y abajo el
	// boton para enviar la puntuacion
	public PaneEstrellas(ObraController obraController, Usuario usu) {
		EstrellasController controller = new EstrellasController(this, obraController, usu);
		this.panel = new VBox();
		panel.setSpacing(5);
		panel.setPrefSize(100, 50);

		estre = new HBox();
		estre.setPrefSize(100, 20);
		estre.setSpacing(0);
		this.estrellas = new Button[5];

		controller.ponerEstrellasVacias();
		
		//No funciona como quiero
		for (int i = 0; i < estrellas.length; i++) {
			final int pos = i+1; //Para saber que calificacion exacta
			estrellas[i].setOnAction(event -> {
				controller.funcionBoton(pos);
			});
			estre.getChildren().add(estrellas[i]);
		}

		enviar = new Button("Calificar");
		enviar.setPrefSize(80, 20);

		VBox.setMargin(enviar, new Insets(0, 0, 0, 50));
		controller.funcionEnviar();
		panel.getChildren().add(estre);
		panel.getChildren().add(enviar);
	}

	public VBox getPanel() {
		return panel;
	}

	public Button[] getEstrellas() {
		return estrellas;
	}

	public Button getEnviar() {
		return enviar;
	}
}

