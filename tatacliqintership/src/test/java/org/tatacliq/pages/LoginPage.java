package org.tatacliq.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize PageFactory
    }

    // ==== ELEMENTS using PageFactory ====

    @FindBy(xpath = "//div[@class='DesktopHeader__logoHolder']")
    private WebElement logo;

    @FindBy(xpath = "//div[@class='DesktopHeader__categoryAndBrand' and contains(text(),'Categories')]")
    private WebElement category;
    
    @FindBy(xpath = "//div[@aria-label=\"Kid's Fashion button, Press right arrow or Enter to expand\"]")
    private WebElement kidsFashionButton;
    
    @FindBy(id = "moe-dontallow_button")
    private WebElement noThanksPopupBtn;

    @FindBy(xpath = "//h1[text()='Kids Online']")
    private WebElement kidsPageTitle;

    @FindBy(xpath = "//div[@aria-label=\"Kid's Fashion button, Press right arrow or Enter to expand\"]")
    private WebElement kidsFashion;
    
 // Infants filter checkbox
    @FindBy(id = "l2FilterDiv-2")
    private WebElement infantsFilterOption;

    @FindBy(xpath = "//h2[contains(text(),'Clt.s Boys Pink & White Cotton Printed Full Sleeves T-Shirt Set')]")
    private WebElement cltsProduct;

    @FindBy(xpath = "//h1[contains(text(),'Infants Online')]")
    private WebElement infantsTitle;
    

    // ==== METHODS using PageFactory ====

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public String verifyCategoryElement() {
        return category.getText();   	
    }

    public void clickCategory() {
        category.click();
    }
    public void clickKidsFashion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(kidsFashionButton)).click();
        System.out.println("Clicked on 'Kid's Fashion' button.");
    }
    
    public void clickInfantsFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(infantsFilterOption)).click();
        System.out.println("Clicked on 'Infants' filter.");
    }

    public boolean isInfantsTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(infantsTitle)).isDisplayed();
    }
    
 // Handle popup if present
    public void dismissPopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(noThanksPopupBtn)).click();
            System.out.println("Popup dismissed: Clicked 'No, Thanks'");
        } catch (TimeoutException e) {
            System.out.println("No popup appeared.");
        }
    }

    // Verify "Kids Online" page title
    public boolean isKidsTitleDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(kidsPageTitle)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
  
}
    

  
    	
   

