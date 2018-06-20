package co.com.ceiba.persistencia.servicio;

import java.util.List;

public interface ServicioParqueadero {
	
    public List<String> getAllVigilantes(long idParqueadero);
	public void addVigilante(String docIdentidad, String nombres, String apellidos);
}
