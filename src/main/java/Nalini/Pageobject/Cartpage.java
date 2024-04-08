package Nalini.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Nalini.Abstractmethods.AbstractComponent;

public class Cartpage extends AbstractComponent{

	WebDriver driver;
	public Cartpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

		@FindBy (css=".cartSection h3")
		List<WebElement> cartitem;
		@FindBy (css=".totalRow button")
		WebElement cartpage;

	  public boolean perfectmatch(String Itemname) {
     boolean match= cartitem.stream().anyMatch(item->item.getText().equalsIgnoreCase(Itemname));
     return match;

     }
	  
	  public CheckoutPage gotoCheckout() throws InterruptedException {
		  Thread.sleep(2000);
		cartpage.click();
		  CheckoutPage CK=new CheckoutPage(driver);
		  return CK;
	  }
}