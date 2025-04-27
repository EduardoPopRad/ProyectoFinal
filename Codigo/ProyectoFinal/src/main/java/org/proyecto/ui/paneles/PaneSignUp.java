package org.proyecto.ui.paneles;

import org.proyecto.controler.RegistroController;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class PaneSignUp {
	private Circle imagen;
	private Label lbl, error;
	private Pane panel;
	private TextField txtUsu, email;
	private PasswordField psw, psw2;
	private CheckBox check;
	private Button register, salir;

	public PaneSignUp(Stage primaryStage, RegistroController regisController) {
		
		panel=new Pane();
		panel.setPrefSize(300, 330); //ancho, alto
		
		error=new Label("Este es el texto de error");
		error.setPrefSize(255, 20);
		error.setAlignment(Pos.CENTER);
		error.setVisible(false);
		error.setStyle("-fx-text-fill: red;");
		
		salir=new Button("X");
		salir.setPrefSize(3, 3);
		salir.setStyle("-fx-background-radius: 10% 50% 10% 50%; -fx-background-color: rgba(23, 40, 56, 1);"
				+ "-fx-text-fill: white; -fx-font-weight: bold;");
		
		lbl=new Label("Sign Up");
		lbl.setPrefSize(80, 25);
		lbl.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: rgba(23, 40, 56, 1);"
				+ "-fx-font-size: 20px;-fx-font-weight: bold;");
		
		imagen = new Circle(30); // Radio del círculo
		Image img = new Image(getClass().getResourceAsStream("/recursos/images/perfil-default.png"));
        imagen.setFill(new ImagePattern(img));
		
		email=new TextField();
		email.setPromptText("Email:");
		email.setPrefWidth(250);
		email.setOnKeyPressed(e -> { 
			if (e.getCode() == KeyCode.ENTER) {
				txtUsu.requestFocus();
			} 
		});
		email.setStyle("-fx-prompt-text-fill: rgba(23, 40, 56, 1); -fx-background-color: rgba(0, 0, 0, 0); "
				+ "-fx-text-fill: rgba(23, 40, 56, 1);");
		
		txtUsu=new TextField();
		txtUsu.setPromptText("User:");
		txtUsu.setPrefWidth(250);
		txtUsu.setOnKeyPressed(e -> { 
			if (e.getCode() == KeyCode.ENTER) {
				psw.requestFocus();
			} 
		});
		txtUsu.setStyle("-fx-prompt-text-fill: rgba(23, 40, 56, 1); -fx-background-color: rgba(0, 0, 0, 0); "
				+ "-fx-text-fill: rgba(23, 40, 56, 1);");
		
		psw=new PasswordField();
		psw.setPromptText("Password:");
		psw.setPrefWidth(250);
		psw.setOnKeyPressed(e -> { 
			if (e.getCode() == KeyCode.ENTER) {
				psw2.requestFocus();
			} 
		});
		psw.setStyle("-fx-prompt-text-fill: rgba(23, 40, 56, 1); -fx-background-color: rgba(0, 0, 0, 0); "
				+ "-fx-text-fill: rgba(23, 40, 56, 1);");
		
		psw2=new PasswordField();
		psw2.setPromptText("Type again your password:");
		psw2.setPrefWidth(250);
		psw2.setOnKeyPressed(e -> { 
			if (e.getCode() == KeyCode.ENTER) {
				check.requestFocus();
			} 
		});
		psw2.setStyle("-fx-prompt-text-fill: rgba(23, 40, 56, 1); -fx-background-color: rgba(0, 0, 0, 0); "
				+ "-fx-text-fill: rgba(23, 40, 56, 1);");
		
		check = new CheckBox("I Accept Terms and Conditions");
		check.getStyleClass().add("check-box");
		check.setOnKeyPressed(e -> { 
			if (e.getCode() == KeyCode.ENTER) {
				check.setSelected(true);
				register.requestFocus();
			}
		});
		
		register=new Button("Register");
		register.setPrefSize(255, 35); //ancho, alto
		register.setStyle("-fx-background-radius: 10; -fx-background-color: rgba(23, 40, 56, 1);"
				+ " -fx-text-fill: white; -fx-font-size: 15px;");
		
		Line lineCorreo = new Line(37, 100, 260, 100); // (x1, y1, x2, y2)
		lineCorreo.setStroke(Color.rgb(23, 40, 56, 1)); // Color de la línea 
		lineCorreo.setStrokeWidth(1); // Ancho de la línea
		
		Line lineUsu = new Line(37, 135, 260, 135); // (x1, y1, x2, y2)
		lineUsu.setStroke(Color.rgb(23, 40, 56, 1)); 
		lineUsu.setStrokeWidth(1); 
		
		Line linePsw = new Line(37, 170, 260, 170); // (x1, y1, x2, y2)
		linePsw.setStroke(Color.rgb(23, 40, 56, 1)); 
		linePsw.setStrokeWidth(1); 
		
		Line linePsw2 = new Line(37, 205, 260, 205); // (x1, y1, x2, y2)
		linePsw2.setStroke(Color.rgb(23, 40, 56, 1));
		linePsw2.setStrokeWidth(1); 
		
		regisController.funcionesBotonesSignUp(this, primaryStage);
		
		panel.getChildren().addAll(lbl,salir, imagen, lineCorreo, email, lineUsu, txtUsu, linePsw, psw, linePsw2, psw2, check, error, register);
		
		salir.setLayoutX(274);		salir.setLayoutY(2);
		lbl.setLayoutX(70);			lbl.setLayoutY(30);
		imagen.setLayoutX(210); 	imagen.setLayoutY(45);
		email.setLayoutX(30);		email.setLayoutY(75);
		txtUsu.setLayoutX(30);		txtUsu.setLayoutY(110);
		psw.setLayoutX(30);			psw.setLayoutY(145);
		psw2.setLayoutX(30);		psw2.setLayoutY(180);
		check.setLayoutX(35);		check.setLayoutY(215);
		error.setLayoutX(23);		error.setLayoutY(234);
		register.setLayoutX(23);	register.setLayoutY(255);
		
		Platform.runLater(() -> email.requestFocus());
	}
	
	public Pane getPanel() {
		return panel;
	}

	public Circle getImagen() {
		return imagen;
	}

	public TextField getTxtUsu() {
		return txtUsu;
	}

	public TextField getEmail() {
		return email;
	}

	public PasswordField getPsw() {
		return psw;
	}

	public PasswordField getPsw2() {
		return psw2;
	}

	public CheckBox getCheck() {
		return check;
	}

	public Button getRegister() {
		return register;
	}

	public Button getSalir() {
		return salir;
	}
	
	public Label getError() {
		return error;
	}
}

