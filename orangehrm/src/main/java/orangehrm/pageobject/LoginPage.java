package orangehrm.pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangehrm.commonutils.CommonUtils;

public class LoginPage {

	private WebDriver driver;
	private CommonUtils commonUtils;

	public static String loadProperties() throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("src/main/resources/config.properties");
		properties.load(file);
		String browser = properties.getProperty("browser");
		return browser;

	}

	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void browserSetup(WebDriver driver)

	{

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	//	public void closeBrowser() {
	//		try {
	//			driver.quit();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

	@FindBy(how = How.CSS, using = "[name='username']")
	private WebElement usernameElement;

	@FindBy(how = How.CSS, using = "[name='password']")
	private WebElement passwordElement;

	@FindBy(how = How.XPATH, using = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	private WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a")
	private WebElement leaveButton;
	

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void doLogin(String username, String password)  throws InterruptedException  {
		commonUtils.waitForElement(driver, usernameElement, 2);
		usernameElement.sendKeys(username);
		commonUtils.waitForElement(driver, passwordElement, 2);
		passwordElement.sendKeys(password);
		commonUtils.waitForElement(driver, loginButton, 2);
		loginButton.click();
		Thread.sleep(2000);
		
		commonUtils.waitForElement(driver, leaveButton, 2);
		leaveButton.click();
		
		
	}

//	public void clickLeaveButton() {
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		wait.until(ExpectedConditions.visibilityOf(leaveButton));
//
//		leaveButton.click();
//
//	}

	public boolean isLoginSuccessful() {

		return driver.getCurrentUrl().equals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

	}

}
