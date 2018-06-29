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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class FlujoFallidoIngresoVehiculos {
	
	private static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo ya se encuentra en el parqueadero";
	
	
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
	public void comprobarRegistroFallidoIngresoMoto() {
		driver.get("http://localhost:8080/");
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Moto");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IJX14E");
		
		WebElement cilindrada = driver.findElement(By.id("cilindraje"));
		cilindrada.sendKeys("180");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar.click();
		
		WebDriverWait mensajeExitoWait = new WebDriverWait(driver, 10);
		mensajeExitoWait.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		btnEnviar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgError")));
		
		assertEquals(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
	

	@Test
	@Transactional
	public void comprobarRegistroFallidoIngresoCarro() {
		driver.get("http://localhost:8080/");
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Carro");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IHV102");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		
		btnEnviar.click();
		
		WebDriverWait mensajeExitoWait = new WebDriverWait(driver, 10);
		mensajeExitoWait.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		btnEnviar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgError")));
		
		assertEquals(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
}
