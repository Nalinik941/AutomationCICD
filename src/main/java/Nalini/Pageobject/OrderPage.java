package Nalini.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Nalini.Abstractmethods.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

		@FindBy (css="tr td:nth-child(3)")
		List<WebElement> productnames;
		@FindBy (css=".totalRow button")
		WebElement cartpage;

	  public Boolean verifyOrderDisplay(String productname ) {
     Boolean match= productnames.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
     return match;
     }

}