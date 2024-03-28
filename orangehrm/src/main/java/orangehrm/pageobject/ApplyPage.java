package orangehrm.pageobject;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangehrm.commonutils.CommonUtils;

public class ApplyPage {
	private  WebDriver driver;
	private CommonUtils commonUtils;
	private LoginPage loginPage;

	public static void browserSetup(WebDriver driver) {

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	public ApplyPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")
	private WebElement leaveTypeButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div/input")
	private WebElement fromDateButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div/input")
	private WebElement toDateButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/button")
	private WebElement applyButton;
	
	
//	@FindBy(how = How.CSS, using = "[name= 'lastName']")
//	private WebElement txtLastName;
	
	
	
	public  void applyLeave(WebDriver driver) throws InterruptedException  {
		
		commonUtils.waitForElement(driver, leaveTypeButton, 2);
		Select leaveTypeSelect = new Select(leaveTypeButton);
		
		Select fromDateSelect = new Select(fromDateButton);
		
		Select toDateSelect = new Select(toDateButton);
		
		List<WebElement> leaveTypeOptions = leaveTypeSelect.getOptions();
//		List<WebElement> options = leaveTypeSelect.getOptions();
//		List<WebElement> options = leaveTypeSelect.getOptions();
		
		int randomIndex = new Random().nextInt(leaveTypeOptions.size());
		 
		leaveTypeSelect.selectByIndex(randomIndex);
		Thread.sleep(3000);
		 
	}
}
	
	
	
//public void addNewEmployee(String firstName, String middleName, String lastName) throws InterruptedException {
//commonUtils.waitForElement(driver, addEmp, 10);
//addEmp.click();
//Thread.sleep(2000);
//commonUtils.waitForElement(driver, txtFirstName, 10);
//txtFirstName.sendKeys(firstName);
//commonUtils.waitForElement(driver, txtMiddleName, 10);
//txtMiddleName.sendKeys(middleName);
//commonUtils.waitForElement(driver, txtLastName, 10);
//txtLastName.sendKeys(lastName);
//commonUtils.waitForElement(driver, saveBtn, 10);
//saveBtn.click();
//Thread.sleep(10000);
//}