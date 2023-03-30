package test.WorkHours;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import pageObjects.DailyWorkHoursPage;
import pageObjects.EditTimeLogsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import test.TC1_Login_To_Application;
import test.TC2_Enter_Time_Logs;
import test.TimeLogs.TC5_Enter_Time_Logs_TimeIn_and_TimeOut;
import utilities.ReadExcelData;

@Listeners(utilities.TestNGListeners.class) // need to uncomment this when running this TC only
public class TC4_TC5_Daily_Work_Hours_Date_Displayed extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	DailyWorkHoursPage dailyWorkHoursPage;

	public static String sheetName = "Timetracker_DWH"; // sheet name of the test data to be used
	public static String tcName = "TC4_TC5_TimeTracker_DWH"; // test case name

	// TestNG Annotation
	// This is the Actual Test
	@Test
	public void Enter_Work_Hours() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		dailyWorkHoursPage = new DailyWorkHoursPage(getDriver());

		// ---Login to Time Tracker---
		loginPage.proceedToLoginPage();
		loginPage.setUsername(sheetName, tcName, "Username");
		loginPage.setPassword(sheetName, tcName, "Password");
		loginPage.clickLogin();
		homePage.verifySuccessfulLogin();

		homePage.selectTimeSheetPeriod(sheetName, tcName, "TimeSheetPeriod");

		// ---Enter Daily Work Hours---
		homePage.clickWhizHours(sheetName, tcName, "Date");
		dailyWorkHoursPage.verifyDateMonthYearIsDisplayed();
		dailyWorkHoursPage.closeDailyWorkHours();
		homePage.clickLogout();

	}
}