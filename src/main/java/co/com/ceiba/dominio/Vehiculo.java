package co.com.ceiba.dominio;

import java.util.Date;

public class Vehiculo {
	private String placa;
	private Date fechaIngreso;
	
	public Vehiculo(String placa, Date fechaIngreso) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
}
