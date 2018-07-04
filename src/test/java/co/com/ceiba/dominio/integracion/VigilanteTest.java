package co.com.ceiba.dominio.integracion;

import static org.assertj.core.api.Assertions.assertThat;
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
import co.com.ceiba.dominio.excepcion.ObtenerVehiculoExcepcion;
import co.com.ceiba.dominio.excepcion.SalidaVehiculoExcepcion;
import co.com.ceiba.dominio.servicio.ServicioVigilanteImpl;
import co.com.ceiba.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.persistencia.entidad.EntidadVehiculo;
import co.com.ceiba.persistencia.repositorio.RepositorioVehiculo;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
//@AutoConfigureMockMvc
public class VigilanteTest {

	private static final String PLACA_MOTO = "IJX14E";
	private static final String PLACA_CARRO= "IHV102";
	private static final String PLACA_COMIENZA_CON_A = "AJX14E";
	
	@Autowired
	private ServicioVigilanteImpl servicioVigilanteImpl;
	
	@Autowired
	private RepositorioVehiculo repositorioVehiculo;
	
	@Test
	@Transactional
	public void ingresarMotoTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA_MOTO);
		
		Moto moto = motoTestDataBuilder.build();
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
		}catch (IngresoVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertNotNull(repositorioVehiculo.findByPlaca(moto.getPlaca()));
		
	}
	
	@Test
	@Transactional
	public void ingresarMotoNoHayCupoTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder();
		
		String placa = "IJX14";
				
		for(int i = 0; i < ServicioVigilanteImpl.CUPO_MAX_MOTOS; i++) {
			motoTestDataBuilder = motoTestDataBuilder.conPlaca(placa + i);
			
			servicioVigilanteImpl.ingresarVehiculo(VehiculoBuilder.convertirAEntidad(motoTestDataBuilder.build()));
		}
		
		Moto moto = motoTestDataBuilder.conPlaca(PLACA_MOTO).build();
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
			
		}
		assertNotNull(repositorioVehiculo.findByPlaca(moto.getPlaca()));
		
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
		}
		
		assertNotNull(repositorioVehiculo.findByPlaca(moto.getPlaca()));
		
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
		}catch (IngresoVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertNotNull(repositorioVehiculo.findByPlaca(carro.getPlaca()));
		
	}
	
	@Test
	@Transactional
	public void ingresarCarroNoHayCupoTest() {
		//arrange
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder();
		
		String placa = "IJX14";
				
		for(int i = 0; i < ServicioVigilanteImpl.CUPO_MAX_CARROS; i++) {
			carroTestDataBuilder = carroTestDataBuilder.conPlaca(placa + i);
			servicioVigilanteImpl.ingresarVehiculo(VehiculoBuilder.convertirAEntidad(carroTestDataBuilder.build()));
		}
		
		Carro carro = carroTestDataBuilder.conPlaca(PLACA_CARRO).build();
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
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
		
		// Fecha lunes
		Date fechaLunes = new Date(1529340521292L);
		
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder()
				.conPlaca(PLACA_COMIENZA_CON_A)
				.conFechaIngreso(fechaLunes);
		
		Carro carro = carroTestDataBuilder.build();
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
			
		}
		assertNotNull(repositorioVehiculo.findByPlaca(carro.getPlaca()));
		
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		
		try {
			//act
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
			
		}catch (IngresoVehiculoExcepcion e) {
			//assert
			fail();
		}
		
		assertNotNull(repositorioVehiculo.findByPlaca(carro.getPlaca()));
		
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		//act
		try {
			servicioVigilanteImpl.ingresarVehiculo(entidadVehiculo);
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		entidadVehiculo.setEstaEnParqueadero(true);
		
		repositorioVehiculo.save(entidadVehiculo);
		
		//act
		try {
			servicioVigilanteImpl.retirarVehiculo(moto.getPlaca(), new Date());
		}catch (SalidaVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertFalse(repositorioVehiculo.findByPlaca(moto.getPlaca()).estaEnParqueadero());
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
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		entidadVehiculo.setEstaEnParqueadero(true);
		
		repositorioVehiculo.save(entidadVehiculo);
		
		//act
		try {
			servicioVigilanteImpl.retirarVehiculo(carro.getPlaca(), new Date());
		}catch (SalidaVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertFalse(repositorioVehiculo.findByPlaca(carro.getPlaca()).estaEnParqueadero());
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
	
	@Test
	@Transactional
	public void obtenerCarroExistenteTest() {
		//arrange
		
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
				conPlaca(PLACA_CARRO)
				.estaEnParqueadero(true);
		
		Carro carro = carroTestDataBuilder.build();
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(carro);
		
		repositorioVehiculo.save(entidadVehiculo);
		
		Carro carroObtenido = null;
		
		//act
		try {
			carroObtenido = (Carro) servicioVigilanteImpl.obtenerVehiculo(carro.getPlaca());
			
		}catch (ObtenerVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertThat(carro).isEqualToComparingFieldByField(carroObtenido);
	}
	
	@Test
	public void obtenerCarroNoExistenteTest() {
		
		//arrange
		
		try {
			
			//act
			servicioVigilanteImpl.obtenerVehiculo(PLACA_CARRO);
			fail();
		}catch (ObtenerVehiculoExcepcion e) {
			
			//assert
			assertEquals(ServicioVigilanteImpl.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
		
	}
	
	
	@Test
	@Transactional
	public void obtenerMotoExistenteTest() {
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA_CARRO)
				.estaEnParqueadero(true);
		
		Moto moto = motoTestDataBuilder.build();
		
		EntidadVehiculo entidadVehiculo = VehiculoBuilder.convertirAEntidad(moto);
		
		repositorioVehiculo.save(entidadVehiculo);
		
		Moto motoObtenida = null;
		
		//act
		try {
			motoObtenida = (Moto) servicioVigilanteImpl.obtenerVehiculo(moto.getPlaca());
			
		}catch (ObtenerVehiculoExcepcion e) {
			fail();
		}
		
		//assert
		assertThat(moto).isEqualToComparingFieldByField(motoObtenida);
	}
	
	@Test
	public void obtenerMotoNoExistenteTest() {
		
		//arrange
		
		try {
			
			//act
			servicioVigilanteImpl.obtenerVehiculo(PLACA_CARRO);
			fail();
		}catch (ObtenerVehiculoExcepcion e) {
			
			//assert
			assertEquals(ServicioVigilanteImpl.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
		
	}
	
	
}
