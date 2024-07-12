package TN_TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TN.utilities.Util;
import com.TN_TestBase.TestBase_Bootcamp.TestBase;


public class RegisterTest_Bootcamp extends TestBase {

	public RegisterTest_Bootcamp() throws Exception{ 
	super();
	}
		
		public WebDriver driver;
		

	@BeforeMethod
		public void Registration() {	
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();  
		driver.findElement(By.linkText("Register")).click(); 
			
	}

	@Test(priority=1, dataProvider = "TNRegister", dataProviderClass= ExcelCode.class)
	public void verifyRegisterWithMandatoryDetails(String firstname, String lastname, String telephone, String password, String confirmpassword) {
		
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(firstname);
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(lastname);
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(Util.emailDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(telephone);
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(confirmpassword);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div@content>p:nth-child(2)")).isDisplayed());
		
	}

	@Test (priority=2)
	public void VerifyregisterWithAllDetails() {
		
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(dataProp.getProperty("firstname"));
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(dataProp.getProperty("lastname"));
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(Util.emailDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newletter' and @value = '1']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div#content>p:nth-child(2)")).isDisplayed());
		
		
	}

	@Test(priority=3)
	public void registerWithExsistingEmail() {
		
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(dataProp.getProperty("firstname"));
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(dataProp.getProperty("lastname"));
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newletter' and @value = '1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("//div[contains(@class,'container')]")).isDisplayed());
		
		String actualExsistingEmailWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert alert-dangeralert-dismissible')]")).getText();
		String expectedExsistingEmailWarningMessage = dataProp.getProperty("exsistingEmailWarning");
		Assert.assertTrue(actualExsistingEmailWarningMessage.contains(expectedExsistingEmailWarningMessage));
		
	}

	@Test (priority=4)
	public void registerWithWrongConfirmPassword() {
		
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(dataProp.getProperty("firstname"));;
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(dataProp.getProperty("lastname"));;
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(Util.emailDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@name = 'newletter' and @value = '1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input#input-confirm+div")).isDisplayed());
	}
	
	
	@Test(priority=5)
	public void verifyRegisterWithNoDetails() {

		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = dataProp.getProperty("privacyPolicyWarning");
		Assert.assertEquals(actualPrivacyPolicyWarningMessage, expectedPrivacyPolicyWarningMessage);
		
		String actualFirstNameWarningMessage = driver.findElement(By.cssSelector("input#input-firtname+div")).getText();
		String expectedFirstNameWarningMessage = dataProp.getProperty("firstNameWarning");
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = driver.findElement(By.cssSelector("input#input-lastname+div")).getText();
		String expectedLastNameWarningMessage = dataProp.getProperty("lastNameWarning");
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.cssSelector("input#input-email+div")).getText();
		String expectedEmailWarningMessage = dataProp.getProperty("invalidEmailWarning");
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.cssSelector("input#input-Telephone+div")).getText();
		String expectedTelpehoneWarningMessage = dataProp.getProperty("telephoneWarning");
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelpehoneWarningMessage));
		
		String actualPasswordWarningMessage = driver.findElement(By.cssSelector("input#input-Password+div")).getText();
		String expectedPasswordWarningMessage = dataProp.getProperty("passwordWarning");
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));

	}
	//@AfterMethod
	//public void tearDown() {
	//	driver.quit();
	

	
}
