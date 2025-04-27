package org.proyecto.util;

import java.util.ArrayList;
import java.util.List;

import org.proyecto.dao.IConsulta;
import org.proyecto.dao.impl.ConsultaDao;
import org.proyecto.excepciones.ConsultaException;
import org.proyecto.vo.Consulta;

public class ConsultasCreadas {
	
	private static ConsultasCreadas c; //Al ser singelton solo existe esta instancia de la clase
	
	private List<Consulta> consultas; //Necesitamos tener un orden de llegada de las consultas
	private IConsulta cons;
	
	private ConsultasCreadas() {
		this.cons = new ConsultaDao();
	}
	
	//Metodo para obtener la instancia
	public static ConsultasCreadas getConsultas() {
		if (c == null) {
			c = new ConsultasCreadas();
		}
		return c;
	}

	public void addValor(Consulta value) {
		consultas.add(value);
	}
	
	public void eliminarValor(Consulta valor) {
		consultas.remove(valor);
	}

	public List<Consulta> getList() {
		try {
			consultas = cons.obtenerTodasNoRespondidas();
		} catch (ConsultaException e) {
			System.out.println(e);
		}
		return new ArrayList<>(consultas); //Para evitar exponer la lista original y que nadie pueda insertar valores en esta

	}
}

