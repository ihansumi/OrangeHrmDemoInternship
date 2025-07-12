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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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
       
    @FindBy(xpath = "//a[.//h3[text()='Bumzee'] and .//h2[contains(text(),'Bumzee Kids Sky Blue')]]")
    private WebElement bumzeeProductCard;
    
    @FindBy(xpath = "//h3[contains(text(),'₹')]")
    private WebElement productPriceTag;
    
 // Select Size
    @FindBy(xpath = "//div[@class='SizeSelectNewPdp__textHolder']//div[text()='9-12 M']")
    private WebElement sizeSelector;

    // Add To Bag
    @FindBy(xpath = "//button[contains(@class,'ProductDescriptionPage__addToBagPDP') and text()='Add To Bag']")
    private WebElement addToBagButton;

    // Go To Bag
    @FindBy(xpath = "//button[contains(@class,'ProductDescriptionPage__addToBagPDP') and text()='Go To Bag']")
    private WebElement goToBagBtn;
    

    @FindBy(xpath = "//div[contains(@class,'AskTara') and @aria-label='Close'] | //button[contains(@aria-label,'Close chat')]")
    WebElement askTaraCloseButton;

    

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
    public void clickBumzeeProduct() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bumzeeProductCard);
        wait.until(ExpectedConditions.elementToBeClickable(bumzeeProductCard)).click();
    }

    // Handle window switch and return to the new window handle
    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // Verify the URL contains expected text
    public boolean isBumzeeProductURLCorrect() {
        return wait.until(ExpectedConditions.urlContains("bumzee-kids-sky-blue-white-printed-romper"));
    }
    public int getProductPrice() {
        String priceText = wait.until(ExpectedConditions.visibilityOf(productPriceTag)).getText();
        // Remove ₹ and any non-digit characters (like commas, extra spaces)
        String numericPrice = priceText.replaceAll("[^\\d]", "");
        return Integer.parseInt(numericPrice);
    }
 // Select product size
    public void selectSize() {
        wait.until(ExpectedConditions.elementToBeClickable(sizeSelector)).click();
    }

    // Click 'Add to Bag'
    public void safeClickAddToBag() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Try closing Ask Tara widget
            try {
                wait.until(ExpectedConditions.elementToBeClickable(askTaraCloseButton));
                askTaraCloseButton.click();
                System.out.println("Ask Tara widget closed.");
            } catch (Exception e) {
                System.out.println("Ask Tara widget not found or already closed.");
            }

            // Scroll and click the Add to Bag button
            wait.until(ExpectedConditions.visibilityOf(addToBagButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToBagButton);
            Thread.sleep(300);

            try {
                addToBagButton.click(); // Normal click
                System.out.println("Clicked Add to Bag normally.");
            } catch (ElementClickInterceptedException e) {
                // Try JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToBagButton);
                System.out.println("Clicked Add to Bag using JavaScript.");
            }

        } catch (Exception e) {
            System.out.println("Failed to click Add to Bag: " + e.getMessage());
        }
    }

    // Click 'Go to Bag'
    public void clickGoToBag() {
        wait.until(ExpectedConditions.elementToBeClickable(goToBagBtn)).click();
    }

    // Verify cart page URL
    public boolean isCartUrlCorrect() {
        return wait.until(ExpectedConditions.urlContains("/cart"));
    }

}
  
    

  
    	
   

