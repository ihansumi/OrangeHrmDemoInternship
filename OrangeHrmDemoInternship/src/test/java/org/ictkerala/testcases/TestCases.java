package org.ictkerala.testcases;

import java.time.Duration;
import java.time.LocalDate;
import org.ictkerala.base.BaseClass;
import org.ictkerala.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	@Test(priority=1)
	public void TC_VerifyDashboard_01()
	{
		//login to Dashboard
		loginpageobj.loginusername("Admin");
		loginpageobj.loginpassword("admin123");
		loginpageobj.logbuttn();
		
	    // Assert that Dashboard header is visible
		WebElement header=loginpageobj.getDashboardHeader();
		Assert.assertTrue(header.isDisplayed(), "Dashboard header is not visible");
		
		//Assert that URL contains '/dashboard'
		 String curturl=loginpageobj.getCurrentUrldashboard();
		 Assert.assertTrue(curturl.contains("/dashboard"), "URL does not contain '/dashboard'");
		 System.out.println("Dashboard page verified successfully.");
	}
	
	@Test(priority=2)
	public void TC_Verify_RecruitMenu_02() 
	{
		//Verify that "Recruitment" menu is displayed
	     String act_rectext=loginpageobj.getRecruitmentMenu();
	     Assert.assertEquals(act_rectext, Constants.Expected_rectext);
	     
	     // Click on the menu
	    loginpageobj.clickRecruitmentMenu();
	   
	    //Verify that URL contains '/recruitment'
	    String currentUrl = loginpageobj.getCurrentUrl();
	    Assert.assertTrue(currentUrl.contains("/recruitment"), "URL does not contain '/recruitment'");
	    System.out.println("Recruitment page navigation verified.");
	   
	}

	@Test(priority=3)
	public void TC_Verify_RecruitmentHeader_03() 
	{
		// Verify Recruitment header is displayed
	    WebElement recruitmentHeader = loginpageobj.getRecruitmentHeader();
	    Assert.assertTrue(recruitmentHeader.isDisplayed(), "Recruitment header is not displayed");
	    System.out.println("Recruitment is displayed on Page header/Title.");
	}
	
	@Test(priority=4)
	public void TC_Verify_CandVacancyTabs_04() 
	{		
		// Verify 'Candidates' tab is displayed
	    WebElement candidatesTab = loginpageobj.getCandidatesTab();
	    Assert.assertTrue(candidatesTab.isDisplayed(), "'Candidates' tab is not displayed");
	    //Verify 'Vacancies' tab is displayed
	    WebElement vacanciesTab = loginpageobj.getVacanciesTab();
	    Assert.assertTrue(vacanciesTab.isDisplayed(), "'Vacancies' tab is not displayed");
	    //Click on 'Candidates' tab
	    loginpageobj.clickCandidatesTab();
	    System.out.println("'Candidates' and 'Vacancies' tabs verified. Clicked on 'Candidates' tab.");
	}

	@Test(priority=5)
	public void TC_Verify_CandidPageTitle_05() 
	{
		// Verify URL contains '/recruitment/viewCandidates'
	    String currentUrl = loginpageobj.getCurrentUrl();
	    Assert.assertTrue(currentUrl.contains("/recruitment/viewCandidates"),
	            "URL does not contain '/recruitment/viewCandidates'");
	    // Verify the page heading is 'Candidates'
	    WebElement heading = loginpageobj.getCandidatesPageHeading();
	    Assert.assertTrue(heading.isDisplayed(), "'Candidates' heading is not displayed");
	    System.out.println("Verified: Candidates page URL and heading.");
	}

	@Test(priority=6)
	public void TC_Verify_CandPageFields_06() {
		 Assert.assertTrue(loginpageobj.getJobTitleDropdown().isDisplayed(), "Job Title dropdown is not visible");
		 Assert.assertTrue(loginpageobj.getVacancyDropdown().isDisplayed(), "Vacancy dropdown is not visible");
	     Assert.assertTrue(loginpageobj.getHiringManagerDropdown().isDisplayed(), "Hiring Manager dropdown is not visible");
	     Assert.assertTrue(loginpageobj.getStatusDropdown().isDisplayed(), "Status dropdown is not visible");
	     Assert.assertTrue(loginpageobj.getCandidateNameInput().isDisplayed(), "Candidate Name input is not visible");
	     Assert.assertTrue(loginpageobj.getKeywordsInput().isDisplayed(), "Keywords input is not visible");
	     Assert.assertTrue(loginpageobj.getFromDateInput().isDisplayed(), "From Date input is not visible");
	     Assert.assertTrue(loginpageobj.getToDateInput().isDisplayed(), "To Date input is not visible");
	     Assert.assertTrue(loginpageobj.getMethodOfApplicationDropdown().isDisplayed(), "Method of Application dropdown is not visible");
	     System.out.println("All candidate page fields are present and visible.");
	}

	@Test(priority=7,dependsOnMethods = {"TC_Verify_AddnewRecord_18"})
	public void TC_Verify_JobTitleField_07() {
		// Click on Job Title dropdown
		loginpageobj.clickJobTitleDropdown();
	    // Select 'Software Engineer'
		loginpageobj.selectJobTitle("Software Engineer");
	    // Click on Search
		loginpageobj.clickSearchButton();
	    // Verify first result contains 'Software Engineer'
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement resultTitle = wait.until(ExpectedConditions.visibilityOf(loginpageobj.getFirstResultJobTitle()));
	    Assert.assertTrue(resultTitle.getText().contains("Software Engineer"),
	        "First result job title does not match 'Software Engineer'");
	    System.out.println("Job Title filter verified for 'Software Engineer'");
	}
	
	@Test(priority=8)
	public void TC_Verify_VacancyField_08() 
	{
		// Reset Job Title field
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
	    loginpageobj.resetJobTitleDropdown();
	
	    // Wait until Vacancy field becomes visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Vacancy']")));
        loginpageobj.clickVacancyDropdown();
        loginpageobj.selectVacancy("Senior QA Lead");
        loginpageobj.clickSearchButton();
        String result = loginpageobj.getFirstVacancyFromResult();
        Assert.assertEquals(result, "Senior QA Lead", "Vacancy mismatch!");
	}
	
	@Test(priority=9)
	public void TC_Verify_HiringManager_09() {
		//Reset Vacancy filter to "-- Select --"
		loginpageobj.resetVacancyFilter();
		
       //Select "John Doe" from Hiring Manager dropdown
       loginpageobj.selectHiringManager("John Doe");
       //Click Search button
       loginpageobj.clickSearchButton();
       //Validate all rows in the Hiring Manager column contain "John Doe"
       String hiringManager = loginpageobj.getFirstHiringManagerName();
       System.out.println("Hiring Manager: " + hiringManager);
       // Now assert that it’s not empty or null
       Assert.assertNotNull(hiringManager, "Hiring Manager name is null");
       Assert.assertFalse(hiringManager.isEmpty(), "Hiring Manager name is empty");
   }

	@Test(priority=10)
	public void TC_Verify_CandStatus_010() 
	{
		 // Select "Shortlisted" from Status dropdown
		loginpageobj.selectCandidateStatus();

	    //Click Search button
		loginpageobj.clickSearchButton();

		//Verify all candidates in results have status "Shortlisted"
	    String statusText = loginpageobj.getFirstStatusText();
	    System.out.println("Status: " + statusText);
	    // Assert it is not null or empty
	    Assert.assertNotNull(statusText, "Status text is null");
	    Assert.assertFalse(statusText.isEmpty(), "Status text is empty");
	}	
	@Test(priority=11)
	public void TC_Verify_SearchCandidate_011() {
		 //Search for a candidate
	    loginpageobj.searchCandidateByName("John Doe");
	    loginpageobj.searchButton();
	    //Get the result and verify
	    String actualName = loginpageobj.getFirstCandidateNameFromResults();
	    System.out.println("Candidate found: " + actualName);
	    //Assertion
	    Assert.assertTrue(actualName.contains("John"), "Candidate name does not match or not found.");
	}
	@Test(priority=12)
	public void TC_Verify_keywords_012() {
		// Search with "Java"
	    loginpageobj.searchByKeyword("Java");
	   
	}
	@Test(priority=13)
	public void TC_Verify_DateRange_013() 
	{

		//Step 1–3: Search by date range
	    String from = "2024-01-01";
	    String to = "2025-07-05";
	    loginpageobj.searchByDateRange(from, to);
	    
	 // Step 4: Get the applied date from the first search result
	    String appliedDateStr = loginpageobj.getFirstAppliedDateFromResults();
	    System.out.println("Applied Date: " + appliedDateStr);

	    // Convert all dates from String to LocalDate
	    LocalDate fromDate = LocalDate.parse(from);
	    LocalDate toDate = LocalDate.parse(to);
	    LocalDate appliedDate = LocalDate.parse(appliedDateStr);

	    // Check if applied date is between fromDate and toDate (inclusive)
	    boolean isInRange = (appliedDate.compareTo(fromDate) >= 0) && (appliedDate.compareTo(toDate) <= 0);
	    Assert.assertTrue(isInRange, "Applied date is not within the selected range.");
	}
	@Test(priority=14)
     public void TC_Verify_MethodofApplication_014() 
     {
 	    loginpageobj.searchByMethodOfApplication();

     }
	@Test(priority=15)
	public void TC_Verify_Resetbutton_015()
	{
		loginpageobj.verifyResetbutton();
		    // Assertions to verify fields are cleared
		    Assert.assertEquals(loginpageobj.getCandidateNameFieldValue(), "", "Candidate Name not cleared");
		    Assert.assertEquals(loginpageobj.getKeywordsFieldValue(), "", "Keywords field not cleared");
		    Assert.assertTrue(loginpageobj.getDropdownText("Job Title").contains("-- Select --"), "Job Title not reset");
		    Assert.assertTrue(loginpageobj.getDropdownText("Vacancy").contains("-- Select --"), "Vacancy not reset");
		    Assert.assertTrue(loginpageobj.getDropdownText("Hiring Manager").contains("-- Select --"), "Hiring Manager not reset");
		    Assert.assertTrue(loginpageobj.getDropdownText("Status").contains("-- Select --"), "Status not reset");
		    Assert.assertTrue(loginpageobj.getDropdownText("Method of Application").contains("-- Select --"), "Method of Application not reset");
		    Assert.assertEquals(loginpageobj.getFromDate(), "", "From date not cleared");
		    Assert.assertEquals(loginpageobj.getToDate(), "", "To date not cleared");
	}
	@Test(priority=16)
	public void TC_Verify_multiplefieldSearch_016() {
		// Click on Job Title dropdown
		loginpageobj.clickJobTitleDropdown();
       // Select 'Software Engineer'
	   loginpageobj.selectJobTitle("Software Engineer");
	   loginpageobj.selectHiringManager("Rahul Patil");
	   loginpageobj.clickSearchButton();
	   String jobTitle = loginpageobj.getFirstJobTitleFromResult();
	   String hiringManager = loginpageobj.getFirstHiringManagerFromResult();
	   Assert.assertEquals(jobTitle, "Software Engineer", "Job Title does not match the filter");
	   Assert.assertEquals(hiringManager, "Rahul Patil", "Hiring Manager does not match the filter");
	}
	@Test(priority=17)
	public void TC_Verify_AddButton_017() {
		loginpageobj.verifyAddbutton();
		String heading=loginpageobj.isAddCandidateTitleDisplayed();
		// Assertion to verify "Add Candidate" title is displayed
		Assert.assertEquals(heading, Constants.Expected_msg);
		System.out.println("Add button successfully verified");
	}
	@Test(priority=18)
	public void TC_Verify_AddnewRecord_18() {
		loginpageobj.clickRecruitmentMenu();
		loginpageobj.verifyAddbutton();
		loginpageobj.firstname("Aswathy");
		loginpageobj.lastname("Ravi");
		loginpageobj.emailid("aswathyr@gmail.com");
		loginpageobj.savebutton();
		Assert.assertTrue(loginpageobj.isSaveSuccessMessageDisplayed(), "Success message 'Successfully Saved' not displayed!");
        System.out.println("Success message 'Successfully Saved' is displayed!");
		
	}
	@Test(priority=19)
	public void TC_Verify_ResumeIcon_019() {
		//Check if download icon is visible
		loginpageobj.clickRecruitmentMenu();
	    boolean isVisible = loginpageobj.isResumeDownloadIconPresent();
	   // Assert it is displayed
	    Assert.assertTrue(isVisible, "Resume download icon is not visible in the candidate row.");
	}
	@Test(priority=20)
	public void TC_Verify_EditButton_020() {
		loginpageobj.clickRecruitmentMenu();
		loginpageobj.clickEditCandidateIcon();
	    //Verify the "Application Stage" title is displayed
	    String Act_text = loginpageobj.isApplicationStagePageDisplayed();
	    Assert.assertEquals(Act_text, Constants.Expected_text);
	    System.out.println("Edit button is verified");
	}

	@Test(priority=21)
	public void TC_Veriy_DeleteButtonVisibility_021() {
		 loginpageobj.clickRecruitmentMenu();
		 Assert.assertTrue(loginpageobj.isDeleteIconPresentInFirstRow(), "Delete icon is not present in the first record row.");
		 System.out.println("Delete Icon is present in the first row");
	}
	@Test(priority=22)
	public void TC_Verify_Deletefunction_022() {
		loginpageobj.deleteFirstCandidate();
		Assert.assertTrue(loginpageobj.isSuccessMessageDisplayed(), "Success message 'Successfully Deleted' not displayed!");
		System.out.println("Success message 'Successfully Deleted' is displayed!");
	}

	@Test(priority=23)
	public void TC_Verify_CancelDeleteCandidate_023() {
	    loginpageobj.clickRecruitmentMenu();  // Navigate to candidate list
	    loginpageobj.cancelDeleteCandidate(); // Perform delete cancel operation

	    // Assert that "Successfully Deleted" message is NOT displayed
	    boolean isSuccessMsgShown = loginpageobj.isDeleteSuccessMessageDisplayed();
	    Assert.assertFalse(isSuccessMsgShown, "'Successfully Deleted' message is displayed even after cancelling deletion.");
	}
	@Test(priority=24)
	public void TC_Verify_clicklogout_24() 
	{
		loginpageobj.logout();	
		driver.quit();
	}

	}
		


