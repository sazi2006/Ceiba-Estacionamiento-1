package co.com.ceiba.dominio.servicio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.persistencia.entidad.EntidadCarro;
import co.com.ceiba.persistencia.entidad.EntidadMoto;
import co.com.ceiba.persistencia.repositorio.RepositorioCarro;
import co.com.ceiba.persistencia.repositorio.RepositorioMoto;

@Service
@Configuration
public class ServicioVigilanteImpl implements ServicioVigilante {
	
	public static final String NO_HAY_CUPO = "No puede ingresar, el parqueadero se encuentra lleno";
	public static final String NO_ESTA_EN_UN_DIA_HABIL = "No puede ingresar porque no está en un dia hábil";
	
	public static final int CUPO_MAX_MOTOS = 10;
	public static final int CUPO_MAX_CARROS = 20;
	
	@Autowired
	private RepositorioCarro repositorioCarro;
	
	@Autowired
	private RepositorioMoto repositorioMoto;
	
	public void ingresarVehiculo(Vehiculo vehiculo) {
		
		EntidadCarro carro = null;
		EntidadMoto moto = null;
		
		if(vehiculo instanceof Moto) {
			Moto vehiculoEsp = (Moto) vehiculo;
			moto = new EntidadMoto();
			moto.setPlaca(vehiculoEsp.getPlaca());
			moto.setCilindrada(vehiculoEsp.getCilindrada());
			moto.setFechaIngreso(vehiculoEsp.getFechaIngreso());
			moto.setEstaEnParqueadero(true);
		}else if(vehiculo instanceof Carro) {
			Carro vehiculoEsp = (Carro) vehiculo;
			carro = new EntidadCarro();
			carro.setPlaca(vehiculoEsp.getPlaca());
			carro.setFechaIngreso(vehiculoEsp.getFechaIngreso());
			carro.setEstaEnParqueadero(true);
		}
		
		if(!verificarCupo(vehiculo)) {
			throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
		}else if(!puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso())){
			throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
		}
		
		if(carro != null) {
			repositorioCarro.save(carro);
		}else if(moto != null){
			repositorioMoto.save(moto);
		}
		
		
	}
	
	/*public void ingresarVehiculo(String placa, Date fechaIngreso) {
		EntidadCarro carro = new EntidadCarro();
		carro.setPlaca(placa);
		carro.setFechaIngreso(fechaIngreso);
		carro.setEstaEnParqueadero(true);
		
		if(!hayCupoCarro()) {
			throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
		}else if(!puedeIngresar(carro.getPlaca(), carro.getFechaIngreso())){
			throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
		}
		
		repositorioCarro.save(carro);
	}
	
	public void ingresarVehiculo(String placa, short cilindrada, Date fechaIngreso) {
		EntidadMoto moto = new EntidadMoto();
		moto.setPlaca(placa);
		moto.setCilindrada(cilindrada);
		moto.setFechaIngreso(fechaIngreso);
		moto.setEstaEnParqueadero(true);
		
		if(!hayCupoCarro()) {
			throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
		}else if(!puedeIngresar(moto.getPlaca(), moto.getFechaIngreso())){
			throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
		}
		
		repositorioMoto.save(moto);
	}*/
	
	public boolean verificarCupo(Vehiculo vehiculo) {
		if(vehiculo instanceof Moto) {
			//System.out.println(obtenerNroMotosEnParqueadero());
			return obtenerNroMotosEnParqueadero() < CUPO_MAX_MOTOS ? true : false;
		}else {
			return obtenerNroCarrosEnParqueadero() < CUPO_MAX_CARROS ? true : false;
		}
	}
	
	public int obtenerNroMotosEnParqueadero() {
		return repositorioMoto.findByEstaEnParqueadero(true).size();
	}
	
	public int obtenerNroCarrosEnParqueadero() {
		return repositorioCarro.findByEstaEnParqueadero(true).size();
	}
	
	/*public boolean hayCupoCarro() {
		return repositorioCarro.findByEstaEnParqueadero(true).size() < 20 ? true : false;
		
		
	}*/
	
	public boolean puedeIngresar(String placa, Date fechaIngreso) throws IngresoVehiculoExcepcion {
		
		if(!placa.startsWith("A")) {
			return true;
		}
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaIngreso);
		
		System.out.println(calendario.toString());
		
		int dia = calendario.get(Calendar.DAY_OF_WEEK);
		
		if (dia == Calendar.SUNDAY || dia == Calendar.MONDAY) {
            return true;
        }
		
		return false;
		
		//throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
	}
	
	public void salida(String placa) {
		
	}
	
	public void cobrar(String placa) { 
		
	}

}
