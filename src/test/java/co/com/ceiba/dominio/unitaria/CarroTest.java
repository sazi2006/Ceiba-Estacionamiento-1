package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.dominio.Carro;
import testdatabuilder.CarroTestDataBuilder;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class CarroTest {
	
	private static final String PLACA = "IJX14D";
	private static final Date FECHA_INGRESO = new Date();
	
	@Test
	public void crearCarroTest() {
		//arrange
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
				conPlaca(PLACA).
				conFechaIngreso(FECHA_INGRESO);
		
		//act
		
		Carro carro = carroTestDataBuilder.build();
		
		//assert
		
		assertEquals(PLACA, carro.getPlaca());
		assertEquals(FECHA_INGRESO, carro.getFechaIngreso());
		
	}

}
