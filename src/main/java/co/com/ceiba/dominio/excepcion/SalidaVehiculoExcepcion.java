package co.com.ceiba.dominio.excepcion;

public class SalidaVehiculoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SalidaVehiculoExcepcion(String message) {
		super(message);
	}
}
