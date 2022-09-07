package pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import utility.BaseClass;
import utility.CommonActions;

public class AssignLeave extends BaseClass{
	WebDriver driver;
	
	@FindBy (id="assignleave_txtFromDate")
	WebElement fromDate;
	
	@FindBy (id="assignleave_txtToDate")
	WebElement toDate;
	
	@FindBy(id="assignleave_txtEmployee_empName")
	WebElement empName;
	
	@FindBy(id="assignleave_txtLeaveType")
	WebElement selLeaveType;
	
	@FindBy(id="assignleave_txtComment")
	WebElement commentBox;
	
	@FindBy(id="assignBtn")
	WebElement assignButton;
	
	public AssignLeave(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean assignLeave() {
		boolean isAddLeave = false;
		logger.log(Status.INFO, "On Assign Leave page, and assigning leave to an Employee.");
		try {
			if(assignButton.isDisplayed()) {
			CommonActions com = new CommonActions();
			Properties pro = com.readDataPropertyFile();
			
			logger.log(Status.INFO, "Set Employee Name");
			empName.sendKeys(pro.getProperty("EmpName"));
			Select selLevTyp = new Select(selLeaveType);
			selLevTyp.selectByVisibleText("Paternity US");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return isAddLeave;
	}
	
} // End of LeaveList[] class
