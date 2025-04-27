package org.proyecto.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.proyecto.dao.IObra;
import org.proyecto.excepciones.ObraException;
import org.proyecto.util.GestorSesionesHibernate;
import org.proyecto.vo.Obra;

public class GenericaObra<T extends Obra> implements IObra<T> {

	private final Class<T> clase;

	public GenericaObra(Class<T> clase) { // Se crea con Numericos.class
		this.clase = clase;
	}

	@Override
	public List<T> obtenerTodos() throws ObraException {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.createQuery("FROM " + clase.getName(), clase).list();
		} catch (Exception e) {
			throw new ObraException(e.getMessage(), ObraException.ERROR_BUSQUEDA, "GenericaObra.ObtenerTodos");
		}
	}

	@Override
	public T obtenerPorId(T obj) throws ObraException {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.get(clase, obj.getId());
		} catch (Exception e) {
			throw new ObraException(e.getMessage(), ObraException.ERROR_BUSQUEDA, "GenericaObra.ObtenerPorId");
		}
	}

	@Override
	public void insertar(T obj) throws ObraException {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new ObraException(e.getMessage(), ObraException.ERROR_INSERCION, "GenericaObra.Insertar");
		}
	}

	@Override
	public void actualizar(T obj) throws ObraException {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new ObraException(e.getMessage(), ObraException.ERROR_ACTUALIZACION, "GenericaObra.Actualizar");
		}
	}

	@Override
	public void eliminar(T obj) throws ObraException {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			T entity = session.get(clase, obj.getId());
			if (entity != null) {
				session.remove(entity);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new ObraException(e.getMessage(), ObraException.ERROR_ELIMINACION, "GenericaObra.Eliminar");
		}
	}

}
