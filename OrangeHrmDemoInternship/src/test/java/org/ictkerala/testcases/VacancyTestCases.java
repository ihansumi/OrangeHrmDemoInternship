package org.ictkerala.testcases;

import java.time.Duration;

import org.ictkerala.base.BaseClass;
import org.ictkerala.pages.VacancyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VacancyTestCases extends BaseClass{
VacancyPage vacancypageobj;
	
	@BeforeClass
	public void objinit() {
		vacancypageobj=new VacancyPage(driver);
				
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	//first test case
	@Test(priority=1)
	public void verify_dashboard()
	{
		vacancypageobj.loginusername("Admin");
		vacancypageobj.loginpassword("admin123");
		vacancypageobj.logbuttn();
		// 1. Assert that Dashboard header is visible
		WebElement header=vacancypageobj.getDashboardHeader();
		Assert.assertTrue(header.isDisplayed(), "Dashboard header is not visible");
				 
				// 2. Assert that URL contains '/dashboard'
		 String curturl=vacancypageobj.getCurrentUrldashboard();
		Assert.assertTrue(curturl.contains("/dashboard"), "URL does not contain '/dashboard'");

		System.out.println("Dashboard page verified successfully.");
	}
	

	@Test(priority=2)
	public void verify_recruitment() {
		//Verify that "Recruitment" menu is displayed
	    WebElement recruitmentMenu = vacancypageobj.getRecruitmentMenu();
	    Assert.assertTrue(recruitmentMenu.isDisplayed(), "'Recruitment' menu is not displayed");
	    
	 // Click on the menu
	    vacancypageobj.clickRecruitmentMenu();
	    
	 //Verify that URL contains '/recruitment'
	    String currentUrl = vacancypageobj.getCurrentUrl();
	    Assert.assertTrue(currentUrl.contains("/recruitment"), "URL does not contain '/recruitment'");
	    System.out.println("Recruitment page navigation verified."); 
	    
	}
	@Test(priority=3)
	public void verify_recruitmenttitle() {
		// Verify Recruitment header is displayed
	    WebElement recruitmentHeader = vacancypageobj.getRecruitmentHeader();
	    Assert.assertTrue(recruitmentHeader.isDisplayed(), "Recruitment header is not displayed");
	    System.out.println("Recruitment is displayed on Page header/Title."); 
	}
	@Test(priority=4)
	public void Verify_vacanciestab() {
		
		// Step 1: Verify 'Candidates' tab is displayed
	    WebElement candidatesTab = vacancypageobj.getCandidatesTab();
	    Assert.assertTrue(candidatesTab.isDisplayed(), "'Candidates' tab is not displayed");

	    // Step 2: Verify 'Vacancies' tab is displayed
	    WebElement vacanciesTab =vacancypageobj.getVacanciesTab();
	    Assert.assertTrue(vacanciesTab.isDisplayed(), "'Vacancies' tab is not displayed");

	    // Step 3: Click on 'vacancies' tab
	    vacancypageobj.clickVacanciesTab();

	    System.out.println("'Candidates' and 'Vacancies' tabs verified. Clicked on 'vacancies' tab.");
	}
	@Test(priority=5)
	public void verify_vacanciestitle() {
		// Verify URL contains '/recruitment/viewJobVacancy'
	    String currentUrl = vacancypageobj.getCurrentUrl();
	    Assert.assertTrue(currentUrl.contains("/recruitment/viewJobVacancy"),
	            "URL does not contain '/recruitment/viewJobVacancy'");

	    // Verify the page heading is 'vacancies'
	    WebElement heading = vacancypageobj.getVacanciesPageHeading();
	    Assert.assertTrue(heading.isDisplayed(), "'vacancies' heading is not displayed");

	    System.out.println("Verified: vacanciespage URL and heading.");
	}
	
	@Test(priority=6)
	public void searchvacancies_by_jobtitle() {
		// Click on Job Title dropdown
		vacancypageobj.clickJobTitleDropdown();

	    // Select 'Software Engineer'
		vacancypageobj.selectJobTitle("Software Engineer");

	    // Click on Search
		vacancypageobj.clickSearchButton();

	    // Verify first result contains 'Software Engineer'
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement resultTitle = wait.until(ExpectedConditions.visibilityOf(vacancypageobj.getFirstResultJobTitle()));

	    Assert.assertTrue(resultTitle.getText().contains("Software Engineer"),
	        "First result job title does not match 'Software Engineer'");

	    System.out.println("Job Title filter verified for 'Software Engineer'");
	}
	
	@Test(priority=7)
	public void search_vacancies_by_vacancy() {
		// Reset Job Title field
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	vacancypageobj.resetJobTitleDropdown();
	
	// Wait until Vacancy field becomes visible
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Vacancy']")));

    vacancypageobj.clickVacancyDropdown();
    vacancypageobj.selectVacancy("Senior QA Lead");
    vacancypageobj.clickSearchButton();

    // Extra wait for JS render
    WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
    waiting.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='data' and text()='Senior QA Lead']")
    ));

    String result =vacancypageobj.getFirstVacancyFromResult();
    Assert.assertEquals(result, "Senior QA Lead", "Vacancy mismatch!");
    
}
	@Test(priority=8)
	public void search_vacancies_by_HiringManager() {
		// Step 1: Reset Vacancy filter to "-- Select --"
		vacancypageobj.resetVacancyFilter();
		

        // Step 2: Select "John Doe" from Hiring Manager dropdown
		vacancypageobj.selectHiringManager("John Doe");

        // Step 3: Click Search button
		vacancypageobj.clickSearchButton();

        // Step 4: Validate all rows in the Hiring Manager column contain "John Doe"
        String hiringManager = vacancypageobj.getFirstHiringManagerName();
        System.out.println("Hiring Manager: " + hiringManager);

        // Now assert that it’s not empty or null
        Assert.assertNotNull(hiringManager, "Hiring Manager name is null");
        Assert.assertFalse(hiringManager.isEmpty(), "Hiring Manager name is empty");

    }
	@Test(priority=9)
	public void search_vacancies_by_status() {
		 // Step 1: Select "Active" from Status dropdown
		vacancypageobj.selectCandidateStatus("Active");

	    // Step 2: Click Search
		vacancypageobj.clickSearchButton();

	    // Step 3: Verify all candidates in results have status "Active"
	    String statusText = vacancypageobj.getFirstStatusText();
	    System.out.println("Status: " + statusText);

	    // Assert it is not null or empty
	    Assert.assertNotNull(statusText, "Status text is null");
	    Assert.assertFalse(statusText.isEmpty(), "Status text is empty");
	}	
	
	@Test(priority=10)
	public void Verify_Reset_Button_ClearsAllFields()
	{
			vacancypageobj.verifyResetbutton();
		    // Assertions to verify fields are cleared
		    Assert.assertTrue(vacancypageobj.getDropdownText("Job Title").contains("-- Select --"), "Job Title not reset");
		    Assert.assertTrue(vacancypageobj.getDropdownText("Vacancy").contains("-- Select --"), "Vacancy not reset");
		    Assert.assertTrue(vacancypageobj.getDropdownText("Hiring Manager").contains("-- Select --"), "Hiring Manager not reset");
		    Assert.assertTrue(vacancypageobj.getDropdownText("Status").contains("-- Select --"), "Status not reset");
	}
	@Test(priority=11)
	public void verify_addbutton() {
		vacancypageobj.verifyAddbutton();
		// Assertion to verify "Add Vacancy" title is displayed
	    Assert.assertTrue(vacancypageobj.isAddVacancyTitleDisplayed(), "'Add Candidate' title is not displayed.");
	}
	@Test(priority=12)
	public void verify_cancelbutton() {
		vacancypageobj.verifycancelbutton();
		  WebElement heading = vacancypageobj.getVacanciesPageHeading();
		    Assert.assertTrue(heading.isDisplayed(), "'vacancies' heading is not displayed");
		
	}
	@Test(priority=13)
	public void verify_deleteicon() {
		 Assert.assertTrue(vacancypageobj.isDeleteIconPresentInFirstRow(), "Delete icon is not present in the first record row.");
	}
	@Test(priority=14)
	public void verify_singlevacancydeletion() {
		
		vacancypageobj.deleteFirstVacancy();
		Assert.assertTrue(vacancypageobj.isSuccessMessageDisplayed(), "Success message 'Successfully Deleted' not displayed!");
		System.out.println("success message 'successfully deleted' is displayed");
	}
	@Test(priority=15)
	public void canceldelete() {
		// Step 1: Get the vacancy name before cancel
	    String nameBefore = vacancypageobj.getFirstvacancyName();
	    // Step 2: Click delete and cancel
	    vacancypageobj.cancelDeleteVacancy();
	    // Step 3: Get the vacancy name after cancel
	    String nameAfter = vacancypageobj.getFirstvacancyName();
	    // Step 4: Assert the vacancy is not deleted
	    Assert.assertEquals(nameAfter, nameBefore, "Vacancy was deleted even after cancel.");
	}
	@Test(priority=16)
	public void verify_edit() {
		vacancypageobj.clickEditvacancyIcon();
	    // Step 2: Verify the "Application Stage" title is displayed
	    boolean result =vacancypageobj.isEditVacancyPageDisplayed();
	    Assert.assertTrue(result, "'Edit Vacancy' title is not displayed after clicking edit.");
	   
	}
	@Test(priority=17)
	public void click_logout() {
		vacancypageobj.logout();
		
		
	}


}
