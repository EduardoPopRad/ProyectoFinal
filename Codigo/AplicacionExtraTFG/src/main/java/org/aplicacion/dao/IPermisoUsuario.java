package org.aplicacion.dao;

import java.util.List;

import org.aplicacion.vo.Permiso;
import org.aplicacion.vo.Usuario;

public interface IPermisoUsuario {
	
	public List<Permiso> permisosUsuario(Usuario usu) throws Exception; //Devuelve la lista de permisos que tiene un usuario
	public void insertarPermisos(List<Permiso> permisos, Usuario usu) throws Exception; //Inserta todos los permisos asociados a un usuario
	public void eliminar(List<Permiso> permisos, Usuario usu) throws Exception; //Elimina todos los permisos asociados a un usuario
	
}
