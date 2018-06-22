package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Configuration;

import co.com.ceiba.persistencia.entidad.EntidadCarro;

@Transactional
@Configuration
public interface RepositorioCarro extends RepositorioBaseVehiculo<EntidadCarro> {
	
	EntidadCarro findByPlaca(String placa);
	
	List<EntidadCarro> findByEstaEnParqueadero(boolean estaEnParqueadero);
	
}