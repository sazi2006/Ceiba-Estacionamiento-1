package co.com.ceiba.dominio.unitaria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.servicio.ServicioVigilanteImpl;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VigilanteTest {
	
	
	
	private static final String PLACA_COMIENZA_CON_A = "AJX14E";
	private static final int MOTOS_EN_PARQUEADERO_MENOR_A_10 = 5;
	private static final int MOTOS_EN_PARQUEADERO_IGUAL_A_10 = 10;
	private static final int CARROS_EN_PARQUEADERO_MENOR_A_20 = 17;
	private static final int CARROS_EN_PARQUEADERO_IGUAL_A_20 = 20;
	
	@Test
	public void hayCupoMotoTest() {
		
		//arrange
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder();
		
		Moto moto = motoTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.obtenerNroMotosEnParqueadero()).thenReturn(MOTOS_EN_PARQUEADERO_MENOR_A_10);
		when(servicioVigilante.verificarCupo(Mockito.any())).thenCallRealMethod();
		
		//act
		boolean hayCupo = servicioVigilante.verificarCupo(moto);
		
		//assert
		assertTrue(hayCupo);
	}
	
	@Test
	public void noHayCupoMotoTest() {
		//arrange
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder();
		
		Moto moto = motoTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.obtenerNroMotosEnParqueadero()).thenReturn(MOTOS_EN_PARQUEADERO_IGUAL_A_10);
		when(servicioVigilante.verificarCupo(Mockito.any())).thenCallRealMethod();
		
		//act
		boolean hayCupo = servicioVigilante.verificarCupo(moto);
		
		//assert
		assertFalse(hayCupo);
	}
	
	@Test
	public void hayCupoCarroTest() {
		//arrange
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder();
		
		Carro carro = carroTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.obtenerNroCarrosEnParqueadero()).thenReturn(CARROS_EN_PARQUEADERO_MENOR_A_20);
		when(servicioVigilante.verificarCupo(Mockito.any())).thenCallRealMethod();
		
		//act
		boolean hayCupo = servicioVigilante.verificarCupo(carro);
		
		//assert
		assertTrue(hayCupo);
	}
	
	@Test
	public void noHayCupoCarroTest() {
		//arrange
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder();
		
		Carro carro = carroTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.obtenerNroCarrosEnParqueadero()).thenReturn(CARROS_EN_PARQUEADERO_IGUAL_A_20);
		when(servicioVigilante.verificarCupo(Mockito.any())).thenCallRealMethod();
		
		//act
		boolean hayCupo = servicioVigilante.verificarCupo(carro);
		
		//assert
		assertFalse(hayCupo);
	}
	
	@Test
	public void puedeIngresarVehiculoConPlacaDifATest() {
		//arrange
		
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso())).thenReturn(true);
		
		//act
		boolean puedeIngresar = servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso());
		
		//assert
		assertTrue(puedeIngresar);
	}
	
	@Test
	public void puedeIngresarVehiculoConPlacaA_DiaHabil() {
		//arrange
		
		Calendar fechaHabil = Calendar.getInstance();
		fechaHabil.set(2018, 6, 21);
		
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
				conPlaca(PLACA_COMIENZA_CON_A).
				conFechaIngreso(fechaHabil.getTime());
		
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso())).thenReturn(true);
		
		//act
		boolean puedeIngresar = servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso());
		
		//assert
		assertTrue(puedeIngresar);
	}
	
	@Test
	public void puedeingresarVehiculoConPlacaA_UnLunesTest() {
		//arrange
		
		Calendar fechaLunes = Calendar.getInstance();
		fechaLunes.set(2018, 6, 18);
		
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
				conPlaca(PLACA_COMIENZA_CON_A).
				conFechaIngreso(fechaLunes.getTime());
		
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso())).thenReturn(false);
		
		//act
		boolean puedeIngresar = servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso());
		
		//assert
		assertFalse(puedeIngresar);
	}
	
	@Test
	public void puedeingresarVehiculoConPlacaA_UnDomingoTest() {
		//arrange
		
		Calendar fechaDomingo = Calendar.getInstance();
		fechaDomingo.set(2018, 6, 17);
		
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
				conPlaca(PLACA_COMIENZA_CON_A).
				conFechaIngreso(fechaDomingo.getTime());
		
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		ServicioVigilanteImpl servicioVigilante = mock(ServicioVigilanteImpl.class);
		
		when(servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso())).thenReturn(false);
		
		//act
		boolean puedeIngresar = servicioVigilante.puedeIngresar(vehiculo.getPlaca(), vehiculo.getFechaIngreso());
		
		//assert
		assertFalse(puedeIngresar);
	}
	
	
}
