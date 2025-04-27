package org.proyecto.ui.paneles;

import org.proyecto.controler.RegistroController;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class PaneContact {
	private Pane panel;
	private Label contact, error;
	private TextField user, description;
	private TextArea txtArea, info;
	private Button enviar;
	
	public PaneContact(RegistroController regisController) {
		
		contact=new Label("Contact Us");
		contact.setPrefSize(120, 35);
		contact.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-text-fill: white;"
				+ "-fx-font-size: 25px; -fx-font-family: 'Times New Roman'");
		
		error = new Label("");
		error.setPrefSize(220, 20);
		error.setAlignment(Pos.CENTER);
		error.setVisible(false);
		error.setStyle("-fx-text-fill: red;");
		
		user=new TextField();
		user.setPrefSize(175, 15);
		user.setPromptText("user:");
		user.getStyleClass().add("txt-texto");
		
		Line lineUser = new Line(60, 82, 228, 82); // (x1, y1, x2, y2)
		lineUser.setStroke(Color.WHITE); 
		lineUser.setStrokeWidth(1);
		
		description= new TextField();
		description.setPrefSize(175, 15);
		description.setPromptText("description:");
		description.getStyleClass().add("txt-texto");
		
		Line lineDesc = new Line(60, 112, 228, 112); // (x1, y1, x2, y2)
		lineDesc.setStroke(Color.WHITE); 
		lineDesc.setStrokeWidth(1);
		
		txtArea=new TextArea();
		txtArea.setPromptText("Write your concern here:");
		txtArea.setPrefSize(170, 160);
		txtArea.setWrapText(true);
		txtArea.getStyleClass().add("txt-area");
		
		enviar=new Button("send");
		enviar.setPrefSize(175, 25);
		enviar.setStyle("-fx-background-color: rgba(64, 62, 63, 0.74); -fx-text-fill: white;"
				+ "-fx-font-size: 14px; -fx-background-radius: 10;");
		
		Line lineV =new Line(285, 30, 285, 330);// (x1, y1, x2, y2)
		lineV.setStroke(Color.GREY); 
		lineV.setStrokeWidth(1);
		
		info=new TextArea("Opening hours:\nMonday-Friday\n9am-5pm\n\nSupport:\nsupport@nomEmpresa.com\n"
				+ "(+34)917952923");
		info.setEditable(false);
		info.setPrefSize(155, 150);
		info.getStyleClass().add("text-area");
		
		regisController.funcionesContactPane(this);
		
		panel=new Pane();
		panel.setPrefSize(450, 360); //ancho, alto
		panel.getChildren().addAll(contact, lineV, user, lineUser, description, lineDesc, txtArea, error, enviar, info);
		
		contact.setLayoutX(85);		contact.setLayoutY(25);
		user.setLayoutX(55); 		user.setLayoutY(60); 
		description.setLayoutX(55); description.setLayoutY(90);
		txtArea.setLayoutX(59);		txtArea.setLayoutY(125);
		enviar.setLayoutX(55); 		enviar.setLayoutY(295);
		error.setLayoutX(30);        error.setLayoutY(325);
		info.setLayoutX(290);		info.setLayoutY(112);
		 
		Platform.runLater(()-> { user.requestFocus(); });
	}
	
	public Pane getPanel() {
		return panel;
	}
	
	public TextField getUser() {
		return user;
	}

	public TextField getDescription() {
		return description;
	}

	public TextArea getTxtArea() {
		return txtArea;
	}

	public TextArea getInfo() {
		return info;
	}

	public Button getEnviar() {
		return enviar;
	}
	
	public Label getError() {
        return error;
    }
}

