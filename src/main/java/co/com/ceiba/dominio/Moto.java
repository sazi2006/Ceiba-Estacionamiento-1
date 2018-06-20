package co.com.ceiba.dominio;

import java.util.Date;

public class Moto extends Vehiculo{
	
	private short cilindrada;
	
	public Moto(String placa, short cilindrada, Date fechaIngreso) {
		super(placa, fechaIngreso);
		this.cilindrada = cilindrada;
	}
	
	public short getCilindrada() {
		return cilindrada;
	}
	
}
