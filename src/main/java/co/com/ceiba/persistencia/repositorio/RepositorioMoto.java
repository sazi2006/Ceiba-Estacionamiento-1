package co.com.ceiba.persistencia.repositorio;

import javax.transaction.Transactional;

import co.com.ceiba.persistencia.entidad.EntidadMoto;

@Transactional
public interface RepositorioMoto extends RepositorioBaseVehiculo<EntidadMoto> { /* ... */ }