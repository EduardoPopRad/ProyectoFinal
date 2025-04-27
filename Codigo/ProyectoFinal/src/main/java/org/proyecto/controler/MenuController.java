package org.proyecto.controler;

import org.proyecto.ui.PantallaMuseo;
import org.proyecto.ui.VentanaRegistro;
import org.proyecto.ui.VentanaTrabajador;
import org.proyecto.ui.paneles.PaneMenu;
import org.proyecto.ui.paneles.PanePerfil;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {

	private PaneMenu menu;
	private PanePerfil perfil;
	
	public MenuController(PaneMenu menu) {
		this.menu = menu;
	}

	public void funciones(Stage primaryStage, Usuario usu) {
		this.perfil=new PanePerfil(usu);
		
		if (usu.isPuedeResponder()) {
			menu.setConsultas(new Button("Consultas"));
			menu.getConsultas().setPrefSize(90, 30);
			menu.getPanel().getChildren().add(menu.getConsultas());
			VBox.setMargin(menu.getConsultas(), new Insets(0, 5, 0, 5));
			menu.getConsultas().setOnAction(event -> {
				UtilesData.animacionFade(menu.getConsultas());
				menu.getPanel().getChildren().remove(menu.getConsultas());
				menu.getPanel().getChildren().add(menu.getMuseo());
				irVentanaTrabajador(primaryStage, usu);
			});

			menu.setMuseo(new Button("Volver Museo"));
			menu.getMuseo().setPrefSize(90, 30);
			VBox.setMargin(menu.getMuseo(), new Insets(0, 5, 0, 5));
			menu.getMuseo().setOnAction(event -> {
				UtilesData.animacionFade(menu.getMuseo());
				menu.getPanel().getChildren().remove(menu.getMuseo());
				menu.getPanel().getChildren().add(menu.getConsultas());
				volverMuseo(primaryStage, usu);
			});
		}

		menu.getPerfil().setOnAction(event -> {
			UtilesData.animacionFade(menu.getPerfil());
			btnPerfil();
		});

		menu.getAjustes().setOnAction(event -> {
			UtilesData.animacionFade(menu.getAjustes());
			btnAjustes();
		});
		
		menu.getVolverRegistro().setOnAction(event ->{ 
			UtilesData.animacionFade(menu.getVolverRegistro());
			btnVolverRegistro(primaryStage);
		});
	}

	private void irVentanaTrabajador(Stage primaryStage, Usuario usu) {
		primaryStage.setMaximized(false);
		primaryStage.setScene(new VentanaTrabajador(primaryStage, menu, usu).getScene());

		primaryStage.setMaximized(true);
	}

	private void btnPerfil() {
		perfil.mostrarVentanaPerfil();
	}

	private void btnAjustes() {
		System.out.println("Se ha pulsado boton ajustes del menu");
	}

	private void btnVolverRegistro(Stage primaryStage) {
		VentanaRegistro ventanaRegistro = new VentanaRegistro(primaryStage);
		primaryStage.setMaximized(false);
		primaryStage.setScene(ventanaRegistro.getScene());
		
	}
	
	private void volverMuseo(Stage primaryStage, Usuario u) {
		primaryStage.setMaximized(false);
		primaryStage.setScene(new PantallaMuseo(primaryStage, u).getScene());
		primaryStage.setMaximized(true); 
	}
}

