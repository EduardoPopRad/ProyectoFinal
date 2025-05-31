package org.aplicacion.vo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "permisos_usuario")
public class PermisosUsuario {
	
	@Embeddable
	public static class PermUsu implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "id_usu")
		private Integer idUsuario;

		@Column(name = "id_permiso")
		private Integer idPermiso;
		
		public PermUsu() {}
		
		public PermUsu(Integer idPermiso, Integer idUsu) {
			this.idPermiso = idPermiso;
			this.idUsuario = idUsu;
		}

		// Esta clase siempre tiene estos dos métodos
		public boolean equals(Object o) {
			boolean result = false;
			if (o != null && o instanceof PermUsu) {
				PermUsu other = (PermUsu) o;
				result = (this.idPermiso.equals(other.idPermiso) && this.idUsuario.equals(other.idUsuario));
			}
			return result;
		}

		public int hashCode() {
			return Objects.hash(idUsuario, idPermiso);
		}
	}

	@EmbeddedId
	private PermUsu id;
	
	@ManyToOne
    @JoinColumn(name = "id_usu", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_permiso", insertable = false, updatable = false)
    private Permiso permiso;
	
	public PermisosUsuario() {}
	
	public PermisosUsuario(Usuario u, Permiso p) {
		this.id=new PermUsu(p.getId(), u.getId());
		this.permiso=p;
		this.usuario=u;
	}
	
	
	
	@Override
	public String toString() {
		return "usuario=" + usuario + ", permiso=" + permiso;
	}

	public PermUsu getId() {
		return id;
	}

	public Usuario getIdUsu() {
		return usuario;
	}

	public void setIdUsu(Usuario idUsu) {
		this.usuario = idUsu;
	}

	public Permiso getIdPermiso() {
		return permiso;
	}

	public void setIdPermiso(Permiso idPermiso) {
		this.permiso = idPermiso;
	}
	
}