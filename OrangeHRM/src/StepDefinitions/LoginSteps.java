package StepDefinitions;

import org.openqa.selenium.WebDriver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Login;
import utility.BaseClass;

public class LoginSteps {
	WebDriver driver;
	@Given("^I am on OrangeHRM application$")
	public void i_am_on_OrangeHRM_application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	BaseClass bc = new BaseClass();
	driver = bc.LaunchBrowser("chrome", "https://opensource-demo.orangehrmlive.com/");
	Thread.sleep(5000);
	//driver.navigate().to("https://www.google.co.in/");
	}

	@When("^I enter UserName \"([^\"]*)\" and Password \"([^\"]*)\"$")
	public void i_enter_UserName_and_Password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	Login lg = new Login(driver);
	lg.LoginToOrangeHRM(arg1,arg2);
	}

	@When("^I click on login button$")
	public void i_click_on_login_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Login lg2 = new Login(driver);
		lg2.clickLoginButton();
	}

	@Then("^I validate the outcomes$")
	public void i_validate_the_outcomes() throws Throwable {
		Login lg = new Login(driver);
		lg.verifyLoginToOrangeHRM();
	    // Write code here that turns the phrase above into concrete actions	
	}
}
