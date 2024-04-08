package Nalini.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Nalini.Abstractmethods.AbstractComponent;

public class ProductCateloge extends AbstractComponent {
  
	WebDriver driver;
	public ProductCateloge(WebDriver driver)
	{
		super(driver);
	    this.driver=driver;	
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	By productby=(By.cssSelector(".mb-3"));
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	By toast=(By.cssSelector("#toast-container"));
	
	public List<WebElement> getproductlist()
	{
		waitElementtoappear(productby);
		return products;
	}
	
	public WebElement getproductbyname(String Itemname)
	{
		WebElement prod=getproductlist().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(Itemname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addproducttocart(String Itemname)
	{
		WebElement prod=getproductbyname(Itemname);
		prod.findElement(addtocart).click();
		waitElementtoappear(toast);
		waitelementtodisappear(spinner);
		
	}
	
	
}
