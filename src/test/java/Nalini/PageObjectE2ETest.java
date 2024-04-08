package Nalini;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Nalini.Pageobject.Cartpage;
import Nalini.Pageobject.CheckoutPage;
import Nalini.Pageobject.FinalPage;
import Nalini.Pageobject.LandingPage;
import Nalini.Pageobject.OrderPage;
import Nalini.Pageobject.ProductCateloge;
import Nalini.Test.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PageObjectE2ETest extends BaseTest {
	String Itemname="ZARA COAT 3";
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		ProductCateloge PC=LP.Logonapplication(input.get("email"),input.get("Pass"));
		List<WebElement> Products=PC.getproductlist();
		PC.addproducttocart(input.get("itemname"));
		Cartpage CP=PC.gotocartpage();
		
		Boolean match=CP.perfectmatch(input.get("itemname"));
		Assert.assertTrue(match);
		CheckoutPage CK=CP.gotoCheckout();
		CK.selectcountry("India");
		FinalPage FP=CK.submitOrder1();
       String Confirmmsg=(FP.confirmationmessage());
      Assert.assertTrue(Confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
      
	
		
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	
	public void OrderHistroyTest() {
		ProductCateloge PC=LP.Logonapplication("nalini@gmail.com","Nalini@95");
		OrderPage orderpage=PC.gotoorderpage();
		orderpage.verifyOrderDisplay(Itemname);
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "pruthvi@gmail.com");
//		map.put("Pass", "Pruthvi@02");
//		map.put("itemname", "ADIDAS ORIGINAL");
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "nalini@gmail.com");
//		map1.put("Pass", "Nalini@95");
//		map1.put("itemname", "ZARA COAT 3");
		List<HashMap<String,String>> data=getjsondatatomap(System.getProperty("user.dir")+"\\src\\test\\java\\Nalini\\data\\PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"pruthvi@gmail.com","Pruthvi@02","ADIDAS ORIGINAL"},{"nalini@gmail.com","Nalini@95","ZARA COAT 3"}};
//	}
	}

	

