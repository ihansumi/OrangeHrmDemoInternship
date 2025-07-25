package org.ictkerala.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VacancyPage {
	WebDriver driver;
	public VacancyPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	public void loginusername(String uname) {
		WebElement orglogin=driver.findElement(By.xpath("//input[@name='username']"));
		orglogin.sendKeys(uname);		
	}
	public void loginpassword(String pass) {
		WebElement orgpass=driver.findElement(By.xpath("//input[@type='password']"));
		orgpass.sendKeys(pass);	
	}
	public void logbuttn() {
		WebElement button=driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();
	}
	public WebElement getDashboardHeader() {
		// Returns the dashboard header WebElement
		WebElement dashboardheader=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
		return dashboardheader;
	}
	// Returns the current page URL
	public String getCurrentUrldashboard() 
	{
	    String Currenturl=driver.getCurrentUrl();
	    return Currenturl;
	}
	
	// Return the 'Recruitment' menu WebElement
	public WebElement getRecruitmentMenu() {
	    WebElement recruit= driver.findElement(By.xpath("//span[text()='Recruitment']"));
	    return recruit;
	}
	// Click on the 'Recruitment' menu
	public void clickRecruitmentMenu() {
	    getRecruitmentMenu().click();
	}
	
	// Return the current URL after click
	public String getCurrentUrl() {
	    return driver.getCurrentUrl();
	}
	
	// Method to get the Recruitment header WebElement
	public WebElement getRecruitmentHeader() {
	    return driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']"));
	}

	// Method to get the Candidates tab WebElement
	public WebElement getCandidatesTab() {
	    return driver.findElement(By.xpath("//li[@class='oxd-topbar-body-nav-tab --visited']"));
	}
	// Returns the 'Vacancies' tab WebElement
	public WebElement getVacanciesTab() {
	    return driver.findElement(By.xpath("//a[contains(@class,'oxd-topbar-body-nav-tab-item') and text()='Vacancies']"));
	}

	// Clicks the 'Candidates' tab
	public void clickVacanciesTab() {
		getVacanciesTab().click();
	}
	// Get current URL after navigating to Candidates page
	public String getCandidatespageCurrentUrl() {
	    return driver.getCurrentUrl();
	}

	// Return the page heading WebElement (usually an <h5>)
	public WebElement getVacanciesPageHeading() {
	    return driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title' and text()='Vacancies']"));
	}
		
	
	public WebElement getJobTitleDropdown() {
		return driver.findElement(By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text-input')][1]"));
		}
	public WebElement getVacancyDropdown() {
        return driver.findElement(By.xpath("//label[text()='Vacancy']/following::div[contains(@class,'oxd-select-text-input')][1]"));
    }

    public WebElement getHiringManagerDropdown() {
        return driver.findElement(By.xpath("//label[text()='Hiring Manager']/following::div[contains(@class,'oxd-select-text-input')][1]"));
    }

    public WebElement getStatusDropdown() {
        return driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text-input')][1]"));
    }

    // Click on Job Title dropdown
    public void clickJobTitleDropdown() {
        driver.findElement(By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')][1]")).click();
    }
 // Select 'Software Engineer' from dropdown
    public void selectJobTitle(String title) {
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + title + "']")).click();
    }
 // Click on Search button
    public void clickSearchButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    // Get first result's job title from results table
    public WebElement getFirstResultJobTitle() {
        return driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][2]"));
    }
    // Reset or set Job Title to default '-- Select --'
    public void resetJobTitleDropdown() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Job Title dropdown
        WebElement dropdown = driver.findElement(By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text-input')]"));
        dropdown.click();

        // Wait for and select "-- Select --" option only if available
        By optionLocator = By.xpath("//div[@role='listbox']//div[text()='-- Select --']");
        wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator)).click();
    }
    
 // Clicks Vacancy dropdown
    public void clickVacancyDropdown() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//label[text()='Vacancy']/following::div[contains(@class,'oxd-select-text')][1]")));
        dropdown.click();
    }

 // Select specific vacancy by visible text
    public void selectVacancy(String vacancyName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@role='listbox']//span[normalize-space()='" + vacancyName + "']")));
        option.click();
    }
 // Get the text of first Vacancy in the result table
    public String getFirstVacancyFromResult() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for at least one vacancy cell
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='data' and text()='Senior QA Lead']")
        ));

        WebElement vacancyCell = driver.findElement(By.xpath(
            "(//div[@class='data' and text()='Senior QA Lead'])[1]"
        ));

        return vacancyCell.getText().trim();
    }
    
    
 // Reset or set Vacancy to default '-- Select --'
    public void resetVacancyFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Vacancy dropdown
        WebElement dropdown = driver.findElement(By.xpath("//label[text()='Vacancy']/following::div[contains(@class,'oxd-select-text-input')]"));
        dropdown.click();

        // Wait for and select "-- Select --" option only if available
        By optionLocator = By.xpath("//div[@role='listbox']//div[text()='-- Select --']");
        wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator)).click();
    }
 // Select Hiring Manager from dropdown
    public void selectHiringManager(String hiringManagerName) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement hiringManagerDropdown = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//div[@class='oxd-select-wrapper'])[3]")));
        hiringManagerDropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='option' and text()='" + hiringManagerName + "']")));
        option.click();
    }

    
    // Get list of Hiring Manager cell elements from the results table
    public String getFirstHiringManagerName() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[@class='oxd-table-cell oxd-padding-cell'][3]//div[@class='data']")
        ));
        return cell.getText();
    }
     //Reset Button click
        public void resetbutton() {
        	WebElement resetBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
        		    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Reset']")));
        		resetBtn.click();
        }
     // Select a status like "Active" from Status dropdown
        public void selectCandidateStatus(String status) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text-input')]")));
            statusDropdown.click();

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//div[text()='" + status + "']")));
            option.click();
        }

        // Return all cells under the Status column in result table (5th column)
        public String getFirstStatusText() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[@class='oxd-table-cell oxd-padding-cell'][5]//div[@class='data']")
            ));
            return cell.getText();
        }
     // Logout from the Orange HRM application
    	public void logout() {
    	    // Step 1: Click on the user dropdown icon
    	    WebElement userDropdownIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']"));
    	    userDropdownIcon.click();
    	    // Step 2: Click on the 'Logout' link from dropdown
    	    WebElement logoutOption =driver.findElement(By.xpath("//a[@role='menuitem' and text()='Logout']"));
    	    logoutOption.click();
    	}
    	public void searchCandidateByName(String name) {

    	    // Enter candidate name
    	    WebElement nameField = driver.findElement(By.xpath("//div[@class='oxd-autocomplete-text-input']//input[@placeholder='Type for hints...']"));
    	    nameField.sendKeys(name);
    	}
    	public void verifyResetbutton() {
    		WebElement reset=driver.findElement(By.xpath("//button[@type='reset']"));
    		reset.click();
    	}
    	public String getDropdownText(String labelText) {
    	    return driver.findElement(By.xpath("//label[text()='" + labelText + "']/following::div[@class='oxd-select-text-input'][1]")).getText();
    	}
    	public void verifyAddbutton() {
    		WebElement addbutt=driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']"));
    		addbutt.click();
    	}
    	
    	
    	public boolean isAddVacancyTitleDisplayed() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	    WebElement addTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//h6[contains(@class, 'orangehrm-main-title') and text()='Add Vacancy']")
    	    ));
    	    return addTitle.isDisplayed();
    	}
    	public void verifycancelbutton() {
    		WebElement addbutt=driver.findElement(By.xpath("//button[normalize-space()='Cancel']"));
    		addbutt.click();
    	}
    	//Verify the presence of delete icon
    	public boolean isDeleteIconPresentInFirstRow() {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement deleteIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//div[@class='oxd-table-body']//i[contains(@class,'bi-trash')]")
    	    ));
    	    return deleteIcon.isDisplayed();
    	}
    	
    	//verifying Deletion operation
    	public void deleteFirstVacancy() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	    // 1. Click Delete icon
    	    WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//div[@class='oxd-table-body']//i[contains(@class,'bi-trash')]")));
    	    deleteIcon.click();

    	    // 2. Confirm Deletion in Popup
    	    WebElement yesDeleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//button[contains(@class,'oxd-button--label-danger')]")));
    	    yesDeleteBtn.click();
    	}

    	public boolean isSuccessMessageDisplayed() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	    WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//p[text()='Successfully Deleted']")));

    	    return successMessage.isDisplayed();
    	}

    	public boolean isVacancyDeleted(String vacancyName) {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
			    By.xpath("//div[@class='oxd-table-body']//div[text()='" + vacancyName+ "']")));
			return true; 
    	}
    	
    	public void cancelDeleteVacancy() {
    	    // Click delete icon on first vacancy row
    	    WebElement deleteIcon = driver.findElement(By.xpath("(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[1]"));
    	    deleteIcon.click();

    	    // Wait for and click the Cancel button on popup
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//button[normalize-space()='No, Cancel']")));
    	    cancelButton.click();
    	}
    	public String getFirstvacancyName() {
    	    WebElement vacancyName = driver.findElement(By.xpath("(//div[@class='oxd-table-card']//div[2])[1]"));
    	    return vacancyName.getText();
    	}
    	// Clicks the edit button on the first vacancy row
    	public void clickEditvacancyIcon() {
    		WebElement editIcon = driver.findElement(By.xpath("(//i[contains(@class, 'bi-pencil-fill')]/ancestor::button)[1]"));
    	    editIcon.click();
    	}

    	// Verifies if the Edit Vacancy title is displayed
    	public boolean isEditVacancyPageDisplayed() {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//h6[normalize-space()='Edit Vacancy']")
    	    ));
    	    return title.isDisplayed();
    	}

}
