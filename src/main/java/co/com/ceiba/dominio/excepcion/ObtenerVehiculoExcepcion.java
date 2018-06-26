package co.com.ceiba.dominio.excepcion;

public class ObtenerVehiculoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObtenerVehiculoExcepcion(String message) {
		super(message);
	}
}
