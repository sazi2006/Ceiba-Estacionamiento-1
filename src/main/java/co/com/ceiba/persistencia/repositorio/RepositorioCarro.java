package co.com.ceiba.persistencia.repositorio;

import javax.transaction.Transactional;

import co.com.ceiba.persistencia.entidad.EntidadCarro;

@Transactional
public interface RepositorioCarro extends RepositorioBaseVehiculo<EntidadCarro> { /* ... */ }