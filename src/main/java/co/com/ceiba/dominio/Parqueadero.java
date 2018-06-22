package co.com.ceiba.dominio;

public class Parqueadero {
	private String nombre;
	private String direccion;
	private short nroCeldas;
	
	public Parqueadero(String nombre, String direccion, short nroCeldas) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.nroCeldas = nroCeldas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public short getNroCeldas() {
		return nroCeldas;
	}
	
}
