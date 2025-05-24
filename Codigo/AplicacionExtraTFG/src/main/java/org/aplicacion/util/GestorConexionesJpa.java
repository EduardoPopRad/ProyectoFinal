package org.aplicacion.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GestorConexionesJpa {

	private GestorConexionesJpa() {
	}

	private final static String AWS = "aws";

	private static EntityManagerFactory entityMngFactoryLocal = null;

	public static EntityManager getEntityManagerRemoto() {
		EntityManager gestorEntidades = null;
		if (entityMngFactoryLocal == null) {
			entityMngFactoryLocal = Persistence.createEntityManagerFactory(AWS);
		}
		gestorEntidades = entityMngFactoryLocal.createEntityManager();
		return gestorEntidades;
	}

}
