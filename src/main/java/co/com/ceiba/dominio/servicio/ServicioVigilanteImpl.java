package co.com.ceiba.dominio.servicio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.ObtenerVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.SalidaVehiculoExcepcion;
import co.com.ceiba.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.persistencia.entidad.EntidadVehiculo;
import co.com.ceiba.persistencia.repositorio.RepositorioVehiculo;

@Service
@Configuration
public class ServicioVigilanteImpl implements ServicioVigilante {
	
	public static final String NO_HAY_CUPO = "No puede ingresar, el parqueadero se encuentra lleno";
	public static final String NO_ESTA_EN_UN_DIA_HABIL = "No puede ingresar porque no esta en un dia habil";
	public static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo solicitado no se encuentra en el parqueadero";
	public static final String EL_VEHICULO_YA_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo ya se encuentra en el parqueadero";
	
	public static final int CUPO_MAX_MOTOS = 10;
	public static final int CUPO_MAX_CARROS = 20;
	private static final boolean SE_ENCUENTRA_EN_EL_PARQUEADERO = true;
	private static final boolean NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = false;
	
	@Autowired
	private RepositorioVehiculo repositorioVehiculo;
	
	public void ingresarVehiculo(EntidadVehiculo entidadVehiculo){
		
		EntidadVehiculo result = repositorioVehiculo.findByPlaca(entidadVehiculo.getPlaca());
		
		if(result != null && result.estaEnParqueadero()) {
			throw new IngresoVehiculoExcepcion(EL_VEHICULO_YA_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		
		entidadVehiculo.setEstaEnParqueadero(SE_ENCUENTRA_EN_EL_PARQUEADERO);
		
		if(!verificarCupo(entidadVehiculo.getTipo())) {
			throw new IngresoVehiculoExcepcion(NO_HAY_CUPO);
		}else if(!puedeIngresar(entidadVehiculo.getPlaca(), entidadVehiculo.getFechaIngreso())){
			throw new IngresoVehiculoExcepcion(NO_ESTA_EN_UN_DIA_HABIL);
		}
	
		repositorioVehiculo.save(entidadVehiculo);

	
	}
	
	public boolean verificarCupo(String tipo) {
		if(tipo.equals("Moto")) {
			return obtenerNroMotosEnParqueadero() < CUPO_MAX_MOTOS;
		}else {
			return obtenerNroCarrosEnParqueadero() < CUPO_MAX_CARROS;
		}
	}
	
	public int obtenerNroMotosEnParqueadero() {
		return repositorioVehiculo.findByTipoYEstaEnParqueadero("Moto", true).size();
	}
	
	public int obtenerNroCarrosEnParqueadero() {
		return repositorioVehiculo.findByTipoYEstaEnParqueadero("Carro", true).size();
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
		
		EntidadVehiculo entidadVehiculo = repositorioVehiculo.findByPlaca(placa);
		
		if(entidadVehiculo != null && entidadVehiculo.estaEnParqueadero()) {
			entidadVehiculo.setEstaEnParqueadero(NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
			entidadVehiculo.setFechaSalida(fechaSalida);
			repositorioVehiculo.save(entidadVehiculo);
			return VehiculoBuilder.convertirADominio(entidadVehiculo);
			
		}else {
			throw new SalidaVehiculoExcepcion(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}
	
	public Vehiculo obtenerVehiculo(String placa) {
		
		EntidadVehiculo entidadVehiculo = repositorioVehiculo.findByPlaca(placa);
		
		if(entidadVehiculo != null && entidadVehiculo.estaEnParqueadero()) {
			return VehiculoBuilder.convertirADominio(entidadVehiculo);
		}else {
			throw new ObtenerVehiculoExcepcion(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		
		
	}
}
