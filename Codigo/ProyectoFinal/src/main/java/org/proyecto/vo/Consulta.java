package org.proyecto.vo;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta", nullable = false)
	private int idConsulta;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Usuario cliente;

	@Column(name = "descripcion", nullable = false, length = 150)
	private String descripcion;

	@Basic(fetch = FetchType.LAZY) // no se carga hasta que no llamas al getter
	@Column(name = "consulta", nullable = false)
	private String consulta;

	@Column(name = "fechaCreacion", nullable = false)
	private LocalDate fechaConsulta;

	@Column(name = "estado", nullable = false)
	private boolean estado;

	public Consulta() {
	}

	public Consulta(int id) {
		this.idConsulta = id;
	}

	public Consulta(Usuario idCliente, String descripcion, String consulta, LocalDate fechaConsulta, boolean estado) {
		this.cliente = idCliente;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.fechaConsulta = fechaConsulta;
		this.estado = estado;
	}

	public Consulta(int idConsulta, Usuario idCliente, String descripcion, String consulta, LocalDate fechaConsulta,
			boolean estado) {
		this.idConsulta = idConsulta;
		this.cliente = idCliente;
		this.descripcion = descripcion;
		this.consulta = consulta;
		this.fechaConsulta = fechaConsulta;
		this.estado = estado;
	}

	public int getId() {
		return idConsulta;
	}

	public void setId(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario idCliente) {
		this.cliente = idCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public LocalDate getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(LocalDate fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "id_consulta: "+idConsulta + "\n" +cliente.getUser() + " (" + cliente.getId() + ")\t"+ fechaConsulta +"\n" + this.descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConsulta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return idConsulta == other.idConsulta;
	}
	
	
}
