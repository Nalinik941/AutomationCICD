package Nalini.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Nalini.Abstractmethods.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(id="userEmail")
	WebElement UserEmail;
    
    @FindBy(id="userPassword")
   	WebElement Password;
    
    @FindBy(id="login")
	WebElement Submit;
    
   // .ng-tns-c4-12.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
    @FindBy(css="[class*='flyInOut']")
    WebElement errormesg;

    public ProductCateloge Logonapplication(String email,String pwd)
    {
    	UserEmail.sendKeys(email);
    	Password.sendKeys(pwd);
    	Submit.click();
    	ProductCateloge PC=new ProductCateloge(driver);
    	return PC;
    }
    
    public String getErrorMessage()
    {
    	waitwebelementtoappear(errormesg);
    	return errormesg.getText();
    }
    
    public void Goto()
    {
    	driver.get("https://rahulshettyacademy.com/client");
    }
}
