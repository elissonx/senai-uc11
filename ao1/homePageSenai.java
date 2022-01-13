package webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeSenai {
	private WebDriver driver;
	
	@Before
	public void ConfigurarTeste() {
		System.setProperty("webdriver.chrome.driver", "../../chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void Testar() {
		driver.get("https://online.sp.senai.br/");
		
		// Aceita os cookies
		driver.findElement(By.id("btnCookieConsent")).click();
		
		// Efetua testes na busca
		String[] termos = { "quimica", "fisica" };
		
		WebElement busca;
		
		for (int i = 0; i < 2; i++) {	
			try {
				busca = driver.findElement(By.id("Busca1_txtFiltro"));
				busca.clear();
				busca.sendKeys(termos[i]);	
				busca.sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Efetua teste no menu lateral
		try {
			driver.findElement(By.xpath("//*[@id=\"nav\"]/li[3]/a")).click();
			Thread.sleep(1500);
			
			driver.findElement(By.xpath("//*[@id=\"MenuLateral_rptMenuEscola_rptSubMenu_1_lnkSubMenu_0\"]")).click();
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			int alturaTotal = Integer.parseInt(js.executeScript("return document.body.scrollHeight").toString());
			
			// Rola a tela até o final e volta
			for (int altura = 0; altura < alturaTotal; altura = altura + 5) {
				js.executeScript("window.scrollTo(0, " + altura + ")");
				Thread.sleep(1);
			}
			
			for (int altura = alturaTotal; altura > 0; altura = altura - 5) {
				js.executeScript("window.scrollTo(0, " + altura + ")");
				Thread.sleep(1);
			}
			
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void EncerrarTeste() {
		driver.close();
	}
}
