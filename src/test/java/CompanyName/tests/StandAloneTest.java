package CompanyName.tests;

import org.testng.annotations.Test;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.testng.annotations.Test;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CompanyName.TestComponents.BaseTest;
import CompanyName.pageObjects.CartPage;
import CompanyName.pageObjects.CheckOutPage;
import CompanyName.pageObjects.ComformationPage;
import CompanyName.pageObjects.LandingPage;
import CompanyName.pageObjects.OrderPage;
import CompanyName.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitorder(HashMap<String,String> input) throws IOException{
		
		
		ProductCatalogue productcatalogue =landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productcatalogue.goToCartPage();
		
		Boolean match =cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartPage.gotoCheckout();
		checkoutpage.selectCountry("india");
		ComformationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmationMessage();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
		
	}
	
	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue =landingpage.loginApplication("meetsundar23@gmail.com", "Testing123!");
		OrderPage orderPage=productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*
		 * HashMap<String,String> map = new HashMap<String,String>();
		 * HashMap<String,String> map1 = new HashMap<String,String>();
		 * HashMap<String,String> map2 = new HashMap<String,String>(); map.put("email",
		 * "abc@company.com"); map.put("password", "Testing123!"); map.put("product",
		 * "ZARA COAT 3"); map1.put("email", "def@company.com"); map1.put("password",
		 * "Testing123!"); map1.put("product", "ADIDAS ORIGINAL"); map2.put("email",
		 * "hij@company.com"); map2.put("password", "Testing123!"); map2.put("product",
		 * "IPHONE 13 PRO");
		 */
		//return new Object [] [] {{"abc@company.com","Testing123!"},{"def@company.com","Testing123!","ADIDAS ORIGINAL"} ,{"hij@company.com","Testing123!","IPHONE 13 PRO"}};
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//CompanyName//data//PurchaseOrder.json");
		
		return new Object [] [] {{data.get(0)},{data.get(1)},{data.get(2)}};
	}
	
	public String getScreenshot(String testcaseName) throws IOException
	{
		TakesScreenshot ts=	(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}
	


}
