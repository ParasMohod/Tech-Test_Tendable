package com.initialization.driver;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	public static final Logger log = Logger.getLogger(String.valueOf(OptionsManager.class));

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
//		co.setExperimentalOption("excludeSwitches", "enable-automation");
//		co.setExperimentalOption("useAutomationExtension", false);
//		co.addArguments("--disable-blink-features=AutomationControlled");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("--incognito");
		return co;		
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");
		return fo;		
	}
	
}
