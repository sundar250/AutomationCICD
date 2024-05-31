package CompanyName.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import CompanyName.TestComponents.BaseTest;
import CompanyName.pageObjects.CartPage;
import CompanyName.pageObjects.CheckOutPage;
import CompanyName.pageObjects.ComformationPage;
import CompanyName.pageObjects.LandingPage;
import CompanyName.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue ;
	public ComformationPage confirmationpage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage=launchApplication();
	}
	
	@Given("^logged in with username (.+) and passcode (.+)$")
	public void logged_in_with_username_and_password(String username,String password)
	{
	 productcatalogue =landingpage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) from cart$")
	public void I_add_product_from_cart(String productName)
	{
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	@And("^And CheckOut (.+) and submit the order$")
    public void CheckOut_and_submit_the_order(String productName)
    {
        CartPage cartPage=productcatalogue.goToCartPage();
		
		Boolean match =cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartPage.gotoCheckout();
		checkoutpage.selectCountry("india");
		 confirmationpage=checkoutpage.submitOrder();
		
    }
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_on_confirmation_page(String string)
	{
		String confirmMessage=confirmationpage.getConfirmationMessage();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		
	}
	
}
