package org.proyecto.controler;

import org.proyecto.ui.PantallaMuseo;
import org.proyecto.ui.VentanaRegistro;
import org.proyecto.ui.VentanaTrabajador;
import org.proyecto.ui.paneles.PaneAjustes;
import org.proyecto.ui.paneles.PaneMenu;
import org.proyecto.ui.paneles.PanePerfil;
import org.proyecto.util.ThMusica;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Usuario;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {

	private PaneMenu menu;
	private PanePerfil perfil;
	
	private boolean cerrar;
	private ThMusica music;
	
	public MenuController(PaneMenu menu) {
		this.menu = menu;
	}

	public void funciones(Stage primaryStage, Usuario usu) {
		this.perfil=new PanePerfil(usu);
		this.cerrar= false;
		
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
			btnAjustes(usu);
		});
		
		menu.getVolverRegistro().setOnAction(event ->{
			cerrar=true;
			music.detenerReproduccion();
			
			UtilesData.animacionFade(menu.getVolverRegistro());
			btnVolverRegistro(primaryStage);
		});
		
		comenzarMusica(this);
	}
	
	public boolean getCerrar() {
		return cerrar;
	}

	private void irVentanaTrabajador(Stage primaryStage, Usuario usu) {
		primaryStage.setMaximized(false);
		primaryStage.setScene(new VentanaTrabajador(primaryStage, menu, usu).getScene());

		primaryStage.setMaximized(true);
	}

	private void btnPerfil() {
		perfil.mostrarVentanaPerfil();
	}

	private void btnAjustes(Usuario u) {
		PaneAjustes ajustes = new PaneAjustes(u);
		ajustes.mostrarVentanaAjustes();
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
	
	public void comenzarMusica(MenuController m) {
		Platform.runLater(() ->  {
			music= new ThMusica(m);
			Thread hilo = new Thread(music);
			hilo.setDaemon(true); // para que se cierre al cerrar la app
			hilo.start();
		});
	}
}

