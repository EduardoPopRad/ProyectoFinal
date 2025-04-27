package org.proyecto.controler;

import java.time.LocalDate;
import java.util.List;

import org.proyecto.dao.IRespuesta;
import org.proyecto.dao.impl.RespuestDao;
import org.proyecto.excepciones.RespuestaException;
import org.proyecto.ui.VentanaTrabajador;
import org.proyecto.util.ConsultasCreadas;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Consulta;
import org.proyecto.vo.Respuesta;
import org.proyecto.vo.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;

public class TrabajadorController {

	private VentanaTrabajador ventana;
	private Consulta seleccionada; // Guardamos la consulta para la hora de enviar la consulta saber cual es
	private Usuario usu;

	public TrabajadorController(VentanaTrabajador tra, Usuario u) {
		this.ventana = tra;
		this.seleccionada = null;
		this.usu = u;
	}

	public void funcionesVentana() {
		ventana.getBtnActualizar().setOnAction(e -> {
			UtilesData.animacionFade(ventana.getBtnActualizar());
			cargarConsultas();
		});

		ventana.getBtnDesseleccionar().setOnAction(e -> {
			UtilesData.animacionFade(ventana.getBtnDesseleccionar());
			seleccionada = null;
			ventana.getTxtArea().clear();
			;
		});

		ventana.getListView().setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				seleccionada = ventana.getListView().getSelectionModel().getSelectedItem();
				if (seleccionada != null) {
					ventana.getTxtArea()
							.setText(seleccionada.getId() + "\t" + seleccionada.getFechaConsulta() + "\n"
									+ seleccionada.getCliente() + "\nDescripcion: " + seleccionada.getDescripcion()
									+ "\n\nConsulta: " + seleccionada.getConsulta());
				}
			}
		});

		ventana.getListView().setCellFactory(lv -> new ListCell<>() {
			@Override
			protected void updateItem(Consulta item, boolean empty) {
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

		ventana.getBtnEnviar().setOnAction(e -> {
			UtilesData.animacionFade(ventana.getBtnEnviar());
			ventana.getError().setVisible(false);

			if (this.seleccionada == null) {
				ventana.getError().setText("Debe seleccionar una consulta.");
				ventana.getError().setVisible(true);
				return;
			}

			if (ventana.getRespuesta().getText().strip().isEmpty()) {
				ventana.getError().setText("Debe ingresar una respuesta.");
				ventana.getError().setVisible(true);
				return;
			}

			enviarCorreo();
			ventana.getBtnDesseleccionar().fire();
			ventana.getRespuesta().clear();
		});

		ventana.getBtnMenu().setOnAction(e -> ventana.getIzquierda().setVisible(!ventana.getIzquierda().isVisible()));

		cargarConsultas(); //Cargar los datos actuales nada mas abrir la ventana
	}

	private void cargarConsultas() {
		List<Consulta> consultas = ConsultasCreadas.getConsultas().getList();
		ObservableList<Consulta> observableList = FXCollections.observableArrayList(consultas);
		ventana.getListView().setItems(observableList);
	}

	private void enviarCorreo() {
		Respuesta resp = new Respuesta(seleccionada, usu, ventana.getRespuesta().getText(), LocalDate.now());
		System.out
				.println("Correo enviado a: " + seleccionada.getCliente().getEmail() + ". Con la Respuesta:\n " + resp);

		IRespuesta iresp = new RespuestDao();
		try {
			iresp.insertar(resp);
		} catch (RespuestaException e) {
			ventana.getError().setText(e.getDescripcionError());
			ventana.getError().setVisible(true);
			System.out.println(e);
		}

		cargarConsultas();
	}
}
