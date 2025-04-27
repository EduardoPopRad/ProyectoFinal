package org.proyecto.dao;

import java.util.List;

import org.proyecto.excepciones.PuntuacionException;
import org.proyecto.vo.Obra;
import org.proyecto.vo.Puntuacion;
import org.proyecto.vo.Usuario;

public interface IPuntuacion {
	List<Puntuacion> obtenerTodas() throws PuntuacionException;
	Puntuacion obtenerPorId(Puntuacion obj) throws PuntuacionException;
	void insertar(Puntuacion obj) throws PuntuacionException;
	void actualizar(Puntuacion obj) throws PuntuacionException;
	void eliminar(Puntuacion id) throws PuntuacionException;
	boolean haCalificadoObra(Usuario u, Obra o) throws PuntuacionException;
	Integer obtenerMediaObra(Obra o) throws PuntuacionException;
}

