package com.test.techChallenge;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.initialization.driver.DriverFactory;
import com.listeners.techchallenge.ExtentReportListener;
import com.pageObjects.techchallenge.*;

public class BaseTest {

	public static WebDriver driver;
	Properties prop;
	DriverFactory df;
	SoftAssert softAssert;

	HomePageTest homePageTest;
	HomePage homePage;
	ContactUsPage contactUsPage;
	SolutionPage solutionPage;
	StoryPage storyPage;
	TendablePage tendablePage;
	ExtentReportListener extentReportListener;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
		softAssert = new SoftAssert();
		df = new DriverFactory();
		prop = df.initProperties();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		System.out.println(12345);
		this.driver = df.initDriver(prop);
		homePage = new HomePage(driver);
		contactUsPage = new ContactUsPage(driver);
		solutionPage = new SolutionPage(driver);
		storyPage = new StoryPage(driver);
		tendablePage = new TendablePage(driver);
		extentReportListener=new ExtentReportListener(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
 
}
