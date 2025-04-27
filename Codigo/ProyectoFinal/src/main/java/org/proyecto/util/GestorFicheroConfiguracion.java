package org.proyecto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;

public class GestorFicheroConfiguracion {

    private static final String ruta = "conf/conf.properties";

    // Obtener valor de la propiedad
    public static String obtenerRuta(String val) {
        Properties p = new Properties();
        String devuelve = "No existe";
        try (FileInputStream fr = new FileInputStream(ruta)){
        	p.load(fr);
			devuelve=p.getProperty(val,"No existe");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devuelve;
    }

    // Actualizar valor en el archivo de propiedades
    public static void actualizarValor(String key, String value) {
        Properties p = new Properties();
        try (InputStream pin = new FileInputStream(new File("conf/conf.properties"))) {
            p.load(pin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        p.setProperty(key, value); // Hace el cambio de valor

        try (FileWriter writer = new FileWriter(ruta)) { 
            p.store(writer, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
