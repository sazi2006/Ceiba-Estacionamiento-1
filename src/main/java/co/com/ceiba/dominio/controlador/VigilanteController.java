package co.com.ceiba.dominio.controlador;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Cobro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.ObtenerVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.SalidaVehiculoExcepcion;
import co.com.ceiba.dominio.respuesta.FormatoRespuesta;
import co.com.ceiba.dominio.respuesta.FormatoRespuestaVehiculo;
import co.com.ceiba.dominio.servicio.ServicioVigilante;

@RestController
public class VigilanteController {
	
	private static final String EL_VEHICULO_HA_SIDO_REGISTRADO = "El vehiculo ha sido registrado correctamente";
	private static final String EL_VEHICULO_SE_HA_RETIRADO_DEL_PARQUEADERO = "El vehiculo ha sido retirado del parqueadero exitosamente";
	public static final String PLACA_EN_USO = "La placa del vehiculo no coincide con el tipo ingresado previamente";
	
	
	private static final boolean ESTADO_OK = true;
	private static final boolean ESTADO_FALLO = false;
	
	private static final String ERROR_AL_INGRESAR_EL_VEHICULO = "Error al tratar de ingresar el vehiculo: ";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VigilanteController.class);
	
	@Autowired
	private ServicioVigilante servicioVigilante;
	
	
	@RequestMapping("/obtener/vehiculo/{placa}")
    public FormatoRespuesta obtenerVehiculo(@PathVariable(value="placa") String placa) {
		
		try {
			Vehiculo vehiculo = servicioVigilante.obtenerVehiculo(placa);
			
			if(vehiculo instanceof Carro) {
				return new FormatoRespuestaVehiculo("OK", ESTADO_OK, (Carro) vehiculo, "Carro");
			}else {
				return new FormatoRespuestaVehiculo("OK", ESTADO_OK, (Moto) vehiculo, "Moto");
			}
			
		}catch (ObtenerVehiculoExcepcion e) {
			LOGGER.error(ERROR_AL_INGRESAR_EL_VEHICULO, e);
			return new FormatoRespuesta(e.getMessage(), ESTADO_FALLO);
		}
			
    }
	

    @RequestMapping(path = "/registrar-ingreso/carro", method = RequestMethod.POST)
    public FormatoRespuesta registrarIngresoCarro(@RequestBody Carro carro) {
    	try {
    		servicioVigilante.ingresarVehiculo(carro);
    		return new FormatoRespuesta(EL_VEHICULO_HA_SIDO_REGISTRADO, ESTADO_OK, carro);
    		
    	}catch (IngresoVehiculoExcepcion | DataAccessException e) {
    		LOGGER.error(ERROR_AL_INGRESAR_EL_VEHICULO, e);
    		return e instanceof DataAccessException ? new FormatoRespuesta(PLACA_EN_USO, ESTADO_FALLO)
    				: new FormatoRespuesta(e.getMessage(), ESTADO_FALLO);
    	}
    	
    }
    
    @RequestMapping(path = "/registrar-ingreso/moto", method = RequestMethod.POST)
    public FormatoRespuesta registrarIngresoMoto(@RequestBody Moto moto) {
    	
    	try {
    		servicioVigilante.ingresarVehiculo(moto);
    		return new FormatoRespuesta(EL_VEHICULO_HA_SIDO_REGISTRADO, ESTADO_OK, moto);
    		
    	}catch (IngresoVehiculoExcepcion | DataAccessException e) {
    		LOGGER.error(ERROR_AL_INGRESAR_EL_VEHICULO, e);
    		return e instanceof DataAccessException ? new FormatoRespuesta(PLACA_EN_USO, ESTADO_FALLO)
    				: new FormatoRespuesta(e.getMessage(), ESTADO_FALLO);
    	}
    	
    }
    
    @RequestMapping(path = "/registrar-salida/vehiculo/{placa}/{fechaSalida}", method = RequestMethod.DELETE)
    public FormatoRespuesta registrarSalidaCarro(@PathVariable("placa") String placa, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable("fechaSalida") Date fechaSalida) {
    	
    	int valorCobro;
    	try {
    		Vehiculo vehiculo = servicioVigilante.retirarVehiculo(placa, fechaSalida);
    		valorCobro = Cobro.generarCobro(vehiculo);
    		
    		return new FormatoRespuesta(EL_VEHICULO_SE_HA_RETIRADO_DEL_PARQUEADERO, ESTADO_OK, valorCobro);
    		
    	}catch (SalidaVehiculoExcepcion e) {
    		LOGGER.error(ERROR_AL_INGRESAR_EL_VEHICULO, e);
    		return new FormatoRespuesta(e.getMessage(), ESTADO_FALLO);
    	}
    	
    }
    
}


