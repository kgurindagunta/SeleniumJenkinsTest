package tests;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driverFactory.InitDriver;
import pages.LoginPage;
import utility.ExtentReportManager;
import utility.RetryAnalyzer;
import utility.ScreenshotUtil;

public class LoginTest {

	static InitDriver driver = new InitDriver();

	ExtentReportManager extent = new ExtentReportManager();

	@BeforeSuite
	public void onSuiteStart() {

		extent.setupExtentReport();

	}

	@AfterSuite
	public void onsuiteEnd() {
		extent.flush();

	}

	@Parameters({ "browserType" })
	@BeforeMethod
	public void testSetup(String browser) throws IOException {
		driver.initBrowser(browser);
		//extent.setupExtentReport();

		InitDriver.getDriver().get(driver.getProperties().getProperty("url"));
		InitDriver.getDriver().manage().window().maximize();
		InitDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@AfterMethod
	public void tearDown() {
		InitDriver.getDriver().quit();
		//extent.flush();	
		InitDriver.removeDriver();
	}

	@Test()
	public static void validLoginTest() throws InterruptedException, IOException {

		LoginPage login = new LoginPage(InitDriver.getDriver());
		login.validLoginText(driver.getProperties().getProperty("uname"), driver.getProperties().getProperty("pwd"));
		Thread.sleep(5000);
		//ScreenshotUtil.takeScreenshot(InitDriver.getDriver(), "validLoginTest");	
	}

	@Test()
	public static void invalidLoginTest() throws InterruptedException, IOException {

		LoginPage login = new LoginPage(InitDriver.getDriver());
		login.validLoginText(driver.getProperties().getProperty("uname"), "ing");
		String wrongAlert = login.errorTextAlert();
		Assert.assertEquals(driver.getProperties().getProperty("invalidError"), wrongAlert);
		Thread.sleep(5000);
		//ScreenshotUtil.takeScreenshot(InitDriver.getDriver(), "InvalidLoginTest");
	}

}
