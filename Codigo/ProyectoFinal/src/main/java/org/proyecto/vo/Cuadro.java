package org.proyecto.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuadro")
@PrimaryKeyJoinColumn(name = "id_obra")
public class Cuadro extends Obra {

	@Column(name = "tecnica", nullable = false, length = 100)
	private String metodo; // oleo, tempera, etc...

	public Cuadro() {
	}

	public Cuadro(int id) {
		super(id);
	}

	public Cuadro(String tit, String aut, String med, String periodo, String expo, String desc, String a,
			String propia, byte[] img) {
		super(tit, aut, med, periodo, expo, desc, a, img);
		this.metodo = propia;
	}

	public String getPropia() {
		return metodo;
	}

	public void setPropia(String metodo) {
		this.metodo = metodo;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
