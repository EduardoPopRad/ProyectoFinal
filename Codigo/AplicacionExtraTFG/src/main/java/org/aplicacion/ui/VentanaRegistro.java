package org.aplicacion.ui;

import org.aplicacion.controller.LogInController;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class VentanaRegistro {
	
	private Pane root, panel;
	private Circle imagen;
	private TextField user;
	private PasswordField contra;
	private Button registrar;
	private ImageView imgViewFondo;
	private Label error;
	
	public VentanaRegistro(Stage primaryStage) {
		primaryStage.setTitle("Ventana LogIn");
		primaryStage.setMinHeight(550);
		primaryStage.setMinWidth(800);
		
		LogInController controller = new LogInController(this);
		
		root = new Pane(); 	
		
		panel = new Pane();
		panel.setMinSize(400, 300);
		panel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
		
		imgViewFondo = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/FondoPlanetas.png")));
		imgViewFondo.setPreserveRatio(false);
		Platform.runLater(() -> {
			imgViewFondo.setFitWidth(primaryStage.getWidth());
			imgViewFondo.setFitHeight(primaryStage.getHeight());
		});
		
		imagen = new Circle(40); // Radio del círculo
        imagen.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/imagenes/perfil-default.png"))));
		
        user = new TextField();
        user.setPromptText("User: ");
        user.setMinSize(250, 30);
        user.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				contra.requestFocus();
			}
		});
        
        contra = new PasswordField();
        contra.setMinSize(250, 30);
        contra.setPromptText("Password");
        contra.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				registrar.fire();
			}
		});
        
        registrar = new Button("Log In");
        
        error = new Label("Aqui va el mensaje del error");
        error.setVisible(false);
        error.setMinSize(400, 30);
        error.setAlignment(Pos.CENTER);
        error.setStyle("-fx-text-fill: red;");
        
        panel.getChildren().addAll(imagen, user, contra, registrar, error);
        imagen.setLayoutX(200);			imagen.setLayoutY(60);
        user.setLayoutX(75);			user.setLayoutY(110);
        contra.setLayoutX(75);			contra.setLayoutY(160);
        registrar.setLayoutX(175);		registrar.setLayoutY(210);
        error.setLayoutX(0);			error.setLayoutY(250);
        
		root.getChildren().addAll(imgViewFondo, panel);
		panel.setLayoutX(200);	panel.setLayoutY(100);
		
		controller.funciones(primaryStage);
	}
	
	public Scene getScene() {
		return new Scene(root, 600, 400);
	}

	public TextField getUser() {
		return user;
	}

	public PasswordField getContra() {
		return contra;
	}

	public Button getRegistrar() {
		return registrar;
	}

	public Label getError() {
		return error;
	}
}
