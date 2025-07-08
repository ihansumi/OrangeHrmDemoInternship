package org.tatacliq.pages;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

WebDriver driver;
	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
    }
	
	// Method to get the homepage title
    public String getHomePageTitle() {
        return driver.getTitle();
    }
	// Method to verify the Tata CLiQ homepage logo
    public boolean isLogoDisplayed() {
    	 WebElement logo = driver.findElement(By.xpath("//div[@class='DesktopHeader__logoHolder']"));
         return logo.isDisplayed();
    }
    public String verifyCategoryElement() {
    	WebElement category=driver.findElement(By.xpath("//div[@class='DesktopHeader__categoryAndBrand' and contains(text(),'Categories')]"));
    	return category.getText();   	
    }
    public void clickCategory() {
    	WebElement categorybut=driver.findElement(By.xpath("//div[@class='DesktopHeader__categoryAndBrand' and contains(text(),'Categories')]"));
    	categorybut.click();
    }
    public String verifyGadgetsbutton() {
    	WebElement gadgetbutt=driver.findElement(By.xpath("//div[@class='DesktopHeader__categoryDetailsValue' and contains(text(),'Gadgets')]"));
    	return gadgetbutt.getText();
    }
    public void gadbuttonClick() {
    	WebElement gadbuttonclick=driver.findElement(By.xpath("//div[@class='DesktopHeader__categoryDetailsValue' and contains(text(),'Gadgets')]"));
    	gadbuttonclick.click();
    }
    public boolean isElectronicsTitleDisplayed() {
        WebElement title = driver.findElement(By.xpath("//div[@class='Plp__headerHeading']/h1[text()='Electronics Online']"));
        return title.isDisplayed();
    }
    public void searchBarLocate() {
    	driver.findElement(By.xpath("//input[@id='search-text-input']")).click();
    	   	
    }
    public void searchAppleProd(String prod) {
    	WebElement appleprod=driver.findElement(By.xpath("//input[@id='search-text-input']"));
    	appleprod.sendKeys(prod);
    }
    public void dismissPopupIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement noThanksButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("moe-dontallow_button")));
            noThanksButton.click();
            System.out.println("Popup dismissed: Clicked 'No, Thanks'");
        } catch (TimeoutException e) {
            System.out.println("No popup appeared.");
        }
    }
    public void searchProduct(String productName) {
        dismissPopupIfPresent(); // <-- Handle the popup first

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-text-input")));

        try {
            searchInput.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchInput);
        }

        searchInput.clear();
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
    }
    public boolean isAppleAirpodsProDisplayed() {     
            WebElement product = driver.findElement(By.xpath("//h2[contains(text(),'Apple AirPods Pro (2nd Generation)')]"));
            return product.isDisplayed();     
    }
    public void appleAirpodsclick() {
    	
    }
}
