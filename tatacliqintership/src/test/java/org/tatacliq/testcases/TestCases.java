package org.tatacliq.testcases;

import org.tatacliq.base.BaseClass;
import org.tatacliq.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases extends BaseClass {
	
LoginPage loginpageobj;
	
	@BeforeMethod
	public void objinit() 
	{
		loginpageobj=new LoginPage(driver);				
	}
	
	@AfterClass
	public void tearDown() {
	    if (driver != null) {
	        driver.quit(); 
	    }
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
	@Test(priority=3)
	public void verifyGadgetmenu_03() {
		String actual_gadtext=loginpageobj.verifyGadgetsbutton();
		Assert.assertEquals(actual_gadtext, Constants.Expected_gadtext);
		loginpageobj.gadbuttonClick();
	}
	@Test(priority=4)
	public void verifyElectronicsTitle_04() {
		Assert.assertTrue(loginpageobj.isElectronicsTitleDisplayed(), "Title not displayed");
	}
	@Test(priority=5)
	public void searchAppleProduct_05() 
	{
	    loginpageobj.searchProduct("apple airpod pro 2nd");
	    Assert.assertTrue(loginpageobj.isAppleAirpodsProDisplayed(), 
	            "Apple AirPods Pro (2nd Generation) product is not displayed!");
	    System.out.println("Apple AirPods Pro (2nd Generation) is displayed.");
	}
	@Test
	public void 
	}
	
	
	
	
	

