package co.com.ceiba.dominio.respuesta;

public class FormatoRespuestaVehiculo extends FormatoRespuesta{
	
	private String tipo;
	
	public FormatoRespuestaVehiculo(String mensaje, boolean estado, Object contenido, String tipo) {
		super(mensaje, estado, contenido);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
