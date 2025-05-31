package org.proyecto.ui.paneles;

import org.proyecto.controler.MenuController;
import org.proyecto.vo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneMenu {
	private VBox panel;

	private Button perfil;
	private Button consultas; // Solo para usuarios con permiso a responder
	private Button museo; // Solo para usuarios que han entrado en ventanaTrabajador para poder volver
	private Button ajustes;
	private Button volverRegistro;

	public PaneMenu(Stage primaryStage, Usuario usu) {
		MenuController controller = new MenuController(this);

		panel = new VBox();
		panel.setPrefSize(100, 200);
		panel.setSpacing(30);
		panel.setBackground(Background.EMPTY);
		
		perfil = new Button("Perfil");
		perfil.setPrefSize(90, 30);
		panel.getChildren().add(perfil);

		ajustes = new Button("Ajustes");
		ajustes.setPrefSize(90, 30);
		panel.getChildren().add(ajustes);
		
		volverRegistro =  new Button("Inicio");	
		volverRegistro.setPrefSize(90, 30);
		panel.getChildren().add(volverRegistro);

		VBox.setMargin(ajustes, new Insets(0, 5, 0, 5));
		VBox.setMargin(perfil, new Insets(0, 5, 0, 5));
		VBox.setMargin(volverRegistro, new Insets(0, 5, 0, 5));
		
		controller.funciones(primaryStage, usu);
	}

	public VBox getPanel() {
		return panel;
	}

	public Button getPerfil() {
		return perfil;
	}

	public Button getConsultas() {
		return consultas;
	}
	public void setConsultas(Button con) {
		this.consultas=con;
	}

	public Button getAjustes() {
		return ajustes;
	}
	
	public Button getMuseo() {
		return museo;
	}
	public void setMuseo(Button con) {
		this.museo=con;
	}

	public Button getVolverRegistro() {
		return volverRegistro;
	}

	public void setVolverRegistro(Button volverRegistro) {
		this.volverRegistro = volverRegistro;
	}
	
}

