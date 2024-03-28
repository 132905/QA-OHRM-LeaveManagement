package orangehrm.pageobject;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangehrm.commonutils.CommonUtils;

public class LeavePage {
	private final WebDriver driver;
	private CommonUtils commonUtils;
	private ApplyPage applyPage;

	public static void browserSetup(WebDriver driver) {

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	public LeavePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[7]/a")
	private WebElement assignButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/a")
	private WebElement applyButton;
	
	
//	@FindBy(how = How.CSS, using = "[name= 'lastName']")
//	private WebElement txtLastName;
	
	
	
	public  double getCurrentLeaveBalance(WebDriver driver) throws InterruptedException  {
		
		commonUtils.waitForElement(driver, assignButton, 2);
		assignButton.click();
		Thread.sleep(2000);
		
		WebElement leaveBalanceElement = driver.findElement(By.cssSelector("p.oxd-text.oxd-text--p.orangehrm-leave-balance-text"));
        String leaveBalanceText = leaveBalanceElement.getText();
        String modifiedText = leaveBalanceText.replace(" Day(s)", "");
        double leaveBalance = Double.parseDouble(modifiedText);
        System.out.println("LeaveBalance: "+leaveBalance);
        
        if(leaveBalance == 0) {
        	System.out.println("So, LeaveBalance will be updated to 10 Days");
        	String script = "arguments[0].innerText = arguments[1];";
            ((JavascriptExecutor) driver).executeScript(script, leaveBalanceElement, String.valueOf(10));
            
            commonUtils.waitForElement(driver, applyButton, 2);
        	applyButton.click();
         	Thread.sleep(2000);
//         	applyPage.applyLeave(driver);
//         	Thread.sleep(2000);
            
        }else {
        	commonUtils.waitForElement(driver, applyButton, 2);
        	applyButton.click();
         	Thread.sleep(2000);
//         	applyPage.applyLeave(driver);
//         	Thread.sleep(2000);
        }
  
        return leaveBalance;
    }
	
	public void checkBal()throws InterruptedException{
		
		commonUtils.waitForElement(driver, assignButton, 2);
		assignButton.click();
		Thread.sleep(2000);
		
		WebElement leaveBalanceElement = driver.findElement(By.cssSelector("p.oxd-text.oxd-text--p.orangehrm-leave-balance-text"));
        String leaveBalanceText = leaveBalanceElement.getText();
        String modifiedText = leaveBalanceText.replace(" Day(s)", "");
        double leaveBalance = Double.parseDouble(modifiedText);
        
        if(leaveBalance>0) {
        	commonUtils.waitForElement(driver, applyButton, 2);
        	applyButton.click();
         	Thread.sleep(2000);
         	applyPage.applyLeave(driver);
        }
	}
	
	public boolean  verifyIfBalUpdated()  throws InterruptedException {
		
		try {
	        // current leave balance
	        double initialLeaveBalance = getCurrentLeaveBalance(driver);

	        // leave application page
	        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/leave/applyLeave");

	       //APPLY FOR LEAVE LOGIC TO BE WRITTEN

	        // applying for leave
	        double updatedLeaveBalance = getCurrentLeaveBalance(driver);

	        // Verify that the leave balance is updated as expected
	        if (updatedLeaveBalance < initialLeaveBalance) {
	            System.out.println("Leave balance is updated after applying for leave.");
	            return true;
	        } else {
	            System.out.println("Leave balance is not updated after applying for leave.");
	            return false;
	        }
	    } finally {
	    	
	        // Closing WebDriver instance
	        driver.quit();
	    }

		
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