package org.proyecto.ui.paneles;

import org.proyecto.controler.ObraController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneDatosObra {

	private VBox panel;

	private TextField autor;
	private TextField estilo;
	private TextField dimension;
	private TextField ubicacion;
	private TextField propia;

	private TextArea descripcion;

	private RadioButton rbTodo;
	private RadioButton rbCuadro;
	private RadioButton rbGrabado;
	private RadioButton rbEscultura;

	private ToggleGroup group;

	public PaneDatosObra(Stage primaryStage, ObraController controller) {
		panel = new VBox();
		panel.setMaxSize(700, 650);
		panel.setSpacing(20);

		autor = new TextField();
		autor.setPromptText("Autor...");
		autor.setMaxWidth(650);
		autor.setPrefHeight(40);
		autor.setEditable(false);
		autor.getStyleClass().add("txt-principales");

		estilo = new TextField();
		estilo.setPromptText("Style...");
		estilo.setMaxWidth(650);
		estilo.setPrefHeight(40);
		estilo.setEditable(false);
		estilo.getStyleClass().add("txt-principales");

		dimension = new TextField();
		dimension.setPromptText("Dimension...");
		dimension.setMaxWidth(650);
		dimension.setPrefHeight(40);
		dimension.setEditable(false);
		dimension.getStyleClass().add("txt-principales");

		ubicacion = new TextField();
		ubicacion.setPromptText("Ubicacion...");
		ubicacion.setMaxWidth(650);
		ubicacion.setPrefHeight(40);
		ubicacion.setEditable(false);
		ubicacion.getStyleClass().add("txt-principales");

		propia = new TextField();
		propia.setPromptText("Self...");
		propia.setMaxWidth(650);
		propia.setPrefHeight(40);
		propia.setEditable(false);
		propia.getStyleClass().add("txt-principales");

		group = new ToggleGroup();

		rbTodo = new RadioButton("Todo");
		rbTodo.getStyleClass().add("radio-button");
		
		rbCuadro = new RadioButton("Cuadro");
		rbCuadro.getStyleClass().add("radio-button");

		rbGrabado = new RadioButton("Grabado");
		rbGrabado.getStyleClass().add("radio-button");

		
		rbEscultura = new RadioButton("Escultura");
		rbEscultura.getStyleClass().add("radio-button");
		
		HBox radioButtons = new HBox(30, rbTodo, rbCuadro, rbGrabado, rbEscultura);
		radioButtons.setAlignment(Pos.CENTER);
		panel.setAlignment(Pos.CENTER);

		Platform.runLater(() -> {
			controller.funcionesBotonesDatosObra();

		});

		// TextArea
		descripcion = new TextArea();
		descripcion.setWrapText(true);
		descripcion.setPromptText("Descripcion...");
		descripcion.setMaxWidth(650);
		descripcion.setMaxHeight(350);
		descripcion.setEditable(false);
		descripcion.setStyle("-fx-background-color: lightblue;");
		descripcion.getStyleClass().add("text-area");

		VBox.setMargin(descripcion, new Insets(0, 0, 0, 30));

		panel.getChildren().addAll(radioButtons, autor, estilo, dimension, ubicacion, propia, descripcion);
	}

	////////////////////////////////////////
	/////////////////////// Getters y Setters

	public VBox getPanel() {
		return panel;
	}

	public TextField getAutor() {
		return autor;
	}

	public void setAutor(TextField autor) {
		this.autor = autor;
	}

	public TextField getEstilo() {
		return estilo;
	}

	public void setEstilo(TextField estilo) {
		this.estilo = estilo;
	}

	public TextField getDimension() {
		return dimension;
	}

	public void setDimension(TextField dimension) {
		this.dimension = dimension;
	}

	public TextField getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(TextField ubicacion) {
		this.ubicacion = ubicacion;
	}

	public TextField getPropia() {
		return propia;
	}

	public void setPropia(TextField propia) {
		this.propia = propia;
	}

	public TextArea getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(TextArea descripcion) {
		this.descripcion = descripcion;
	}

	public void setPanel(VBox panel) {
		this.panel = panel;
	}

	public RadioButton getRbTodo() {
		return rbTodo;
	}

	public RadioButton getRbCuadro() {
		return rbCuadro;
	}

	public RadioButton getRbGrabado() {
		return rbGrabado;
	}

	public RadioButton getRbEscultura() {
		return rbEscultura;
	}

	public ToggleGroup getGroup() {
		return group;
	}
}

