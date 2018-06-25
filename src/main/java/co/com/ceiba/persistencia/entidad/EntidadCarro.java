package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Carro")
public class EntidadCarro extends EntidadVehiculo{
	
	@Id
	@Column(nullable = false)
	private String placa;
	
	@Override
	public String getPlaca() {
		return placa;
	}
	
	@Override
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
