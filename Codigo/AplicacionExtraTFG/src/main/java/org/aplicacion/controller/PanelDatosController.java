package org.aplicacion.controller;

import java.util.List;

import org.aplicacion.dao.IPermiso;
import org.aplicacion.dao.IPermisoUsuario;
import org.aplicacion.dao.IUsuario;
import org.aplicacion.dao.impl.PermisoDao;
import org.aplicacion.dao.impl.PermisosUsuarioDao;
import org.aplicacion.dao.impl.UsuarioDao;
import org.aplicacion.ui.VentanaTrabajadores;
import org.aplicacion.ui.paneles.PaneDatosTrabajador;
import org.aplicacion.vo.Permiso;
import org.aplicacion.vo.Usuario;

public class PanelDatosController {

	private PaneDatosTrabajador panel;
	private Usuario usu;
	private List<Permiso> permisos;
	private VentanaTrabajadores ventana;

	public PanelDatosController(PaneDatosTrabajador p, Usuario u, VentanaTrabajadores v) {
		this.panel = p;
		this.usu = u;
		this.ventana=v;
		this.permisos = permisosUsuario();
	}

	public void funciones() {
		panel.getAceptar().setOnAction(event -> {
			try {
				panel.getError().setVisible(false);
				
				if(panel.getEmail().getText().isEmpty() || panel.getUser().getText().isEmpty()) {
					panel.getError().setText("Alguno de los campos está vacio");
					panel.getError().setVisible(true);
					return;
				}
				
				IPermisoUsuario ipu = new PermisosUsuarioDao();

				ipu.eliminar(permisos, usu); //Se eliminan los permisos del usuario
				
				cambiarListaPermisos(); //Se cambian los permisos y se vuelven a introducirr todos
				ipu.insertarPermisos(permisos, usu);
				
				usu.setEmail(panel.getEmail().getText().strip());
				usu.setUser(panel.getUser().getText().strip());
				
				IUsuario iusu = new UsuarioDao();
				iusu.actualizar(usu);
				
				ventana.getBtnActualizar().fire();
			} catch (Exception e) {
				panel.getError().setVisible(true);
				panel.getError().setText("Ha habido algun error");
			}
		});
		
		panel.getEliminar().setOnAction(event -> {
			IUsuario iu = new UsuarioDao();
			try {
				iu.eliminar(usu);
				ventana.getBtnQuitar().fire();
				ventana.getBtnActualizar().fire();
			} catch (Exception e) {
				panel.getError().setText("Error durante la eliminación del usuario");
			}
		});

		seleccionarPermisos();
	}

	private List<Permiso> permisosUsuario() {
		try {
			IPermisoUsuario ipu = new PermisosUsuarioDao();
			return ipu.permisosUsuario(usu);
		} catch (Exception e) {
			panel.getError().setVisible(true);
			panel.getError().setText("Ha habido algun error durante la obtencion de permisos del usuario");
			return null;
		}
	}

	private void seleccionarPermisos() {
		for (int i = 0; i < panel.getCheckBoxs().size(); i++) {
			for (int j = 0; j < permisos.size(); j++) {
				if (panel.getCheckBoxs().get(i).getText().equals(permisos.get(j).getTipo())) {
					panel.getCheckBoxs().get(i).setSelected(true);
					break;
				}
			}
		}
	}

	private void cambiarListaPermisos() {
		try {
			permisos.clear();
			IPermiso iper = new PermisoDao();
			for (int i = 0; i < panel.getCheckBoxs().size(); i++) {
				if (panel.getCheckBoxs().get(i).isSelected())
					permisos.add(iper.obtenerPorTipo(new Permiso(panel.getCheckBoxs().get(i).getText())));
			}
		} catch (Exception e) {
			panel.getError().setVisible(true);
			panel.getError().setText("Ha habido algun error durante el cambio de permisos");
		}
	}

}
