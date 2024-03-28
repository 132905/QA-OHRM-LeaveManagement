package orangehrm.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.commonutils.CommonUtils;
import orangehrm.pageobject.ApplyPage;
import orangehrm.pageobject.LeavePage;
import orangehrm.pageobject.LoginPage;

public class LeaveManagementTest {
	private WebDriver driver;
	private LeavePage leavePage;
	private LoginPage loginPage;
	private ApplyPage applyPage;

	@BeforeMethod
	public void setUp() throws IOException {
		driver = CommonUtils.createDriver(LoginPage.loadProperties());

		loginPage = new LoginPage(driver);
		leavePage = new LeavePage(driver);
		applyPage = new ApplyPage(driver);
		loginPage.browserSetup(driver);
		leavePage.browserSetup(driver);
		applyPage.browserSetup(driver);

	}

	@Test
	public void testLeaveBalance() throws InterruptedException {
		loginPage.doLogin("Admin", "admin123");

//     	loginPage.clickLeaveButton();
//		Thread.sleep(2000);
//		leavePage.clickAssignButton();
//		Thread.sleep(2000);
		leavePage.getCurrentLeaveBalance(driver);
		Thread.sleep(2000);
		applyPage.applyLeave(driver);
     	Thread.sleep(2000);
		
		


	}

	@AfterMethod
	public void tearDown() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.closeBrowser();
	}
}
