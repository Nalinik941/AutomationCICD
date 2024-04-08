package Nalini.Abstractmethods;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Nalini.Pageobject.Cartpage;
import Nalini.Pageobject.OrderPage;

public class AbstractComponent {
    WebDriver driver;
    
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
    @FindBy (css="[routerlink*='cart']")
    WebElement cartpage;
    
    @FindBy (css="[routerlink*='myorders']")
    WebElement orderpage;
    
	public void waitElementtoappear(By FindBy) {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
}
	public void waitwebelementtoappear(WebElement FindBy) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(FindBy));
		
	}
	public void waitelementtodisappear(WebElement ele) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public Cartpage gotocartpage() {
		cartpage.click();
		Cartpage CP=new Cartpage(driver);
		return CP;
		}
	public OrderPage gotoorderpage() {
		orderpage.click();
		OrderPage OP=new OrderPage(driver);
		return OP;
		}
}
