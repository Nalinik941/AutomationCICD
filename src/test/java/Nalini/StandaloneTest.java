package Nalini;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String Itemname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("nalini@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Nalini@95");
		driver.findElement(By.id("login")).click();
		driver.manage().window().maximize();
		
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		WebElement prod= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(Itemname)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	List<WebElement> cartitem=	driver.findElements(By.cssSelector(".cartSection h3"));
	
      boolean match= cartitem.stream().anyMatch(item->item.getText().equalsIgnoreCase(Itemname));
      
     Assert.assertTrue(match);
      driver.findElement(By.cssSelector(".totalRow button")).click();
     Actions a=new Actions(driver);
     a.sendKeys(driver.findElement(By.xpath("//input[contains(@placeholder,'Select Country')]")),"India").build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@type,'button')][2]")));
      driver.findElement(By.xpath("//button[contains(@type,'button')][2]")).click();
      JavascriptExecutor js=(JavascriptExecutor)driver;
      js.executeScript("window.scrollBy(0,700)");
      Thread.sleep(2000);
      driver.findElement(By.cssSelector(".action__submit")).click();
      String confirmmesg=driver.findElement(By.cssSelector(".hero-primary")).getText();
      Assert.assertTrue(confirmmesg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
      driver.close();
		
		
	}

}
