package co.com.ceiba.dominio;

import java.util.Date;

public class Factura {
	private Date fechaIngreso;
	private Date fechaSalida;
	
	public Factura(Date fechaIngreso, Date fechaSalida) {
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public int generarFactura() {
		return 0;
		
	}

}
