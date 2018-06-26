package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Moto")
public class EntidadMoto extends EntidadVehiculo{
	
	@Id
	@Column(nullable = false)
	private String placa;
	
	private short cilindrada;
	
	@Override
	public String getPlaca() {
		return placa;
	}
	
	@Override
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public short getCilindrada() {
		return cilindrada;
	}
	
	public void setCilindrada(short cilindrada) {
		this.cilindrada = cilindrada;
	}
}
