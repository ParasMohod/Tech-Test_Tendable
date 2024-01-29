package com.test.techChallenge;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.pageObjects.techchallenge.*;


public class HomePageTest extends BaseTest {
	

	@Test(priority = 1,enabled = true)
    public void homePage_Verification() throws InterruptedException {
		homePage = new HomePage(BaseTest.driver);
        AssertJUnit.assertTrue(homePage.isAboutUsVisible());
        storyPage  = homePage.storyClick();
        Thread.sleep(5000);
        storyPage = new StoryPage(BaseTest.driver);
        AssertJUnit.assertTrue(storyPage.getCurrentUrl().equalsIgnoreCase("https://www.tendable.com/our-story"));
        AssertJUnit.assertTrue(homePage.requestDemoPresent());
        AssertJUnit.assertTrue(homePage.requestDemoEnabled());
        solutionPage = new SolutionPage(BaseTest.driver);
        solutionPage  = homePage.solutionClick();
        AssertJUnit.assertTrue(solutionPage.getCurrentUrl().equalsIgnoreCase("https://www.tendable.com/our-solution"));
        AssertJUnit.assertTrue(homePage.requestDemoPresent());
        AssertJUnit.assertTrue(homePage.requestDemoEnabled());
        tendablePage = new TendablePage(BaseTest.driver);
        tendablePage  = homePage.tendableClick();
        AssertJUnit.assertTrue(tendablePage.getCurrentUrl().equalsIgnoreCase("https://www.tendable.com/why-tendable"));
        AssertJUnit.assertTrue(homePage.requestDemoPresent());
        AssertJUnit.assertTrue(homePage.requestDemoEnabled());
        homePage = new HomePage(BaseTest.driver);
        contactUsPage = homePage.contactUsClick();
        contactUsPage = new ContactUsPage(BaseTest.driver);
        contactUsPage.submitForm();
        Thread.sleep(5000);
        AssertJUnit.assertEquals(contactUsPage.validateErrorMessage(),"Sorry, there was an error submitting the form. Please try again.");
    }
}
