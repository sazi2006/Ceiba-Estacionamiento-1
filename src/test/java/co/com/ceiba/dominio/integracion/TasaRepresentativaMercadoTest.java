package co.com.ceiba.dominio.integracion;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.dominio.excepcion.ObtenerTRMExcepcion;
import co.com.ceiba.dominio.trm.TasaRepresentativaMercado;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TasaRepresentativaMercadoTest {
	
	@Test
	public void obtenerTrm() {
		
		//arrange
		TasaRepresentativaMercado trm = new TasaRepresentativaMercado();
		try {
			
			//act
			trm.construirTRM();
		}catch(ObtenerTRMExcepcion e) {
			fail();
		}
		
		//assert
		assertNotNull(trm.getValor());
		
	}
	
	@Test
	public void obtenerTrmPorFecha() {
		
		//arrange
		TasaRepresentativaMercado trm = new TasaRepresentativaMercado();
		try {
			
			//act
			trm.construirTRMPorFecha(new Date());
		}catch(ObtenerTRMExcepcion | ParseException e) {
			fail();
		}
		
		//assert
		assertNotNull(trm.getValor());
		
	}
}
