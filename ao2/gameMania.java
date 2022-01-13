package webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GameMania {
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
		driver.get("https://ortegavan.github.io/gamemania/");

		try {
			Thread.sleep(espera);
			
			// Aceita os cookies
			driver.findElement(By.id("botao-cookies")).click();
			Thread.sleep(espera);
			
			// Clica na lupa para efetuar busca
			driver.findElement(By.className("fa-search")).click();
			Thread.sleep(espera);
			
			// Digita "rosa" e busca
			WebElement txtBusca = driver.findElement(By.id("busca"));
			txtBusca.click();
			txtBusca.sendKeys("rosa");
			Thread.sleep(espera);
			driver.findElement(By.xpath("//*[@id=\"div-busca\"]/button")).click();
			Thread.sleep(espera);
			
			// Clica no item encontrado
			driver.findElement(By.xpath("/html/body/section[3]/div[2]/div/a")).click();
			
			// Adiciona na cesta de compras e na lista de favoritos
			Thread.sleep(espera);
			driver.findElement(By.className("botao-sacola")).click();
			Thread.sleep(espera);
			driver.findElement(By.className("botao-favorito")).click();
			Thread.sleep(espera);
			
			// Vai pra lista de favoritos e remove o item
			driver.findElement(By.className("fa-heart")).click();
			Thread.sleep(espera);
			driver.findElement(By.xpath("//*[@id=\"carrinho\"]/div/p[1]/a")).click();
			Thread.sleep(espera);
						
			// Vai pra cesta de compras e remove o item
			driver.findElement(By.className("fa-shopping-basket")).click();
			Thread.sleep(espera);
			driver.findElement(By.xpath("//*[@id=\"carrinho\"]/div/p[1]/a")).click();
			Thread.sleep(espera);
			
			// Volta pra home
			driver.findElement(By.className("logo")).click();	
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
