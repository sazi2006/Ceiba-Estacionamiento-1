package co.com.ceiba.persistencia.builder;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.persistencia.entidad.EntidadCarro;

public class CarroBuilder {
	private CarroBuilder() {}
	
	public static Carro convertirADominio(EntidadCarro entidadCarro) {
		Carro carro = null;
		if(entidadCarro != null) {
			carro = new Carro(entidadCarro.getPlaca(), entidadCarro.getFechaIngreso(), null, entidadCarro.estaEnParqueadero());
		}
		return carro;
	}
	
	public static EntidadCarro convertirAEntidad(Carro carro) {
		EntidadCarro entidadCarro = new EntidadCarro();
		entidadCarro.setPlaca(carro.getPlaca());
		entidadCarro.setFechaIngreso(carro.getFechaIngreso());
		entidadCarro.setEstaEnParqueadero(carro.estaEnParqueadero());
		
		return entidadCarro;
	}

}
