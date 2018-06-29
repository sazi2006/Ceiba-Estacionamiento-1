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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class FlujoBusquedaVehiculo {
	
	private static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo solicitado no se encuentra en el parqueadero";
	
	private static final String URL = "http://localhost:8080/";
	
	private static WebDriver driver = null;
	
	@BeforeClass
	public static void URL() {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("disable-gpu");
		options.addArguments("window-size=1200,1100");
		
		driver = new ChromeDriver(options);
	}
	
	@AfterClass
	public static void destruirDriver() {
		driver.quit();
	}
	
	@Test
	@Transactional
	public void buscarMoto() {
		driver.get(URL);
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Moto");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IJX14E");
		
		WebElement cilindrada = driver.findElement(By.id("cilindraje"));
		cilindrada.sendKeys("180");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar.click();
		
		WebDriverWait mensajeExito = new WebDriverWait(driver, 5);
		mensajeExito.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		WebElement inputPlaca = driver.findElement(By.id("inputPlaca"));
		inputPlaca.sendKeys("IJX14E");
		
		WebElement btnBuscar = driver.findElement(By.id("buscarVehiculo"));
		btnBuscar.click();
		
		WebElement txtPlaca = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("placaVehiculo")));
		
		WebElement txtTipo = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("tipoVehiculo")));
		
		/*WebElement txtFechaIng = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("fechaIngVehiculo")));*/
		
		WebElement txtCilindraje = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("cilindrajeVehiculo")));
		
		assertEquals("IJX14E", txtPlaca.getText());
		assertEquals("Moto", txtTipo.getText());
		assertEquals("180", txtCilindraje.getText());
		
	}
	
	@Test
	@Transactional
	public void buscarCarro() {
		driver.get(URL);
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Carro");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IHV102");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar.click();
		
		WebDriverWait mensajeExito = new WebDriverWait(driver, 5);
		mensajeExito.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		WebElement inputPlaca = driver.findElement(By.id("inputPlaca"));
		inputPlaca.sendKeys("IHV102");
		
		WebElement btnBuscar = driver.findElement(By.id("buscarVehiculo"));
		btnBuscar.click();
		
		WebElement txtPlaca = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("placaVehiculo")));
		
		WebElement txtTipo = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("tipoVehiculo")));
		
		/*WebElement txtFechaIng = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("fechaIngVehiculo")));*/
		
		WebElement txtCilindraje = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("cilindrajeVehiculo")));
		
		assertEquals("IHV102", txtPlaca.getText());
		assertEquals("Carro", txtTipo.getText());
		assertEquals("N/A", txtCilindraje.getText());
		
	}
	
	@Test
	@Transactional
	public void buscarMotoNoDisponible() {
		driver.get(URL);
		
		WebElement inputPlaca = driver.findElement(By.id("inputPlaca"));
		inputPlaca.sendKeys("IJX14T");
		
		WebElement btnBuscar = driver.findElement(By.id("buscarVehiculo"));
		btnBuscar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("buscarMsgError")));

		assertEquals(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
	
	@Test
	@Transactional
	public void buscarCarroNoDisponible() {
		driver.get(URL);
		
		WebElement inputPlaca = driver.findElement(By.id("inputPlaca"));
		inputPlaca.sendKeys("IHV103");
		
		WebElement btnBuscar = driver.findElement(By.id("buscarVehiculo"));
		btnBuscar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("buscarMsgError")));

		assertEquals(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
	
	
	
	
}
