package org.proyecto.dao.impl;

import org.hibernate.Session;
import org.proyecto.dao.IPermisos;
import org.proyecto.util.GestorSesionesHibernate;
import org.proyecto.vo.PermisosUsuario;

public class PermisosDao implements IPermisos {

	@Override
	public boolean tienePermiso(PermisosUsuario per) {
		try (Session session = GestorSesionesHibernate.getSessionFactory().openSession()) {
			return session.get(PermisosUsuario.class, per.getId()) != null;
		}catch (Exception e) {
			return false; //Saltaría la excepción si no existe el usuario o el permiso
		}
	}
	
}
