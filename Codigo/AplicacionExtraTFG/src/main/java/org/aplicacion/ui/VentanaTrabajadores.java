package org.aplicacion.ui;

import org.aplicacion.controller.PrincipalController;
import org.aplicacion.util.GestorFicheroConfiguracion;
import org.aplicacion.vo.Usuario;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class VentanaTrabajadores {

	private Pane root, izda, dcha;
	private static ListView<Usuario> listView;
	private Button btnActualizar, btnQuitar, btnAnadir;
	private Label error;
	
	private TextField user, email;
	private PasswordField contra;
	private Circle imagen;

	public VentanaTrabajadores(Stage primaryStage) {
		primaryStage.setTitle("Datos Trabajadores");
		
		PrincipalController controller = new PrincipalController(this);
		
		primaryStage.setOnCloseRequest(event -> { // Cuando se cierra la ventana
			GestorFicheroConfiguracion.actualizarValor("estaLogeado", "false");
		});
		
		root = new Pane();	izda = new Pane();	dcha = new Pane(); Pane dchaInferior = new Pane();
		root.setStyle("-fx-background-color: rgba(185, 195, 217, 1);");
		dcha.setStyle("-fx-background-color: rgba(185, 195, 217, 1);");
		
		Platform.runLater(() ->{
			izda.setMinSize(root.getWidth()/2, root.getHeight());
			izda.setLayoutX(0); izda.setLayoutY(0);
			
			dcha.setMinSize(root.getWidth()/2, root.getHeight());
			dcha.setLayoutX(root.getWidth()/2); dcha.setLayoutY(0);
			
			dchaInferior.setMinSize(root.getWidth()/2, root.getHeight());
			dchaInferior.setLayoutX(root.getWidth()/2); dcha.setLayoutY(0);
			
			error.setLayoutX((root.getWidth()/2) - 200); 	error.setLayoutY(750);
		});
		
		dcha.setVisible(false);
		
		error = new Label("");
		error.setMinSize(500, 30);
		error.setVisible(false);
		error.setStyle("-fx-text-fill: red;");
		
		/////////////////////////////////////////////////
		///////////////////////////////// Panel izquierda
		btnActualizar = new Button("⟳");
		
		listView = new ListView<Usuario>();
		listView.setMinSize(550, 700);
		
		izda.getChildren().addAll(btnActualizar, listView);
		listView.setLayoutX(100);	listView.setLayoutY(30);
		btnActualizar.setLayoutX(60); 	btnActualizar.setLayoutY(30);
		
		/////////////////////////////////////////////////
		/////////////////////////////////// Panel derecha
		btnQuitar = new Button("X");
		
		dcha.getChildren().addAll(btnQuitar);
		btnQuitar.setLayoutX(20);	btnQuitar.setLayoutY(30);
		
		/////////////////////////////////////////////////
		////////////////////////////////////////// aparte
		imagen = new Circle(45); // Radio del círculo
		Image img = new Image(getClass().getResourceAsStream("/imagenes/perfil-default.png"));
        imagen.setFill(new ImagePattern(img));
        
		email=new TextField();
		email.setPromptText("Email:");
		email.setPrefWidth(350);
		
		user=new TextField();
		user.setPromptText("Usuario:");
		user.setPrefWidth(350);
		
		contra = new PasswordField();
		contra.setPromptText("Conntraseña:");
		contra.setPrefWidth(350);
		
		btnAnadir = new Button("Añadir Trabajador");
		
		dchaInferior.getChildren().addAll(imagen, user, contra, email, btnAnadir);
		imagen.setLayoutX(390);	imagen.setLayoutY(250);
		email.setLayoutX(210);	email.setLayoutY(310);
		user.setLayoutX(210);	user.setLayoutY(360);
		contra.setLayoutX(210);	contra.setLayoutY(410);
		btnAnadir.setLayoutX(450); 	btnAnadir.setLayoutY(450);
		
		root.getChildren().addAll(izda, dchaInferior, dcha, error);
		
		controller.funcionesVentana();
		controller.funcionesAnadir(primaryStage);
	}

	public Scene getScene() {
		return new Scene(root, 600, 400);
	}

	public Pane getDcha() {
		return dcha;
	}

	public void setDcha(Pane dcha) {
		this.dcha = dcha;
	}

	public ListView<Usuario> getListView() {
		return listView;
	}

	public Button getBtnActualizar() {
		return btnActualizar;
	}

	public Button getBtnQuitar() {
		return btnQuitar;
	}

	public Label getError() {
		return error;
	}

	public Button getBtnAnadir() {
		return btnAnadir;
	}

	public TextField getUser() {
		return user;
	}

	public TextField getEmail() {
		return email;
	}

	public PasswordField getContra() {
		return contra;
	}

	public Circle getImagen() {
		return imagen;
	}
	
}
