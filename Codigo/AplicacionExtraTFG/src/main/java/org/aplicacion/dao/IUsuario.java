package org.aplicacion.dao;

import java.util.List;

import org.aplicacion.vo.Usuario;

public interface IUsuario {
	
	public List<Usuario> listaTrabajadores() throws Exception; //Metodo que obtiene de la tabla Usuario, todos que tengan el valor 1 en Rol
	public Usuario obtenerPorId(Usuario usu) throws Exception;
	public void actualizar (Usuario usu) throws Exception;
	public void insertar (Usuario usu) throws Exception;
	public void eliminar (Usuario usu) throws Exception;
	Usuario obtenerPorUser(Usuario obj) throws Exception;
}
