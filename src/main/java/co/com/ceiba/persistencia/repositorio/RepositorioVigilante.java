package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.EntidadVigilante;

@Repository
public interface RepositorioVigilante extends CrudRepository<EntidadVigilante, Long> {
	
	List<EntidadVigilante> findByParqueadero(long idParqueadero);
	
}