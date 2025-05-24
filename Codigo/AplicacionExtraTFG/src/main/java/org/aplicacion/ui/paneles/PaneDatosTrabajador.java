package org.aplicacion.ui.paneles;

import java.util.ArrayList;
import java.util.List;

import org.aplicacion.controller.PanelDatosController;
import org.aplicacion.dao.IPermiso;
import org.aplicacion.dao.impl.PermisoDao;
import org.aplicacion.util.UtilesData;
import org.aplicacion.vo.Permiso;
import org.aplicacion.vo.Usuario;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class PaneDatosTrabajador {

	private Pane panel;
	private List<CheckBox> checkBoxs;
	private TextField user, email;
	private Label fecha, id, rol, error;
	private Circle imagen;
	private Button aceptar;

	public PaneDatosTrabajador(Usuario u) {
		PanelDatosController controllerDatos = new PanelDatosController(this, u);

		panel = new Pane();
		panel.setMinSize(675, 700);

		IPermiso iper = new PermisoDao();
		List<Permiso> permisos = null;
		try {	permisos = iper.obtenerTodos();	} catch (Exception e) {	}
		
		checkBoxs = new ArrayList<CheckBox>();
		for (int i = 0, x = 290, y = 80; i < permisos.size(); i++) {
			checkBoxs.add(new CheckBox(permisos.get(i).getTipo()));
			panel.getChildren().add(checkBoxs.get(i));
			//Colocarlos en orden de dos en dos
			if (i % 2 == 0) { 
				checkBoxs.get(i).setLayoutX(x);
				checkBoxs.get(i).setLayoutY(y);
				x+=175;
			}else{
				checkBoxs.get(i).setLayoutX(x);
				checkBoxs.get(i).setLayoutY(y);
				y+=50;
				x=290;
			}
		}

		imagen = new Circle(60); // Radio del círculo
		imagen.setFill(new ImagePattern(UtilesData.pasarByteAImagen(u.getImage())));

		user = new TextField(u.getUser());
		user.setPrefSize(250, 30);

		email = new TextField(u.getEmail());
		email.setPrefSize(250, 30);

		aceptar = new Button("Actualizar Usuario");
		
		id = new Label(u.getId()+"");
		fecha = new Label(u.getFechaRegistro()+"");
		rol = new Label(u.getRol().getTipo());
		
		error = new Label("Aqui va el error");
		error.setMinSize(675, 30);
		error.setVisible(false);
		error.setStyle("-fx-text-fill: red;");
		error.setAlignment(Pos.CENTER);
		
		panel.getChildren().addAll(user, email, imagen, aceptar, id, rol, fecha, error);
		imagen.setLayoutX(150);		imagen.setLayoutY(120);
		id.setLayoutX(230);			id.setLayoutY(250);
		rol.setLayoutX(300);			rol.setLayoutY(250);
		fecha.setLayoutX(400);			fecha.setLayoutY(250);
		user.setLayoutX(220);		user.setLayoutY(280);
		email.setLayoutX(220);		email.setLayoutY(330);
		aceptar.setLayoutX(290); 	aceptar.setLayoutY(390);
		error.setLayoutX(0);		error.setLayoutY(420);
		
		controllerDatos.funciones();
	}

	public Pane getPanel() {
		return panel;
	}

	public List<CheckBox> getCheckBoxs() {
		return checkBoxs;
	}

	public TextField getUser() {
		return user;
	}

	public TextField getEmail() {
		return email;
	}

	public Button getAceptar() {
		return aceptar;
	}
	
	public Label getError() {
		return error;
	}
}
