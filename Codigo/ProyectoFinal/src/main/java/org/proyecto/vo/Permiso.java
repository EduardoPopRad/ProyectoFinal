package org.proyecto.vo;

public class Permiso {
	
	private int id;
	
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
