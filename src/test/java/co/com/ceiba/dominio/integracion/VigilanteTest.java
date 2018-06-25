package co.com.ceiba.dominio.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.excepcion.IngresoVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.SalidaVehiculoExcepcion;
import co.com.ceiba.dominio.servicio.ServicioVigilanteImpl;
import co.com.ceiba.persistencia.builder.CarroBuilder;
import co.com.ceiba.persistencia.builder.MotoBuilder;
import co.com.ceiba.persistencia.entidad.EntidadCarro;
import co.com.ceiba.persistencia.entidad.EntidadMoto;
import co.com.ceiba.persistencia.repositorio.RepositorioCarro;
import co.com.ceiba.persistencia.repositorio.RepositorioMoto;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
//@AutoConfigureMockMvc
public class VigilanteTest {

	private static final String PLACA_MOTO = "IJX14E";
	private static final String PLACA_CARRO = "IHV102";
	private static final String PLACA_COMIENZA_CON_A = "AJX14E";
	
	@Autowired
	private ServicioVigilanteImpl servicioVigilanteImpl;
	
	@Autowired
	private RepositorioMoto repositorioMoto;
	
	@Autowired
	private RepositorioCarro repositorioCarro;
	
	@Test
	@Transactional
	public void ingresarMotoTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA_MOTO);
		
		Moto moto = motoTestDataBuilder.build();
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(moto);
		}catch (IngresoVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertNotNull(repositorioMoto.findByPlaca(moto.getPlaca()));
		
	}
	
	@Test
	@Transactional
	public void ingresarMotoNoHayCupoTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder();
		
		String placa = "IJX14";
				
		for(int i = 0; i < ServicioVigilanteImpl.CUPO_MAX_MOTOS; i++) {
			motoTestDataBuilder = motoTestDataBuilder.conPlaca(placa + i);
			servicioVigilanteImpl.ingresarVehiculo(motoTestDataBuilder.build());
		}
		
		Moto moto = motoTestDataBuilder.conPlaca(PLACA_MOTO).build();
		
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(moto);
			fail();
		}catch (IngresoVehiculoExcepcion e) {
			
			//assert
			assertEquals(ServicioVigilanteImpl.NO_HAY_CUPO, e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void ingresarMotoPlacaA_UnLunesTest() {
		//arrange
		
		//Calendar fechaLunes = Calendar.getInstance();
		//fechaLunes.set(2018, 5, 7);
		
		Date fechaLunes = new Date(1529340521292L);
		
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaLunes);
		
		Moto moto = motoTestDataBuilder.build();
		
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(moto);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
			
		}
		assertNotNull(repositorioMoto.findByPlaca(moto.getPlaca()));
		
	}
	
	@Test
	@Transactional
	public void ingresarMotoPlacaA_UnDomingoTest() {
		//arrange

		// Fecha domingo
		Date fechaDomingo = new Date(1529254121292L);
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaDomingo);
		
		Moto moto = motoTestDataBuilder.build();
		
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(moto);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
		}
		
		assertNotNull(repositorioMoto.findByPlaca(moto.getPlaca()));
		
	}
	
	@Test
	public void ingresarMotoPlacaA_UnDiaHabilTest() {
		//arrange
		
		// Fecha de un miercoles
		Date fechaHabil = new Date(1529513321292L);
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaHabil);
		
		Moto moto = motoTestDataBuilder.build();
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(moto);
			fail();
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			assertEquals(ServicioVigilanteImpl.NO_ESTA_EN_UN_DIA_HABIL, e.getMessage());
		}
		
		
	}
	
	@Test
	@Transactional
	public void ingresarCarroTest() {
		//arrange
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
				conPlaca(PLACA_CARRO);
		
		Carro carro = carroTestDataBuilder.build();
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(carro);
		}catch (IngresoVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertNotNull(repositorioCarro.findByPlaca(carro.getPlaca()));
		
	}
	
	@Test
	@Transactional
	public void ingresarCarroNoHayCupoTest() {
		//arrange
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder();
		
		String placa = "IJX14";
				
		for(int i = 0; i < ServicioVigilanteImpl.CUPO_MAX_CARROS; i++) {
			carroTestDataBuilder = carroTestDataBuilder.conPlaca(placa + i);
			servicioVigilanteImpl.ingresarVehiculo(carroTestDataBuilder.build());
		}
		
		Carro carro = carroTestDataBuilder.conPlaca(PLACA_CARRO).build();
		
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(carro);
			fail();
		}catch (IngresoVehiculoExcepcion e) {
			
			//assert
			assertEquals(ServicioVigilanteImpl.NO_HAY_CUPO, e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void ingresarCarroPlacaA_UnLunesTest() {
		//arrange
		
		Date fechaLunes = new Date(1529340521292L);
		
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaLunes);
		
		Carro carro = carroTestDataBuilder.build();
		
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(carro);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
			
		}
		assertNotNull(repositorioCarro.findByPlaca(carro.getPlaca()));
		
	}
	
	@Test
	@Transactional
	public void ingresarCarroPlacaA_UnDomingoTest() {
		//arrange

		// Fecha domingo
		Date fechaDomingo = new Date(1529254121292L);
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaDomingo);
		
		Carro carro = carroTestDataBuilder.build();
		
		
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(carro);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
		}
		
		assertNotNull(repositorioCarro.findByPlaca(carro.getPlaca()));
		
	}
	
	@Test
	public void ingresarCarroPlacaA_UnDiaHabilTest() {
		//arrange
		
		// Fecha de un miercoles
		Date fechaHabil = new Date(1529513321292L);
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaHabil);
		
		Carro carro = carroTestDataBuilder.build();
		
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(carro);
			fail();
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			assertEquals(ServicioVigilanteImpl.NO_ESTA_EN_UN_DIA_HABIL, e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void retirarMotoTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA_MOTO);
		
		Moto moto = motoTestDataBuilder.build();
		
		EntidadMoto entidadMoto = MotoBuilder.convertirAEntidad(moto);
		entidadMoto.setEstaEnParqueadero(true);
		
		repositorioMoto.save(entidadMoto);
		
		//act
		try {
			servicioVigilanteImpl.retirarVehiculo(moto.getPlaca(), new Date());
		}catch (SalidaVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertFalse(repositorioMoto.findByPlaca(moto.getPlaca()).estaEnParqueadero());
	}
	
	@Test
	@Transactional
	public void retirarMotoNoParqueadaTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA_MOTO);
		
		Moto moto = motoTestDataBuilder.build();
		
		try {
			//act
			servicioVigilanteImpl.retirarVehiculo(moto.getPlaca(), new Date());
			fail();
		}catch (SalidaVehiculoExcepcion e) {
			
			//assert
			assertEquals(ServicioVigilanteImpl.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void retirarCarroTest() {
		//arrange
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
				conPlaca(PLACA_CARRO);
		
		Carro carro = carroTestDataBuilder.build();
		
		EntidadCarro entidadCarro = CarroBuilder.convertirAEntidad(carro);
		entidadCarro.setEstaEnParqueadero(true);
		
		repositorioCarro.save(entidadCarro);
		
		//act
		try {
			servicioVigilanteImpl.retirarVehiculo(carro.getPlaca(), new Date());
		}catch (SalidaVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertFalse(repositorioCarro.findByPlaca(carro.getPlaca()).estaEnParqueadero());
	}
	
	@Test
	@Transactional
	public void retirarCarroNoParqueadaTest() {
		//arrange
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
				conPlaca(PLACA_CARRO);
		
		Carro carro = carroTestDataBuilder.build();
		
		try {
			//act
			servicioVigilanteImpl.retirarVehiculo(carro.getPlaca(), new Date());
			fail();
		}catch (SalidaVehiculoExcepcion e) {
			
			//assert
			assertEquals(ServicioVigilanteImpl.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
		
	}
	
	
}
