package com.pageObjects.techchallenge;

import com.initialization.driver.DriverComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SolutionPage {
	
    WebDriver driver;
    
    public SolutionPage(WebDriver driver) {
    	System.out.println("In SolutionPage");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Our Story')]")
    WebElement ourStory;

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }
}
