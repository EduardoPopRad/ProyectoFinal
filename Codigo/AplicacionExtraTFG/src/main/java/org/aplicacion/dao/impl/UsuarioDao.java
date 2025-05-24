package org.aplicacion.dao.impl;

import java.util.List;

import org.aplicacion.dao.IUsuario;
import org.aplicacion.util.GestorConexionesJpa;
import org.aplicacion.vo.Rol;
import org.aplicacion.vo.Usuario;

import jakarta.persistence.EntityManager;

public class UsuarioDao implements IUsuario {

	public UsuarioDao() {
	}

	@SuppressWarnings("unchecked") // Es porque hibernate no sabe seguro que tipo de dato devuelve, por lo que te
									// indica warning
	@Override
	public List<Usuario> listaTrabajadores() throws Exception { // Obtiene de la BBDD solo los que son trabajadores
		try (EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()) {
			return entity.createNativeQuery(
					"SELECT u.* FROM usuario u JOIN rol r ON u.id_rol = r.id_rol WHERE r.nombre_corto = :nombre",
					Usuario.class).setParameter("nombre", "Trabajador").getResultList();
		}
	}

	@Override
	public Usuario obtenerPorId(Usuario usu) throws Exception {
		try (EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()) {
			return entity.find(Usuario.class, usu.getId());
		}
	}

	@Override
	public Usuario obtenerPorUser(Usuario obj) throws Exception {
		try (EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()) {
			return (Usuario) entity.createNativeQuery("SELECT * FROM usuario WHERE usuario = :Usu", Usuario.class)
					.setParameter("Usu", obj.getUser()).getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void actualizar(Usuario usu) throws Exception {
		try (EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()) {
			entity.getTransaction().begin();
			entity.merge(usu);
			entity.getTransaction().commit();
		}
	}

	@Override
	public void insertar(Usuario usu) throws Exception {
		try (EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()) {
			Rol rolBD = entity.find(Rol.class, usu.getRol().getId());
			if (rolBD == null) {
				throw new Exception("No se encontró el rol con ID " + usu.getRol().getId());
			}

			usu.setRol(rolBD);

			entity.getTransaction().begin();
			entity.persist(usu);
			entity.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(Usuario usu) throws Exception {
		try (EntityManager entity = GestorConexionesJpa.getEntityManagerRemoto()) {
			entity.getTransaction().begin();
			entity.remove(entity.find(Usuario.class, usu.getId()));
			entity.getTransaction().commit();
		}
	}
}
