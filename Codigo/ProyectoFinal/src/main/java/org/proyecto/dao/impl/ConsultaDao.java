package org.proyecto.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.proyecto.dao.IConsulta;
import org.proyecto.excepciones.ConsultaException;
import org.proyecto.util.GestorSesionesHibernate;
import org.proyecto.vo.Consulta;

public class ConsultaDao implements IConsulta {

	@Override
	public List<Consulta> obtenerTodas() throws ConsultaException {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.createQuery("FROM " + Consulta.class.getName(), Consulta.class).list();
		} catch (Exception e) {
			throw new ConsultaException(e.getMessage(), ConsultaException.ERROR_BUSQUEDA, "ConsultaDao.obtenerTodas");
		}
	}

	@Override
	public List<Consulta> obtenerTodasNoRespondidas() throws ConsultaException {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.createNativeQuery("SELECT * FROM consulta WHERE estado = :estado", Consulta.class)
					.setParameter("estado", false).getResultList();
		} catch (Exception e) {
			throw new ConsultaException(e.getMessage(), ConsultaException.ERROR_BUSQUEDA,
					"ConsultaDao.ObtenerNoRespondidas");
		}
	}

	@Override
	public Consulta obtenerPorId(Consulta obj) throws ConsultaException {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.get(Consulta.class, obj.getId());
		} catch (Exception e) {
			throw new ConsultaException(e.getMessage(), ConsultaException.ERROR_BUSQUEDA, "ConsultaDao.ObtenerPorId");
		}
	}

	@Override
	public void insertar(Consulta obj) throws ConsultaException {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new ConsultaException(e.getMessage(), ConsultaException.ERROR_INSERCION,
					"ConsultaDao.Insertar");
		}
	}

	@Override
	public void actualizar(Consulta obj) throws ConsultaException {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new ConsultaException(e.getMessage(), ConsultaException.ERROR_ACTUALIZACION,
					"ConsultaDao.Actualizar");
		}
	}

	@Override
	public void eliminar(Consulta obj) throws ConsultaException {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Consulta entity = session.get(Consulta.class, obj.getId());
			if (entity != null) {
				session.remove(entity);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new ConsultaException(e.getMessage(), ConsultaException.ERROR_ELIMINACION,
					"ConsultaDao.Eliminar");
		}
	}

	// Metodo para validar que realmente no esta respondida la consulta
	@Override
	public boolean estaRespondida(Consulta obj) throws ConsultaException {
		obj = obtenerPorId(obj);
		return obj.isEstado();
	}

}
