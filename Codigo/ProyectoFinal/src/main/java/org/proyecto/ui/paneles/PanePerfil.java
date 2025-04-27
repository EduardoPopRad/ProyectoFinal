package org.proyecto.ui.paneles;

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
	
	private boolean edit, deshabilitado;
	private String email, us;
	
	public PanePerfil(Usuario usu) {
		ventana = new Stage();
        ventana.setTitle("Perfil de Usuario");
        
        panel= new Pane();
        panel.setMaxSize(550, 350);
        
        edit = false;
        deshabilitado = true;
        
        imagen = new Circle(50); // Radio del círculo
		Image img = UtilesData.pasarByteAImagen(usu.getImage());
        imagen.setFill(new ImagePattern(img));
        panel.getChildren().add(imagen);
        imagen.setLayoutX(70);	imagen.setLayoutY(70);
        
        id = new TextField(usu.getId()+"");
        id.setMaxSize(40, 20);
        id.setEditable(false);
        panel.getChildren().add(id);
        id.setLayoutX(150);		id.setLayoutY(15);
        
        user = new TextField(usu.getUser());
        user.setMinSize(200, 20);
        user.setEditable(false);
        panel.getChildren().add(user);
        user.setLayoutX(150); 	user.setLayoutY(50);
        
        correo= new TextField(usu.getEmail());
        correo.setMinSize(300, 20);
        correo.setEditable(false);
        panel.getChildren().add(correo);
        correo.setLayoutX(150);	correo.setLayoutY(85);
        
        ImageView imgLapiz = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/editar.png")));
		imgLapiz.setFitWidth(25);
		imgLapiz.setFitHeight(25);
		
        btnEditar = new Button();
		btnEditar.setPrefSize(30, 20);
		btnEditar.setGraphic(imgLapiz);
		btnEditar.getStyleClass().add("botones");
		btnEditar.setOnAction(event -> { //Esto iría en un controller
			if(!edit) {
				email = correo.getText().strip();
				us = user.getText().strip();
			}
			
			if(contrasenaAntigua.isDisabled())
				btnAceptar.setVisible(!btnAceptar.isVisible());
			
			edit = !edit;
			correo.setEditable(edit);
			user.setEditable(edit);
			
			correo.setText(email);
			user.setText(us);
		});
		panel.getChildren().add(btnEditar);
		btnEditar.setLayoutX(103);	btnEditar.setLayoutY(82);
		
		btnContra = new Button("Cambiar Contraseña");
		btnContra.setMaxSize(120, 20);
		btnContra.getStyleClass().add("botones");
		btnContra.setOnAction(event -> {
			if(!correo.isEditable())
				btnAceptar.setVisible(!btnAceptar.isVisible());
			
			deshabilitado = !deshabilitado;
			contrasenaAntigua.setDisable(deshabilitado);
			nuevaContrasena.setDisable(deshabilitado);
		});
		panel.getChildren().add(btnContra);
		btnContra.setLayoutX(50); 	btnContra.setLayoutY(145);
		
		contrasenaAntigua = new PasswordField();
		contrasenaAntigua.setMinSize(200, 20);
		contrasenaAntigua.setPromptText("Contraseña antigua");
		contrasenaAntigua.setDisable(deshabilitado);
		panel.getChildren().add(contrasenaAntigua);
		contrasenaAntigua.setLayoutX(30);	contrasenaAntigua.setLayoutY(180);
		
		nuevaContrasena = new PasswordField();
		nuevaContrasena.setMinSize(200, 20);
		nuevaContrasena.setPromptText("Contraseña nueva");
		nuevaContrasena.setDisable(deshabilitado);
		panel.getChildren().add(nuevaContrasena);
		nuevaContrasena.setLayoutX(30);		nuevaContrasena.setLayoutY(210);
	
		btnAceptar = new Button("Aceptar");
		btnAceptar.setMaxSize(100, 30);
		btnAceptar.setVisible(false);
		btnAceptar.setOnAction(event -> { //Esto iría en un controller
			btnAceptar.setVisible(false);
			
			//Guardar lo editado y no quitar que sea editable el texto
			if(edit) {
				edit=!edit;
				correo.setEditable(edit);
				user.setEditable(edit);
			}
			
//			String contra = Seguridad.hashearContrasena(nuevaContrasena.getText().strip());
			//Guardar la nueva contraseña y deshabilitar los campos
			if(!nuevaContrasena.isDisabled()) {
				deshabilitado = true;
				nuevaContrasena.setDisable(deshabilitado);
				contrasenaAntigua.setDisable(deshabilitado);
			}
			nuevaContrasena.setText("");
			contrasenaAntigua.setText("");
		});
		panel.getChildren().add(btnAceptar);
		btnAceptar.setLayoutX(400);	btnAceptar.setLayoutY(200);
		
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
