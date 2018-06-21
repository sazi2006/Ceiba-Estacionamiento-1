package co.com.ceiba.dominio.servicio;

import java.util.Date;

public interface ServicioVigilante {
	
	public void ingresarVehiculo(String placa, Date fechaIngreso);
	public void ingresarVehiculo(String placa, short cilindrada, Date fechaIngreso);
	
	public boolean hayCupoMoto();
	public boolean hayCupoCarro();
	
	public boolean puedeIngresar(String placa, Date fechaIngreso);
	
	public void salida(String placa);
	
	public void cobrar(String placa);

}
