package webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GameManiaAngular {
	private WebDriver driver;
	
	private int espera = 1500;
	
	@Before
	public void ConfigurarTeste() {
		System.setProperty("webdriver.chrome.driver", "../../chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void Testar() {
		driver.get("http://localhost:4200");

		try {
			Thread.sleep(espera);
			
			// Clica em "minha conta"
			driver.findElement(By.xpath("/html/body/app-root/app-header/header/nav/a[7]")).click();
			Thread.sleep(espera);
			
			// Tenta se logar com usuário inexistente 
			WebElement email = driver.findElement(By.id("email"));
			email.sendKeys("emailerrado@teste.com");
			Thread.sleep(espera);
			
			WebElement senha = driver.findElement(By.id("senha"));
			senha.sendKeys("12345");
			Thread.sleep(espera);
			
			WebElement entrar = driver.findElement(By.xpath("/html/body/app-root/app-minha-conta/section/form[1]/div/button"));
			entrar.click();
			Thread.sleep(espera);
			
			// Cadastra novo usuário
			driver.findElement(By.id("nome")).sendKeys("Teste Automatizado");
			Thread.sleep(espera);
			driver.findElement(By.id("email2")).sendKeys("emailcerto@teste.com");
			Thread.sleep(espera);
			driver.findElement(By.id("senha2")).sendKeys("12345");
			Thread.sleep(espera);
			driver.findElement(By.xpath("/html/body/app-root/app-minha-conta/section/form[2]/div/button")).click();
			Thread.sleep(espera);
			
			// Tenta se logar, mas com senha errada
			email.clear();
			email.sendKeys("emailcerto@teste.com");
			Thread.sleep(espera);
			senha.clear();
			senha.sendKeys("11111");
			Thread.sleep(espera);
			entrar.click();
			Thread.sleep(espera);
			
			// Coloca a senha correta
			senha.clear();
			senha.sendKeys("12345");
			Thread.sleep(espera);
			entrar.click();
			Thread.sleep(espera);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@After
	public void EncerrarTeste() {
		driver.close();
	}
}
