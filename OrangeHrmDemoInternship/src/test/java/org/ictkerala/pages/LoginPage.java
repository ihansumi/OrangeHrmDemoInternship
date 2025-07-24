package org.ictkerala.pages;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
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
	
	//Methods for login to OrangeHrm Dashboard
	public void loginusername(String uname) 
	{
		WebElement orglogin=driver.findElement(By.xpath("//input[@name='username']"));
		orglogin.sendKeys(uname);		
	}
	public void loginpassword(String pass)
	{
		WebElement orgpass=driver.findElement(By.xpath("//input[@type='password']"));
		orgpass.sendKeys(pass);	
	}
	public void logbuttn() 
	{
		WebElement button=driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();
	}

	// Methods for Verifying the Dashboard header and URL
	public WebElement getDashboardHeader() 
	{
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
	
	//Methods for Verifying the REcruitment menu
	// Return the 'Recruitment' menu WebElement
		public String getRecruitmentMenu() {
		   // WebElement recruit= driver.findElement(By.xpath("//span[text()='Recruitment']"));
		   // return recruit;
			WebElement recruit=driver.findElement(By.xpath("//span[text()='Recruitment']"));
			String rectext=recruit.getText();
			return rectext;
		}
		// Click on the 'Recruitment' menu
		public void clickRecruitmentMenu() {
			WebElement recruitbutt=driver.findElement(By.xpath("//span[text()='Recruitment']")); 
			recruitbutt.click();
		}
		// Return the current URL after click
		public String getCurrentUrl() {
		    return driver.getCurrentUrl();
		}

		// Method to get the Recruitment header WebElement
		public WebElement getRecruitmentHeader() {
		    return driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']"));
		}

		//Methods for Verifying Both Vacancies and Candidates tab are present
		// Method to get the Candidates tab WebElement
		public WebElement getCandidatesTab() {
		    return driver.findElement(By.xpath("//li[@class='oxd-topbar-body-nav-tab --visited']"));
		}
		// Returns the 'Vacancies' tab WebElement
		public WebElement getVacanciesTab() {
		    return driver.findElement(By.xpath("//a[contains(@class,'oxd-topbar-body-nav-tab-item') and text()='Vacancies']"));
		}
		// Clicks the 'Candidates' tab
		public void clickCandidatesTab() {
		    getCandidatesTab().click();
		}
		
		//Methods to Verify Candidates page is loaded correctly
		// Get current URL after navigating to Candidates page
		public String getCandidatespageCurrentUrl() {
		    return driver.getCurrentUrl();
		}
		// Return the page heading WebElement 
		public WebElement getCandidatesPageHeading() 
		{
		    return driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title' and text()='Candidates']"));
		}
		
		
		//Methods for Verifying all the required fields and their corresponding dropdowns are present in Candidates page
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

	    public WebElement getCandidateNameInput() {
	        return driver.findElement(By.xpath("//label[text()='Candidate Name']/following::input[1]"));
	    }

	    public WebElement getKeywordsInput() {
	        return driver.findElement(By.xpath("//label[text()='Keywords']/following::input[1]"));
	    }

	    public WebElement getFromDateInput() {
	        return driver.findElement(By.xpath("//input[@placeholder='From']/following-sibling::i[contains(@class, 'oxd-date-input-icon')]"));
	    }

	    public WebElement getToDateInput() {
	        return driver.findElement(By.xpath("//input[@placeholder='To']/following-sibling::i[contains(@class, 'oxd-date-input-icon')]"));
	    }

	    public WebElement getMethodOfApplicationDropdown() {
	        return driver.findElement(By.xpath("//label[text()='Method of Application']/following::div[contains(@class,'oxd-select-text-input')][1]"));
	    }

	    //Methods for Veryfing the functionality of JOB Title field
	    // Click on Job Title dropdown
	    public void clickJobTitleDropdown() 
	    {
	        driver.findElement(By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')][1]")).click();
	    }
	    // Select 'Software Engineer' from dropdown
	    public void selectJobTitle(String title)
	    {
	        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + title + "']")).click();
	    }
	   // Click on Search button
	    public void clickSearchButton() 
	    {
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	    }
	   // Get first result's job title from results table
	    public WebElement getFirstResultJobTitle() 
	    {
	        return driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-cell')][2]"));
	    }
	    
	    
	    // Mehtods to Reset or set JobTitle to default '-- Select --'
	    public void resetJobTitleDropdown() 
	    {
	        // Click Job Title dropdown
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
	        WebElement dropdown = driver.findElement(By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text-input')]"));
	        dropdown.click();

	        // Wait for and select "-- Select --" option 
	        By optionLocator = By.xpath("//div[@role='listbox']//div[text()='-- Select --']");
	        wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator)).click();
	    }
	    // Clicks Vacancy dropdown
	    public void clickVacancyDropdown() {
	        WebElement dropdown =driver.findElement(By.xpath("//label[text()='Vacancy']/following::div[contains(@class,'oxd-select-text')][1]"));
	        dropdown.click();
	    }

	    public void selectVacancy(String vacancyName) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); 
	        //Wait for and click the desired option
	        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@role='listbox']//span[normalize-space(text())='" + vacancyName + "']")));
	        option.click();
	    }

	 // Get the text of first Vacancy in the result table
	    public String getFirstVacancyFromResult() {
	    	
	        WebElement vacancyCell = driver.findElement(By.xpath(
	            "(//div[@class='data' and text()='Senior QA Lead'])[1]"
	        ));

	        return vacancyCell.getText().trim();
	    }

	 // Reset or set Vacancy to default '-- Select --'
	    public void resetVacancyFilter() {

	        // Click Vacancy dropdown
	        WebElement dropdown = driver.findElement(By.xpath("//label[text()='Vacancy']/following::div[contains(@class,'oxd-select-text-input')]"));
	        dropdown.click();

	        // Wait for and select "-- Select --" option only if available
	        WebElement  reselect = driver.findElement(By.xpath("//div[@role='listbox']//div[text()='-- Select --']"));
	        reselect.click();
	    }
	    // Select Hiring Manager from dropdown
	    public void selectHiringManager(String hiringManagerName) {
	        WebElement hiringManagerDropdown =driver.findElement(By.xpath("//div[@class='oxd-select-wrapper'])[3]"));          
	        hiringManagerDropdown.click();
	        
	        WebElement option=driver.findElement(By.xpath("//div[@role='option' and text()='\" + hiringManagerName + \"']"));
	        option.click();
	       
	    }
	   	// Get list of Hiring Manager cell elements from the results table
	    public String getFirstHiringManagerName() {
	    	WebElement cell=driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[@class='oxd-table-cell oxd-padding-cell'][3]//div[@class='data']"));      
	        return cell.getText();
	    }
	    
	   
	    
	    public void selectCandidateStatus() {
	       
	        // 1. Click the Status dropdown (identified by '-- Select --' text)
	        WebElement statusDropdown =driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text-input')][1]"));
	        statusDropdown.click();

	        // 2. Click on the "Shortlisted" option from dropdown
	        WebElement shortlistedOption = driver.findElement(By.xpath("(//div[@role='option' and text()='Shortlisted'])[3]"));
	        shortlistedOption.click();
	    }
	    
	    // Return all cells under the Status column in result table (5th column)
        public String getFirstStatusText() {
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
           WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[@class='oxd-table-cell oxd-padding-cell'][5]//div[@class='data']")
            ));
            return cell.getText();

	    }
        //Methods for searching using candidates name
        public void searchCandidateByName(String name) {

   	    // Enter candidate name
    	    WebElement nameField = driver.findElement(By.xpath("//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']//input[@placeholder='Type for hints...']"));
    	    nameField.sendKeys(name);
    	}
    	public void searchButton() {
    	    // Click the Search button
    	    WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
    	    searchButton.click();
    	}
    	public String getFirstCandidateNameFromResults() {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    	    WebElement candidateCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[@class='oxd-table-cell oxd-padding-cell'][2]//div[@class='data']")));	    
    	    return candidateCell.getText();
    	}
    	
    	public void searchByKeyword(String keyword) 
    	{     	   
     	    // Enter keyword (e.g., "Java")
     	    WebElement keywordField =driver.findElement(By.xpath("//input[@placeholder='Enter comma seperated words...']"));
     	   keywordField.click();
     	    keywordField.sendKeys(keyword);

     	    // Click the Search button
     	    WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));  	    
     	    searchButton.click();
     	}
    	public void searchByDateRange(String fromDate, String toDate) {
     	   // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

     	    // Enter From Date
     	    WebElement fromDateField =driver.findElement(By.xpath("//input[@placeholder='From']"));
     	    fromDateField.clear();
     	    fromDateField.sendKeys(fromDate);

     	    // Enter To Date
     	    WebElement toDateField = driver.findElement(By.xpath("//input[@placeholder='To']"));
     	    toDateField.clear();
     	    toDateField.sendKeys(toDate);

     	    // Click the Search button
     	    WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
     	    searchButton.click();
     	}
     	public String getFirstAppliedDateFromResults() 
     	{
     		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
     		WebElement dateCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
     		    By.xpath("//div[@class='oxd-table-body']//div[@role='row'][1]//div[@class='oxd-table-cell oxd-padding-cell'][4]")
     		));
     		return dateCell.getText();  // Example: "2024-06-15"
     	}
     	
     	public void searchByMethodOfApplication() {
    	    
    	    // Click the Method of Application dropdown
    	    WebElement dropdown = driver.findElement(By.xpath("//label[text()='Method of Application']/following::div[contains(@class, 'oxd-select-text-input')][1]"));	
    	    dropdown.click();

    	    // Select the method (e.g., Manual)
    	    WebElement option = driver.findElement(By.xpath("//div[@class='oxd-select-text-input' and text()='Manual']"));
    	    option.click();

    	    // Click the Search button
    	    WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));  	    		
    	    searchButton.click();
    	}
    	
     	public void verifyResetbutton() {
    		WebElement reset=driver.findElement(By.xpath("//button[@type='reset']"));
    		reset.click();
    	}
    	// Methods to verify all fileds are empty after clicking reset button
    	public String getCandidateNameFieldValue() {
    	    return driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).getAttribute("value");
    	}

    	public String getKeywordsFieldValue() {
    	    return driver.findElement(By.xpath("//input[@placeholder='Enter comma seperated words...']")).getAttribute("value");
    	}

    	public String getDropdownText(String labelText) {
    	    return driver.findElement(By.xpath("//label[text()='" + labelText + "']/following::div[@class='oxd-select-text-input'][1]")).getText();
    	}

    	public String getFromDate() {
    	    return driver.findElement(By.xpath("//input[@placeholder='From']")).getAttribute("value");
    	}

    	public String getToDate() {
    	    return driver.findElement(By.xpath("//input[@placeholder='To']")).getAttribute("value");
    	}
    	// Get first job title from result
    	public String getFirstJobTitleFromResult() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    	    WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='row'])[2]//div[2]")));
    	    return jobTitle.getText().trim();
    	}

    	// Get first hiring manager from result
    	public String getFirstHiringManagerFromResult() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    	    WebElement manager = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='row'])[2]//div[4]")));
    	    return manager.getText().trim();
    	}
    	public void verifyAddbutton() {
    		WebElement addbutt=driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']"));
    		addbutt.click();
    	}
    	   	
    	public String isAddCandidateTitleDisplayed() {   	  
    	    WebElement addTitle =driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='Add Candidate']"));
    	    String title= addTitle.getText();
		     return title;
    	}
    	public boolean isResumeDownloadIconPresent() {
    	    try {
    	        WebElement downloadIcon = driver.findElement(
    	            By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell']//button[contains(@class,'oxd-icon-button') and .//i[contains(@class,'bi-download')]])[1]")
    	        );
    	        return downloadIcon.isDisplayed();
    	    } catch (NoSuchElementException e) {
    	        return false;
    	    }
    	}
    	// Clicks the edit button on the first candidate row
    	public void clickEditCandidateIcon() {
    		WebElement editIcon = driver.findElement(By.xpath("//i[contains(@class, 'bi-eye-fill')]"));
    	    editIcon.click();
    	}

    	// Verifies if the Application Stage title is displayed
    	public String isApplicationStagePageDisplayed() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='Application Stage']")
    	    ));
    	    return title.getText();
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
    	public void deleteFirstCandidate() {
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
  	
    	   	
    	// Clicks the delete icon and then cancels the delete
    	public void cancelDeleteCandidate() {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    // Click the delete icon
    	    WebElement deleteIconcan =  wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("//div[@class='oxd-table-body']//i[contains(@class,'bi-trash')]")));
    	    deleteIconcan.click();

    	    // Wait for Cancel button and click it
    	   // WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(1));
    	    WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost orangehrm-button-margin']")
    	    ));
    	    cancelButton.click();
    	}

    	// Checks if the success message "Successfully Deleted" is displayed
    	public boolean isDeleteSuccessMessageDisplayed() {
    	    try {
    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    	        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	            By.xpath("//*[contains(text(), 'Successfully Deleted')]")
    	        ));
    	        return successMsg.isDisplayed();
    	    } catch (TimeoutException e) {
    	        // If timeout happens, it means the message is NOT displayed, which is expected
    	        return false;
    	    } catch (NoSuchElementException e) {
    	        // If element is not found at all
    	        return false;
    	    }
    	}
    	
    	public void firstname(String name)
    	{
    		WebElement firstname=driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-firstname' and @name='firstName' and @placeholder='First Name']"));
    		firstname.sendKeys(name);
    	}
    	public void lastname(String lname) {
    		
    		WebElement lastname=driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-lastname' and @name='lastName' and @placeholder='Last Name']"));
    		lastname.sendKeys(lname);
    	}
    	public void emailid(String email) {
    		WebElement emailid=driver.findElement(By.xpath("(//input[@placeholder='Type here'])[1]"));
    		emailid.sendKeys(email);
    	}
    	public void savebutton() {
    		WebElement savebut=driver.findElement(By.xpath("//button[@type='submit' and @class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"));
    		savebut.click();
    	}
    	// Method to check if "Successfully Saved" message is displayed
    	public boolean isSaveSuccessMessageDisplayed() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	    WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//p[text()='Successfully Saved']")));
    	    return successMessage.isDisplayed();
    	}
    	public void logout() {
    	    // Step 1: Click on the user dropdown icon
    		WebElement userDropdownIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']"));
    		 userDropdownIcon.click();
    	    // Step 2: Click on the 'Logout' link from dropdown
    	   WebElement logoutOption = driver.findElement(By.xpath("//a[@role='menuitem' and text()='Logout']"));
    	   logoutOption.click();
           }
}

	

    	


    	







	
	
	
	
	
	
	

