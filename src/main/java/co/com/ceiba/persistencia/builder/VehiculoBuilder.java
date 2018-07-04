package co.com.ceiba.persistencia.builder;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.EntidadVehiculo;

public final class VehiculoBuilder {
	private VehiculoBuilder() {}
	
	public static Vehiculo convertirADominio(EntidadVehiculo entidadVehiculo) {
		
		Vehiculo vehiculo = null;
		if(entidadVehiculo != null) {
			
			if(entidadVehiculo.getTipo().equals("Moto")) {
				vehiculo = new Moto(entidadVehiculo.getPlaca(),
						entidadVehiculo.getCilindrada(),
						entidadVehiculo.getFechaIngreso(),
						entidadVehiculo.getFechaSalida(),
						entidadVehiculo.estaEnParqueadero());
			}else if(entidadVehiculo.getTipo().equals("Carro")){
				vehiculo = new Carro(entidadVehiculo.getPlaca(),
						entidadVehiculo.getFechaIngreso(),
						entidadVehiculo.getFechaSalida(),
						entidadVehiculo.estaEnParqueadero());
			}
		}
		return vehiculo;
	}
	
	public static EntidadVehiculo convertirAEntidad(Vehiculo vehiculo) {
		EntidadVehiculo entidadVehiculo = new EntidadVehiculo();
		entidadVehiculo.setPlaca(vehiculo.getPlaca());
		
		entidadVehiculo.setFechaIngreso(vehiculo.getFechaIngreso());
		entidadVehiculo.setFechaSalida(vehiculo.getFechaSalida());
		entidadVehiculo.setEstaEnParqueadero(vehiculo.estaEnParqueadero());
		
		if(vehiculo instanceof Moto) {
			entidadVehiculo.setTipo("Moto");
			entidadVehiculo.setCilindrada(((Moto) vehiculo).getCilindrada());
		}else if(vehiculo instanceof Carro) {
			entidadVehiculo.setTipo("Carro");
		}
		
		return entidadVehiculo;
	}

}
