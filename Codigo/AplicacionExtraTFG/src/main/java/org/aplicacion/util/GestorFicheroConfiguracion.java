package org.aplicacion.util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class GestorFicheroConfiguracion {
private static final String ruta="conf/conf.properties"; 
	
	public static String obtenerRuta(String val) {
		Properties p=new Properties();
		String devuelve="";
		try {
			FileInputStream fr= new FileInputStream(ruta);
			p.load(fr);
			devuelve=p.getProperty(val,"No existe");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelve;
	}
	
	public static void actualizarValor(String key, String value) {
		Properties p = new Properties();
		try (FileInputStream pin = new FileInputStream(ruta)) {
			p.load(pin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setProperty(key, value); //Hace el cambio de valor

		try (FileWriter writer = new FileWriter(ruta)) {
			p.store(writer, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
