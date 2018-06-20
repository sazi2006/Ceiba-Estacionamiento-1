package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.persistencia.entidad.EntidadParqueadero;

@Repository
public interface RepositorioParqueadero extends CrudRepository<EntidadParqueadero, Long> {
    
	EntidadParqueadero findByVigilantes(long vigilanteId);
	
	List<EntidadParqueadero> findByNombre(String nombre);
	
}
