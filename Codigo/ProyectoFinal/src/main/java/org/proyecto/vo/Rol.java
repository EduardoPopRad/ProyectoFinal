package org.proyecto.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol", nullable = false)
	private int id;
	
	@Column(name = "nombre_corto", length = 255, nullable = false)
	private String tipo;

	public Rol() {
	}

	public Rol(int id) {
	}

	public Rol(String des) {
		this.tipo = des;
	}
	
	public Rol(int id, String des) {
		this.id = id;
		this.tipo = des;
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

	@Override
	public String toString() {
		return tipo;
	}
}
