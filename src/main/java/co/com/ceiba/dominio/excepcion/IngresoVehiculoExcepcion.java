package co.com.ceiba.dominio.excepcion;

public class IngresoVehiculoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IngresoVehiculoExcepcion(String message) {
		super(message);
	}
}
