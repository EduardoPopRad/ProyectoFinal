package org.proyecto.ui;

import org.proyecto.controler.MuseoController;
import org.proyecto.controler.ObraController;
import org.proyecto.dao.impl.ObraService;
import org.proyecto.ui.paneles.PaneDatosObra;
import org.proyecto.ui.paneles.PaneImagenObra;
import org.proyecto.ui.paneles.PaneMenu;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Obra;
import org.proyecto.vo.Usuario;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PantallaMuseo {
	private Scene scene;

	private BorderPane panel;
	private BorderPane central; 

	private Button pasarDerecha;
	private Button pasarIzquierda;
	private Button btnBuscar;
	private Button btnMenu;

	private TextField buscador;

	private BorderPane paneObra;
	private VBox paneDatos;
	
	private PaneMenu paneMenu;
	private VBox menu;

	private ListView<Obra> listView;

	public PantallaMuseo(Stage primaryStage, Usuario usu) { 
		
		primaryStage.setTitle("Museum");
		
		panel = new BorderPane();
		panel.setPrefWidth(primaryStage.getWidth());
		panel.setPrefHeight(primaryStage.getHeight());
		
		ObraService obraService = new ObraService();
		
		Image fondo = new Image(getClass().getResourceAsStream("/recursos/images/fondo.jpg"));
        // Al estar ocupado todos los sectores del BorderPane se debe establecer la imagen de fondo asi
        BackgroundImage background = new BackgroundImage(
            fondo, 
            BackgroundRepeat.REPEAT, 
            BackgroundRepeat.REPEAT, 
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        panel.setBackground(new Background(background));
		
		//////////////////////////////////////////////////////
		///////////////////////////////////////// ZONA DERECHA
		HBox dcha = new HBox();
		pasarDerecha = new Button(">");
		pasarDerecha.setFocusTraversable(false);
		pasarDerecha.setPrefSize(30, 700);
		dcha.getChildren().add(pasarDerecha);
		panel.setRight(dcha); // Lo ponemos a la derecha del BorderPane
		
		// Arriba, Derecha, Abajo, Izquierda 
		BorderPane.setMargin(dcha, new Insets(0, 10, 100, 0));

		///////////////////////////////////////////////////////
		//////////////////////////////////////// ZONA IZQUIERDA
		HBox izda = new HBox();
		pasarIzquierda = new Button("<");
		pasarIzquierda.setFocusTraversable(false);
		pasarIzquierda.setPrefSize(30, 700);
		izda.getChildren().add(pasarIzquierda);
		panel.setLeft(izda); // Lo ponemos a la izquierda en el BorderPane
		
		// Arriba, Derecha, Abajo, Izquierda
		BorderPane.setMargin(izda, new Insets(0, 0, 100, 10));
		
		paneMenu= new PaneMenu(primaryStage, usu);
		menu=paneMenu.getPanel();
		BorderPane.setMargin(menu, new Insets(0, 0, 100, 10));

		//////////////////////////////////////////////////////
		//////////////////////////////////////// ZONA SUPERIOR
		buscador = new TextField();
		buscador.setPromptText("Buscar...");
		buscador.setPrefWidth(400);
		buscador.getStyleClass().add("buscador");

		btnBuscar = new Button();
		btnBuscar.setFocusTraversable(false);
		btnBuscar.setPrefSize(40, 30);
		btnBuscar.getStyleClass().add("botones");
		ImageView imgBuscar = new ImageView(new Image(getClass().getResourceAsStream("/recursos/images/lupa.png")));
		imgBuscar.setFitWidth(25);
		imgBuscar.setFitHeight(25);
		btnBuscar.setGraphic(imgBuscar);
		btnBuscar.getStyleClass().add("botones");

		listView = new ListView<>(); // Devuelve la lista de posibles resultados de la busqueda
		listView.setVisible(false); // Oculto inicialmente
		
		BorderPane brp = new BorderPane();
		
		btnMenu=new Button(); // Boton con la imagen del usuario
		btnMenu.setFocusTraversable(false);
		btnMenu.setShape(new Circle(25));
        btnMenu.setMaxSize(50, 50);
        btnMenu.setMinSize(50, 50);
        ImageView img = new ImageView(UtilesData.pasarByteAImagen(usu.getImage()));
        img.setSmooth(true);
        btnMenu.setGraphic(img);
        btnMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
		brp.setLeft(btnMenu);
		
		StackPane busc = new StackPane();
		busc.getChildren().addAll(buscador, btnBuscar);
		StackPane.setAlignment(btnBuscar, Pos.CENTER_RIGHT); // Alinear la imagen a la derecha del StackPane
		StackPane.setAlignment(buscador, Pos.CENTER);

		VBox superior = new VBox(busc, listView);
		superior.setMaxSize(400, 100);
		superior.setMinSize(400, 100);
		superior.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(superior, Pos.CENTER);
		BorderPane.setAlignment(btnMenu, Pos.CENTER);
		BorderPane.setMargin(btnMenu, new Insets(0, 0, 0, 10));
		
		brp.setCenter(superior);
		panel.setTop(brp);

		/////////////////////////////////////////////////////
		//////////////////////////////////////// ZONA CENTRAL
		central = new BorderPane();
		
		ObraController controller = new ObraController(usu, obraService, this);
		
		// Imagen de la obra
		PaneImagenObra imgObra = new PaneImagenObra(usu,primaryStage, controller);
		paneObra = imgObra.getPanel();
		controller.setPaneImagen(imgObra);
		central.setCenter(paneObra);
		BorderPane.setAlignment(paneObra, Pos.TOP_CENTER);
		
		// Datos de la obra
		PaneDatosObra dataObra = new PaneDatosObra(primaryStage, controller);
		paneDatos = dataObra.getPanel();
		controller.setPaneDatos(dataObra);
		central.setRight(paneDatos);
		BorderPane.setAlignment(paneDatos, Pos.CENTER_LEFT);
		
		BorderPane.setMargin(paneDatos, new Insets(0, 100, 0, 0));
		
		//Ponemos en el centro los dos paneles principales
		panel.setCenter(central);

		////////////////////////////////////////////
		////////////////////////////////// FUNCIONES
		MuseoController musCont = new MuseoController(this, obraService, controller);
		musCont.funcionesBuscador();
		musCont.funcionesBotones(izda);
		
		scene = new Scene(panel, 800, 540);
		scene.getStylesheets().add(getClass().getResource("/recursos/styles/CSSMuseo").toExternalForm());
	}
	
	//////////////////////////////////
	//////////////// Getters y Setters
	public Scene getScene() {
		return scene;
	}

	public TextField getBuscador() {
		return buscador;
	}
	
	public ListView<Obra> getListView() {
		return listView;
	}

	public Button getPasarDerecha() {
		return pasarDerecha;
	}

	public Button getPasarIzquierda() {
		return pasarIzquierda;
	}

	public Button getBtnBuscar() {
		return btnBuscar;
	}

	public Button getBtnMenu() {
		return btnMenu;
	}

	public BorderPane getPanel() {
		return panel;
	}

	public VBox getMenu() {
		return menu;
	}
	
	public PaneMenu getPaneMenu() {
		return paneMenu;
	}
}

