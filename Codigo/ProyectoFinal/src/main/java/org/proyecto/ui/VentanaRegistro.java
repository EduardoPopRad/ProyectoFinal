package org.proyecto.ui;

import org.proyecto.controler.RegistroController;
import org.proyecto.ui.paneles.PaneContact;
import org.proyecto.ui.paneles.PaneLogIn;
import org.proyecto.ui.paneles.PaneSignUp;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VentanaRegistro {

	private Scene scene;
	private Pane panel, paneDifu;
	private ImageView imgViewFondo, imgViewBorrosa;
	private PaneLogIn paneLogIn;
	private PaneSignUp paneSignUp;
	private PaneContact contactPane;
	private Button login, contact;
	private Label logo;
	private double valAncho, valAlto;

	public VentanaRegistro(Stage primaryStage) {

		primaryStage.setTitle("Register");
		primaryStage.setResizable(false);

		paneDifu = new Pane();

		panel = new Pane();
		panel.setPrefWidth(primaryStage.getWidth());
		panel.setPrefHeight(primaryStage.getHeight());

		imgViewFondo = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/fondo.png")));
		imgViewFondo.setPreserveRatio(true);
		Platform.runLater(() -> {
			imgViewFondo.setFitWidth(primaryStage.getWidth());
			imgViewFondo.setFitHeight(primaryStage.getHeight());
		});

		logo = new Label("POP");
		logo.setPrefSize(100, 25);
		logo.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);-fx-text-fill: white; -fx-font-size: 30px;");

		contact = new Button("Contact");
		contact.setPrefSize(90, 30);
		contact.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-text-fill: white; -fx-font-size: 15px;");

		login = new Button("Login");
		login.setPrefSize(90, 30);
		login.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-text-fill: white; -fx-font-size: 15px;");

		imgViewBorrosa = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/fondo.png")));
		imgViewBorrosa.setFitWidth(295);
		imgViewBorrosa.setFitHeight(330);
		imgViewBorrosa.setStyle("-fx-background-radius: 20; -fx-border-radius: 20;");
		imgViewBorrosa.setEffect(new GaussianBlur(15)); // Aplicar desenfoque
		// Ajustar el recorte horizontal pos coge | vertical posicion coge | ancho area
		// visible | alto area visible
		imgViewBorrosa.setViewport(new javafx.geometry.Rectangle2D(435, 184, 435, 540)); // Área a desenfocar

		panel.getChildren().addAll(imgViewFondo, logo, imgViewBorrosa, paneDifu, contact, login);

		logo.setLayoutX(20);
		logo.setLayoutY(5);
		contact.setLayoutX(600);
		contact.setLayoutY(5);
		login.setLayoutX(700);
		login.setLayoutY(5);

		Platform.runLater(() -> {
			login.fire();

			paneDifu.setLayoutX((primaryStage.getWidth() - paneDifu.getWidth()) / 2);
			paneDifu.setLayoutY((primaryStage.getHeight() - paneDifu.getHeight()) / 2.4);
			imgViewBorrosa.setLayoutX((primaryStage.getWidth() - paneDifu.getWidth()) / 2);
			imgViewBorrosa.setLayoutY((primaryStage.getHeight() - paneDifu.getHeight()) / 2.4);

		});

		RegistroController controller = new RegistroController(this);
		controller.funcionesBotones(primaryStage);

		scene = new Scene(panel, 800, 540);
		scene.getStylesheets().add(getClass().getResource("/recursos/styles/CSS").toExternalForm());

	} // Fin constructor

	public Scene getScene() {
		return scene;
	}

	public Double getValAncho() {
		return valAncho;
	}

	public Double getValAlto() {
		return valAlto;
	}

	public Pane getPanel() {
		return panel;
	}

	public Pane getPaneDifu() {
		return paneDifu;
	}

	public void setPaneDifu(Pane p) {
		this.paneDifu = p;
	}

	public PaneLogIn getPaneLogIn() {
		return paneLogIn;
	}

	public void setPaneLogIn(PaneLogIn pl) {
		this.paneLogIn = pl;
	}

	public PaneSignUp getPaneSignUp() {
		return paneSignUp;
	}

	public void setPaneSignUp(PaneSignUp ps) {
		this.paneSignUp = ps;
	}

	public PaneContact getContactPane() {
		return contactPane;
	}

	public void setPaneContact(PaneContact pc) {
		this.contactPane = pc;
	}

	public Button getLogin() {
		return login;
	}

	public Button getContact() {
		return contact;
	}

	public ImageView getImgViewBorrosa() {
		return imgViewBorrosa;
	}

} 
