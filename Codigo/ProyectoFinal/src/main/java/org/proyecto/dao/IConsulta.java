package org.proyecto.dao;

import java.util.List;

import org.proyecto.excepciones.ConsultaException;
import org.proyecto.vo.Consulta;

public interface IConsulta {
	public List<Consulta> obtenerTodas() throws ConsultaException;
	public List<Consulta> obtenerTodasNoRespondidas() throws ConsultaException;
	public Consulta obtenerPorId(Consulta obj) throws ConsultaException;
	public void insertar(Consulta obj) throws ConsultaException;
	public void actualizar(Consulta obj) throws ConsultaException;
	public void eliminar(Consulta id) throws ConsultaException;
	boolean estaRespondida(Consulta obj) throws ConsultaException;
}
