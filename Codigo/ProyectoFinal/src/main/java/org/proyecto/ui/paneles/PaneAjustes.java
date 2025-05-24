package org.proyecto.ui.paneles;

import org.proyecto.util.GestorFicheroConfiguracion;
import org.proyecto.vo.Usuario;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaneAjustes {
	
	private Stage ventana;
	private Pane panel;
	private CheckBox recordar;
	//Deslizador para el volumen de la musica
	
	public PaneAjustes(Usuario u) {
		ventana = new Stage();
        ventana.setTitle("Ajustes");
		
		panel = new Pane();
		panel.setPrefSize(250, 150);
		panel.setStyle("-fx-background-color: rgba(23, 40, 56, 1);");
		
		recordar = new CheckBox("Recordar Sesión");
		
		recordar.setSelected(GestorFicheroConfiguracion.obtenerRuta("recordar").equals("true"));
		
		panel.getChildren().add(recordar);
		recordar.setLayoutX(40); 	recordar.setLayoutY(80);
		
		Scene escenaAjustes = new Scene(panel, 480, 250);
        ventana.setScene(escenaAjustes);
        
        ventana.setOnCloseRequest(event -> { // Cuando se cierra la ventana
			comprobarRecordado(u);
		});
	}
	
	private void comprobarRecordado(Usuario u) {
		if(recordar.isSelected()) {
			GestorFicheroConfiguracion.actualizarValor("recordar", "true");
			GestorFicheroConfiguracion.actualizarValor("usuario", u.getUser());
			GestorFicheroConfiguracion.actualizarValor("password", u.getPassword()); //Tengo que guardar de alguna manera la contraseña con la que entra
		}
	}

	public void mostrarVentanaAjustes() {
        ventana.show();
    }
}