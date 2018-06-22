package co.com.ceiba.dominio;

import java.util.Date;

public class Vehiculo {
	private String placa;
	private Date fechaIngreso;
	private boolean estaEnParqueadero;
	
	public Vehiculo(String placa, Date fechaIngreso, boolean estaEnParqueadero) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.estaEnParqueadero = estaEnParqueadero;
		
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public boolean estaEnParqueadero() {
		return estaEnParqueadero;
	}
}
