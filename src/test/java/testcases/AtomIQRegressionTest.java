package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AIQPages.Dataset;
import AIQPages.LoginPage;

public class AtomIQRegressionTest {

    WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    ExtentReports reports;
    ExtentTest test;
    SoftAssert softAssertValue;
    WebDriverWait wait;

	@BeforeTest 
	public void callURL() throws IOException {
				
		driver = new ChromeDriver();
		reports = new ExtentReports("D:\\AIQDIS\\RegressionTest\\src\\test\\java\\OutputReports\\extentReportFiletest.html");
	  	test = reports.startTest("Dataset Creation");
	  	wait = new WebDriverWait(driver, 3);
	  	
	  //Get workbook and sheet 
	  	workbook = new XSSFWorkbook("D:\\InputFilesForSeleniumJavaAutomation\\AIQ Users.xlsx"); 
		sheet = workbook.getSheet("CreateRole"); 
		 
        // implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       
        // open Keycloak web page
        driver.get("https://150.136.142.157/#/login"); 
        System.out.println("Page title : "+driver.getTitle());

       // maximize window
        driver.manage().window().maximize();
      //opening url in unsecured mode  
        
		
		  driver.findElement(By.id("details-button")).click();
		  driver.findElement(By.id("proceed-link")).click();
		 
   }
	    
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
		//POM Model for login functionality      
	    LoginPage login = new  LoginPage(driver,reports,test,wait);
	    login.atomiqLogin();
		System.out.println("Logged into atomiq app successfully");
		Thread.sleep(3000);
		
	}

	@Test(priority = 2)
	public void datasetCreation() throws IOException, InterruptedException {
		//POM Model for Dataset creation
		Dataset createDataset = new  Dataset(driver,reports,test,wait);
		createDataset.createDataset();
	}


	    

	@AfterTest
	public void finishTest() {
		driver.quit();
		reports.endTest(test);
		reports.flush();
	}
	
	

}