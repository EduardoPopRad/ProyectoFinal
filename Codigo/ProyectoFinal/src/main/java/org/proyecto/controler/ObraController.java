package org.proyecto.controler;

import java.io.File;

import org.proyecto.dao.impl.ObraService;
import org.proyecto.ui.PantallaMuseo;
import org.proyecto.ui.paneles.PaneDatosObra;
import org.proyecto.ui.paneles.PaneImagenObra;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Cuadro;
import org.proyecto.vo.Escultura;
import org.proyecto.vo.Grabado;
import org.proyecto.vo.Obra;
import org.proyecto.vo.Usuario;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Clase para insertar los datos en los Paneles que muestran los datos de la obra
 * Funciones de los botones de AMBOS PANELES
 */
public class ObraController {

	private PaneImagenObra paneImagen;
	private PaneDatosObra paneDatos;
	private PantallaMuseo museo;
	private Usuario usuario;

	private Button btnEliminar, btnGuardarEdit, btnEditar, btnAnadir, btnGuardarAnadir;

	private boolean imgSele = false; // Si esta a true es pq se va a actualizar o insertar
	private ObraService obraService;
	private EstrellasController estrellas;
	
	public ObraController(Usuario usu, ObraService obr, PantallaMuseo museo) {
		this.usuario = usu;
		this.obraService = obr;
		this.museo=museo;
	}

	public void cargarObra(Obra o) {
		
		String propi ="";
		if (o instanceof Cuadro) 
			propi = ((Cuadro) o).getPropia();
		else if(o instanceof Escultura)
			propi = ((Escultura) o).getPropia();
		else if(o instanceof Grabado)
			propi = ((Grabado) o).getPropia();
        
		paneImagen.getTitulo().setText(o.getTitulo()); //Por si actualizan o insertan que puedan ver el valor;
		paneImagen.getAnyo().setText(o.getAnyo()); //Por si actualizan o insertanque puedan ver el valor
		paneImagen.getTituloYanyo().setText(o.getTitulo()+"( "+o.getAnyo()+" )");
		paneImagen.getImagen().setImage(UtilesData.pasarByteAImagen(o.getImage()));

		paneDatos.getAutor().setText(o.getAutor());
		paneDatos.getEstilo().setText(o.getPeriodo());
		paneDatos.getDimension().setText(o.getMedidas());
		paneDatos.getUbicacion().setText(o.getExposicion());
		paneDatos.getPropia().setText(propi);
		paneDatos.getDescripcion().setText(o.getDescripcion());
		
		estrellas.pasarObra(o);
	}

	public void cargarIzquierda() {
		cargarObra(obraService.obtenerIzquierda());
	}

	public void cargarDerecha() {
		cargarObra(obraService.obtenerDerecha());
	}
	
	public Obra obtenerObraActual() {
		return obraService.obtenerActual();
	}

	// Metodo para comprobar si tiene los permisos para ver los botones y añadirlos
	// si tiene la opcion
	public void botonesVisibles() {
		if (usuario.getRol().getTipo().equals("Cliente"))
			return; // Si el usuario es un cliente no puede ver ninguno

		// Comprobar si tiene los permisos para ver los botones, si los tienen se añaden
		if (usuario.isPuedeActualizar())
			botonesActualizar();

		if (usuario.isPuedeEliminar())
			botonEliminar();

		if (usuario.isPuedeInsertar())
			botonesInsertar();
	}

	public void funcionesBotones(Stage primaryStage) {

		// Permitir abrir el fileChooser solo si ha sido apretado el boton Añadir
		paneImagen.getImagen().setOnMouseClicked(e -> {
			if (imgSele) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg"));
				File file = fileChooser.showOpenDialog(primaryStage);

				if (file != null) {
					Image image = new Image(file.toURI().toString());
					paneImagen.getImagen().setImage(image);
				}
			}
		});
	}

	public void funcionesBotonesDatosObra() {
		// Agruparlos en un ToggleGroup (solo uno puede estar seleccionado a la vez)
		paneDatos.getRbTodo().setToggleGroup(paneDatos.getGroup());
		paneDatos.getRbCuadro().setToggleGroup(paneDatos.getGroup());
		paneDatos.getRbGrabado().setToggleGroup(paneDatos.getGroup());
		paneDatos.getRbEscultura().setToggleGroup(paneDatos.getGroup());

		// Agregar eventos a cada botón
		paneDatos.getRbTodo().setOnAction(event -> {
			obraService.hacerFiltrado(paneDatos.getRbTodo().getText());
			if (!imgSele) {
				cargarObra(obraService.obtenerActual());
			} 
		});
		paneDatos.getRbCuadro().setOnAction(event -> {
			obraService.hacerFiltrado(paneDatos.getRbCuadro().getText());
			if (!imgSele) {
				cargarObra(obraService.obtenerActual());
			}
		});
		paneDatos.getRbGrabado().setOnAction(event -> {
			obraService.hacerFiltrado(paneDatos.getRbGrabado().getText());
			if (!imgSele) {
				cargarObra(obraService.obtenerActual());
			} 
		});
		paneDatos.getRbEscultura().setOnAction(event -> {
			obraService.hacerFiltrado(paneDatos.getRbEscultura().getText());
			if (!imgSele) {
				cargarObra(obraService.obtenerActual());
			}
		});

		paneDatos.getRbTodo().fire();
	}
	
	private String obtenerRadioButtonSeleccionado(ToggleGroup group) {
		RadioButton seleccionado = (RadioButton) group.getSelectedToggle();
		return (seleccionado != null) ? seleccionado.getText().strip() : "Ninguno";
	}

	// Metodo para insertar en la base de datos la obra de arte
	public void insertarObra() {
		cambiarEditableTexto(false);
		obraService.insertarObra(paneImagen.getImagen(), paneImagen.getTitulo().getText(),
				paneImagen.getAnyo().getText(), paneDatos.getAutor().getText(), paneDatos.getEstilo().getText(),
				paneDatos.getDimension().getText(), paneDatos.getUbicacion().getText(), paneDatos.getPropia().getText(),
				paneDatos.getDescripcion().getText(), obtenerRadioButtonSeleccionado(paneDatos.getGroup()));

		cargarObra(obraService.obtenerActual()); // Se muestra la obra que habia antes
	}
	
	// Metodo para actualizar en la base de datos la obra de arte
	public void actualizarObra() {
		Obra ob = obtenerObraActual(); //Para saber el id
		
		byte[] img = UtilesData.pasarImagenAByte(paneImagen.getImagen());
		
		ob.setTitulo(paneImagen.getTitulo().getText());
		ob.setAnyo(paneImagen.getAnyo().getText());
		ob.setAutor(paneDatos.getAutor().getText());
		ob.setPeriodo(paneDatos.getEstilo().getText());
		ob.setMedidas(paneDatos.getDimension().getText());
		ob.setExposicion(paneDatos.getUbicacion().getText());
		ob.setImage(img);
		ob.setDescripcion(paneDatos.getDescripcion().getText());
		
		obraService.actualizarObra(ob, paneDatos.getPropia().getText());
	}
	
	// Metodo para eliminar en la base de datos la obra de arte
	public void eliminarObra() {
		Obra ob = obtenerObraActual(); //Para saber el id
		obraService.eliminarObra(ob);
	}

	// Metodo usado al pulsar el boton de añadir se vacian los datos de las obras
	// se ponen editable = true los TextField
	private void vaciarDatos() { //Se llama desde el boton Insertar

		////////////////////////// PaneImagenObra
		paneImagen.getAnyo().setText("");
		paneImagen.getTitulo().setText("");

		////////////////////////// PaneDatosObra
		paneDatos.getAutor().setText("");
		paneDatos.getEstilo().setText("");
		paneDatos.getDimension().setText("");
		paneDatos.getUbicacion().setText("");
		paneDatos.getPropia().setText("");
		paneDatos.getDescripcion().setText("");

		cambiarEditableTexto(true);
	}

	private void cambiarEditableTexto(boolean val) { //Se llama desde vaciarDatos al pulsar Insertar, actualizar
		paneImagen.getTitulo().setEditable(val);
		paneImagen.getAnyo().setEditable(val);
		
		paneDatos.getAutor().setEditable(val);
		paneDatos.getEstilo().setEditable(val);
		paneDatos.getDimension().setEditable(val);
		paneDatos.getUbicacion().setEditable(val);
		paneDatos.getPropia().setEditable(val);
		paneDatos.getDescripcion().setEditable(val);
	}

	private void cambiarEnableBoton(boolean val) {
		if (btnAnadir != null)
			btnAnadir.setDisable(val);

		if (btnEditar != null)
			btnEditar.setDisable(val);

		if (btnEliminar != null)
			btnEliminar.setDisable(val);
		
		museo.getPasarDerecha().setDisable(val);
		museo.getPasarIzquierda().setDisable(val);
		museo.getBtnBuscar().setDisable(val);
		paneImagen.getPaneEstrellas().getEnviar().setDisable(val);
		
		cambiarArriba(val);
	}
	
	//Si esta a true deberia aparecer la doble sino solo tituloYanyo
	private void cambiarArriba(boolean val) {
		if(val) {
			paneImagen.getArriba().getChildren().remove(paneImagen.getTituloYanyo());
			paneImagen.getArriba().getChildren().addAll(paneImagen.getTitulo(), paneImagen.getAnyo());
		}else {
			paneImagen.getArriba().getChildren().removeAll(paneImagen.getAnyo(), paneImagen.getTitulo());
			paneImagen.getArriba().getChildren().add(paneImagen.getTituloYanyo());
		}
	}

	///////////////////////////////////////
	///////////////////// Botones Opcionales
	private void botonesActualizar() {
		// Boton Editar
		ImageView imgLapiz = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/editar.png")));
		imgLapiz.setFitWidth(25);
		imgLapiz.setFitHeight(25);

		btnEditar = new Button();
		btnEditar.setPrefSize(40, 30);
		btnEditar.setGraphic(imgLapiz);
		btnEditar.getStyleClass().add("botones");
		btnEditar.setOnAction(event -> {
			UtilesData.animacionFade(btnEditar);
			btnGuardarEdit.setVisible(!btnGuardarEdit.isVisible());
			imgSele = !imgSele;
			cambiarEnableBoton(imgSele);
			btnEditar.setDisable(false); // Este tiene que seguir estando able para quitar el edit si no quieres editar
			cambiarEditableTexto(imgSele);
		});
		paneImagen.getPaneAbajo().getChildren().add(btnEditar);
		btnEditar.setLayoutX(0);

		// Boton Guardar la edicion
		ImageView imgGuardar = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/guardar.png")));
		imgGuardar.setFitWidth(25);
		imgGuardar.setFitHeight(25);

		btnGuardarEdit = new Button();
		btnGuardarEdit.setPrefSize(40, 30);
		btnGuardarEdit.setVisible(false);
		btnGuardarEdit.setGraphic(imgGuardar);
		btnGuardarEdit.getStyleClass().add("botones");
		btnGuardarEdit.setOnAction(event -> {
			UtilesData.animacionFade(btnGuardarEdit);
			btnGuardarEdit.setVisible(false);
			imgSele = !imgSele;
			cambiarEnableBoton(imgSele); // Cambiar los botones a Enable o no Enable, segun si se ha pulsado el boton
			cambiarEditableTexto(imgSele);

			actualizarObra();
		});
		paneImagen.getPaneAbajo().getChildren().add(btnGuardarEdit);
		btnGuardarEdit.setLayoutX(40);
	}

	private void botonEliminar() {
		ImageView imgPapelera = new ImageView(
				new Image(getClass().getResourceAsStream("/recursos/images/papelera.png")));
		imgPapelera.setFitHeight(30);
		imgPapelera.setFitWidth(30);

		btnEliminar = new Button();
		btnEliminar.setPrefSize(40, 30);
		btnEliminar.setGraphic(imgPapelera);
		btnEliminar.getStyleClass().add("botones");
		btnEliminar.setOnAction(event -> {
			UtilesData.animacionFade(btnEliminar);
			eliminarObra();
			cargarDerecha();
		});
		paneImagen.getPaneAbajo().getChildren().add(btnEliminar);
		btnEliminar.setLayoutX(600);
	}

//	Deberia haber tres radioButton para indicar que tipo de obra se inserta, y segun la que se pulsa se crea de esa
	private void botonesInsertar() {
		ImageView imgAnadir = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/anadir.png")));
		imgAnadir.setFitHeight(30);
		imgAnadir.setFitWidth(30);

		btnAnadir = new Button();
		btnAnadir.setPrefSize(40, 30);
		btnAnadir.setGraphic(imgAnadir);
		btnAnadir.getStyleClass().add("botones");
		btnAnadir.setOnAction(event -> {
			UtilesData.animacionFade(btnAnadir);
			btnGuardarAnadir.setVisible(!btnGuardarAnadir.isVisible());
			if (!imgSele) // Si antes estaba a false significa q se acaba de pulsar y se vacian los datos
							// para poder insertal los datos de la nueva obra
				vaciarDatos();
			imgSele = !imgSele;
			cambiarEnableBoton(imgSele); // Cambiar los botones a Enable o no Enable, segun si se ha pulsado el boton
			btnAnadir.setDisable(false);
			cambiarEditableTexto(imgSele); // Volver los textos a Enable
			if(!imgSele) { // Ha vuelto a ser pulsado por lo que deberia cargar la obra ya que no se ha insertado nada
				cargarObra(obraService.obtenerActual());
			}
		});
		paneImagen.getPaneAbajo().getChildren().add(btnAnadir);
		btnAnadir.setLayoutX(550);

		ImageView imgGuardar = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/guardar.png")));
		imgGuardar.setFitWidth(25);
		imgGuardar.setFitHeight(25);
		btnGuardarAnadir = new Button();
		btnGuardarAnadir.setPrefSize(40, 30);
		btnGuardarAnadir.setVisible(false);
		btnGuardarAnadir.setGraphic(imgGuardar);
		btnGuardarAnadir.getStyleClass().add("botones");
		btnGuardarAnadir.setOnAction(event -> {
			UtilesData.animacionFade(btnGuardarAnadir);
			btnGuardarAnadir.setVisible(false);
			imgSele = !imgSele;
			cambiarEditableTexto(imgSele); // Volver los textos a Enable
			cambiarEnableBoton(imgSele); // Cambiar los botones a Enable o no Enable, segun si se ha pulsado el boton
			insertarObra();
		});
		paneImagen.getPaneAbajo().getChildren().add(btnGuardarAnadir);
		btnGuardarAnadir.setLayoutX(500);
	}


	/////////////////////////////////////////
	/////////////////////// Getters y Setters
	public PaneImagenObra getPaneImagen() {
		return paneImagen;
	}

	public void setPaneImagen(PaneImagenObra paneImagen) {
		this.paneImagen = paneImagen;
	}

	public PaneDatosObra getPaneDatos() {
		return paneDatos;
	}

	public void setPaneDatos(PaneDatosObra paneDatos) {
		this.paneDatos = paneDatos;
	}

	public void setEstrellas(EstrellasController est) {
		this.estrellas = est;
	}

}

