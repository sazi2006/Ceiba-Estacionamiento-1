package co.com.ceiba.dominio.servicio;

import java.util.Date;

import co.com.ceiba.dominio.Vehiculo;

public interface ServicioVigilante {
	
	public void ingresarVehiculo(Vehiculo vehiculo);
	/*public void ingresarVehiculo(String placa, Date fechaIngreso);
	public void ingresarVehiculo(String placa, short cilindrada, Date fechaIngreso);*/
	
	public boolean verificarCupo(Vehiculo vehiculo);
	
	public boolean puedeIngresar(String placa, Date fechaIngreso);
	
	public void salida(String placa);
	
	public void cobrar(String placa);

}
