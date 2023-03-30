package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.TestNGListeners;
import utilities.ReadConfig;
import utilities.ReadExcelData;

public class BaseClass {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public String baseURL;
	public String actualBrowser;
	public static String testDataLoc;
	public static String category;

	ReadExcelData red = new ReadExcelData();
	
	public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

	@Parameters({"categoryXML", "browser", "url", "td"})//These parameters can be configured in testng_Agile.xml
	@BeforeClass
	public void invokeBrowser(String categoryXML, String browser, String url, String td) {
		category = categoryXML; // this will be called by TestNGListeners.java to assign the category
		actualBrowser = browser;
		baseURL = url;
		testDataLoc = td;
		switch (actualBrowser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver.set(new FirefoxDriver());
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				driver.set(new InternetExplorerDriver());
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
				break;
			}

		getDriver().get(baseURL);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
//		getDriver().close();
		getDriver().quit();
		//driver.quit();
		//System.out.println("Test Completed Successfully!");
	}
}
