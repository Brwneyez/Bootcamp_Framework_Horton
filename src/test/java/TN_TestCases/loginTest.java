package TN_TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TN.utilities.Util;
import com.TN_TestBase.TestBase_Bootcamp.TestBase;

public class loginTest  extends TestBase {
	
	public loginTest() throws Exception {
		super();	
	}	
    public WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();  
		driver.findElement(By.linkText("Login")).click();  
	}
	@Test (priority= 1, dataProvider = "Excel_LoginTN", dataProviderClass = Excel.class)
	public void verifyLoginWithValidCredentials (String email, String password) {
	
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
	}
	
	@Test (priority= 2)
	public void verifyLoginWithValidEmailInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = (dataProp.getProperty("loginWarningMessage"));
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	
		
}
	@Test (priority= 3)
	public void verifyLoginWithInValidEmailValidPassword () {
		
		
		driver.findElement(By.id("input-email")).sendKeys(Util.emailDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys("Sunshine22");
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		//String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		//Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	}
	
	@Test (priority= 4)
	public void verifyLoginWithInvalidCredentials () {
	
		  
		driver.findElement(By.id("input-email")).sendKeys(Util.emailDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	
		
	}
	@Test (priority= 5)
	public void verifyLoginWithNoCredentials () {
	
		  
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click(); //locater data leave alone
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();  //locator data leave alone
		//String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		//Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	}
	
