package com.initialization.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	private Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static final Logger log = Logger.getLogger(String.valueOf(DriverFactory.class));

	/**
	 * This method is used to initialize the driver
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		if(prop.getProperty("browser") == null) {
			String browserName = System.getProperty("browser");
		}
		String browserName = prop.getProperty("browser");
		String browserVersion = prop.getProperty("browserversion");
		log.info("browser name : " + browserName + " and browserversion: " + browserVersion);
		highlight = prop.getProperty("highlight");
		System.out.println("browser name is : " + browserName);
		optionsManager = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("------------ ChromeDriver launch -----------");
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			tlDriver.set(new SafariDriver());
		}
		else{
			System.out.println("please pass the right browserName : " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

//	private void init_remoteDriver(String browserName, String browserVersion) {
//		System.out.println("running test on remote with browser: " + browserName + ":" + browserVersion);
//		if (browserName.equals("chrome")) {
//			DesiredCapabilities caps = DesiredCapabilities.chrome();
//			caps.setCapability("browserName", "chrome");
//			caps.setCapability("browserVersion", browserVersion);
//			caps.setCapability("enableVNC", true);
//			caps.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
//			try {
//				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), caps));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		} else if (browserName.equals("firefox")) {
//			DesiredCapabilities caps = DesiredCapabilities.firefox();
//			caps.setCapability("browserName", "firefox");
//			caps.setCapability("browserVersion", browserVersion);
//			caps.setCapability("enableVNC", true);
//			caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
//			try {
//				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), caps));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properties on the basis of given env
	 * name
	 * 
	 * @return
	 */
	public Properties initProperties() {
		prop = null;
		FileInputStream ip = null;
		String env = "qa";// mvn clean install -Denv="qa"
		try {
			if (env == null) {
				System.out.println("Running on Environment: PROD env....");
				log.info("Running on Environment: PROD env....");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			}
			else {
				System.out.println("Running on Environment: " + env);
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config.properties/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config.properties/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config.properties/stage.config.properties");
					break;
				default:
					System.out.println("No ENV found.....");
					throw new Exception("NOENVFOUNDEXCEPTION");
				}
			}

		} catch (FileNotFoundException e) {
			log.error("file not found exception is coming.....");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			prop = new Properties();
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	 /**
		 * take screenshot Method
		 */
	    public String getScreenshot(WebDriver driver) {
	    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    	String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
	        File destination = new File(path);
	        try {
	        	FileUtils.copyFile(srcFile, destination);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	        return path;
	    }
}
