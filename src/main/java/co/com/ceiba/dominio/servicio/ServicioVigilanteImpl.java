package co.com.ceiba.dominio.servicio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.persistencia.entidad.EntidadCarro;
import co.com.ceiba.persistencia.entidad.EntidadMoto;
import co.com.ceiba.persistencia.repositorio.RepositorioCarro;
import co.com.ceiba.persistencia.repositorio.RepositorioMoto;

@Service
public class ServicioVigilanteImpl implements ServicioVigilante {
	
	public static final String NO_HAY_CUPO = "No puede ingresar, el parqueadero se encuentra lleno";
	public static final String NO_ESTA_EN_UN_DIA_HABIL = "No puede ingresar porque no está en un dia hábil";
	
	@Autowired
	private RepositorioCarro repositorioCarro;
	
	@Autowired
	private RepositorioMoto repositorioMoto;
	
	
	public void ingresarVehiculo(String placa, Date fechaIngreso) {
		EntidadCarro carro = new EntidadCarro();
		carro.setPlaca(placa);
		carro.setFechaIngreso(fechaIngreso);
		
		repositorioCarro.save(carro);
	}
	
	public void ingresarVehiculo(String placa, short cilindrada, Date fechaIngreso) {
		EntidadMoto moto = new EntidadMoto();
		moto.setPlaca(placa);
		moto.setCilindrada(cilindrada);
		moto.setFechaIngreso(fechaIngreso);
		
		repositorioMoto.save(moto);
	}
	
	public boolean hayCupoMoto() {
		//return repositorioMoto.count() < 10 ? true : false;
		if(repositorioMoto.count() < 10) {
			return true;
		}
		throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
	}
	
	public boolean hayCupoCarro() {
		//return repositorioCarro.count() < 20 ? true : false;
		
		if(repositorioCarro.count() < 20) {
			return true;
		}
		throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
	}
	
	public boolean puedeIngresar(String placa, Date fechaIngreso) {
		
		if(!placa.startsWith("A")) {
			return true;
		}
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaIngreso);
		
		int dia = calendario.get(Calendar.DAY_OF_WEEK);
		
		if (dia == Calendar.SUNDAY || dia == Calendar.MONDAY) {
            return true;
        }
		throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
	}
	
	public void salida(String placa) {
		
	}
	
	public void cobrar(String placa) { 
		
	}

}
