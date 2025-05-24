package org.proyecto.controler;

import java.io.File;
import java.time.LocalDate;

import org.proyecto.dao.IConsulta;
import org.proyecto.dao.IUsuario;
import org.proyecto.dao.impl.ConsultaDao;
import org.proyecto.dao.impl.UsuarioDao;
import org.proyecto.excepciones.ConsultaException;
import org.proyecto.excepciones.UsuarioException;
import org.proyecto.ui.PantallaMuseo;
import org.proyecto.ui.VentanaRegistro;
import org.proyecto.ui.paneles.PaneContact;
import org.proyecto.ui.paneles.PaneLogIn;
import org.proyecto.ui.paneles.PaneSignUp;
import org.proyecto.util.GestorFicheroConfiguracion;
import org.proyecto.util.Seguridad;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Consulta;
import org.proyecto.vo.Rol;
import org.proyecto.vo.Usuario;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RegistroController {

	private VentanaRegistro ventana;
	private String ultimoBoton;

	public RegistroController(VentanaRegistro v) {
		this.ventana = v;
		this.ultimoBoton = ventana.getLogin().getText();
	}

	public void funcionesBotones(Stage primaryStage) {
		ventana.getLogin().setOnAction(event -> {
			mostrarLogInPanel(primaryStage);
		});

		ventana.getContact().setOnAction(event -> {
			mostrarContactPanel(primaryStage);
		});

	}

	///////////////////////////////////
	/////////////////// Cambiar Paneles
	private void mostrarSignUpPanel(Stage primaryStage) {
		if (ventana.getPaneSignUp() == null)
			ventana.setPaneSignUp(new PaneSignUp(primaryStage, this));
		// PaneSignUp
		double X = ventana.getPaneDifu().getLayoutX();
		double Y = ventana.getPaneDifu().getLayoutY();

		ventana.getPanel().getChildren().remove(ventana.getPaneDifu()); // Eliminar el PaneLogIn
		ventana.setPaneDifu(ventana.getPaneSignUp().getPanel());
		ventana.getPaneDifu()
				.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);"
						+ "-fx-border-color: white; -fx-border-width: 2;"
						+ "-fx-border-radius: 15; -fx-background-radius: 15;");
		ventana.getPanel().getChildren().add(ventana.getPaneDifu()); // Agregar el nuevo panel
		ventana.getPaneDifu().setLayoutX(X);
		ventana.getPaneDifu().setLayoutY(Y);
	}

	private void mostrarLogInPanel(Stage primaryStage) {
		if (ultimoBoton.equals(ventana.getContact().getText()))
			vaciarContactPane();

		cambiarEstiloBoton();
		ultimoBoton = ventana.getLogin().getText();

		if (ventana.getPaneLogIn() == null)
			ventana.setPaneLogIn(new PaneLogIn(primaryStage, this));

		ventana.getLogin().setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-text-fill: white; "
				+ "-fx-font-size: 15px; -fx-border-color: white;" + "-fx-border-width: 1; -fx-border-radius: 5;");

		ventana.getPanel().getChildren().remove(ventana.getPaneDifu()); // Si no se elimina se sobrepone encima
		ventana.setPaneDifu(ventana.getPaneLogIn().getPanel());
		ventana.getPaneDifu().setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-border-color: white;"
				+ "-fx-border-width: 2; -fx-border-radius: 15; -fx-background-radius: 15;");
		ventana.getPanel().getChildren().add(ventana.getPaneDifu());
		ventana.getImgViewBorrosa().setFitWidth(295);
		ventana.getImgViewBorrosa().setFitHeight(330);
		ventana.getImgViewBorrosa().setViewport(new javafx.geometry.Rectangle2D(435, 184, 435, 540));

		Platform.runLater(() -> {
			ventana.getPaneDifu().setLayoutX((primaryStage.getWidth() - ventana.getPaneDifu().getWidth()) / 2);
			ventana.getPaneDifu().setLayoutY((primaryStage.getHeight() - ventana.getPaneDifu().getHeight()) / 2.4);

			ventana.getImgViewBorrosa().setLayoutX((primaryStage.getWidth() - ventana.getPaneDifu().getWidth()) / 2);
			ventana.getImgViewBorrosa()
					.setLayoutY((primaryStage.getHeight() - ventana.getPaneDifu().getHeight()) / 2.4);
		});
	}

	private void mostrarContactPanel(Stage primaryStage) {
		if (ultimoBoton.equals(ventana.getLogin().getText())) {
			if (!ventana.getPaneLogIn().seRecuerda())
				vaciarPaneLogIn();
			if (ventana.getPaneSignUp() != null)
				vaciarPaneSignUp();
		}

		cambiarEstiloBoton();
		ultimoBoton = ventana.getContact().getText();

		if (ventana.getContactPane() == null)
			ventana.setPaneContact(new PaneContact(this));

		ventana.getContact().setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-text-fill: white; "
				+ "-fx-font-size: 15px; -fx-border-color: white;" + "-fx-border-width: 1; -fx-border-radius: 5;");

		ventana.getPanel().getChildren().remove(ventana.getPaneDifu()); // Si no se elimina se sobrepone el nuevo sobre
		ventana.setPaneDifu(ventana.getContactPane().getPanel());
		ventana.getPaneDifu()
				.setStyle("-fx-background-color:rgba(5, 5, 5, 0.83);" + "-fx-border-color: white; -fx-border-width: 2;"
						+ "-fx-border-radius: 15; -fx-background-radius: 15;");
		ventana.getPanel().getChildren().add(ventana.getPaneDifu());

		ventana.getImgViewBorrosa().setViewport(new javafx.geometry.Rectangle2D(325, 144, 675, 600)); // CAMBIA

		Platform.runLater(() -> {
			ventana.getPaneDifu().setLayoutX((primaryStage.getWidth() - ventana.getPaneDifu().getWidth()) / 2);
			ventana.getPaneDifu().setLayoutY((primaryStage.getHeight() - ventana.getPaneDifu().getHeight()) / 2.4);

			ventana.getImgViewBorrosa().setFitWidth(ventana.getContactPane().getPanel().getWidth() - 5);
			ventana.getImgViewBorrosa().setFitHeight(ventana.getContactPane().getPanel().getHeight());
			ventana.getImgViewBorrosa().setLayoutX((primaryStage.getWidth() - ventana.getPaneDifu().getWidth()) / 2);
			ventana.getImgViewBorrosa()
					.setLayoutY((primaryStage.getHeight() - ventana.getPaneDifu().getHeight()) / 2.4);
		});
	}

	private void cambiarEstiloBoton() {
		switch (ultimoBoton) {
		case "Contact":
			ventana.getContact().setStyle(
					"-fx-background-color: rgba(255, 255, 255, 0.0); -fx-text-fill: white;" + "-fx-font-size: 15px;");
			break;
		case "Login":
			ventana.getLogin().setStyle(
					"-fx-background-color: rgba(255, 255, 255, 0.0); -fx-text-fill: white;" + "-fx-font-size: 15px;");
			break;
		}
	}

	/////////////////////////////////////////
	////////////////////// PaneSignUp metodos
	public void funcionesBotonesSignUp(PaneSignUp signUp, Stage primaryStage) {
		// Boton salir
		signUp.getSalir().setOnAction(event -> {
			// Cambiar a panel LogIn
			vaciarPaneSignUp();
			signUp.getEmail().requestFocus();
			mostrarLogInPanel(primaryStage);
		});

		// Seleccionar imagen
		signUp.getImagen().setOnMouseClicked(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg"));
			File file = fileChooser.showOpenDialog(primaryStage);

			if (file != null) {
				Image image = new Image(file.toURI().toString());
				signUp.getImagen().setFill(new ImagePattern(image));
			}
		});

		// Boton registrar
		signUp.getRegister().setOnAction(event -> {
			
			IUsuario iusu = new UsuarioDao();
			// Poner no visible TextField de error, ya que al pulsar no deberia aparecer ya
			// que supuestamente se ha solucionado el error
			
			signUp.getError().setVisible(false);
			// Comprobar si hay algun campo vacio
			
			if (signUp.getEmail().getText().strip().isEmpty() || signUp.getPsw().getText().strip().isEmpty()
					|| signUp.getTxtUsu().getText().strip().isEmpty() || signUp.getPsw2().getText().strip().isEmpty()) {
				signUp.getError().setText("Debes rellenar todos los campos");
				signUp.getError().setVisible(true);
				return;
			}
			
			// Comprobar si condiciones estan aceptadas
			if (!signUp.getCheck().isSelected()) {
				signUp.getError().setText("No ha aceptado los términos y condiciones");
				signUp.getError().setVisible(true);
				return;
			}
			
//			Comprobar las contraseñas son iguales
			if (!signUp.getPsw().getText().strip().equals(signUp.getPsw2().getText().strip())) {
				signUp.getError().setText("Las contraseñas no coinciden");
				signUp.getError().setVisible(true);
				return; // Las contraseñas no son iguales por lo que no deberia ejecutar mas codigo
			}
			
			String user = signUp.getTxtUsu().getText().strip();
			
			// Comprobar si el usuario existe en la bbdd ya que es unique
			try {
				if (iusu.existeUsuario(new Usuario(user))) {
					signUp.getError().setText("El usuario ya existe");
					signUp.getError().setVisible(true);
					return;
				}
			}catch(UsuarioException e) {
				System.out.println(e);
			}
			
			// Comprobar si el email esta bien
			String email = signUp.getEmail().getText().strip();
			if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com"))) {
				signUp.getError().setText("El email no es válido");
				signUp.getError().setVisible(true);
				return;
			}
			
			
			
			// Obtener la imagen del usuario
			Image img = (signUp.getImagen().getFill() instanceof ImagePattern)
					? ((ImagePattern) signUp.getImagen().getFill()).getImage(): null;

			byte[] imagen = UtilesData.redimensionarImagenAByte(img);
			
			String contra = Seguridad.hashearContrasena(signUp.getPsw().getText().strip());

			Usuario usu=null;
			try {
				System.out.println( iusu.obtenerRol(new Rol(1)));
				usu = new Usuario(user, contra, email, new Rol(), LocalDate.now(), imagen);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				iusu.insertar(usu);
			} catch (UsuarioException e) {
				System.out.println(e);
			}
			signUp.getSalir().fire();
		});
	}

	/////////////////////////////////////////
	////////////////////// PaneLogIn metodos
	public void funcionesPaneLogIn(PaneLogIn login, Stage primaryStage) {
		// Boton pasar a signUp
		login.getSignUp().setOnAction(event -> {
			vaciarPaneLogIn();
			mostrarSignUpPanel(primaryStage);
		});

		// Boton para registrarse
		login.getLogIn().setOnAction(event -> {

			IUsuario iusu = new UsuarioDao();
			login.getError().setVisible(false);

			if (login.getPsw().getText().strip().isEmpty() || login.getTxtUsu().getText().strip().isEmpty()) {
				login.getError().setText("Debes rellenar todos los campos");
				login.getError().setVisible(true);
				return;
			}

			if (login.getRecordar().isSelected()) {
				GestorFicheroConfiguracion.actualizarValor("usuario", login.getTxtUsu().getText().strip());
				GestorFicheroConfiguracion.actualizarValor("password", login.getPsw().getText().strip());
				GestorFicheroConfiguracion.actualizarValor("recordar", "true");
			} else {
				GestorFicheroConfiguracion.actualizarValor("recordar", "false");
				GestorFicheroConfiguracion.actualizarValor("usuario", "");
				GestorFicheroConfiguracion.actualizarValor("password", "");
			}

			// Busca el cliente en la base de datos con:
			Usuario u = null;
			try {
				u = iusu.obtenerPorUser(new Usuario(login.getTxtUsu().getText().strip()));
			} catch (UsuarioException e) {
				System.out.println(e);
			}
			if (u == null) {
				login.getError().setText("Alguna de las credenciales no concuerdan");
				login.getError().setVisible(true);
				return;
			}

			// Comprobar contraseña hasheada
			if (!Seguridad.verificarContrasena(login.getPsw().getText().strip(), u.getPassword())) {
                login.getError().setText("Alguna de las credenciales no concuerdan");
                login.getError().setVisible(true);
                return;
            }

			primaryStage.setResizable(true);
			primaryStage.setScene(new PantallaMuseo(primaryStage, u).getScene());
			primaryStage.setMaximized(true);
			vaciarPaneLogIn();
		});
	}

	public void comprobarRecordar(PaneLogIn login) {
		if (GestorFicheroConfiguracion.obtenerRuta("recordar").equals("true")) {
			login.getTxtUsu().setText(GestorFicheroConfiguracion.obtenerRuta("usuario"));
			login.getPsw().setText(GestorFicheroConfiguracion.obtenerRuta("password"));
			login.getRecordar().setSelected(true);
		}
	}

	/////////////////////////////////////////
	///////////////////// ContactPane metodos
	public void funcionesContactPane(PaneContact contact) {
		contact.getEnviar().setOnAction(event -> {
			contact.getError().setVisible(false);
			
			if (contact.getTxtArea().getText().strip().isEmpty() || contact.getDescription().getText().strip().isEmpty()
					|| contact.getUser().getText().strip().isEmpty()) {
				
				contact.getError().setText("Debes rellenar todos los campos");
				contact.getError().setVisible(true);
				return;
			}
			
			String desc = contact.getDescription().getText().strip();
			if(desc.length()>=150) {
				contact.getError().setText("Descripcion demasiado larga");
				contact.getError().setVisible(true);
				return;
			}
			
			String user = contact.getUser().getText().strip();
			IUsuario iusu = new UsuarioDao();
			Usuario u = null;
			try {
				u = iusu.obtenerPorUser(new Usuario(user));
			} catch (UsuarioException e) {
				System.out.println(e);
			}
			if (u == null) {
				contact.getError().setText("Alguna de las credenciales no concuerdan");
				contact.getError().setVisible(true);
				return;
			}
			
			IConsulta icons = new ConsultaDao();
			try {
				icons.insertar(new Consulta(u, desc, contact.getTxtArea().getText().strip(), LocalDate.now(), false ));
			} catch (ConsultaException e) {
				System.out.println(e);
			}
			vaciarContactPane();
		});

		contact.getDescription().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				contact.getTxtArea().requestFocus();
			}
		});

		contact.getUser().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				contact.getDescription().requestFocus();
			}
		});
	}

	/////////////////////////////////////
	////////////////////// Metodos vaciar
	public void vaciarPaneSignUp() {
		Image img = new Image(getClass().getResourceAsStream("/recursos/images/perfil-default.png"));
		ventana.getPaneSignUp().getImagen().setFill(new ImagePattern(img));
		ventana.getPaneSignUp().getError().setVisible(false);
		ventana.getPaneSignUp().getEmail().clear();
		ventana.getPaneSignUp().getTxtUsu().clear();
		ventana.getPaneSignUp().getPsw().clear();
		ventana.getPaneSignUp().getPsw2().clear();
		ventana.getPaneSignUp().getCheck().setSelected(false);
	}

	public void vaciarPaneLogIn() {
		ventana.getPaneLogIn().getTxtUsu().clear();
		ventana.getPaneLogIn().getError().setVisible(false);
		ventana.getPaneLogIn().getPsw().clear();
		ventana.getPaneLogIn().getTxtUsu().requestFocus();
		ventana.getPaneLogIn().getRecordar().setSelected(false);
	}

	public void vaciarContactPane() {
		ventana.getContactPane().getUser().clear();
		ventana.getContactPane().getDescription().clear();
		ventana.getContactPane().getTxtArea().clear();
		ventana.getContactPane().getUser().requestFocus();
	}
	
}
