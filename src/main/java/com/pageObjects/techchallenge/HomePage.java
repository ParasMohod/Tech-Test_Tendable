package com.pageObjects.techchallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
    WebDriver driver;
	
    public HomePage(WebDriver driver) {
    	System.out.println("In homepage");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Our Story')]")
    WebElement ourStory;

    @FindBy(xpath = "//a[contains(text(),'Our Solution')]")
    WebElement ourSolution;

    @FindBy(xpath = "//a[contains(text(),'Why Tendable?')]")
    WebElement whyTendable;

    @FindBy(xpath = "//a[contains(text(),'About Us')]")
    WebElement aboutUs;

    @FindBy(xpath = "//div[@class='button-links-panel']/a[contains(text(),'Request a Demo')]")
    WebElement requestDemo;

    @FindBy(xpath = "//div[@class='button-links-panel']/a[contains(text(),'Contact Us')]")
    WebElement contactUs;


   public boolean isOurStoryVisible()
   {
       return ourStory.isDisplayed();
   }

    public StoryPage storyClick()
    {
        ourStory.click();
        return new StoryPage(driver);
    }

    public boolean requestDemoPresent()
    {
     return requestDemo.isDisplayed();
    }

    public boolean requestDemoEnabled()
    {
     return requestDemo.isEnabled();
    }

    public boolean isOurSolutionVisible()
    {
        return ourSolution.isDisplayed();
    }

    public SolutionPage solutionClick()
    {
        ourSolution.click();
        return new SolutionPage(driver);
    }

    public boolean isWhyTendableVisible()
    {
        return whyTendable.isDisplayed();
    }

    public TendablePage tendableClick()
    {
        whyTendable.click();
        return new TendablePage(driver);
    }


    public boolean isAboutUsVisible()
    {
        return aboutUs.isDisplayed();
    }

    public void aboutUsClick()
    {
        aboutUs.click();
    }

    public boolean contactUsVisible()
    {
        return contactUs.isDisplayed();
    }

    public ContactUsPage contactUsClick()
    {
        contactUs.click();
        return new ContactUsPage(driver);
    }

    public void goTo() {
        driver.navigate().to("https://www.tendable.com/");
    }
}
