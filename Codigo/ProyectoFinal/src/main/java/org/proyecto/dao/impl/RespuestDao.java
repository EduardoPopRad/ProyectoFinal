package org.proyecto.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.proyecto.dao.IConsulta;
import org.proyecto.dao.IRespuesta;
import org.proyecto.excepciones.ConsultaException;
import org.proyecto.util.GestorSesionesHibernate;
import org.proyecto.vo.Consulta;
import org.proyecto.vo.Respuesta;

public class RespuestDao implements IRespuesta {

	@Override
	public List<Respuesta> obtenerTodas() {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.createQuery("FROM " + Respuesta.class.getName(), Respuesta.class).list();
		}
	}

	@Override
	public Respuesta obtenerPorId(Respuesta obj) {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.get(Respuesta.class, obj.getId());
		}
	}

	@Override
	public void insertar(Respuesta obj) {
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		
		IConsulta icon = new ConsultaDao();
		obj.getConsulta().setEstado(true);
		try {
			icon.actualizar(obj.getConsulta());
		} catch (ConsultaException e) {
			System.out.println(e);
		}
	}

	@Override
	public void actualizar(Respuesta obj) {
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
	public void eliminar(Respuesta obj) {
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
			e.printStackTrace();
		}
	}

}
