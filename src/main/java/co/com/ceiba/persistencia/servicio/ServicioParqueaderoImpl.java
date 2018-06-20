package co.com.ceiba.persistencia.servicio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.persistencia.entidad.ParqueaderoEntity;
import co.com.ceiba.persistencia.entidad.VigilanteEntity;

import co.com.ceiba.persistencia.repositorio.RepositorioParqueadero;
import co.com.ceiba.persistencia.repositorio.RepositorioVigilante;

@Service
public class ServicioParqueaderoImpl implements ServicioParqueadero {
	
    @Autowired
    private RepositorioVigilante repositorioVigilante;
    @Autowired
    private RepositorioParqueadero repositorioParqueadero;
    
	public List<String> getAllVigilantes(long idParqueadero) {
		List<String> result = new ArrayList<String>();
        List<VigilanteEntity> vigilantes = repositorioVigilante.findByParqueadero(idParqueadero);
        for (VigilanteEntity vigilante : vigilantes) {
            result.add(vigilante.getNombres());
        }
        return result;
	}
	
	public void addVigilante(String docIdentidad, String nombres, String apellidos) {
		ParqueaderoEntity parqueadero = repositorioParqueadero.findById(1l).orElse(null);;
		
        VigilanteEntity nuevoVigilante = new VigilanteEntity();
        nuevoVigilante.setNombres(nombres);
        nuevoVigilante.setApellidos(apellidos);
        repositorioVigilante.save(nuevoVigilante);
		
	}
}