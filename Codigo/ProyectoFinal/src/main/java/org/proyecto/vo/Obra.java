package org.proyecto.vo;

import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "obra")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Obra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_obra", nullable = false)
	private int id;

	@Column(name = "titulo", nullable = false, length = 255)
	protected String titulo;

	@Column(name = "autor", nullable = false, length = 255)
	protected String autor;

	@Column(name = "dimensiones", nullable = false, length = 13) // Alto x ancho
	protected String medidas;

	@Column(name = "estilo", nullable = false, length = 100)
	protected String estilo;

	@Column(name = "ubicacion", nullable = false, length = 255)
	protected String exposicion;

	@Column(name = "descripcion", nullable = false)
	protected String descripcion;

	@Column(name = "anyo", nullable = false, length = 11)
	protected String anyo; 

	@Lob
	@Basic(fetch = FetchType.LAZY) //No lo carga hasta que no se llama al metodo getter de la imagen
	@Column(name = "imagen", nullable = false)
	protected byte[] imagen;

	public Obra() {
	}

	public Obra(int id) {
		this.id = id;
	}

	public Obra(String tit, String aut, String med, String est, String expo, String desc, String a, byte[] img) {
		this.titulo = tit;
		this.autor = aut;
		this.medidas = med;
		this.estilo = est;
		this.exposicion = expo;
		this.descripcion = desc;
		this.anyo = a;
		this.imagen=img;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getMedidas() {
		return medidas;
	}

	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}

	public String getPeriodo() {
		return estilo;
	}

	public void setPeriodo(String periodo) {
		this.estilo = periodo;
	}

	public String getExposicion() {
		return exposicion;
	}

	public void setExposicion(String exposicion) {
		this.exposicion = exposicion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return imagen;
	}

	public void setImage(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return titulo + " ( " + autor + ", " + anyo + " )";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obra other = (Obra) obj;
		return id == other.id;
	}
	
	
}

