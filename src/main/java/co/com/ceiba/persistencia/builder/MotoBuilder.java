package co.com.ceiba.persistencia.builder;

import co.com.ceiba.dominio.Moto;
import co.com.ceiba.persistencia.entidad.EntidadMoto;

public class MotoBuilder {
	private MotoBuilder() {}
	
	public static Moto convertirADominio(EntidadMoto entidadMoto) {
		Moto moto = null;
		if(entidadMoto != null) {
			moto = new Moto(entidadMoto.getPlaca(),
					entidadMoto.getCilindrada(),
					entidadMoto.getFechaIngreso(),
					entidadMoto.getFechaSalida(),
					entidadMoto.estaEnParqueadero());
		}
		return moto;
	}
	
	public static EntidadMoto convertirAEntidad(Moto moto) {
		EntidadMoto entidadMoto = new EntidadMoto();
		entidadMoto.setPlaca(moto.getPlaca());
		entidadMoto.setCilindrada(moto.getCilindrada());
		entidadMoto.setFechaIngreso(moto.getFechaIngreso());
		entidadMoto.setFechaSalida(moto.getFechaSalida());
		entidadMoto.setEstaEnParqueadero(moto.estaEnParqueadero());
		
		return entidadMoto;
	}

}
