package org.proyecto.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.proyecto.dao.IUsuario;
import org.proyecto.util.GestorSesionesHibernate;
import org.proyecto.vo.Rol;
import org.proyecto.vo.Usuario;

public class UsuarioDao implements IUsuario {

	@Override
	public List<Usuario> obtenerTodos() {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.createQuery("FROM " + Usuario.class.getName(), Usuario.class).list();
		}
	}

	@Override
	public Usuario obtenerPorId(Usuario obj) {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.get(Usuario.class, obj.getId());
		}
	}

	@Override
	public Usuario obtenerPorUser(Usuario obj) {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return (Usuario) session.createNativeQuery("SELECT * FROM usuario WHERE usuario = ?", Usuario.class)
					.setParameter(1, obj.getUser()).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean existeUsuario(Usuario obj) {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return obtenerPorUser(obj) != null; // Si es false significa q no existe, si es true significa que existe
		}
	}

	@Override
	public void insertar(Usuario obj) {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			Rol rolBD = session.get(Rol.class, obj.getRol().getId());
			if (rolBD == null) {
				throw new Exception("No se encontró el rol con ID " + obj.getRol().getId());
			}

			obj.setRol(rolBD);
			transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar(Usuario obj) {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(Usuario obj) {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Usuario entity = session.get(Usuario.class, obj.getId());
			if (entity != null) {
				session.remove(entity);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

}
