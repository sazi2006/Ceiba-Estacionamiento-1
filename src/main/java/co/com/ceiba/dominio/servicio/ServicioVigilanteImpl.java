package co.com.ceiba.dominio.servicio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.persistencia.entidad.EntidadCarro;
import co.com.ceiba.persistencia.entidad.EntidadMoto;
import co.com.ceiba.persistencia.repositorio.RepositorioCarro;
import co.com.ceiba.persistencia.repositorio.RepositorioMoto;

@Service
public class ServicioVigilanteImpl implements ServicioVigilante{
	
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
		return repositorioMoto.count() < 10 ? true : false;
	}
	
	public boolean hayCupoCarro() {
		return repositorioMoto.count() < 20 ? true : false;
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
		return false;
	}
	
	public void salida(String placa) {
		
	}
	
	public void cobrar(String placa) { 
		
	}

}
