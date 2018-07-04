package co.com.ceiba.dominio.servicio;

import java.util.Date;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.EntidadVehiculo;

public interface ServicioVigilante {
	
	public void ingresarVehiculo(EntidadVehiculo entidadVehiculo);
	
	public boolean verificarCupo(String tipo);
	
	public boolean puedeIngresar(String placa, Date fechaIngreso);
	
	public Vehiculo retirarVehiculo(String placa, Date fechaSalida);
	
	public Vehiculo obtenerVehiculo(String placa);
	

}
