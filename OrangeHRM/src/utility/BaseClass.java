package utility;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public WebDriver driver;

//@BeforeTest
public void TestBeforTest() {
	System.out.println("I am in @BeforeTest");
}

//@AfterTest
public void TestAfterTest() {
	System.out.println("I am in @AfterTest");
}

@BeforeSuite
public void startReport(){
//	System.out.println("I am in @BeforesSuit");
	htmlReporter = new ExtentHtmlReporter("C:\\SAUMI\\MyWork\\ExecuationReport\\ReportOrangeHRM.html");
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("OS", "Mac ");
    extent.setSystemInfo("Host Name", "test");
    extent.setSystemInfo("Environment", "QA");
    extent.setSystemInfo("User Name", "Test User");
}

@AfterSuite
public void endReport(){
//	System.out.println("I am in @AfterSuit");
	extent.flush();
    //extent.close();
}

@Parameters({"browser","url"})
@BeforeMethod
	public WebDriver LaunchBrowser(@Optional("browser")String browser,@Optional("url") String url) throws IOException{
//	System.out.println("I am in @BeforeMethod");
	if (browser.equalsIgnoreCase("chrome")){
		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();

		System.setProperty(pro.getProperty("chromeDriver"),pro.getProperty("chromeDriverPath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);  // Note .get() execuation waits until the page is loaded.
		}
	else if (browser.equalsIgnoreCase("mozilla")){
		System.setProperty("webdriver.ie.driver","C:\\SAUMI\\MyWork\\EclipseWorkSpace\\AllExternalJars\\WebDrivers\\geckodriver-v0.31.0-win64\\geckodriver.exe");
//		System.setProperty("webdriver.ie.driver","/home/saumi/SAUMI/MyWork/WORKSPACE_CLASSES/SeleniumJars/Chrome_75.0.377");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.get(url);
		}
		/*
		 * else if (browser.equalsIgnoreCase("phantomjs")){
		 * System.setProperty("phantomjs.binary.path",
		 * "/home/saumi/SAUMI/MyWork/WORKSPACE_CLASSES/SeleniumJars/chromedriver"); //
		 * System.setProperty("webdriver.ie.driver",
		 * "/home/saumi/SAUMI/MyWork/WORKSPACE_CLASSES/SeleniumJars/Chrome_75.0.377");
		 * driver = new PhantomJSDriver(); driver.manage().window().maximize();
		 * driver.get(url); }
		 */

	else {	driver = null;	}
	return driver;
	}		 // End of LaunchBrowser

@AfterMethod
    public void getResult(ITestResult result){
//	System.out.println("I am in 1st @AfterMethod");
     if(result.getStatus() == ITestResult.SUCCESS) {
      logger.log(Status.PASS, "Test Case \"" + result.getName()+"\" Passed");	}

     if(result.getStatus() == ITestResult.FAILURE){
       logger.log(Status.FAIL, "Test Case \""+result.getName()+"\" Falied "+ "\n"+result.getThrowable());	}

     else if(result.getStatus() == ITestResult.SKIP){
              logger.log(Status.SKIP, "Test Case \""+result.getName()+"\" Skipped");	}
}

@AfterMethod
	public void CloseBrowser(){
//	System.out.println("I am in 2nd @AfterMethod");
	driver.close();
	logger.log(Status.INFO, "Closing the Browser");
	}
}
