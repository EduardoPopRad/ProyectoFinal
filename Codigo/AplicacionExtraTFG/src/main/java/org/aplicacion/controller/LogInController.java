package org.aplicacion.controller;

import org.aplicacion.ui.VentanaRegistro;
import org.aplicacion.ui.VentanaTrabajadores;
import org.aplicacion.util.GestorFicheroConfiguracion;

import javafx.stage.Stage;

public class LogInController {
	private VentanaRegistro ventana;
	
	public LogInController (VentanaRegistro v) {
		this.ventana=v;
	}
	
	public void funciones(Stage primaryStage) {
		ventana.getRegistrar().setOnAction(event -> {
			ventana.getError().setVisible(false);
			
			if(ventana.getContra().getText().isEmpty() || ventana.getUser().getText().isEmpty()) {
				ventana.getError().setText("Alguno de los campos está vacio");
				ventana.getError().setVisible(true);
				return;
			}
			
			comprobar(ventana.getUser().getText().strip(), ventana.getContra().getText().strip(), primaryStage);
			
			ventana.getContra().setText("");
			ventana.getUser().setText("");
		});
	}
	
	public void comprobar(String usu, String con, Stage primaryStage) {
		if(puedeEntrar(usu, con)) {
			cambiarVentana(primaryStage);
		}else {
			ventana.getError().setText("Alguna de las credenciales es incorrecta");
			ventana.getError().setVisible(true);	
		
			ventana.getContra().setText("");
			ventana.getUser().setText("");
		}
	}
	
	private boolean puedeEntrar(String usu, String contra) {
		if(usu.equals(GestorFicheroConfiguracion.obtenerRuta("usu")) &&  contra.equals(GestorFicheroConfiguracion.obtenerRuta("password")))
			return true;
		
		return false;
	}
	
	private void cambiarVentana(Stage primaryStage) {
		if(GestorFicheroConfiguracion.obtenerRuta("estaLogeado").equals("true")) {
			ventana.getError().setText("Ya hay una sesion iniciada, espere a que termine");
			ventana.getError().setVisible(true);
			return;
		}
		primaryStage.setScene(new VentanaTrabajadores(primaryStage).getScene());
		primaryStage.setMaximized(true);
		GestorFicheroConfiguracion.actualizarValor("estaLogeado", "true");
	}
}
