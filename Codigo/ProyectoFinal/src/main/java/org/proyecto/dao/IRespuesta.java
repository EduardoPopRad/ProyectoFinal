package org.proyecto.dao;

import java.util.List;

import org.proyecto.excepciones.RespuestaException;
import org.proyecto.vo.Respuesta;

public interface IRespuesta {
	List<Respuesta> obtenerTodas() throws RespuestaException;
	Respuesta obtenerPorId(Respuesta obj) throws RespuestaException;
	void insertar(Respuesta obj) throws RespuestaException;
	void actualizar(Respuesta obj) throws RespuestaException;
	void eliminar(Respuesta obj) throws RespuestaException;
}
