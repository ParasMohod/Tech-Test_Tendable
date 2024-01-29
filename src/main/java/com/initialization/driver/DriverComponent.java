package com.initialization.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import java.time.Duration;

public class DriverComponent {
	
	public static final Logger log = Logger.getLogger(String.valueOf(DriverFactory.class));
	
	public WebDriver driver;

    public DriverComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void staticWait(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
  
}
