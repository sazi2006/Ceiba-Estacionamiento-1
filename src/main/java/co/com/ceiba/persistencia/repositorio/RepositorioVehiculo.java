package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.persistencia.entidad.EntidadVehiculo;

@Transactional
@Configuration
public interface RepositorioVehiculo extends CrudRepository<EntidadVehiculo, String> {
	
	EntidadVehiculo findByPlaca(String placa);
	
	List<EntidadVehiculo> findByEstaEnParqueadero(boolean estaEnParqueadero);
	List<EntidadVehiculo> findByTipo(String tipo);
	
	@Query("SELECT v FROM Vehiculo v WHERE v.tipo = :tipo and v.estaEnParqueadero = :estaEnParqueadero")
	List<EntidadVehiculo> findByTipoYEstaEnParqueadero(@Param("tipo") String tipo, @Param("estaEnParqueadero") boolean estaEnParqueadero);
}