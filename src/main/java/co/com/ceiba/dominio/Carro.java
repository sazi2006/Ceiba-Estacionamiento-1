package co.com.ceiba.dominio;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Carro extends Vehiculo{
	
	@JsonCreator
	public Carro(@JsonProperty(value="placa", required=true) String placa,@JsonProperty(value="fechaIngreso", required=true) Date fechaIngreso) {
		super(placa, fechaIngreso);
	}
	
}
