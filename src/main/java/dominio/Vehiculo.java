package dominio;

public class Vehiculo {
	private short cilindrada;
	private String placa;
	
	public Vehiculo(short cilindrada, String placa) {
		this.cilindrada = cilindrada;
		this.placa = placa;
	}
	
	public short getCilindrada() {
		return cilindrada;
	}
	
	public String getPlaca() {
		return placa;
	}
}
