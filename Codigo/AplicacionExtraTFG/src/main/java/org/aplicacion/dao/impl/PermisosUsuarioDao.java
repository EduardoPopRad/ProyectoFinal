package org.aplicacion.dao.impl;

import java.util.List;

import org.aplicacion.dao.IPermisoUsuario;
import org.aplicacion.util.GestorConexionesJpa;
import org.aplicacion.vo.Permiso;
import org.aplicacion.vo.PermisosUsuario;
import org.aplicacion.vo.Usuario;

import jakarta.persistence.EntityManager;

public class PermisosUsuarioDao implements IPermisoUsuario {

	public PermisosUsuarioDao() {
	}

	@SuppressWarnings("unchecked") // Es porque hibernate no sabe seguro que tipo de dato devuelve, por lo que te
									// indica warning
	@Override
	public List<Permiso> permisosUsuario(Usuario usu) throws Exception{ // Devuelve la lista de Permisos que tiene un usuario
		try(EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()){
			String sql = "SELECT p.* FROM permiso p " + "JOIN permisos_usuario pu ON p.id_permiso = pu.id_permiso "
					+ "WHERE pu.id_usu = :idUsuario";
			
			return entity.createNativeQuery(sql, Permiso.class).setParameter("idUsuario", usu.getId()).getResultList();
		}
	}

	@Override
	public void insertarPermisos(List<Permiso> permisos, Usuario usu) throws Exception{
		try(EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()){
			entity.getTransaction().begin();
			for (int i = 0; i < permisos.size(); i++) {
				entity.persist(new PermisosUsuario(usu, permisos.get(i)));
			}
			entity.getTransaction().commit();
		}
	}

	@Override
	public void eliminar(List<Permiso> permisos, Usuario usu) throws Exception{
		try(EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()){
			entity.getTransaction().begin();
			for (int i = 0; i < permisos.size(); i++) {
				PermisosUsuario pu = new PermisosUsuario(usu, permisos.get(i));
				entity.remove(entity.find(PermisosUsuario.class, pu.getId()));
			}
			entity.getTransaction().commit();
		}
	}

}
