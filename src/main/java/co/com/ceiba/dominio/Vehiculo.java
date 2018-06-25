package co.com.ceiba.dominio;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehiculo {
	private String placa;
	private Date fechaIngreso;
	private Date fechaSalida;
	private boolean estaEnParqueadero;
	
	@JsonCreator
	public Vehiculo(@JsonProperty(value="placa") String placa,
			@JsonProperty(value="fechaIngreso") Date fechaIngreso,
			@JsonProperty(value="fechaSalida") Date fechaSalida,
			@JsonProperty(value="estaEnParqueadero") boolean estaEnParqueadero) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.estaEnParqueadero = estaEnParqueadero;
		
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public boolean estaEnParqueadero() {
		return estaEnParqueadero;
	}
}
