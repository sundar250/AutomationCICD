package CompanyName.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import CompanyName.TestComponents.BaseTest;
import CompanyName.pageObjects.CartPage;
import CompanyName.pageObjects.ProductCatalogue;


public class ErrorValidations extends BaseTest{
	
	@Test(groups= {"Error Handling"})
	public void loginErrorValidation() throws IOException{
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue =landingpage.loginApplication("meetsunda@gmail.com", "Testing123!");
		
		Assert.assertEquals("Incorrect email or passwor",landingpage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation()
	
	{
String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue =landingpage.loginApplication("abc@company.com", "Testing123!");
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartPage=productcatalogue.goToCartPage();
		
		Boolean match =cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
