package co.com.ceiba.dominio.controlador;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.servicio.ServicioVigilante;

@RestController
public class VigilanteController {
	
	@Autowired
	private ServicioVigilante servicioVigilante;

    @RequestMapping("/agregar/carro")
    public Object registrarIngresoCarro(@RequestParam(value="placa") String placa) {
    	
    	Date fechaIngreso = new Date();
    	
    	if(servicioVigilante.hayCupoCarro() && servicioVigilante.puedeIngresar(placa, fechaIngreso)) {
    		servicioVigilante.ingresarVehiculo(placa, fechaIngreso);
    		
    		return new Carro(placa, fechaIngreso);
    	}
    	
        return null;
    }
    
    @RequestMapping("/agregar/moto")
    public Object registrarIngresoMoto(@RequestParam(value="placa") String placa,
    		@RequestParam(value="cilindrada") short cilindrada) {
    	
    	Date fechaIngreso = new Date();
    	
    	if(servicioVigilante.hayCupoMoto() && servicioVigilante.puedeIngresar(placa, fechaIngreso)) {
    		servicioVigilante.ingresarVehiculo(placa, cilindrada, fechaIngreso);
    		
    		return new Moto(placa, cilindrada, fechaIngreso);
    	}
    	
    	return null;
    	
        
    }
}


