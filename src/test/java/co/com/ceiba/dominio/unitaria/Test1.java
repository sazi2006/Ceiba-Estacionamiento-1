package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test1 {

	private static final int a = 1;

	@Test
	public void sumaTest() {
		// arrange
		int b;
		// act
		b = a + 2;

		// assert
		assertEquals(3, b);
	}
	

}
