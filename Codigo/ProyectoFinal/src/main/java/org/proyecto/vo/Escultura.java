package org.proyecto.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "escultura")
@PrimaryKeyJoinColumn(name = "id_obra")
public class Escultura extends Obra {

	@Column(name = "material", nullable = false, length = 100)
	private String material;

	public Escultura() {
	}

	public Escultura(int id) {
		super(id);
	}

	public Escultura(String tit, String aut, String med, String per, String expo, String desc, String a, String mat,
			byte[] img) {
		super(tit, aut, med, per, expo, desc, a, img);
		this.material = mat;
	}

	public String getPropia() {
		return material;
	}

	public void setPropia(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
