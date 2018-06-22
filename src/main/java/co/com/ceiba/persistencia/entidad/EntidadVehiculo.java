package co.com.ceiba.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity(name="Vehiculo")
@Inheritance
public abstract class EntidadVehiculo {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String placa;
	
	@Column(nullable = false)
	private Date fechaIngreso;
	
	private boolean estaEnParqueadero;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public boolean estaEnParqueadero() {
		return estaEnParqueadero;
	}

	public void setEstaEnParqueadero(boolean estaEnParqueadero) {
		this.estaEnParqueadero = estaEnParqueadero;
	}
	
	

}
