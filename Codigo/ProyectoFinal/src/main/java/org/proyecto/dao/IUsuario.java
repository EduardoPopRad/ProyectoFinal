package org.proyecto.dao;

import java.util.List;

import org.proyecto.excepciones.UsuarioException;
import org.proyecto.vo.Usuario;

public interface IUsuario {

	public List<Usuario> obtenerTodos() throws UsuarioException;
	public Usuario obtenerPorId(Usuario obj) throws UsuarioException;
	public Usuario obtenerPorUser(Usuario obj) throws UsuarioException;
	public boolean existeUsuario(Usuario obj) throws UsuarioException;
	public void insertar(Usuario obj) throws UsuarioException;
	public void actualizar(Usuario obj) throws UsuarioException;
	public void eliminar(Usuario id) throws UsuarioException;
	
}
