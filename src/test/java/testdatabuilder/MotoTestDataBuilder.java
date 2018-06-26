package testdatabuilder;

import java.util.Date;

import co.com.ceiba.dominio.Moto;

public class MotoTestDataBuilder extends VehiculoTestDataBuilder {
	
	private static final short CILINDRADA = 180;
	
	private short cilindrada;
	
	public MotoTestDataBuilder() {
		super();
		this.cilindrada = CILINDRADA;
		
	}
	
	public MotoTestDataBuilder conPlaca(String placa) {
		this.setPlaca(placa);;
		return this;
	}
	
	public MotoTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.setFechaIngreso(fechaIngreso);
		return this;
	}
	
	public MotoTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.setFechaSalida(fechaSalida);
		return this;
	}
	
	public MotoTestDataBuilder estaEnParqueadero(boolean estaEnParqueadero) {
		this.setEstaEnParqueadero(estaEnParqueadero);
		return this;
	}
	
	public MotoTestDataBuilder conCilindrada(short cilindrada) {
		this.cilindrada = cilindrada;
		return this;
	}
	
	public Moto build() {
		return new Moto(this.getPlaca(), this.cilindrada, this.getFechaIngreso(), this.getFechaSalida(), this.estaEnParqueadero());
	}

}
