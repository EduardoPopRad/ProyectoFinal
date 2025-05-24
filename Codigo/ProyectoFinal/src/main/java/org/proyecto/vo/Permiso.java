package org.proyecto.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permiso")
public class Permiso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permiso", nullable = false)
	private int id;
	
	@Column(name="tipo", nullable = false)
	private String tipo;
	
	public Permiso() {}
	
	public Permiso(int i) {
		this.id=i;
	}
	
	public Permiso(int i, String p) {
		this.id=i;
		this.tipo=p;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
