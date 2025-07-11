package org.tatacliq.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tatacliq.base.BaseClass;
import org.tatacliq.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases extends BaseClass {
	
LoginPage loginpageobj;
	
	@BeforeClass
	public void objinit() 
	{
		loginpageobj=new LoginPage(driver);				
	}
	
	@AfterClass
	public void tearDown() 
	{  
	  driver.quit(); 
	}
	
	@Test(priority=1)
    public void verifyTataCliqHomePageElements_01() 
	{
        // 1. Verify logo
        Assert.assertTrue(loginpageobj.isLogoDisplayed(), "Tata CLiQ logo is not displayed!");

        // 2. Verify title
        String title = loginpageobj.getHomePageTitle();
        Assert.assertTrue(title.contains("Tata CLiQ"), "Homepage title does not contain 'Tata CLiQ'");
    }
	@Test(priority=2)
	public void verifyCategoryElement_02() {
		String act_text=loginpageobj.verifyCategoryElement();
		Assert.assertEquals(act_text, Constants.Expected_text);
		loginpageobj.clickCategory();
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifyCategoryElement_02"})
	public void clickKidsFashion_03() {
	    loginpageobj.clickKidsFashion();
	    Assert.assertTrue(driver.getCurrentUrl().contains("kids"), "URL does not contain expected keyword!");
	}
	
	@Test(priority = 4, dependsOnMethods = {"clickKidsFashion_03"})
	public void verifyKidsFashionPage_04() {
	    loginpageobj.dismissPopupIfPresent();    // Dismiss popup if visible
	    Assert.assertTrue(loginpageobj.isKidsTitleDisplayed(), 
	        "Kids Online title not displayed as expected!");

	    System.out.println("Verified: 'Kids Online' title is displayed.");
	}
	
	@Test(priority = 5, dependsOnMethods = {"verifyKidsFashionPage_04"})
	public void verifyInfantsFilter_05() {
	    loginpageobj.clickInfantsFilter();
	    Assert.assertTrue(loginpageobj.isInfantsTitleDisplayed(), 
	        "'Infants Online' title not displayed after selecting Infants filter.");
	    System.out.println("Verified: 'Infants Online' title is displayed.");
	}
}
	
	
	
	

