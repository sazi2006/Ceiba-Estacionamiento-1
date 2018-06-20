package co.com.ceiba.persistencia.servicio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.persistencia.entidad.EntidadParqueadero;
import co.com.ceiba.persistencia.entidad.EntidadVigilante;

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
        List<EntidadVigilante> vigilantes = repositorioVigilante.findByParqueadero(idParqueadero);
        for (EntidadVigilante vigilante : vigilantes) {
            result.add(vigilante.getNombres());
        }
        return result;
	}
	
	public void addVigilante(String docIdentidad, String nombres, String apellidos) {
		EntidadParqueadero parqueadero = repositorioParqueadero.findById(1l).orElse(null);;
		
        EntidadVigilante nuevoVigilante = new EntidadVigilante();
        nuevoVigilante.setNombres(nombres);
        nuevoVigilante.setApellidos(apellidos);
        repositorioVigilante.save(nuevoVigilante);
		
	}
}