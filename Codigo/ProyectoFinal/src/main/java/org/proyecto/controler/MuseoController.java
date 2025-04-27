package org.proyecto.controler;

import java.util.ArrayList;
import java.util.List;

import org.proyecto.dao.impl.ObraService;
import org.proyecto.ui.PantallaMuseo;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Obra;

import javafx.scene.layout.HBox;

public class MuseoController {

	private PantallaMuseo pantallaMuseo;

	private ObraController obraController; // Para mostrar los detalles de la obra seleccionada
	private ObraService obraService; // Para obtener todas las obras al comenzar
	private Obra obraSeleccionada;

	private boolean menuActivado = false;

	public MuseoController(PantallaMuseo pantallaMuseo, ObraService obr, ObraController obctr) {
		this.pantallaMuseo = pantallaMuseo;
		this.obraService = obr;

		obraService.cargarTodos();
		obraController = obctr;
	}

	public void funcionesBuscador() {

		// Permite filtrar las coincidencias cuando se escribe en el buscador
		pantallaMuseo.getBuscador().textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				pantallaMuseo.getListView().setVisible(false); // Ocultar si no hay texto
			} else {
				// Buscamos las coincidencias
				List<Obra> filteredData = new ArrayList<Obra>();
				for (Obra item : obraService.obtenerTodos()) {
					if (item.getTitulo().toLowerCase().startsWith(newValue.toLowerCase())) {
						filteredData.add(item);
					}
				}

				pantallaMuseo.getListView().getItems().setAll(filteredData);
				if (pantallaMuseo.getListView().getItems().isEmpty()) { // Si esta vacio no es visible
					pantallaMuseo.getListView().setVisible(false);
				} else { // Hacemos visible y ajustamos el tamaño del contenedor segun los posibles
							// resultados
					pantallaMuseo.getListView().setVisible(true);
					switch (pantallaMuseo.getListView().getItems().size()) {
					case 1:
						pantallaMuseo.getListView().setPrefHeight(25);
						break;
					case 2:
						pantallaMuseo.getListView().setPrefHeight(50);
						break;
					default:
						pantallaMuseo.getListView().setPrefHeight(75);
						break;
					}
				}
			}
		});

		pantallaMuseo.getListView().setOnMouseClicked(event -> {
			obraSeleccionada = pantallaMuseo.getListView().getSelectionModel().getSelectedItem();
			if (obraSeleccionada != null) {
				pantallaMuseo.getBuscador().setText(obraSeleccionada.toString());
				pantallaMuseo.getListView().setVisible(false);
			}
		});

		pantallaMuseo.getBtnBuscar().setOnAction(event -> {
			UtilesData.animacionFade(pantallaMuseo.getBtnBuscar());
			pantallaMuseo.getBuscador().setText("");
			if (obraSeleccionada != null)
				obraController.cargarObra(obraSeleccionada); // Cargamos la obraSeleccionada

			obraSeleccionada = null;
		});
	}

	public void funcionesBotones(HBox izda) {
		pantallaMuseo.getBtnMenu().setOnAction(event -> {
			menuActivado = !menuActivado;
			if (menuActivado)
				pantallaMuseo.getPanel().setLeft(pantallaMuseo.getMenu());
			else
				pantallaMuseo.getPanel().setLeft(izda);
		});

		pantallaMuseo.getPasarDerecha().setOnAction(event -> {
			UtilesData.animacionFade(pantallaMuseo.getPasarDerecha());
			obraController.cargarDerecha();
		});

		pantallaMuseo.getPasarIzquierda().setOnAction(event -> {
			UtilesData.animacionFade(pantallaMuseo.getPasarIzquierda());
			obraController.cargarIzquierda();
		});
	}
}
