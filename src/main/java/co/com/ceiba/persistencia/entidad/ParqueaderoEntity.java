package co.com.ceiba.persistencia.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="Parqueadero")
public class ParqueaderoEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	private String direccion;
	
	@Column(nullable = false)
	private short nroCeldas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parqueadero")
    private List<VigilanteEntity> vigilantes;
	
	protected ParqueaderoEntity() {}
	
	public ParqueaderoEntity(String nombre, short nroCeldas) {
		this.nombre = nombre;
		this.nroCeldas = nroCeldas;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public short getNroCeldas() {
		return nroCeldas;
	}
	
	public void getNroCeldas(short nroCeldas) {
		this.nroCeldas = nroCeldas;
	}
}
