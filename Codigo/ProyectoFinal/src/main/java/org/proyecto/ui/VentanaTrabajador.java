package org.proyecto.ui;

import org.proyecto.controler.TrabajadorController;
import org.proyecto.ui.paneles.PaneMenu;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Consulta;
import org.proyecto.vo.Usuario;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class VentanaTrabajador {

	private ListView<Consulta> listView; 
    private TextArea txtArea;
    private TextArea respuesta;
    private BorderPane root;
    private Button btnActualizar, btnEnviar;
    private VBox izquierda;
    private Button btnMenu, btnDesseleccionar;
    private Label error;
    
    public VentanaTrabajador(Stage primaryStage, PaneMenu paneMenu, Usuario u) { 
    	primaryStage.setTitle("Consultas");
    	TrabajadorController controller = new TrabajadorController(this, u);
    	root = new BorderPane();
        root.setStyle("-fx-background-color: rgba(185, 195, 217, 0.8);");
    	
    	//////////////////////////////////////
    	//////////////////////// Zona Superior
    	btnMenu = new Button();
        btnMenu.setShape(new Circle(25));
        btnMenu.setMaxSize(50, 50);
        btnMenu.setMinSize(50, 50);
        ImageView img = new ImageView(UtilesData.pasarByteAImagen(u.getImage()));
        img.setSmooth(true);
        btnMenu.setGraphic(img);
        btnMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        HBox topBox = new HBox(btnMenu);
        topBox.setAlignment(Pos.CENTER_LEFT);
        root.setTop(topBox);
        
    	//////////////////////////////////////
    	/////////////////////// Zona Izquierda
        izquierda = paneMenu.getPanel();
        izquierda.setVisible(false);
		BorderPane.setMargin(izquierda, new Insets(20, 0, 10, 0));
		root.setLeft(izquierda);
    	
    	//////////////////////////////////////
        ///////////////////////// Zona Central
        btnActualizar = new Button("⟳");
        btnActualizar.setFocusTraversable(false);
        btnDesseleccionar = new Button("X");
        btnDesseleccionar.setFocusTraversable(false);
        
        Label lblConsultas = new Label("Consultas");
        lblConsultas.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        
        listView = new ListView<>();
        listView.setPrefHeight(680);
        
        HBox cabezeraCentro = new HBox(285, btnActualizar, lblConsultas, btnDesseleccionar);
        
        VBox centro = new VBox(10, cabezeraCentro, listView);
        centro.setPrefWidth(550);
        root.setCenter(centro);
        
        /////////////////////////////////////
        //////////////////////// Zona Derecha
        txtArea = new TextArea();
        txtArea.setWrapText(true);
        txtArea.setEditable(false);
        txtArea.setPrefSize(700, 400);
        txtArea.setPromptText("Consulta seleccionada...");

        respuesta = new TextArea();
        respuesta.setPrefSize(700, 400);
        respuesta.setWrapText(true);
        respuesta.setPromptText("Escribe la respuesta aquí...");
        
        error = new Label("");
        error.setPrefSize(610, 50);
        error.setAlignment(Pos.CENTER);
		error.setVisible(false);
		error.setStyle("-fx-text-fill: red;");
        
        btnEnviar = new Button("Enviar");
        btnEnviar.setFocusTraversable(false);
        btnEnviar.setPrefSize(100, 50);
        HBox enviarBox = new HBox(error, btnEnviar);
        enviarBox.setPrefSize(100, 50);
        enviarBox.setAlignment(Pos.CENTER_RIGHT);
        
        VBox derecha = new VBox(20, txtArea, respuesta, enviarBox);
        derecha.setPrefWidth(750);
        root.setRight(derecha);
        
        HBox.setMargin(btnMenu, new Insets(0, 0, 0, 10));
        VBox.setMargin(txtArea, new Insets(0, 20, 0, 20));
        VBox.setMargin(respuesta, new Insets(0, 20, 0, 20));
        VBox.setMargin(enviarBox, new Insets(0, 20, 20, 0));
        controller.funcionesVentana();
    }
    
    public Scene getScene() {
        return new Scene(root, 600, 400);
    }

	public ListView<Consulta> getListView() {
		return listView;
	}

	public TextArea getTxtArea() {
		return txtArea;
	}

	public TextArea getRespuesta() {
		return respuesta;
	}

	public BorderPane getRoot() {
		return root;
	}

	public Button getBtnActualizar() {
		return btnActualizar;
	}

	public Button getBtnEnviar() {
		return btnEnviar;
	}
	
	public Button getBtnMenu() {
		return btnMenu;
	}
	
	public VBox getIzquierda() {
		return izquierda;
	}
	
	public Label getError() {
        return error;
    }
	
	public Button getBtnDesseleccionar() {
        return btnDesseleccionar;
    }
}
