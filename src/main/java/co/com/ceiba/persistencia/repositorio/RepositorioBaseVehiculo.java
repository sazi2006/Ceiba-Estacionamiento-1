package co.com.ceiba.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import co.com.ceiba.persistencia.entidad.EntidadVehiculo;

@NoRepositoryBean
public interface RepositorioBaseVehiculo<T extends EntidadVehiculo>  extends CrudRepository<T, Long> {

  public T findByPlaca(String placa);
 
}
