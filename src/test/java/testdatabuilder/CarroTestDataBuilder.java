package testdatabuilder;

import java.util.Date;

import co.com.ceiba.dominio.Carro;

public class CarroTestDataBuilder extends VehiculoTestDataBuilder{
	
	public CarroTestDataBuilder() {
		super();
	}
	
	public CarroTestDataBuilder conPlaca(String placa) {
		this.setPlaca(placa);;
		return this;
	}
	
	public CarroTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.setFechaIngreso(fechaIngreso);
		return this;
	}
	
	public CarroTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.setFechaSalida(fechaSalida);
		return this;
	}
	
	public Carro build() {
		return new Carro(this.getPlaca(), this.getFechaIngreso(), this.getFechaSalida(), this.estaEnParqueadero());
	}
	
}
