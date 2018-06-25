package co.com.ceiba.dominio.servicio;

import java.util.Date;

import co.com.ceiba.dominio.Vehiculo;

public interface ServicioVigilante {
	
	public void ingresarVehiculo(Vehiculo vehiculo);
	
	public boolean verificarCupo(Vehiculo vehiculo);
	
	public boolean puedeIngresar(String placa, Date fechaIngreso);
	

}
