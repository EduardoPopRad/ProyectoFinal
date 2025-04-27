package org.proyecto.vo;

public class PermisosUsuario {
	
	private int idUsu;
	
	private int idPermiso;
	
	public PermisosUsuario() {}
	
	public PermisosUsuario(int u, int p) {
		this.idPermiso=p;
		this.idUsu=u;
	}

	public int getIdUsu() {
		return idUsu;
	}

	public void setIdUsu(int idUsu) {
		this.idUsu = idUsu;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	
}
