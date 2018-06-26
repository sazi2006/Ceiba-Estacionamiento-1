package co.com.ceiba.dominio.respuesta;

import java.util.Date;

public class FormatoRespuesta {
	private String mensaje;
	private boolean estado;
	private Object contenido;
	private Date timestamp;
	
	public FormatoRespuesta(String mensaje, boolean estado, Object contenido) {
		this.mensaje = mensaje;
		this.estado = estado;
		this.contenido = contenido;
		this.timestamp = new Date();
	}
	
	public FormatoRespuesta(String mensaje, boolean estado) {
		this.mensaje = mensaje;
		this.estado = estado;
		this.contenido = null;
		this.timestamp = new Date();
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean correcto) {
		this.estado = correcto;
	}
	
	public Object getContenido() {
		return contenido;
	}
	public void setContenido(Object contenido) {
		this.contenido = contenido;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
