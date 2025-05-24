package org.aplicacion.dao;

import java.util.List;

import org.aplicacion.vo.Permiso;

public interface IPermiso {
	
	public List<Permiso> obtenerTodos() throws Exception;
	public Permiso obtenerPorId(Permiso per) throws Exception;
	public Permiso obtenerPorTipo(Permiso per) throws Exception;
}
