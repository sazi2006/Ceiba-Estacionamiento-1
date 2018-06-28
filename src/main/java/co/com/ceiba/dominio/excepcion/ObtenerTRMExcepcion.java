package co.com.ceiba.dominio.excepcion;

public class ObtenerTRMExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObtenerTRMExcepcion(String message) {
		super(message);
	}
}

