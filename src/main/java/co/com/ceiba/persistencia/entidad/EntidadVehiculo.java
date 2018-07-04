package co.com.ceiba.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Vehiculo")
public class EntidadVehiculo {
	
	@Id
	@Column(nullable = false)
	private String placa;
	
	@Column(nullable = false)
	private String tipo;
	
	@Column(nullable = false)
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private boolean estaEnParqueadero;
	
	private short cilindrada;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public boolean estaEnParqueadero() {
		return estaEnParqueadero;
	}

	public void setEstaEnParqueadero(boolean estaEnParqueadero) {
		this.estaEnParqueadero = estaEnParqueadero;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public short getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(short cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	

}
