package org.tatacliq.testcases;

import org.tatacliq.base.BaseClass;
import org.tatacliq.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCases extends BaseClass {
	
LoginPage loginpageobj;
	
	@BeforeClass
	public void objinit() 
	{
		loginpageobj=new LoginPage(driver);				
	}
	
	/*@AfterClass
	public void tearDown() 
	{  
	  driver.quit(); 
	}*/
	
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
	
	@Test(priority = 6)
	public void verifyBumzeeProductPageOpensCorrectly_06() {
	    
		loginpageobj.clickBumzeeProduct();
		loginpageobj.switchToNewTab();

	    Assert.assertTrue(loginpageobj.isBumzeeProductURLCorrect(), "Bumzee product URL is incorrect or product page not opened.");
	    System.out.println("Bumzee product page opened successfully.");
	}
	
	@Test(priority = 7)
	public void verifyBumzeeProductPriceIsCorrect_07() 
	{	    
	    int actualPrice = loginpageobj.getProductPrice();
	    Assert.assertEquals(actualPrice, Constants.EXPECTED_BUMZEE_PRICE, 
	        "Product price does not match! Expected: " + Constants.EXPECTED_BUMZEE_PRICE + " but got: " + actualPrice);	    
	    System.out.println("Product price is correctly displayed as ₹" + actualPrice);
	}
	
	@Test(priority = 8)
	public void verifyCartPageAfterAddToBag_08() {
	    
		loginpageobj.selectSize();
	    System.out.println("Size selected successfully.");

	    loginpageobj.safeClickAddToBag();
	    System.out.println("Product added to bag.");

	    loginpageobj.clickGoToBag();
	    System.out.println("Navigated to bag.");

	    Assert.assertTrue(loginpageobj.isCartUrlCorrect(), "Cart URL does not contain '/cart'");
	    System.out.println("Cart page verified with correct URL.");
	}
}
	
	
	
	

