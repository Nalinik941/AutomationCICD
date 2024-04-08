package Nalini.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Nalini.Abstractmethods.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//input[contains(@placeholder,'Select Country')]")
	 private WebElement Country;
	@FindBy (xpath="//button[contains(@type,'button')][2]")
	private WebElement cname;
	@FindBy (css=".action__submit")
	private WebElement Placeorder;
	private By countryname=(By.xpath("//button[contains(@type,'button')][2]"));
	
	 public void selectcountry(String country) throws InterruptedException {
		 Actions a=new Actions(driver);
	     a.sendKeys((Country),country).build().perform();
	     waitElementtoappear(countryname);
	    cname.click();
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	      js.executeScript("window.scrollBy(0,700)");
	      Thread.sleep(2000);
	      
	 }
	 
	 public FinalPage submitOrder1() throws InterruptedException
	 {
		 
		 Placeorder.click();
		 FinalPage FP=new FinalPage(driver);
		 return FP;
	 }
}
