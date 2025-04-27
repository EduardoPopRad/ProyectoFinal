package org.proyecto.controler;

import org.proyecto.dao.IPuntuacion;
import org.proyecto.dao.impl.PuntuacionDao;
import org.proyecto.excepciones.PuntuacionException;
import org.proyecto.ui.paneles.PaneEstrellas;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Obra;
import org.proyecto.vo.Puntuacion;
import org.proyecto.vo.Usuario;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EstrellasController {

	private PaneEstrellas panel;
	private Usuario usu;
	private int puntuacion;
	private ObraController obraController;

	public EstrellasController(PaneEstrellas p, ObraController obraController, Usuario usu) { 
		this.panel = p;
		this.obraController = obraController;
		this.usu = usu;
		this.puntuacion =0;
		obraController.setEstrellas(this);
	}

	public void ponerEstrellasVacias() {
		for (int i = 0; i < panel.getEstrellas().length; i++) {
			panel.getEstrellas()[i] = new Button("");
			panel.getEstrellas()[i].setPrefSize(20, 20);
			ImageView img = new ImageView(
					new Image(getClass().getResourceAsStream("/recursos/images/estrellaVacia.png")));
			img.setFitWidth(20);
			img.setFitHeight(25);
			panel.getEstrellas()[i].setGraphic(img);
			panel.getEstrellas()[i].getStyleClass().add("botones");
		}
	}

	private void ponerVacios() {
		for (int i = 0; i < puntuacion; i++) {
			ImageView img = new ImageView(
					new Image(getClass().getResourceAsStream("/recursos/images/estrellaVacia.png")));
			img.setFitWidth(20);
			img.setFitHeight(25);
			panel.getEstrellas()[i].setGraphic(img);
		}
	}

	public void funcionEnviar() {
		panel.getEnviar().setOnAction(event -> {
			
			UtilesData.animacionFade(panel.getEnviar());
			IPuntuacion ipunt = new PuntuacionDao();
			Puntuacion punt = new Puntuacion(usu, obraController.obtenerObraActual(), puntuacion);

			try {
				ipunt.actualizar(punt);
			} catch (PuntuacionException e) {
				System.out.println(e);
			}
			pasarObra(obraController.obtenerObraActual());
		});
	}

	public void funcionBoton(int pos) {
		ponerVacios(); // Si se ha pulsado menos que antes se deben vaciar
		this.puntuacion = pos; // Actualizamos la posicion actual
		for (int i = 0; i < puntuacion; i++) {
			ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/estrella.png")));
			img.setFitWidth(20);
			img.setFitHeight(25);
			panel.getEstrellas()[i].setGraphic(img);
		}
	}

	public void pasarObra(Obra o) {
		ponerVacios();
		panel.getEnviar().setVisible(true);
		comprobarCalificado(o);
		
	}
	
	private void comprobarCalificado(Obra o) {
		try {
			IPuntuacion ipunt = new PuntuacionDao();
			boolean haCalificado = ipunt.haCalificadoObra(usu, o);
			if(haCalificado) {
				int media = ipunt.obtenerMediaObra(o);
				mostrarMedia(media);
			}
		}catch(PuntuacionException e) {
			System.out.println(e);
		}
	}
	
	private void mostrarMedia(int media) {
		this.puntuacion = media;
		for (int i = 0; i < puntuacion; i++) {
			ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/estrellaMedia.png")));
			img.setFitWidth(20);
			img.setFitHeight(25);
			panel.getEstrellas()[i].setGraphic(img);
		}
		
		panel.getEnviar().setVisible(false);
	}
}