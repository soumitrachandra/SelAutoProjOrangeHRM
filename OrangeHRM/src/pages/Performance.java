package pages;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.ws.WebEndpoint;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utility.BaseClass;
import utility.CommonActions;

public class Performance extends BaseClass {
	//WebDriver driver;   // I had commented and need to check with Sachin
	
	@CacheLookup
	@FindBy(id="menu__Performance")
	WebElement PerformanceMenu;
	
	@CacheLookup
	@FindBy(id="menu_performance_Configure")
	WebElement ConfigSubMenu;
	
	@CacheLookup
	@FindBy(id="menu_performance_searchKpi")
	WebElement KpiSubMenu;
	
	@CacheLookup
	@FindBy(id="btnAdd")
	WebElement AddButton;
	
	@CacheLookup
	@FindBy(id="btnDelete")
	WebElement DelButton;
	String delBtn = "btnDelete";
	
	@CacheLookup
	@FindBy(id="dialogDeleteBtn")
	WebElement DelBtnPop;
	String delBtnPop="dialogDeleteBtn";
	
	@CacheLookup
	@FindBy(id="defineKpi360_jobTitleCode")
	WebElement SelJobTitle;
	
	@CacheLookup
	@FindBy(id="defineKpi360_keyPerformanceIndicators")
	WebElement KeyPerfIndicator;
	
	@CacheLookup
	@FindBy(id="defineKpi360_minRating")
	WebElement MinRating;
	
	@CacheLookup
	@FindBy(id="defineKpi360_maxRating")
	WebElement MaxRating;
	
	@CacheLookup
	@FindBy(id="defineKpi360_makeDefault")
	WebElement DefaultScaleChkBox;
	
	@CacheLookup
	@FindBy(id="saveBtn")
	WebElement Savebutton;
	
	@CacheLookup
	@FindBy(xpath="//*[@id='resultTable']/tbody")
	WebElement kpiTableData;
	
	public Performance(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean addKpi(String JobTit, String Kpi, String MinRat, String MaxRat ) {
		boolean isKpi = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			logger.log(Status.INFO, "Creating Action object for mouse action");
			Actions actMouse = new Actions(driver);
			wait.until(ExpectedConditions.visibilityOf(PerformanceMenu));
			logger.log(Status.INFO, "Selecting Performance menu option");
			actMouse.moveToElement(PerformanceMenu).build().perform();
			wait.until(ExpectedConditions.visibilityOf(ConfigSubMenu));
			logger.log(Status.INFO, "Selecting Config sub menu option");
			actMouse.moveToElement(ConfigSubMenu).build().perform();
			wait.until(ExpectedConditions.visibilityOf(KpiSubMenu));
			logger.log(Status.INFO, "Click on KPIs sub menu");
			KpiSubMenu.click();
			if(AddButton.isDisplayed()) {
				logger.log(Status.INFO, "Clicking on Add KPIs button");
				AddButton.click();
				logger.log(Status.INFO, "Select Job Title = CEO");
				Select jobTit = new Select(SelJobTitle);
				jobTit.selectByVisibleText(JobTit);
				logger.log(Status.INFO, "Set key performance indicator");
				KeyPerfIndicator.sendKeys(Kpi);
				logger.log(Status.INFO, "Setting the Min Rating");
				MinRating.clear();
				MinRating.sendKeys(MinRat);
				logger.log(Status.INFO, "Setting the Max Rating");
				MaxRating.clear();
				MaxRating.sendKeys(MaxRat);
				logger.log(Status.INFO, "Check the checkbox for default Scale");
				DefaultScaleChkBox.click();
				logger.log(Status.INFO, "Click on Save button");
				
				CommonActions objCom = new CommonActions();
				Properties pro = objCom.readDataPropertyFile();
				objCom.takeSnapShot(driver, pro.getProperty("ScrShotPerformanceClass")+"BeforClickSaveButton.jpeg");
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				Savebutton.click();
				wait.until(ExpectedConditions.visibilityOf(kpiTableData));
				List<WebElement> listOfRows = kpiTableData.findElements(By.tagName("tr"));
				for(WebElement row: listOfRows) {
					List<WebElement> cols = row.findElements(By.tagName("td"));
					for(WebElement col: cols) {
						if(col.getText().equalsIgnoreCase("Knowledge of Java")) {
							isKpi = true;
							logger.log(Status.INFO, "KPI is successfully added.");
						}
						/*else {
							logger.log(Status.INFO, "KPI is not added successfully.");
							isPass=false;
						}*/
					}
				}				
			}
		} catch (Exception e) {
			e.getMessage();
			isKpi=false;
			logger.log(Status.INFO, "Exception while creating new KPI");
			logger.log(Status.ERROR, e.getMessage());
			logger.log(Status.ERROR,e.getCause());
		}
		return isKpi;
	}
	
	public boolean delKpi(String KpiToDel) {
		int curCol=0;
		int curRow=0;
		boolean kpiDel=false;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			logger.log(Status.INFO, "Creating Action object for mouse action");
			Actions actMouse = new Actions(driver);
			logger.log(Status.INFO, "Selecting Performance menu option");
			wait.until(ExpectedConditions.visibilityOf(PerformanceMenu));
			actMouse.moveToElement(PerformanceMenu).build().perform();
			logger.log(Status.INFO, "Selecting Config sub menu option");
			wait.until(ExpectedConditions.visibilityOf(ConfigSubMenu));
			actMouse.moveToElement(ConfigSubMenu).build().perform();
			wait.until(ExpectedConditions.visibilityOf(KpiSubMenu));
			logger.log(Status.INFO, "Click on KPIs sub menu");
			KpiSubMenu.click();
			
			wait.until(ExpectedConditions.visibilityOf(kpiTableData));
			List<WebElement> Rows = kpiTableData.findElements(By.tagName("tr"));
			String [][]wbEle = new String[Rows.size()][2];
			for(WebElement row:Rows) {
				List<WebElement> cols = row.findElements(By.tagName("td"));
				/*if(!nextRow) {
				wbEle[Rows.size()][2]=null; //<--- Creating a two dimensional array.
				}*/
				for(int r=curRow;r<Rows.size();) {
					for(int c=curCol;c<2;) {
					for(WebElement col:cols) {
						if(c==0) {
							WebElement chkBox = col.findElement(By.tagName("input"));
							wbEle[r][c]=chkBox.getAttribute("id");
							c++;
							curCol=c;
							continue;
						}
						if(c==1) {
							WebElement hypLik=col.findElement(By.tagName("a"));
							wbEle[r][c]= hypLik.getText();
							curCol=0;
							break;
						}
					} //<--- End of for each loop
					break;
					}
					r++;
					curRow=r;
					break;
				}
			continue;
			}
			
			for(int r =0;r<wbEle.length;r++) {
				for(int c=0;c<2;c++) {
					if(c==0) {
						driver.findElement(By.id(wbEle[r][c])).click();
					}
					if(c==1) {
						if(KpiToDel.equalsIgnoreCase(driver.findElement(By.linkText(wbEle[r][c])).getText())) {
							driver.findElement(By.id(delBtn)).click();
							driver.findElement(By.id(delBtnPop)).click();
							kpiDel=true;
						}
						if(!kpiDel) {
						driver.findElement(By.id(wbEle[r][c-1])).click();
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.getMessage();
			logger.log(Status.INFO, "Exception while deleting KPI");
		}
		
		
		return kpiDel;
	}
} // End of Performance
