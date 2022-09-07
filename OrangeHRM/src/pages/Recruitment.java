package pages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import utility.BaseClass;
import utility.CommonActions;

public class Recruitment extends BaseClass{
	WebDriver driver;
	
	@CacheLookup
	@FindBy (id="menu_recruitment_viewRecruitmentModule")	
	WebElement RecruitmentMenu;
	
	@CacheLookup
	@FindBy(id="menu_recruitment_viewJobVacancy")	
	WebElement VacancySubMenu;
	
	@CacheLookup
	@FindBy(id="menu_recruitment_viewCandidates")	
	WebElement CandidateSubMenu;
	
	@CacheLookup
	@FindBy(id="btnAdd")	
	WebElement AddVacancy;
	
	@CacheLookup
	@FindBy(id="addJobVacancy_jobTitle")
	WebElement SelJobTitle;
	
	@CacheLookup
	@FindBy(id="addJobVacancy_name")	
	WebElement VacancyName;
	
	@CacheLookup
	@FindBy(id="addJobVacancy_hiringManager")	
	WebElement HiringManager;
	
	@CacheLookup
	@FindBy (id="addJobVacancy_noOfPositions")	
	WebElement NoOfPositions;
	
	@CacheLookup
	@FindBy(id="addJobVacancy_description")	
	WebElement VacancyDescription;
	
	@CacheLookup
	@FindBy(id="btnSave")	
	WebElement SaveButton;
	
	@CacheLookup
	@FindBy(id="btnBack")
	WebElement BackButton;
	
	@CacheLookup
	@FindBy(xpath="//*[@id='resultTable']/tbody")
	WebElement VacTableData;
	
	@CacheLookup
	@FindBy(id="btnDelete")
	WebElement DelButton;
	
	@CacheLookup
	@FindBy(id="dialogDeleteBtn")
	WebElement DelBtnPopUp;
	
	public Recruitment(WebDriver driver) { // Constructor of class Recruitment[] 
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}// <----- End of constructor Recruitment()
	
	public boolean addVacancy(String JobTitle, String VacyName, String HirMangr, String NoOfPosit, String VacyDesrp) {
		boolean isVacancy = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			logger.log(Status.INFO, "Creating Action object for mouse action");
			Actions actMouse = new Actions(driver);			
			logger.log(Status.INFO, "Selecting Recruitment menu option");
			actMouse.moveToElement(RecruitmentMenu).build().perform();
			wait.until(ExpectedConditions.visibilityOf(VacancySubMenu));
			logger.log(Status.INFO, "clicking on Sub menu option Vacancy");
			VacancySubMenu.click();
		
			if(AddVacancy.isDisplayed()) {
				logger.log(Status.INFO, "Clicking on Add vacancy button");
				AddVacancy.click();
				logger.log(Status.INFO, "Selecting job title = CEO");
				Select selJobTit = new Select(SelJobTitle);
				selJobTit.selectByVisibleText(JobTitle);
				logger.log(Status.INFO, "Setting vacancy name");
				VacancyName.sendKeys(VacyName);
				logger.log(Status.INFO, "Setting hiring Manager");
				HiringManager.sendKeys(HirMangr);
				logger.log(Status.INFO, "Setting no of positions");
				NoOfPositions.sendKeys(NoOfPosit);
				logger.log(Status.INFO, "Setting Description");
				VacancyDescription.sendKeys(VacyDesrp);
				logger.log(Status.INFO, "Clicking on Save button");
				SaveButton.click();
				logger.log(Status.INFO, "Clicking on Back button");
				BackButton.click();
				logger.log(Status.INFO, "Going in an Implicit wait");
				wait.until(ExpectedConditions.visibilityOf(VacTableData)); // <--- Example of implicit wait 
					List<WebElement> listOfRows = VacTableData.findElements(By.tagName("tr"));
					for(WebElement row: listOfRows) {
						List<WebElement> cols = row.findElements(By.tagName("td"));
						for(WebElement col: cols) {
							if(col.getText().equalsIgnoreCase(VacyName)) {
								isVacancy = true;
								logger.log(Status.INFO, "Vacancy is successfully added.");
							}
						}
					}			
			}
		} catch (Exception e) {
			e.getMessage();
			isVacancy=false;
			logger.log(Status.INFO, "Error while creating new Vacancy");
		}
		return isVacancy;
	} // End of addVacancy()

	public boolean delVacancy(String vacName) {
		boolean isDelVac = false;
		WebElement colChk= null;
		WebElement vacNam = null;
		boolean isCheckChked = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			logger.log(Status.INFO, "Creating Action object for mouse action");
			Actions actMouse = new Actions(driver);			
			logger.log(Status.INFO, "Selecting Recruitment menu option");
			actMouse.moveToElement(RecruitmentMenu).build().perform();
			wait.until(ExpectedConditions.visibilityOf(VacancySubMenu));
			logger.log(Status.INFO, "clicking on Sub menu option Vacancy");
			VacancySubMenu.click();
			wait.until(ExpectedConditions.visibilityOf(VacTableData));
			List<WebElement> liOfRows = VacTableData.findElements(By.tagName("tr"));
			for(WebElement row: liOfRows) {
				List<WebElement> cols = row.findElements(By.tagName("td"));
				for(WebElement col:cols) {
				if(isCheckChked==false) {
				if(col.findElement(By.tagName("input"))!=null) {
					colChk = col.findElement(By.tagName("input"));
					if(colChk.getAttribute("type").equals("checkbox")) {
						colChk.click();
						isCheckChked = true;
						continue;
					}
				}
				}

				if(isDelVac==false) {
				if(col.findElement(By.tagName("a"))!=null) {
					vacNam = col.findElement(By.tagName("a"));
					if(vacNam.getText().equalsIgnoreCase(vacName)) {
						DelButton.click();
						wait.until(ExpectedConditions.visibilityOf(DelBtnPopUp));
						DelBtnPopUp.click();
						logger.log(Status.INFO, "Delet vacancy is successfull");
						isDelVac = true;
						break;
					}
					colChk.click();
					isCheckChked = false;
					break;
				}
				}
				}// End of Inner For loop
				if(isDelVac) {	break;	}
			}// End of Outer For loop
		}
		catch (Exception e) {
			logger.log(Status.INFO, "Delete vacancy not successfull");
			e.getMessage();
			isDelVac = false;
		}
		return isDelVac;
	} // End of delVacancy()
	
	
}
