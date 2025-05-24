package org.aplicacion.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Seguridad {
	
	public static String hashearContrasena(String c) {
		return BCrypt.withDefaults().hashToString(12, c.toCharArray());
	}
	
	//Devuelve true si coinciden
	public static boolean verificarContrasena(String c, String bbdd) {
	    BCrypt.Result resultado = BCrypt.verifyer().verify(c.toCharArray(), bbdd);
	    return resultado.verified;
	}
}
