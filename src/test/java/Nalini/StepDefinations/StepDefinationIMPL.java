package Nalini.StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Nalini.Pageobject.Cartpage;
import Nalini.Pageobject.CheckoutPage;
import Nalini.Pageobject.FinalPage;
import Nalini.Pageobject.LandingPage;
import Nalini.Pageobject.ProductCateloge;
import Nalini.Test.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationIMPL extends BaseTest {
 
	public LandingPage LP;
	public ProductCateloge PC;
	public FinalPage FP;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		LP=LaunchApplication();
	}
	
	 @Given("^Logged in with Username (.+) and password(.)$ ")
	 public void Logged_in_Username_and_password(String Username, String password)
	 {
		 PC=LP.Logonapplication(Username,password);
	}
	 

	 @When("^I add productname (.+) to cart$")
	 public void I_add_Product_name_to_cart(String ProductName)
	 {
		 List<WebElement> Products=PC.getproductlist();
			PC.addproducttocart(ProductName);
	 }
	 @When ("^Checkout (.+) and submit the order$")
	 public void checkout_submit_order(String Productname) throws InterruptedException
	 {
		 Cartpage CP=PC.gotocartpage();
			
			Boolean match=CP.perfectmatch(Productname);
			Assert.assertTrue(match);
			CheckoutPage CK=CP.gotoCheckout();
			CK.selectcountry("India");
			FP=CK.submitOrder1();
	 }

	  @Then("{string} message is dispalyed on ConfirmationPage")
	 public void message_diaplayed_ConfirmationPage(String string)
	 {
		  String Confirmmsg=(FP.confirmationmessage());
	      Assert.assertTrue(Confirmmsg.equalsIgnoreCase(string));
	  
	 }
	  
	  @Then("{string} message is displayed")
	  public void message_displayed(String string)
	  {
		  LP.Logonapplication("nalini@gmail.com", "Nalini95");
			Assert.assertEquals("Incorrect email or password.", LP.getErrorMessage());
	  }
}
