package org.proyecto.ui.paneles;

import org.proyecto.controler.PerfilController;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Usuario;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PanePerfil {
	
	private Stage ventana;
	private Pane panel;
	private Button btnEditar,btnAceptar, btnContra;
	private TextField user, correo, id;
	private PasswordField contrasenaAntigua, nuevaContrasena;
	private Circle imagen;
	
	public PanePerfil(Usuario usu) {
		ventana = new Stage();
        ventana.setTitle("Perfil de Usuario");
        
        PerfilController controller = new PerfilController(this, usu);
        
        panel= new Pane();
        panel.setMaxSize(550, 350);
        
        imagen = new Circle(50); // Radio del círculo
		Image img = UtilesData.pasarByteAImagen(usu.getImage());
        imagen.setFill(new ImagePattern(img));
        panel.getChildren().add(imagen);
        imagen.setLayoutX(70);	imagen.setLayoutY(70);
        
        id = new TextField(usu.getId()+"");
        id.setFocusTraversable(false);
        id.setMaxSize(40, 20);
        id.setEditable(false);
        panel.getChildren().add(id);
        id.setLayoutX(150);		id.setLayoutY(15);
        
        user = new TextField();
        user.setText(usu.getUser());
        user.setFocusTraversable(false);
        user.setMinSize(200, 20);
        user.setEditable(false);
        panel.getChildren().add(user);
        user.setLayoutX(150); 	user.setLayoutY(50);
        
        correo= new TextField();
        correo.setText(usu.getEmail());
        correo.setFocusTraversable(false);
        correo.setMinSize(300, 20);
        correo.setEditable(false);
        panel.getChildren().add(correo);
        correo.setLayoutX(150);	correo.setLayoutY(85);
        
        ImageView imgLapiz = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/editar.png")));
		imgLapiz.setFitWidth(25);
		imgLapiz.setFitHeight(25);
		
		btnEditar = new Button();
		btnEditar.setStyle("-fx-background-color: transparent;");
		btnEditar.setFocusTraversable(false);
		btnEditar.setPrefSize(30, 20);
		btnEditar.setGraphic(imgLapiz);
		btnEditar.getStyleClass().add("botones");
		panel.getChildren().add(btnEditar);
		btnEditar.setLayoutX(103);	btnEditar.setLayoutY(82);
		
		btnContra = new Button("Cambiar Contraseña");
		btnContra.setFocusTraversable(false);
		btnContra.setMaxSize(125, 20);
		btnContra.getStyleClass().add("botones");
		panel.getChildren().add(btnContra);
		btnContra.setLayoutX(50);	btnContra.setLayoutY(145);
		
		contrasenaAntigua = new PasswordField();
		contrasenaAntigua.setMinSize(200, 20);
		contrasenaAntigua.setPromptText("Contraseña antigua");
		panel.getChildren().add(contrasenaAntigua);
		contrasenaAntigua.setLayoutX(30);	contrasenaAntigua.setLayoutY(180);
		
		nuevaContrasena = new PasswordField();
		nuevaContrasena.setMinSize(200, 20);
		nuevaContrasena.setPromptText("Contraseña nueva");
		panel.getChildren().add(nuevaContrasena);
		nuevaContrasena.setLayoutX(30);		nuevaContrasena.setLayoutY(210);
	
		btnAceptar = new Button("Aceptar");
		btnAceptar.setMaxSize(100, 30);
		btnAceptar.setVisible(false);
		panel.getChildren().add(btnAceptar);
		btnAceptar.setLayoutX(400);	    btnAceptar.setLayoutY(200);
		
		controller.funcionesBotones();
		
        Scene escenaPerfil = new Scene(panel, 480, 250);
        ventana.setScene(escenaPerfil);
	}
	
    public void mostrarVentanaPerfil() {
        ventana.show();
    }

    ////////////////////////////////////
    //////////////////////////// Getters
	public Button getBtnEditar() {
		return btnEditar;
	}
	public Button getBtnAceptar() {
		return btnAceptar;
	}
	public Button getBtnContra() {
		return btnContra;
	}
	public TextField getUser() {
		return user;
	}
	public TextField getCorreo() {
		return correo;
	}
	public PasswordField getNuevaContrasena() {
		return nuevaContrasena;
	}
	public PasswordField getContrasenaAntigua() {
		return contrasenaAntigua;
	}
    
}
