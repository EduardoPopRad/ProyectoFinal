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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class PaneLogIn {
	private Pane panel;
	private TextField txtUsu;
	private PasswordField psw;
	private Button logIn, signUp;
	private ImageView imgViewPerfil;
	private CheckBox recordar;
	private Label txt, error;	

	// Cuando esto pase se da foco a txtUsu
	// error aparece debajo de psw con el mensaje cuando no existe el usuario o no
	// concuerda la contraseña con el user
	// y este se elimina cuando se empiece a ingrsar texto,
	public PaneLogIn(Stage primaryStage, RegistroController regisController) {

		txt = new Label("Don't have an account?");
		txt.setStyle("-fx-text-fill: rgba(23, 40, 56, 1);");

		error = new Label("Este es el texto error");
		error.setPrefSize(255, 20);
		error.setAlignment(Pos.CENTER);
		error.setVisible(false);
		error.setStyle("-fx-text-fill: red;");
		
		psw = new PasswordField();
		txtUsu = new TextField();
		recordar = new CheckBox("Remember Me");
		logIn = new Button("LogIn");
		signUp = new Button("Register");

		recordar.getStyleClass().add("check-box");

		// Comprobar si en el fichero de configuracion esta true recordar
		regisController.comprobarRecordar(this);

		txtUsu.setPromptText("User:"); // texto que aparece cuando esta vacio el TextField
		txtUsu.setPrefWidth(250);
		txtUsu.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				psw.requestFocus();
			}
		});
		txtUsu.setStyle("-fx-prompt-text-fill: rgba(23, 40, 56, 1); -fx-background-color: rgba(0, 0, 0, 0); "
				+ "-fx-text-fill: rgba(23, 40, 56, 1);");

		psw.setPromptText("Password:"); // texto que aparece cuando esta vacio el TextField
		psw.setPrefWidth(250);
		psw.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				logIn.fire();
			}
		});
		psw.setStyle(
				"-fx-prompt-text-fill: rgba(23, 40, 56, 1); -fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: rgba(23, 40, 56, 1);");

		signUp.setPrefSize(63, 5); // ancho, alto
		signUp.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0);"
				+ "-fx-text-fill: rgba(23, 40, 56, 1);-fx-font-weight: bold;");
		// Agregar un evento de ratón para poner el efecto al pasar por encima con el ratón
		signUp.setOnMouseEntered(
				e -> signUp.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0);"
						+ "-fx-text-fill: rgba(23, 40, 56, 1);-fx-font-weight: bold;-fx-underline: true;"));
		signUp.setOnMouseExited(
				e -> signUp.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0);"
						+ "-fx-text-fill: rgba(23, 40, 56, 1);-fx-font-weight: bold;"));

		logIn.setPrefSize(255, 35); // ancho, alto
		logIn.setStyle("-fx-background-radius: 10; -fx-background-color: rgba(23, 40, 56, 1);"
				+ "-fx-text-fill: white; -fx-font-size: 15px;");

		imgViewPerfil = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/perfil.png")));
		imgViewPerfil.setFitWidth(80);
		imgViewPerfil.setFitHeight(80);
		imgViewPerfil.setPreserveRatio(true);
		imgViewPerfil.setLayoutX(110);
		imgViewPerfil.setLayoutY(20);

		Line linePsw = new Line(37, 170, 265, 170); // (x1, y1, x2, y2)
		linePsw.setStroke(Color.rgb(23, 40, 56, 1)); // Color de la línea
		linePsw.setStrokeWidth(1); // Ancho de la línea

		Line lineTxt = new Line(37, 130, 265, 130); // (x1, y1, x2, y2)
		lineTxt.setStroke(Color.rgb(23, 40, 56, 1)); // Color de la línea
		lineTxt.setStrokeWidth(1); // Ancho de la línea

		regisController.funcionesPaneLogIn(this, primaryStage);

		panel = new Pane();
		panel.setPrefSize(300, 330); // ancho, alto
		panel.getChildren().addAll(logIn, txt, error, recordar, signUp, psw, linePsw, txtUsu, lineTxt, imgViewPerfil);

		// Organizar hijos de panel
		txtUsu.setLayoutX(30);		txtUsu.setLayoutY(107);
		psw.setLayoutX(30);		psw.setLayoutY(145);
		recordar.setLayoutX(35);		recordar.setLayoutY(185);
		error.setLayoutX(23);        error.setLayoutY(210);
		logIn.setLayoutX(23);		logIn.setLayoutY(235);
		txt.setLayoutX(57);		txt.setLayoutY(284);
		signUp.setLayoutX(177);		signUp.setLayoutY(280);

		Platform.runLater(() -> {
			txtUsu.requestFocus();
		});
	}

	public Pane getPanel() {
		return panel;
	}

	public boolean seRecuerda() {
		return recordar.isSelected();
	}

	public CheckBox getRecordar() {
		return recordar;
	}

	public ImageView getImgViewPerfil() {
		return imgViewPerfil;
	}

	public void setImgViewPerfil(ImageView imgViewPerfil) {
		this.imgViewPerfil = imgViewPerfil;
	}

	public TextField getTxtUsu() {
		return txtUsu;
	}

	public PasswordField getPsw() {
		return psw;
	}

	public Button getLogIn() {
		return logIn;
	}

	public Button getSignUp() {
		return signUp;
	}
	
	public Label getError() {
		return error;
	}
}

