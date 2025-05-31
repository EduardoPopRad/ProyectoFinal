package org.proyecto.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.proyecto.dao.IObra;
import org.proyecto.excepciones.ObraException;
import org.proyecto.util.UtilesData;
import org.proyecto.vo.Cuadro;
import org.proyecto.vo.Escultura;
import org.proyecto.vo.Grabado;
import org.proyecto.vo.Obra;

import javafx.scene.image.ImageView;
/**
 * Clase que contiene la lista de Obras que se va a llegar a mostrar en la app 
 * Pasa las obras a la clase ObraController para insertar los datos
 * Tiene contacto con la clase que obtiene los datos de la BBDD
 */
public class ObraService {
	private List<Obra> obras;
	private int pos;
	
	public ObraService() {
		obras = new ArrayList<>();
		pos = 0;
	}

	public void cargarTodos() {
		IObra<Cuadro> cua = new GenericaObra<Cuadro>(Cuadro.class);
		IObra<Grabado> gra = new GenericaObra<Grabado>(Grabado.class);
		IObra<Escultura> esc = new GenericaObra<Escultura>(Escultura.class);

		try {
			obras.addAll(cua.obtenerTodos());
			obras.addAll(gra.obtenerTodos());
			obras.addAll(esc.obtenerTodos());
		} catch (ObraException e) {
			System.out.println(e);
		}
		
		Collections.shuffle(obras);
	}

	public List<Obra> obtenerTodos() {
		return obras;
	}

	public void hacerFiltrado(String tipo) {
		try {
			switch (tipo) {
			case "Cuadro":
				IObra<Cuadro> cua = new GenericaObra<Cuadro>(Cuadro.class);
				obras.clear();
				obras.addAll(cua.obtenerTodos());
				Collections.shuffle(obras);
				break;
			case "Grabado":
				IObra<Grabado> gra = new GenericaObra<Grabado>(Grabado.class);
				obras.clear();
				obras.addAll(gra.obtenerTodos());
				Collections.shuffle(obras);
				break;
			case "Escultura":
				IObra<Escultura> esc = new GenericaObra<Escultura>(Escultura.class);
				obras.clear();
				obras.addAll(esc.obtenerTodos());
				Collections.shuffle(obras);
				break;
			default:
				obras.clear();
				cargarTodos();
			}
			pos = 0;
		}catch(ObraException e) {
			System.out.println(e);
		}
	}

	public Obra obtenerActual() {
		return obras.get(pos);
	}

	public Obra obtenerDerecha() {
		pos = (pos + 1) % obras.size();
		return obras.get(pos);
	}

	public Obra obtenerIzquierda() {
		pos = (pos - 1 + obras.size()) % obras.size();
		return obras.get(pos);
	}

	public void insertarObra(ImageView img, String tit, String anyo, String aut, String est, String dim, String ubi,
			String propia, String desc, String tipo) {
		try {
			byte[] imagen = UtilesData.pasarImagenAByte(img);
			switch (tipo) {
			case "Cuadro":
				IObra<Cuadro> cua = new GenericaObra<Cuadro>(Cuadro.class);
				cua.insertar(new Cuadro(tit, aut, dim, est, ubi, desc, anyo, propia, imagen));
				break;
			case "Grabado":
				IObra<Grabado> gra = new GenericaObra<Grabado>(Grabado.class);
				gra.insertar(new Grabado(tit, aut, dim, est, ubi, desc, anyo, propia, imagen));
				break;
			case "Escultura":
				IObra<Escultura> esc = new GenericaObra<Escultura>(Escultura.class);
				esc.insertar(new Escultura(tit, aut, dim, est, ubi, desc, anyo, propia, imagen));
				break;
			default:
				throw new ObraException("Se necesita especificar el tipo de dato que se va a insertar con los RadioButton", ObraException.ERROR_INSERCION, "ObraService.insertarObra");
			}
		}catch(ObraException e) {
			System.out.println(e);
		}
	}
	
	public void actualizarObra(Obra ob, String propia) {
		try {
			if(ob instanceof Cuadro) {
				Cuadro cu = (Cuadro) ob;
				cu.setPropia(propia);
				IObra<Cuadro> cua = new GenericaObra<Cuadro>(Cuadro.class);
				cua.actualizar(cu);
			}else if(ob instanceof Escultura) {
				Escultura es = (Escultura) ob;
				es.setPropia(propia);
				IObra<Escultura> esc = new GenericaObra<Escultura>(Escultura.class);
				esc.actualizar(es);
			}else if(ob instanceof Grabado) {
				Grabado gr = (Grabado) ob;
				gr.setPropia(propia);
				IObra<Grabado> gra = new GenericaObra<Grabado>(Grabado.class);
				gra.actualizar(gr);
			}else {
				throw new ObraException("Se necesita especificar el tipo de dato que se va a actualizar con los RadioButton", ObraException.ERROR_ACTUALIZACION, "ObraService.actualizarObra");
			}
		}catch (ObraException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarObra(Obra ob) {
		try {
			if(ob instanceof Cuadro) {
				Cuadro cu = (Cuadro) ob;
				IObra<Cuadro> cua = new GenericaObra<Cuadro>(Cuadro.class);
				cua.eliminar(cu);
			}else if(ob instanceof Escultura) {
				Escultura es = (Escultura) ob;
				IObra<Escultura> esc = new GenericaObra<Escultura>(Escultura.class);
				esc.eliminar(es);
			}else if(ob instanceof Grabado) {
				Grabado gr = (Grabado) ob;
				IObra<Grabado> gra = new GenericaObra<Grabado>(Grabado.class);
				gra.eliminar(gr);
			}else {
				throw new ObraException("Se necesita especificar el tipo de dato que se va a eliminar con los RadioButton", ObraException.ERROR_ELIMINACION, "ObraService.eliminarObra");
			}
		}catch(ObraException e) {
			System.out.println(e);
		}
	}
}

