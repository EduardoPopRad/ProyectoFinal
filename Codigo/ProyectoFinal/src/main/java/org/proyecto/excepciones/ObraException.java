package org.proyecto.excepciones;

public class ObraException extends Exception {

	private static final long serialVersionUID = 1L;

	// Códigos de error para identificar la causa
	public static final long ERROR_INSERCION = 0;
	public static final long ERROR_ACTUALIZACION = 1;
	public static final long ERROR_ELIMINACION = 2;
	public static final long ERROR_BUSQUEDA = 3;
	public static final long ERROR_OTRO = 4;

	// Variable que guardará el tipo de error
	private final long codigoError;

	// Variable que guardará el nombre de la clase
	private final String nombreClase;

	// Constructor sin argumentos
	public ObraException() {
		super("Ha ocurrido un error relacionado con la Obra.");
		this.codigoError = -1;
		this.nombreClase = "";
	}
	
	public ObraException(String mensaje, long codigoError, String nombreClase) {
		super(mensaje);
		this.codigoError = codigoError;
		this.nombreClase = nombreClase;
	}

	// Constructor con mensaje, código de error y causa
	public ObraException(String mensaje, long codigoError, String nombreClase, Throwable causa) {
		super(mensaje, causa);
		this.codigoError = codigoError;
		this.nombreClase = nombreClase;
	}

	// Getter para el código de error
	public long getCodigoError() {
		return codigoError;
	}

	// Método para obtener una descripción del código de error
	public String getDescripcionError() {
		switch ((int) codigoError) {
		case (int) ERROR_INSERCION:
			return "Error durante la insercion de la Obra en la BBDD.";
		case (int) ERROR_ACTUALIZACION:
			return "Error durante la actualización de la Obra en la BBDD.";
		case (int) ERROR_ELIMINACION:
			return "Error durante la eliminación de la Obra en la BBDD.";
		case (int) ERROR_BUSQUEDA:
			return "Error durante la búsqueda de la Obra en la BBDD.";
		case (int) ERROR_OTRO:
			return "Error desconocido pero controlado.";
		default:
			return "Error desconocido y no manejado.";
		}
	}

	// ToString para darle forma a la excepción
	@Override
	public String toString() {
		return "Usuario Exception {\nClase = " + this.nombreClase + "\nCódigo = " + codigoError + "\nMensaje = "
				+ getMessage() + "\nDescripcion = " + getDescripcionError() + "\n}";
	}
}
