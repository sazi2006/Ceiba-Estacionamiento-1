package co.com.ceiba.dominio.funcional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class FlujoSalidaVehiculosTest {
	
	private static final String EL_VEHICULO_SE_HA_RETIRADO_DEL_PARQUEADERO = "El vehiculo ha sido retirado del parqueadero exitosamente";
	
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
	public void comprobarRegistroSalidaMoto() {
		driver.get("http://localhost:8080/");
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Moto");
		
		WebElement placa1 = driver.findElement(By.id("placa"));
		placa1.sendKeys("IJX14E");
		
		WebElement cilindrada1 = driver.findElement(By.id("cilindraje"));
		cilindrada1.sendKeys("180");
		
		WebElement btnEnviar1 = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar1.click();
		
		WebDriverWait msgExitoWait = new WebDriverWait(driver, 5);
		msgExitoWait.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		WebElement btnRegistrarSalida = driver.findElement(By.id("dir_salida"));
		btnRegistrarSalida.click();
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IJX14E");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarSalidaVehiculo"));
		btnEnviar.click();
		
		WebElement mensajeExito = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgExitoSalida")));
		
		WebElement valorCobro = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("valorCobro")));
		
		assertEquals(EL_VEHICULO_SE_HA_RETIRADO_DEL_PARQUEADERO, mensajeExito.getText());
		assertTrue(Integer.parseInt(valorCobro.getText()) >= 0);
		
		
	}
	
	@Test
	@Transactional
	public void comprobarRegistroSalidaCarro() {
		driver.get("http://localhost:8080/");
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Carro");
		
		WebElement placa1 = driver.findElement(By.id("placa"));
		placa1.sendKeys("IHV102");
		
		WebElement btnEnviar1 = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar1.click();
		
		WebDriverWait msgExitoWait = new WebDriverWait(driver, 5);
		msgExitoWait.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		WebElement btnRegistrarSalida = driver.findElement(By.id("dir_salida"));
		btnRegistrarSalida.click();
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IHV102");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarSalidaVehiculo"));
		btnEnviar.click();
		
		WebElement mensajeExito = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgExitoSalida")));
		
		WebElement valorCobro = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("valorCobro")));
		
		assertEquals(EL_VEHICULO_SE_HA_RETIRADO_DEL_PARQUEADERO, mensajeExito.getText());
		assertTrue(Integer.parseInt(valorCobro.getText()) >= 0);
		
		
	}
}
