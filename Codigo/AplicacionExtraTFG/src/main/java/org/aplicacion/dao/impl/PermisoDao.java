package org.aplicacion.dao.impl;

import java.util.List;

import org.aplicacion.dao.IPermiso;
import org.aplicacion.util.GestorConexionesJpa;
import org.aplicacion.vo.Permiso;

import jakarta.persistence.EntityManager;

public class PermisoDao implements IPermiso {

	public PermisoDao() {
	}

	@Override
	public Permiso obtenerPorId(Permiso per) throws Exception{
		try(EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()){
			return entity.find(Permiso.class, per.getId());
		}
	}

	@Override
	public List<Permiso> obtenerTodos() throws Exception {
		try(EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()){
			return entity.createQuery("FROM " + Permiso.class.getName(), Permiso.class).getResultList();
		}
	}

	@Override
	public Permiso obtenerPorTipo(Permiso per)throws Exception {
		try(EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()){
			return (Permiso) entity.createNativeQuery("SELECT * FROM permiso WHERE tipo = :Tipo", Permiso.class)
					.setParameter("Tipo", per.getTipo()).getSingleResult();
		}
	}

}
