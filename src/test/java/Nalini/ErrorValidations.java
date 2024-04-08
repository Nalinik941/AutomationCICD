package Nalini;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Nalini.Pageobject.Cartpage;
import Nalini.Pageobject.ProductCateloge;
import Nalini.Test.BaseTest;
import Nalini.Test.Retry;

public class ErrorValidations extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginerrorvalidation() throws IOException, InterruptedException
	{
		

		String Itemname="ZARA COAT 3";
		LP.Logonapplication("nalini@gmail.com", "Nalini95");
		Assert.assertEquals("Incorrect email or password.", LP.getErrorMessage());
		}
	
	@Test
	public void ProductErrorValidation()
	{
		String Itemname="ZARA COAT 3";
		ProductCateloge PC=LP.Logonapplication("nalini@gmail.com", "Nalini@95");
		List<WebElement> Products=PC.getproductlist();
		PC.addproducttocart(Itemname);
		Cartpage CP=PC.gotocartpage();
		Boolean match=CP.perfectmatch("ZARA COAT 34");
		Assert.assertFalse(match);
	}
}
