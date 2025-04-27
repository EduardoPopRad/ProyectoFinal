package org.proyecto.ui.paneles;

import org.proyecto.controler.ObraController;
import org.proyecto.vo.Usuario;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneImagenObra {

	private BorderPane panel;
	private ImageView imagen;
	private Pane pane;
	private TextField titulo, anyo, tituloYanyo;
	private HBox arriba;
	private VBox estrellas;
	private PaneEstrellas paneEstrellas;

	// Faltan las estrellas y el boton de calificar

	public PaneImagenObra(Usuario usu, Stage primaryStage, ObraController controler) {
		panel = new BorderPane();
		panel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
		panel.setMaxSize(650, 650);

		////////////////////////////////////
		//////////////////////// Parte Centro
		imagen = new ImageView();
		imagen.setFitWidth(550);
		imagen.setFitHeight(550);
		imagen.setPreserveRatio(true);
		panel.setCenter(imagen);

		////////////////////////////////////
		/////////////////////// Parte Arriba
		titulo = new TextField();
		titulo.setPromptText("Title");
		titulo.setPrefWidth(400);
		titulo.setPrefHeight(40);
		titulo.setEditable(false);
		titulo.getStyleClass().add("txt-principales");
		
		tituloYanyo = new TextField();
		tituloYanyo.setPromptText("Title and year");
		tituloYanyo.setPrefWidth(650); 
		tituloYanyo.setPrefHeight(40);
		tituloYanyo.setEditable(false);
		tituloYanyo.getStyleClass().add("txt-principales");
		
		anyo = new TextField();
		anyo.setPromptText("Year");
		anyo.setPrefWidth(90);
		anyo.setPrefHeight(40);
		anyo.setEditable(false);
		anyo.getStyleClass().add("txt-principales");

		arriba = new HBox();
		arriba.setMaxSize(650, 30);
		arriba.setAlignment(Pos.CENTER);
		arriba.getChildren().addAll(tituloYanyo);

		panel.setTop(arriba);
		BorderPane.setAlignment(arriba, Pos.CENTER);

		///////////////////////////////////
		//////////////////////// Parte Abajo
		///// Botones opcionales + calificacion
		pane = new Pane();
		pane.setMaxWidth(650);

		paneEstrellas = new PaneEstrellas(controler, usu);
		estrellas = paneEstrellas.getPanel();
		pane.getChildren().add(estrellas);
		estrellas.setLayoutX(220);
		
		panel.setBottom(pane);

		Platform.runLater(() -> {
			controler.botonesVisibles();
			controler.funcionesBotones(primaryStage);
		});
	}

	/////////////////////////////////////////
	//////////////////////// Getters y Setters
	public BorderPane getPanel() {
		return panel;
	}

	public ImageView getImagen() {
		return imagen;
	}

	public void setImagen(ImageView imagen) {
		this.imagen = imagen;
	}

	public TextField getTitulo() {
		return titulo;
	}

	public void setTitulo(TextField titulo) {
		this.titulo = titulo;
	}

	public TextField getAnyo() {
		return anyo;
	}

	public void setAnyo(TextField anyo) {
		this.anyo = anyo;
	}

	public void setPanel(BorderPane panel) {
		this.panel = panel;
	}

	public Pane getPaneAbajo() {
		return pane;
	}
	
	public TextField getTituloYanyo() {
        return tituloYanyo;
    }
	
	public HBox getArriba() {
		return arriba;
	}
	
	public VBox getEstrellas() {
		return estrellas;
	}
	
	public PaneEstrellas getPaneEstrellas() {
		return paneEstrellas;
	}

}
