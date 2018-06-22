package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Configuration;

import co.com.ceiba.persistencia.entidad.EntidadMoto;

@Transactional
@Configuration
public interface RepositorioMoto extends RepositorioBaseVehiculo<EntidadMoto> {
	EntidadMoto findByPlaca(String placa);
	
	List<EntidadMoto> findByEstaEnParqueadero(boolean estaEnParqueadero);
	
}