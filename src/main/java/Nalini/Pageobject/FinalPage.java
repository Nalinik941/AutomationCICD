package Nalini.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Nalini.Abstractmethods.AbstractComponent;

public class FinalPage extends AbstractComponent{

	WebDriver driver;
	public FinalPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	@FindBy(css=".hero-primary")
	WebElement Confirmmsg;
    
	public String confirmationmessage() {
		
	return (Confirmmsg).getText();
}



}