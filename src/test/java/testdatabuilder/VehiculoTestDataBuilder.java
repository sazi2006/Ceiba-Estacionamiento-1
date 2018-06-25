package testdatabuilder;

import java.util.Date;

import co.com.ceiba.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final String PLACA = "IJX14E";
	private static final Date FECHA_INGRESO = new Date();
	private static final boolean NO_ESTA_EN_EL_PARQUEADERO = false;
	
	private String placa;
	private Date fechaIngreso;
	private Date fechaSalida;
	private boolean estaEnParqueadero;
	
	public VehiculoTestDataBuilder(String placa, Date fechaIngreso, boolean estaEnParqueadero) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.estaEnParqueadero = estaEnParqueadero;
	}
	
	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.fechaIngreso = FECHA_INGRESO;
		this.estaEnParqueadero = NO_ESTA_EN_EL_PARQUEADERO;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public boolean estaEnParqueadero() {
		return estaEnParqueadero;
	}

	public void setEstaEnParqueadero(boolean estaEnParqueadero) {
		this.estaEnParqueadero = estaEnParqueadero;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public VehiculoTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public VehiculoTestDataBuilder estaEnParqueadero(boolean estaEnParqueadero) {
		this.estaEnParqueadero = estaEnParqueadero;
		return this;
	}

	public Vehiculo build() {
		return new Vehiculo(this.placa, this.fechaIngreso, this.fechaSalida, this.estaEnParqueadero);
	}
	
}
