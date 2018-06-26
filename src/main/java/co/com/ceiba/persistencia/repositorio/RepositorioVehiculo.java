package co.com.ceiba.persistencia.repositorio;

import javax.transaction.Transactional;

import co.com.ceiba.persistencia.entidad.EntidadVehiculo;

@Transactional
public interface RepositorioVehiculo extends RepositorioBaseVehiculo<EntidadVehiculo> {
	EntidadVehiculo findByPlaca(String placa);
}