package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.dominio.Moto;
import testdatabuilder.MotoTestDataBuilder;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class MotoTest {
	
	private static final String PLACA = "IHV1O2";
	private static final short CILINDRADA = 220;
	private static final Date FECHA_INGRESO = new Date();
	
	@Test
	public void crearMotoTest() {
		
		//arrange
		
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA).
				conCilindrada(CILINDRADA).
				conFechaIngreso(FECHA_INGRESO);
		
		//act
		Moto moto = motoTestDataBuilder.build();
		
		//assert
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(CILINDRADA, moto.getCilindrada());
		assertEquals(FECHA_INGRESO, moto.getFechaIngreso());
		
	}
	

}
