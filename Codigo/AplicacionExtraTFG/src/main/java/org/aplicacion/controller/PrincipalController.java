package org.aplicacion.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.aplicacion.dao.IUsuario;
import org.aplicacion.dao.impl.UsuarioDao;
import org.aplicacion.ui.VentanaTrabajadores;
import org.aplicacion.ui.paneles.PaneDatosTrabajador;
import org.aplicacion.util.Seguridad;
import org.aplicacion.util.UtilesData;
import org.aplicacion.vo.Rol;
import org.aplicacion.vo.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrincipalController {

	private VentanaTrabajadores ventana;
	private Usuario seleccionado;
	private Pane panelDatos;

	public PrincipalController(VentanaTrabajadores v) {
		this.ventana = v;
	}

	public void funcionesVentana() {
		//////////////////////////////////////// ListView
		ventana.getListView().setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				seleccionado = ventana.getListView().getSelectionModel().getSelectedItem();
				if (seleccionado != null) {
					// Cambiar los datos dentro de PaneDatos con los datos del usuario seleccionado
					cambiarDatos();
					vaciarInfoAnadir();
				}
			}
		});

		ventana.getListView().setCellFactory(lv -> new ListCell<>() {
			@Override
			protected void updateItem(Usuario item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
					setStyle("");
				} else {
					setText(item.toString());
					setStyle("-fx-border-color: black; -fx-border-width: 1px;");
				}
			}
		});

		///////////////////////////////////////// Botones
		ventana.getBtnActualizar().setOnAction(e -> {
			UtilesData.animacionFade(ventana.getBtnActualizar());
			cargarUsuarios();
			// Si hay alguno seleccionado se deberia actualizar los datos tmb
			if (seleccionado != null)
				cambiarDatos();

			vaciarInfoAnadir();
		});

		ventana.getBtnQuitar().setOnAction(e -> {
			UtilesData.animacionFade(ventana.getBtnQuitar());
			ventana.getDcha().getChildren().remove(panelDatos);
			ventana.getDcha().setVisible(false);
			seleccionado = null;
			panelDatos = null;
		});
	}

	//////////////////////////////////////////////////////
	/////////////////////// Funciones parte añadir usuario
	public void funcionesAnadir(Stage primaryStage) {
		///////////////////////////////////////// Imagen
		ventana.getImagen().setOnMouseClicked(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg"));
			File file = fileChooser.showOpenDialog(primaryStage);

			if (file != null) {
				Image image = new Image(file.toURI().toString());
				ventana.getImagen().setFill(new ImagePattern(image));
			}
		});

		///////////////////////////////////////// btnAnadir
		ventana.getBtnAnadir().setOnAction(event -> {
			ventana.getError().setVisible(false);
			try {
				UtilesData.animacionFade(ventana.getBtnAnadir());
				
				// Comprobar si hay algo vacio
				if (ventana.getUser().getText().isEmpty() || ventana.getEmail().getText().isEmpty()
						|| ventana.getContra().getText().isEmpty()) {
					ventana.getError().setText("No se puede continuar porque hay campos vacios");
					ventana.getError().setVisible(true);
					return;
				}
				
				// Comprobar si user existe
				String user = ventana.getUser().getText().strip();
				IUsuario iusu = new UsuarioDao();
				if (iusu.obtenerPorUser(new Usuario(user)) != null) {
					ventana.getError().setText("No se puede continuar porque el usuario ya existe en la BBDD");
					ventana.getError().setVisible(true);
					return;
				}
				
				// Comprobar si email es correcto
				String email = ventana.getEmail().getText().strip();
				if (!email.endsWith("@miempresa.com")) {
					ventana.getError().setText("No se puede continuar porque el Email no es valido");
					ventana.getError().setVisible(true);
					return;
				}
			
				// Obtener la imagen del circle
				Image img = (ventana.getImagen().getFill() instanceof ImagePattern)
						? ((ImagePattern) ventana.getImagen().getFill()).getImage(): null;

				byte[] imagen = UtilesData.redimensionarImagenAByte(img);
				
				String contra = Seguridad.hashearContrasena(ventana.getContra().getText().strip());

				// Añadir un usuario
				Usuario usu = new Usuario(user, contra, email, new Rol(), LocalDate.now(), imagen);
				iusu.insertar(usu);

				vaciarInfoAnadir();
				ventana.getBtnActualizar().fire();
			} catch (Exception e) {
				ventana.getError().setText("Se ha producido algun error durante la creacion del usuario");
			}
		});
		
		cargarUsuarios();
	}

	///////////////////////////////////////////////
	////////////////////////////// Metodos privados
	private void cargarUsuarios() {
		try {
			IUsuario iu = new UsuarioDao();
			List<Usuario> usuarios = iu.listaTrabajadores();
			ObservableList<Usuario> observableList = FXCollections.observableArrayList(usuarios);
			ventana.getListView().setItems(observableList);
		} catch (Exception e) {
			ventana.getError().setText("Se ha producido algun error");
		}
	}

	private void cambiarDatos() {
		PaneDatosTrabajador paneDatos = new PaneDatosTrabajador(seleccionado);
		panelDatos = paneDatos.getPanel();
		ventana.getDcha().getChildren().add(panelDatos);
		ventana.getDcha().setVisible(true);
		panelDatos.setLayoutX(50);
		panelDatos.setLayoutY(30);
	}

	private void vaciarInfoAnadir() {
		ventana.getEmail().setText("");
		ventana.getUser().setText("");
		ventana.getContra().setText("");
		ventana.getImagen()
				.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/imagenes/perfil-default.png"))));
	}
}
