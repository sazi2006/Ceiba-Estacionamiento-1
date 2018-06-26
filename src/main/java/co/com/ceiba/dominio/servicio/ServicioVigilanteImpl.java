package co.com.ceiba.dominio.servicio;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.ObtenerVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.SalidaVehiculoExcepcion;
import co.com.ceiba.persistencia.builder.CarroBuilder;
import co.com.ceiba.persistencia.builder.MotoBuilder;
import co.com.ceiba.persistencia.entidad.EntidadCarro;
import co.com.ceiba.persistencia.entidad.EntidadMoto;
import co.com.ceiba.persistencia.repositorio.RepositorioCarro;
import co.com.ceiba.persistencia.repositorio.RepositorioMoto;

@Service
@Configuration
public class ServicioVigilanteImpl implements ServicioVigilante {
	
	public static final String NO_HAY_CUPO = "No puede ingresar, el parqueadero se encuentra lleno";
	public static final String NO_ESTA_EN_UN_DIA_HABIL = "No puede ingresar porque no está en un dia hábil";
	public static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo solicitado no se encuentra en el parqueadero";
	public static final String PLACA_EN_USO = "El vehiculo ya se encuentra en el parqueadero";
	
	public static final int CUPO_MAX_MOTOS = 10;
	public static final int CUPO_MAX_CARROS = 20;
	private static final boolean SE_ENCUENTRA_EN_EL_PARQUEADERO = true;
	private static final boolean NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = false;
	
	@Autowired
	private RepositorioCarro repositorioCarro;
	
	@Autowired
	private RepositorioMoto repositorioMoto;
	
	public void ingresarVehiculo(Vehiculo vehiculo) {
		
		EntidadCarro carro = null;
		EntidadMoto moto = null;
		
		if(vehiculo instanceof Moto) {
			Moto vehiculoEsp = (Moto) vehiculo;
			moto = MotoBuilder.convertirAEntidad(vehiculoEsp);
			moto.setEstaEnParqueadero(SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}else if(vehiculo instanceof Carro) {
			Carro vehiculoEsp = (Carro) vehiculo;
			carro = CarroBuilder.convertirAEntidad(vehiculoEsp);
			carro.setEstaEnParqueadero(SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		
		if(!verificarCupo(vehiculo)) {
			throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
		}else if(!puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso())){
			throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
		}
		
		try {
			if(carro != null) {
				repositorioCarro.save(carro);
			}else if(moto != null){
				repositorioMoto.save(moto);
			}
		}catch (ConstraintViolationException e) {
			throw new IngresoVehiculoExcepcion(PLACA_EN_USO);
		}
		
	}
	
	public boolean verificarCupo(Vehiculo vehiculo) {
		if(vehiculo instanceof Moto) {
			return obtenerNroMotosEnParqueadero() < CUPO_MAX_MOTOS;
		}else {
			return obtenerNroCarrosEnParqueadero() < CUPO_MAX_CARROS;
		}
	}
	
	public int obtenerNroMotosEnParqueadero() {
		return repositorioMoto.findByEstaEnParqueadero(true).size();
	}
	
	public int obtenerNroCarrosEnParqueadero() {
		return repositorioCarro.findByEstaEnParqueadero(true).size();
	}
	
	public boolean puedeIngresar(String placa, Date fechaIngreso) {
		
		if(!placa.startsWith("A")) {
			return true;
		}
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaIngreso);
		
		int dia = calendario.get(Calendar.DAY_OF_WEEK);
		
		
		return dia == Calendar.SUNDAY || dia == Calendar.MONDAY;
		
	}
	
	public Vehiculo retirarVehiculo(String placa, Date fechaSalida) {
		
		EntidadCarro carro = repositorioCarro.findByPlaca(placa);
		EntidadMoto moto = repositorioMoto.findByPlaca(placa);
		
		if(moto != null && moto.estaEnParqueadero()) {
			moto.setEstaEnParqueadero(NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
			moto.setFechaSalida(fechaSalida);
			repositorioMoto.save(moto);
			return MotoBuilder.convertirADominio(moto);
			
		}else if(carro != null && carro.estaEnParqueadero()) {
			carro.setEstaEnParqueadero(NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
			carro.setFechaSalida(fechaSalida);
			repositorioCarro.save(carro);
			return CarroBuilder.convertirADominio(carro);
			
		}else {
			throw new SalidaVehiculoExcepcion(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}
	
	public Vehiculo obtenerVehiculo(String placa) {
		
		EntidadCarro carro = repositorioCarro.findByPlaca(placa);
		EntidadMoto moto = repositorioMoto.findByPlaca(placa);
		
		if(moto != null && moto.estaEnParqueadero()) {
			return MotoBuilder.convertirADominio(moto);
			
		}else if(carro != null && carro.estaEnParqueadero()) {
			return CarroBuilder.convertirADominio(carro);
			
		}else {
			throw new ObtenerVehiculoExcepcion(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		
		
	}
}
