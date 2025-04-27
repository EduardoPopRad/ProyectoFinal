package org.proyecto.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "grabado")
@PrimaryKeyJoinColumn(name = "id_obra")
public class Grabado extends Obra {

	@Column(name = "matriz", nullable = false, length = 100)
	private String matriz;

	public Grabado() {
	}

	public Grabado(int id) {
		super(id);
	}

	public Grabado(String tit, String aut, String med, String per, String expo, String desc, String a, String mat,
			byte[] img) {
		super(tit, aut, med, per, expo, desc, a, img);
		this.matriz = mat;
	}

	public String getPropia() {
		return matriz;
	}

	public void setPropia(String mat) {
		this.matriz = mat;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
