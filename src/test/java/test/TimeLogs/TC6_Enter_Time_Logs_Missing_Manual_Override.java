package test.TimeLogs;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import pageObjects.EditTimeLogsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import test.TC1_Login_To_Application;
import utilities.ReadExcelData;

@Listeners(utilities.TestNGListeners.class) // need to uncomment this when running this TC only
public class TC6_Enter_Time_Logs_Missing_Manual_Override extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	EditTimeLogsPage editTimeLogsPage;
	TC1_Login_To_Application tc1;

	public static String sheetName = "TimeTracker_ETL"; // sheet name of the test data to be used
	public static String tcName = "TC6_TimeTracker"; // test case name

	// TestNG Annotation
	// This is the Actual Test

	@Test(priority = 1)
	public void Enter_Time_Logs_Missing_Manual_Override() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		editTimeLogsPage = new EditTimeLogsPage(getDriver());

		// Log in to Time Tracker Application
		loginPage.proceedToLoginPage();
		loginPage.setUsername(sheetName, tcName, "Username");
		loginPage.setPassword(sheetName, tcName, "Password");
		loginPage.clickLogin();
		homePage.verifySuccessfulLogin();
		
		// Selects Time Sheet Period
		homePage.selectTimeSheetPeriod(sheetName, tcName, "TimeSheetPeriod");
		
		// Gets the Date from external source file
		String date = homePage.getDate(sheetName, tcName, "Date");
		
		// Checks if Date is in the selected Time Sheet Period
		if (homePage.checkIfDateExists(date).equalsIgnoreCase("Yes")) {
			
			// Clicks the Date
			homePage.clickDate(date);
			
			// Checks if Date is in the Edit Time Logs Page
			String dateExists = editTimeLogsPage.checkIfDateExists(date);
			
			if (dateExists.equalsIgnoreCase("Yes")) {
				
				// Sets Time Log Details, save the time logs and verify if details have reflected in the table
				editTimeLogsPage.setTimeIn(sheetName, tcName, "TimeInHR", "TimeInMin", "TimeInDP",
						"TimeInLoc", date);
				editTimeLogsPage.setTimeOut(sheetName, tcName, "TimeOutHR", "TimeOutMin", "TimeOutDP",
						"TimeOutLoc", date);
				editTimeLogsPage.saveTimeLogs();
				editTimeLogsPage.verifyIfMissingOverrideReasonErrorMessageIsDisplayed();
				editTimeLogsPage.cancelTimeLogs();
			}
			
		} else {
			homePage.dateNotFound();
		}
		homePage.clickLogout();
		loginPage.verifyUserIsLoggedOut();
	}
}