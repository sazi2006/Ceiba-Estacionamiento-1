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

//import io.github.bonigarcia.wdm.WebDriverManager;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class FlujoIngresoVehiculosTest {
	
	private static final String EL_VEHICULO_HA_SIDO_REGISTRADO = "El vehiculo ha sido registrado correctamente";
	private static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo ya se encuentra en el parqueadero";
	private static final String URL = "http://localhost:8080/";
	
	private static WebDriver driver = null;
	
	@BeforeClass
	public static void inicializarDriver() {
		System.setProperty("webdriver.chrome.driver", "libs\\chromedriver-linux\\chromedriver");
		//WebDriverManager.chromedriver().setup();
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
	public void comprobarRegistroIngresoMoto() {
		driver.get("http://localhost:8080/");
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Moto");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IJX14V");
		
		WebElement cilindrada = driver.findElement(By.id("cilindraje"));
		cilindrada.sendKeys("180");
		
		/*WebElement fechaIngreso = driver.findElement(By.id("fecha_ingreso"));
		fechaIngreso.sendKeys("0806080020180725");*/
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar.click();
		
		WebElement mensajeExito = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		assertEquals(EL_VEHICULO_HA_SIDO_REGISTRADO, mensajeExito.getText());
		
	}
	
	@Test
	@Transactional
	public void comprobarRegistroIngresoCarro() {
		driver.get("http://localhost:8080/");
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Carro");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IHV101");
		
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar.click();
		
		WebElement mensajeExito = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		assertEquals(EL_VEHICULO_HA_SIDO_REGISTRADO, mensajeExito.getText());
		
	}
	
	@Test
	@Transactional
	public void comprobarRegistroFallidoIngresoMoto() {
		driver.get(URL);
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Moto");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IJX14C");
		
		WebElement cilindrada = driver.findElement(By.id("cilindraje"));
		cilindrada.sendKeys("180");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		btnEnviar.click();
		
		WebDriverWait mensajeExitoWait = new WebDriverWait(driver, 5);
		mensajeExitoWait.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		btnEnviar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgError")));
		
		assertEquals(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
	

	@Test
	@Transactional
	public void comprobarRegistroFallidoIngresoCarro() {
		driver.get(URL);
		
		Select tipoVehiculo = new Select(driver.findElement(By.id("tipo")));
		tipoVehiculo.selectByValue("Carro");
		
		WebElement placa = driver.findElement(By.id("placa"));
		placa.sendKeys("IHV109");
		
		WebElement btnEnviar = driver.findElement(By.id("enviarIngresoVehiculo"));
		
		btnEnviar.click();
		
		WebDriverWait mensajeExitoWait = new WebDriverWait(driver, 5);
		mensajeExitoWait.until(ExpectedConditions.presenceOfElementLocated(By.id("msgExito")));
		
		btnEnviar.click();
		
		WebElement mensajeError = (new WebDriverWait(driver, 5))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("msgError")));
		
		assertEquals(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, mensajeError.getText());
		
	}
	
	
	
	
}
