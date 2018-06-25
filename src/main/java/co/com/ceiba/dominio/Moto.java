package co.com.ceiba.dominio;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Moto extends Vehiculo{
	
	private short cilindrada;
	
	@JsonCreator
	public Moto(@JsonProperty(value="placa", required=true) String placa,
			@JsonProperty(value="cilindrada") short cilindrada,
			@JsonProperty(value="fechaIngreso") Date fechaIngreso, 
			@JsonProperty(value="estaEnParqueadero", defaultValue="false") boolean estaEnParqueadero) {
		
		super(placa, fechaIngreso, estaEnParqueadero);
		this.cilindrada = cilindrada;
	}
	
	public short getCilindrada() {
		return cilindrada;
	}
	
}
