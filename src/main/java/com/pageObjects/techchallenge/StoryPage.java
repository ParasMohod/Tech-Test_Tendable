package com.pageObjects.techchallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class StoryPage {
	
    WebDriver driver;
    
    public StoryPage(WebDriver driver) {
    	System.out.println("In StoryPage");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

}
