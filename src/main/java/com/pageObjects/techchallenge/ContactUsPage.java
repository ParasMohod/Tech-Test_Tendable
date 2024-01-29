package com.pageObjects.techchallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.initialization.driver.DriverComponent;
import java.util.List;

public class ContactUsPage {
	
	  WebDriver driver;
	  
    public ContactUsPage(WebDriver driver) {
    	System.out.println("In ContactUsPage");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#form-input-fullName")
    WebElement firstName;
    @FindBy(css = "#form-input-organisationName")
    WebElement organisationName;
    @FindBy(css = "#form-input-cellPhone")
    WebElement mobileNumber;
    @FindBy(css = "#form-input-email")
    WebElement email;
    @FindBy(css = "#form-input-jobRole")
    WebElement jobRole;
    @FindBy(css = "#form-input-message")
    WebElement message;
    @FindBy(css = "#form-input-consentAgreed-0")
    WebElement radioButton;

    @FindBy(xpath = "(//div[@class='text-center']//div/button[contains(text(),'Contact')])[1]")
    WebElement contact;

    @FindBy(xpath = "//select[@id='form-input-jobRole']")
    WebElement dropDown;

    @FindBy(xpath = "//select[@id='form-input-jobRole']/option")
    List<WebElement> selectingText;

    @FindBy(xpath = "(//button[contains(text(),'Submit')])[1]")
    WebElement submitClick;

    @FindBy(xpath = "//div[@class='ff-form-errors']/p")
    WebElement errorMessage;

    public void submitForm() throws InterruptedException {
        Thread.sleep(5000);
        contact.click();
        Thread.sleep(5000);
      firstName.sendKeys("Demo");
      email.sendKeys("abc@gmail.com");
      selectingJobRole();
      mobileNumber.sendKeys("9877897897");
      organisationName.sendKeys("Amazon");
      radioButton.click();
      submitClick.click();
    }

    public void selectingJobRole()
    {
        for(int i=0;i<selectingText.size();i++)
        {
            Select element = new Select(dropDown);
            element.selectByVisibleText("Management");
        }
    }

    public String validateErrorMessage()
    {
    	
       return errorMessage.getText();
    }

}
