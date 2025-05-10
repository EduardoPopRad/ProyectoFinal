package org.proyecto.vo;

import java.time.LocalDate;

import org.proyecto.dao.IPermisos;
import org.proyecto.dao.impl.PermisosDao;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "usuario", nullable = false, length = 100, unique = true)
	private String user;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "email", nullable = false, length = 255, unique = true)
	private String email;

	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;

	@Column(name = "fecha_registro", nullable = false) // Deberia haber mas anotaciones para la fecha
	private LocalDate fechaRegistro;

	@Lob
	@Basic(fetch = FetchType.LAZY) // optimizar el rendimiento y reducir la carga de datos innecesaria, evita que
									// se traigan a la memoria hasta que se necesiten
	@Column(name = "imagen", nullable = false)
	private byte[] imagen;

	public Usuario() {
		//Ya no se pueden crear Trabajadores en esta aplicacion, se debería hacer en la otra
		//por lo que permisos nunca se dan aqui, solo se obtienen.
	}

	public Usuario(int id) {
		this.id = id;
	}
	
	public Usuario(String usu) {
		this.user=usu;
	}

	public Usuario(int id, String usu, String contra, Rol r) {
		this();
		this.id = id;
		this.user = usu;
		this.password = contra;
		this.rol = r;
	}
	
	public Usuario(String usu, String contra, String email, Rol r, LocalDate fecha, byte[] img) {
		this();
		this.email = email;
		this.user = usu;
		this.password = contra;
		this.rol = r;
		this.fechaRegistro = fecha;
		this.imagen = img;
	}

	public Usuario(int ide, String usu, String contra, String email, Rol r, LocalDate fecha, byte[] img) {
		this.email = email;
		this.user = usu;
		this.password = contra;
		this.id = ide;
		this.rol = r;
		this.fechaRegistro = fecha;
		this.imagen = img;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isPuedeInsertar() { 
		IPermisos per = new PermisosDao();
		return per.tienePermiso(new PermisosUsuario(this, new Permiso(1))); 
	}

	public boolean isPuedeEliminar() { 
		IPermisos per = new PermisosDao();
		return per.tienePermiso(new PermisosUsuario(this, new Permiso(4))); 
	}

	public boolean isPuedeActualizar() {
		IPermisos per = new PermisosDao();
		return per.tienePermiso(new PermisosUsuario(this, new Permiso(2))); 
	}

	public boolean isPuedeResponder() {
		IPermisos per = new PermisosDao();
		return per.tienePermiso(new PermisosUsuario(this, new Permiso(5)));
	}

	public byte[] getImage() {
		return imagen;
	}

	public void setImage(byte[] img) {
		this.imagen = img;
	}

	@Override
	public String toString() {
		return id + "(" + user + ") ";
	}
}

