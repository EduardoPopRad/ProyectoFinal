package org.proyecto.vo;

import java.time.LocalDate;

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
@Table(name = "respuesta")
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_respuesta", nullable = false)
	private int idRespuesta;
	
	@ManyToOne
	@JoinColumn(name="id_consulta", nullable = false)
	private Consulta consulta;
	
	@ManyToOne
	@JoinColumn(name="id_trabajador", nullable = false)
	private Usuario trabajador;
	
	@Basic(fetch = FetchType.LAZY) // no se carga hasta que no llamas al getter
	@Column(name="respuesta", nullable = false)
	private String respuesta;
	
	//Falta anotaciones
	@Column(name="fecha_respuesta", nullable = false) // en localHost es fechaRespuesta
	private LocalDate fecha;

	public Respuesta() {
	}

	public Respuesta(int id) {
		this.idRespuesta = id;
	}

	public Respuesta(Consulta consulta, Usuario trabajador, String respuesta, LocalDate fecha) {
		this.consulta = consulta;
		this.trabajador = trabajador;
		this.respuesta = respuesta;
		this.fecha = fecha;
	}
	
	public Respuesta(int id, Consulta consulta, Usuario trabajador, String respuesta, LocalDate fecha) {
		this.idRespuesta = id;
		this.consulta = consulta;
		this.trabajador = trabajador;
		this.respuesta = respuesta;
		this.fecha = fecha;
	}

	public int getId() {
		return idRespuesta;
	}

	public void setId(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Usuario getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Usuario trabajador) {
		this.trabajador = trabajador;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return consulta.toString() + "\n" + fecha + " respondida por " + trabajador.getUser()+" ("+trabajador.getId()+")";
	}
}

