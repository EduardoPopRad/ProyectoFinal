package org.proyecto.dao;

import java.util.List;

import org.proyecto.excepciones.ObraException;

public interface IObra<T>{
	public List<T> obtenerTodos() throws ObraException;
	public T obtenerPorId(T obj) throws ObraException;
	public void insertar(T obj) throws ObraException;
	public void actualizar(T obj) throws ObraException;
	public void eliminar(T obj) throws ObraException;
}
