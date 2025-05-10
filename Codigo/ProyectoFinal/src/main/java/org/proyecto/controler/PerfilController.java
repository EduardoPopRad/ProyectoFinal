package org.proyecto.controler;

import org.proyecto.dao.IUsuario;
import org.proyecto.dao.impl.UsuarioDao;
import org.proyecto.excepciones.UsuarioException;
import org.proyecto.ui.paneles.PanePerfil;
import org.proyecto.util.Seguridad;
import org.proyecto.vo.Usuario;

public class PerfilController {

	private boolean edit, deshabilitado;
	private String email, us;
	private PanePerfil perfil;
	private Usuario usu;

	public PerfilController(PanePerfil per, Usuario u) {
		this.edit = false;
		this.deshabilitado = true;
		this.perfil = per;
		this.usu = u;
	}

	public void funcionesBotones() {
		// Funcion boton Editar datos del perfil
		perfil.getBtnEditar().setOnAction(event -> { 
			if (!edit) {
				email = perfil.getCorreo().getText().strip();
				us = perfil.getUser().getText().strip();
			}

			if (perfil.getContrasenaAntigua().isDisabled())
				perfil.getBtnAceptar().setVisible(!perfil.getBtnAceptar().isVisible());

			edit = !edit;
			perfil.getCorreo().setEditable(edit);
			perfil.getUser().setEditable(edit);

			perfil.getCorreo().setText(email);
			perfil.getUser().setText(us);
		});

		// Funcion boton poder ver para cambiar la contraseña
		perfil.getBtnContra().setOnAction(event -> {
			if (!perfil.getCorreo().isEditable())
				perfil.getBtnAceptar().setVisible(!perfil.getBtnAceptar().isVisible());

			deshabilitado = !deshabilitado;
			perfil.getContrasenaAntigua().setDisable(deshabilitado);
			perfil.getNuevaContrasena().setDisable(deshabilitado);
			perfil.getContrasenaAntigua().requestFocus();
		});

		perfil.getBtnAceptar().setOnAction(event -> { 
			if (edit) { //Comprobacion datos editar
				if(comprobarDatos()) { //Si sale true es que hay algun error
					System.out.println("Hay algun dato indebido para poder editar tu  perfil");
					return;
				}
			}
			
			if(!deshabilitado) { //Comprobacion datos relacionados con cambio de contraseña
				// Comprobar que ContrasenaAntigua es igual a la que tiene el usuario
				if (!Seguridad.verificarContrasena(perfil.getContrasenaAntigua().getText().strip(), usu.getPassword())) {
					System.out.println("La contraseña antigua no coincide");
					return;
				}
			}
			
			permitirCambio();
			
			IUsuario usuDao = new UsuarioDao();
			try {
				usuDao.actualizar(usu);
			} catch (UsuarioException e) {
				e.printStackTrace();
			}
			
			perfil.getBtnAceptar().setVisible(false);

		});

		perfil.getContrasenaAntigua().setDisable(deshabilitado);
		perfil.getNuevaContrasena().setDisable(deshabilitado);
	}
	
	private void permitirCambio() {
		//Datos Edit
		if(edit) {
			usu.setEmail(perfil.getCorreo().getText().strip());
			usu.setUser(perfil.getUser().getText().strip());
			edit = false;
			
			perfil.getCorreo().setEditable(edit);
			perfil.getUser().setEditable(edit);
		}

		//Datos contraseña
		if(!deshabilitado) {
			String contra = Seguridad.hashearContrasena(perfil.getNuevaContrasena().getText().strip());
			usu.setPassword(contra);
			deshabilitado = true;
			
			perfil.getNuevaContrasena().setDisable(deshabilitado);
			perfil.getContrasenaAntigua().setDisable(deshabilitado);
			
			perfil.getNuevaContrasena().setText("");
			perfil.getContrasenaAntigua().setText("");
		}
	}

	private boolean comprobarDatos() {
		//Comprobacion User
		IUsuario usuDao = new UsuarioDao();
		try {
			Usuario u = usuDao.obtenerPorUser(new Usuario(perfil.getUser().getText().strip()));
			if (! (u == null || u.getUser().equals(perfil.getUser().getText().strip()))) 
				return true;
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		
		//Comprobacion correo
		if(!(perfil.getCorreo().getText().endsWith("@gmail.com") ||  perfil.getCorreo().getText().endsWith("@hotmail.com")
				|| perfil.getCorreo().getText().endsWith("@miempresa.com"))) {
			System.out.println("Correo invalido");
			return true;
		}
			
		return false;
	}
}
