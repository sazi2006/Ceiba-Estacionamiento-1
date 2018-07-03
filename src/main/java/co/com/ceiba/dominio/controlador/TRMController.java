package co.com.ceiba.dominio.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.excepcion.ObtenerTRMExcepcion;
import co.com.ceiba.dominio.respuesta.FormatoRespuesta;
import co.com.ceiba.dominio.trm.TasaRepresentativaMercado;

@RestController
public class TRMController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TRMController.class);
	
	private static final boolean ESTADO_OK = true;
	private static final boolean ESTADO_FALLO = false;
	
	private static final String OPERACION_EXITOSA = "Operacion exitosa";
	private static final String OPERACION_FALLIDA = "Operacion fallida";
	
	
	@RequestMapping("/obtener/trm")
    public FormatoRespuesta obtenerVehiculo(){
		
		TasaRepresentativaMercado trm;
		try {
			trm = new TasaRepresentativaMercado().construirTRM(); 
		}catch(ObtenerTRMExcepcion e) {
			LOGGER.error(OPERACION_FALLIDA, e);
			return new FormatoRespuesta(e.getMessage(), ESTADO_FALLO);
		}
		
		return new FormatoRespuesta(OPERACION_EXITOSA, ESTADO_OK, trm);
    }
    
}


