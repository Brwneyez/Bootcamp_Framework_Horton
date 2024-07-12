package TN_TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TN_TestBase.TestBase_Bootcamp.TestBase;

public class SearchProduct_Bootcamp extends TestBase{

	public SearchProduct_Bootcamp () throws Exception {
		super();
	}

	
	public WebDriver driver;
	

	@BeforeMethod
		public void loginSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
	}
	@Test(priority=1)
	public void verifySearchValidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());	
		
	}
	
	@Test(priority=2)
	public void verifySearchInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is no product that matches the search criteria.']")).isDisplayed());
	}
		
	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is no product that matches the search criteria.']")).isDisplayed());
}	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	
	}
}
