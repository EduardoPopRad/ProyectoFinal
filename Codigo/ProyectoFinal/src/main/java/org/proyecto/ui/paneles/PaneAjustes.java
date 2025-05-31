package org.proyecto.ui.paneles;

import org.proyecto.util.GestorFicheroConfiguracion;
import org.proyecto.vo.Usuario;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaneAjustes {

	private Stage ventana;
	private Pane panel;
	private CheckBox recordar;
	private Slider volumen;
	private Label valorVol;

	public PaneAjustes(Usuario u) {
		ventana = new Stage();
		ventana.setTitle("Ajustes");

		panel = new Pane();
		panel.setPrefSize(250, 150);
		panel.setStyle("-fx-background-color: rgb(185, 186, 183);");

		recordar = new CheckBox("Recordar Sesión");
		recordar.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-padding: 10px; "
				+ "-fx-min-height: 30px; -fx-min-width: 30px;");

		recordar.setSelected(GestorFicheroConfiguracion.obtenerRuta("recordar").equals("true"));
		
		double valor = Double.parseDouble(GestorFicheroConfiguracion.obtenerRuta("volumen"));
		volumen = new Slider(0, 1, valor);
		volumen.setMinWidth(240);
		volumen.setShowTickLabels(true);
		
		valorVol = new Label(String.format("%.2f", valor));
		valorVol.setStyle("-fx-text-fill: black;");

		volumen.valueProperty().addListener((obs, oldVal, newVal) -> {
		    valorVol.setText(String.format("%.2f", newVal.doubleValue()));
		    GestorFicheroConfiguracion.actualizarValor("volumen", valorVol.getText().replace(',', '.'));
		});
		
		panel.getChildren().addAll(recordar, volumen, valorVol);
		recordar.setLayoutX(40);	recordar.setLayoutY(60);
		volumen.setLayoutX(45);		volumen.setLayoutY(140);
		valorVol.setLayoutX(290);	valorVol.setLayoutY(140);
		
		Scene escenaAjustes = new Scene(panel, 350, 250);
		ventana.setScene(escenaAjustes);

		ventana.setOnCloseRequest(event -> { // Cuando se cierra la ventana
			comprobarRecordado();
		});
	}

	private void comprobarRecordado() {
		GestorFicheroConfiguracion.actualizarValor("recordar", String.valueOf(recordar.isSelected()));
		// Los datos del usuario se guardan siempre ya que no se pueden desencriptar la
		// contraseña, por lo tanto solo se guarda el valor de si se recuerda o no la sesión
	}

	public void mostrarVentanaAjustes() {
		ventana.show();
	}
}