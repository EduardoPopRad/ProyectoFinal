package org.proyecto.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.proyecto.dao.IPuntuacion;
import org.proyecto.excepciones.PuntuacionException;
import org.proyecto.util.GestorSesionesHibernate;
import org.proyecto.vo.Obra;
import org.proyecto.vo.Puntuacion;
import org.proyecto.vo.Usuario;

public class PuntuacionDao implements IPuntuacion{

	@Override
	public List<Puntuacion> obtenerTodas() throws PuntuacionException {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.createQuery("FROM " + Puntuacion.class.getName(), Puntuacion.class).list();
		}catch (Exception e) {
			throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_BUSQUEDA, "PuntuacionDao.ObtenerTodos");
		}
	}

	@Override
	public Puntuacion obtenerPorId(Puntuacion obj) throws PuntuacionException{
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.get(Puntuacion.class, obj.getId());
		}catch (Exception e) {
			throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_BUSQUEDA, "PuntuacionDao.ObtenerPorId");
		}
	}

	@Override
	public void insertar(Puntuacion obj) throws PuntuacionException{
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_INSERCION, "PuntuacionDao");
		}
	}

	@Override
	public void actualizar(Puntuacion obj) throws PuntuacionException{
		Transaction transaction = null;
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_ACTUALIZACION, "PuntuacionDao");
		}
	}

	@Override
	public void eliminar(Puntuacion obj) throws PuntuacionException{
		Transaction transaction = null;
        try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Puntuacion entity = session.get(Puntuacion.class, obj.getId());
            if (entity != null) {
                session.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_ELIMINACION, "PuntuacionDao");
        }
	}

	@Override
	public Integer obtenerMediaObra(Obra o) throws PuntuacionException{
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
	        Double resultado = session.createQuery(
	                "SELECT AVG(valor) FROM Puntuacion WHERE obra = :idObra", Double.class)
	                .setParameter("idObra", o)
	                .getSingleResult();
	        return (resultado != null) ? (int) Math.floor(resultado) : 0;
	    } catch (Exception e) {
	    	throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_OBTENCION_MEDIA, "PuntuacionDao.ObtenerMedia");
	    }
	}

	@Override
	public boolean haCalificadoObra(Usuario u, Obra o) throws PuntuacionException{
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
	        Long count = session.createQuery(
	                "SELECT COUNT(valor) FROM Puntuacion WHERE obra = :idObra AND usuario = :idUsuario",
	                Long.class)
	                .setParameter("idObra", o)
	                .setParameter("idUsuario", u)
	                .getSingleResult();
	        
	        return count != null && count > 0;
	    } catch (Exception e) {
	    	throw new PuntuacionException(e.getMessage(), PuntuacionException.ERROR_OBTENCION_CALIFICACION, "PuntuacionDao.haCalificado");
	    }
	}

}
