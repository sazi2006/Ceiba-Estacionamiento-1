package co.com.ceiba.dominio.controlador;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.dominio.respuesta.FormatoRespuesta;
import co.com.ceiba.dominio.servicio.ServicioVigilante;

@RestController
public class VigilanteController {
	
	private static final String EL_VEHICULO_HA_SIDO_REGISTRADO = "El vehiculo ha sido registrado correctamente";
	
	@Autowired
	private ServicioVigilante servicioVigilante;
	

    @RequestMapping(path = "/registrar-ingreso/carro", method = RequestMethod.POST)
    public Object registrarIngresoCarro(@RequestBody Carro carro) {
    	
    	try {
    		servicioVigilante.ingresarVehiculo(carro);
    		return new FormatoRespuesta(EL_VEHICULO_HA_SIDO_REGISTRADO, true, carro, new Date());
    		
    	}catch (IngresoVehiculoExcepcion e) {
    		return new FormatoRespuesta(e.getMessage(), false, new Date());
    	}
    	
    }
    
    @RequestMapping(path = "/registrar-ingreso/moto", method = RequestMethod.POST)
    public Object registrarIngresoMoto(@RequestBody Moto moto) {
    	
    	try {
    		servicioVigilante.ingresarVehiculo(moto);
    		return new FormatoRespuesta(EL_VEHICULO_HA_SIDO_REGISTRADO, true, moto, new Date());
    		
    	}catch (IngresoVehiculoExcepcion e) {
    		return new FormatoRespuesta(e.getMessage(), false, new Date());
    	}
    	
    }
    
    @RequestMapping(path = "/registrar-ingreso/carro", method = RequestMethod.POST)
    public Object registrarSalidaCarro(@RequestBody Carro carro) {
    	
    	try {
    		servicioVigilante.ingresarVehiculo(carro);
    		return new FormatoRespuesta(EL_VEHICULO_HA_SIDO_REGISTRADO, true, carro, new Date());
    		
    	}catch (IngresoVehiculoExcepcion e) {
    		return new FormatoRespuesta(e.getMessage(), false, new Date());
    	}
    	
    }
    
    @RequestMapping(path = "/registrar-salida/moto", method = RequestMethod.POST)
    public Object registrarSalidaMoto(@RequestBody Moto moto) {
    	
    	try {
    		servicioVigilante.ingresarVehiculo(moto);
    		return new FormatoRespuesta(EL_VEHICULO_HA_SIDO_REGISTRADO, true, moto, new Date());
    		
    	}catch (IngresoVehiculoExcepcion e) {
    		return new FormatoRespuesta(e.getMessage(), false, new Date());
    	}
    	
    }
}


