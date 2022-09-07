package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utility.BaseClass;

public class Login extends BaseClass{
	//WebDriver driver;  // I had commented and need to check with Sachin
	//<----------------- Object repository --------------->>
	@CacheLookup
	@FindBy(id="txtUsername")
	WebElement txtUsername;
	
	@CacheLookup
	@FindBy(id="txtPassword")
	WebElement txtPassword;

	@CacheLookup
	@FindBy(id="btnLogin")
	WebElement btnLogin;

	@CacheLookup
	@FindBy(xpath="//*[@id='content']/div/div[1]/h1")
	WebElement txtDashboard;

	@CacheLookup
	@FindBy(id="spanMessage")
	WebElement invLoginMsg;

	SoftAssert sofAssert = new SoftAssert();

	public Login(WebDriver driver) { // Never create object of class inside its Constructor it becomes infinite.
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

//@Test(priority=1)
	
	public void LoginToOrangeHRM(String user, String pwd) {
		try {
//logger = extent.createTest("Login To Orange HRM", "Trying to set Username and Password to Login page on Orange HRM");
			logger.log(Status.INFO, "Entering username");
			txtUsername.sendKeys(user);
			logger.log(Status.INFO, "Entering password");
			txtPassword.sendKeys(pwd);
			Thread.sleep(1000);
			logger.log(Status.INFO, "click on Login button");
			//btnLogin.click();
			Login ln = new Login(driver);
			ln.clickLoginButton();
			sofAssert.assertEquals(verifyLoginToOrangeHRM(), true);
			logger.log(Status.PASS, "Login successuflly to Orange HRM");
		} catch (Exception e) {
			e.getMessage();
			//logger.log(Status.FAIL, "Falied to reach the main page of Orange HRM");
		}
		//logger = extent.createTest("LoginToFlipkart", "Verifies if a user is able to login to \"flipkat.com\"");
		//logger.log(Status.INFO, "Trying to execute LoginToFlipkart() of Login.java");
		//sofAssert.assertEquals(true, true);
	} // <----- End of LoginToOrangeHRM()
	
	public void clickLoginButton() {
		logger.log(Status.INFO, "click on Login button");
		btnLogin.click();
	}
	
	public boolean verifyLoginToOrangeHRM() {
		boolean isLogin = false;
		if(txtDashboard.getText().equalsIgnoreCase("Dashboard")) {
			logger.log(Status.INFO, "Verifying if login successfull to Orange HRM and Dashboard is visible.");
			isLogin = true;
		}
		else {
			logger.log(Status.INFO, "Login is not successful to Orange HRM and Dashboard is not visible.");
			isLogin = false;
		}
		return isLogin;
	} // End of verifyLoginToOrangeHRM()
	
	public boolean verifyNegativeLoginToOrangeHRM() {
		boolean isLogin = false;
		WebDriverWait wait = new WebDriverWait(driver,10);
		logger.log(Status.INFO, "Waiting for Login button to be visible.");
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		if(driver.getTitle().equalsIgnoreCase("OrangeHRM")) {
			logger.log(Status.INFO, "Login Page title is present on Login Page.");
			isLogin = true;
		}
		else {
			logger.log(Status.INFO, "Login page title not present.");
			isLogin = false;
		}		
		return isLogin;
	}
}

