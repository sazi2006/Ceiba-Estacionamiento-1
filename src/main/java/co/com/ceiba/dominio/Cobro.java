package co.com.ceiba.dominio;

public final class Cobro {
	
	private Cobro() {}
	
	private static final int VALOR_HORA_CARRO = 1000;
	private static final int VALOR_HORA_MOTO = 500;
	private static final int VALOR_DIA_CARRO = 8000;
	private static final int VALOR_DIA_MOTO = 4000;
	
	
	public static int generarCobro(Vehiculo vehiculo) {
		double fechaIngreso = (double) vehiculo.getFechaIngreso().getTime();
		double fechaSalida = (double) vehiculo.getFechaSalida().getTime();
		
		double horas = Math.ceil((fechaSalida - fechaIngreso) / 1000 / 60 / 60);
		
		int horasTotales = (int) horas;
		
		int cobroTotal = 0;
		
		if(vehiculo instanceof Moto) {
			
			cobroTotal = calcularCobroTotal(horasTotales, ((Moto) vehiculo).getCilindrada());
			
		}else if(vehiculo instanceof Carro) {
			
			cobroTotal = calcularCobroTotal(horasTotales, null);
		}
		
		return cobroTotal;
	}
	
	public static int calcularCobroTotal(int nroHoras, Short cilindrada) {
		int diasAcum = 0;
		int horasAcum = 0;
		int valorHora;
		int valorDia;
		boolean estaEnDia = false;
		int contDia = 1;
		
		if (cilindrada == null) {
			valorHora = VALOR_HORA_CARRO;
			valorDia = VALOR_DIA_CARRO;
		}else {
			valorHora = VALOR_HORA_MOTO;
			valorDia = VALOR_DIA_MOTO;
		}
		for(int i = 1; i <= nroHoras; i++) {
			if(!estaEnDia) {
				horasAcum = horasAcum + valorHora;
			}
			if(contDia == 9 && !estaEnDia) {
				diasAcum = diasAcum + valorDia;
				horasAcum = 0;
				estaEnDia =  true;
			}
			if(contDia == 24) {
				estaEnDia = false;
				contDia = 0;
			}
			contDia++;
		}
		
		return cilindrada != null && cilindrada > 500 ? horasAcum + diasAcum + 2000 : horasAcum + diasAcum;
	}
}
