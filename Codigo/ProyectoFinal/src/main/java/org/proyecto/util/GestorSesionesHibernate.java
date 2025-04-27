package org.proyecto.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GestorSesionesHibernate {
	 private static SessionFactory sessionFactory=null;

	    static {
	        try {
	            // Configuración inicial de Hibernate
	            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	        } catch (Throwable ex) {
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public static void shutdown() {
	        // Cierra caches y conexiones
	        getSessionFactory().close();
	    }
}

