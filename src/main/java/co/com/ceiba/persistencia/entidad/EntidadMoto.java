package co.com.ceiba.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Moto")
public class EntidadMoto extends EntidadVehiculo{
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private short cilindrada;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public short getCilindrada() {
		return cilindrada;
	}
	
	public void setCilindrada(short cilindrada) {
		this.cilindrada = cilindrada;
	}
}
