package org.proyecto.vo;

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
@Table(name = "puntuacion")
public class Puntuacion {

	@Embeddable
	public static class UsuObra implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "id_usuario")
		private Integer idUsuario;

		@Column(name = "id_obra")
		private Integer idObra;
		
		public UsuObra() {}
		
		public UsuObra(Integer idObra, Integer idUsu) {
			this.idObra = idObra;
			this.idUsuario = idUsu;
		}

		// Esta clase siempre tiene estos dos métodos
		public boolean equals(Object o) {
			boolean result = false;
			if (o != null && o instanceof UsuObra) {
				UsuObra other = (UsuObra) o;
				result = (this.idObra.equals(other.idObra) && this.idUsuario.equals(other.idUsuario));
			}
			return result;
		}

		public int hashCode() {
			return Objects.hash(idUsuario, idObra);
		}
	}

	@EmbeddedId
	private UsuObra id;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_obra", insertable = false, updatable = false)
    private Obra obra;

	@Column(name = "valor", nullable = false)
	private int valor;

	public Puntuacion() {
	}

	public Puntuacion(Usuario u, Obra obra) {
		this.id= new UsuObra(obra.getId(), u.getId());
		this.usuario = u;
		this.obra = obra;
	}

	public Puntuacion(Usuario u, Obra o, int p) {
		this.id= new UsuObra(o.getId(), u.getId());
		this.usuario = u;
		this.obra = o;
		this.valor = p;
	}

	public UsuObra getId() {
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public int getPuntaje() {
		return valor;
	}

	public void setPuntaje(int puntaje) {
		this.valor = puntaje;
	}

	@Override
	public String toString() {
		return obra + " valorada por el usuario "+ usuario+ ": "+valor;
	}
}

