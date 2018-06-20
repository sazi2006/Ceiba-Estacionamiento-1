package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.VigilanteEntity;

@Repository
public interface RepositorioVigilante extends CrudRepository<VigilanteEntity, Long> {
	
	List<VigilanteEntity> findByParqueadero(long idParqueadero);
	
}