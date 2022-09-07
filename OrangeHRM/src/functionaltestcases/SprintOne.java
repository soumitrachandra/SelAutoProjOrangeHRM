package functionaltestcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.Login;
import pages.Performance;
import pages.Recruitment;
import utility.BaseClass;
import utility.CommonActions;

//@Listeners(learn.selenium.listners.Listener.class)  //<---------------- How to call listener methods you can add <listeners> in testng.xml file.

public class SprintOne extends BaseClass{
	SoftAssert sofAssert = new SoftAssert();
	//@Test(dataProvider="getData")

/*	@Parameters({"num1"})
	@Test
	public void tc001(@Optional("num1")String num1) {
		System.out.println("I am in tc001() method and num1 value is " +num1);
		sofAssert.assertEquals(true, true);
		sofAssert.assertAll();
	}
	
	@Parameters({"num1"})
	@Test
	public void tc002(@Optional("num1") String num1) {
		System.out.println("I am ub tc002() method and num1 value is " + num1);
		//System.out.println("value in browser is " + browser);
		sofAssert.assertEquals(true, true);
		sofAssert.assertAll();
	}
	*/

	@Test
	public void tc01() throws IOException {// tc01() Launch Orange HRM login and verify if login is successful?
		logger = extent.createTest("tc01 >> Login To Orange HRM", "Set Username and Password on Login page of Orange HRM");
		logger.log(Status.INFO, "Creating object of Login{} class");
		Login login = new Login(driver);
		logger.log(Status.INFO, "Setting username and password and click on Login button on login page.");
		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();
		
		login.LoginToOrangeHRM(pro.getProperty("UserName"), pro.getProperty("Pwd"));
		//logger.log(Status.INFO, "Click on login button on login page");
		boolean islogin = login.verifyLoginToOrangeHRM();
		sofAssert.assertEquals(islogin, true);
		sofAssert.assertAll();
	}// End of tc01()0


//	@DataProvider(name="getLoginData")
//	@DataProvider
	public Object[][] getDataForLogin() throws IOException{
		Object[][] arr = new Object[2][2];
		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();
		arr[0][0]=pro.getProperty("UserName_InCorrect");
		arr[0][1]=pro.getProperty("Pwd_InCorrect");
		arr[1][0]="";
		arr[1][1]="";
		return arr;
	}
	
//	@DataProvider(name="listOfDevice")
	public Object[] setDevice() {
		Object[] arrDevice = new Object[5];
		CommonActions com = new CommonActions();
		Properties pro = new Properties();
		arrDevice[0] = pro.getProperty("MyDeviceOne");
		arrDevice[1] = pro.getProperty("MyDeviceTwo");
		arrDevice[2] = pro.getProperty("MyDeviceThree");
		arrDevice[3] = pro.getProperty("MyDeviceFoure");
		arrDevice[4] = pro.getProperty("MyDeviceFive");
		return arrDevice;
	}

//	@Test(dataProvider="getLoginData",dataProviderClass=functionaltestcases.DataProvider.class)
//	@Test(dataProvider="getDataForLogin",dataProviderClass=functionaltestcases.DataProvider.class)
//	@Test(dataProvider="getLoginData")
//	@Test(dataProvider="getDataForLogin")
	public void tc02(String User, String Pwd) throws IOException, InterruptedException {// tc01() Launch Orange HRM login and verify if login is successful?
		logger = extent.createTest("tc02 >> Verify negative scenario Login to Orange HRM", "Set Username and Password on Login page of Orange HRM");
		logger.log(Status.INFO, "Creating object of Login{} class");
		Login login = new Login(driver);
		logger.log(Status.INFO, "Setting username and password and click on Login button on login page.");
		login.LoginToOrangeHRM(User, Pwd);
		Thread.sleep(1000);
		//boolean islogin = login.verifyNegativeLoginToOrangeHRM();
		boolean islogin = login.verifyNegativeLoginToOrangeHRM();
		sofAssert.assertEquals(islogin, true);
		logger.log(Status.PASS, "Verified negative scenario for login.");
		//sofAssert.assertFalse(islogin);
		sofAssert.assertAll();
	}// End of tc01()0

	
//	@Test
	public void tc03() throws IOException {
		logger = extent.createTest("tc03 >> Add a new Vacancy", "Select menu option to Add a Vacancy and fill all required fields.");
		logger.log(Status.INFO, "Creating object of Login{} class");
		Login login = new Login(driver);

		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();
		
		logger.log(Status.INFO, "Setting username and password and click on Login button on login page.");
		login.LoginToOrangeHRM(pro.getProperty("UserName"), pro.getProperty("Pwd"));
		Recruitment rec = new Recruitment(driver);
		logger.log(Status.INFO, "Adding new vacancy");
		sofAssert.assertEquals(rec.addVacancy(pro.getProperty("JobTitle"),pro.getProperty("VacancyName"),pro.getProperty("HiringManager"), pro.getProperty("NoOfPositions"),pro.getProperty("VacancyDescription")), true);
		sofAssert.assertAll();
	}// End of tc02()

//	@Test(description ="Test add new KPI functionality")
	public void tc04() throws IOException {
		logger = extent.createTest("tc04 >> Add a new KPI", "Select menu option to Add a KPI and fill all rquired fields.");
		logger.log(Status.INFO, "Creating object of Login{} class");
		Login login = new Login(driver);
		logger.log(Status.INFO, "Setting username and password and click on Login button on login page.");
		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();

		login.LoginToOrangeHRM(pro.getProperty("UserName"), pro.getProperty("Pwd"));
		Performance perfm =new Performance(driver);
		logger.log(Status.INFO, "Adding a new KPI");
		sofAssert.assertEquals(perfm.addKpi(pro.getProperty("JobTitKpi"),pro.getProperty("PefKpi"), pro.getProperty("MinRating"),pro.getProperty("MaxRating")), true);
		sofAssert.assertAll();
	}// End of tc03()

//	@Test
	public void tc05() throws IOException {
		logger = extent.createTest("tc05 >> Delete an existing Vacancy", "Select menu options to reach Vacancy table select a given vacancy and delete");
		logger.log(Status.INFO, "Creating object of Login{} class");
		Login login = new Login(driver);
		logger.log(Status.INFO, "Setting username and password and click on Login button on login page.");
		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();

		login.LoginToOrangeHRM(pro.getProperty("UserName"), pro.getProperty("Pwd"));
		logger.log(Status.INFO, "Creating object of Recruitment[] class");
		Recruitment rec = new Recruitment(driver);
		logger.log(Status.INFO, "Searching for Vacancy and deleting");
		sofAssert.assertEquals(rec.delVacancy(pro.getProperty("VacancyName")), true);
		sofAssert.assertAll();
	}
	
//	@Test(description="Test delete KIP functionality")
	public void tc06() throws IOException {
		logger = extent.createTest("tc06 >> Delete an existing KPI", "Select menu options to reach KPI table");
		logger.log(Status.INFO, "Creating object of Login{} class");
		Login login = new Login(driver);
		logger.log(Status.INFO, "Setting username and password and click on Login button on login page.");
		CommonActions com = new CommonActions();
		Properties pro = com.readDataPropertyFile();

		login.LoginToOrangeHRM(pro.getProperty("UserName"), pro.getProperty("Pwd"));
		logger.log(Status.INFO, "Creating Object of Performance{} class");
		Performance perfm = new Performance(driver);
		logger.log(Status.INFO, "Searching for Kpi and Deleting");
		sofAssert.assertEquals(perfm.delKpi(pro.getProperty("PefKpi")), true);
		sofAssert.assertAll();
	}
	
//	@Test
	public void tc07() throws IOException{
		logger = extent.createTest("tc07 >> Demonstrate Fail test", "This method is to demo Failed test");
		sofAssert.assertTrue(false);
		sofAssert.assertEquals(true, false);
		sofAssert.assertAll();
	}
}

