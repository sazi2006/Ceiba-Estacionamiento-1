package co.com.ceiba.dominio.funcional;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class FlujoFallidoSalidaVehiculos {
	
	private static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo solicitado no se encuentra en el parqueadero";
	
	
	private static WebDriver driver = null;
	
	@BeforeClass
	public static void inicializarDriver() {
		//System.setProperty("webdriver.gecko.driver", "libs\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "libs\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void destruirDriver() {
		driver.quit();
	}
	
	
	@Test
	@Transactional
	public void comprobarRegistroFallidoSalidaMoto() {
		driver.get("http://localhost:8080/");
		
		WebElement btnRegistrarSalida = driver.findElement(By.id("dir_salida"));
		btnRegistrarSalida.click();
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IJX14E");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarSalidaVehiculo"));
		btnEnviar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgErrorSalida")));
		
		assertEquals(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
	
	@Test
	@Transactional
	public void comprobarRegistroFallidoSalidaCarro() {
		driver.get("http://localhost:8080/");
		
		WebElement btnRegistrarSalida = driver.findElement(By.id("dir_salida"));
		btnRegistrarSalida.click();
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IHV102");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarSalidaVehiculo"));
		btnEnviar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgErrorSalida")));
		
		assertEquals(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
}
