package AIQPages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.UserType;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {
	WebDriver driver;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    ExtentReports reports;
    ExtentTest test;
    WebDriverWait wait;
    
	@FindBy(xpath="//input[@name='username']")
	WebElement Username;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement Password;
	
	@FindBy(tagName="select")
	WebElement UserTypeDropDown;
	
	@FindBy(linkText="Forgot Password?")
	WebElement ForgotPasswordLink;
			
	@FindBy(xpath="//input[@name='remember']")
	WebElement RememberMeCheckBox;
		
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement LoginButton;
	
	@FindBy(xpath="//a[@href='#/superuser/profile']")
	WebElement ProfileName;
	
	@FindBy(xpath="//span[contains(text(),'Logout')]")
	WebElement LogoutButton;
	
	public LoginPage(WebDriver driver, ExtentReports reports, ExtentTest test,WebDriverWait wait) {
		this.driver=driver;
		this.reports=reports;
		this.test=test;
		this.wait=wait;
		PageFactory.initElements(driver, this);
	}
	
	public void atomiqLogin() throws InterruptedException, IOException {
		
		//Get workbook and sheet		   
        workbook = new XSSFWorkbook("D:\\InputFilesForSeleniumJavaAutomation\\AIQ Users.xlsx");
   
       sheet = workbook.getSheet("AtomIQ Users");
       int totalrowCount= sheet.getLastRowNum();
	//	for(int i = 1;i <= totalrowCount; i++){  
			
       for(int i = 1;i <= 1; i++){  
		XSSFRow rowNumber = sheet.getRow(i);
             
		String username =rowNumber.getCell(0).getRichStringCellValue().toString(); 
		String password =rowNumber.getCell(1).getRichStringCellValue().toString(); 
		String usertype =rowNumber.getCell(2).getRichStringCellValue().toString(); 
		Username.sendKeys(username);
		System.out.println("Username is entered");
		Password.sendKeys(password);
		System.out.println("Password is entered");
		userType();
		System.out.println("UserType is selected");
		Thread.sleep(1000);
		forgotPassword();
		System.out.println("Forgot Password link is enabled : "+forgotPassword());
		Thread.sleep(1000);
		rememberMe();
		System.out.println("Remember me check box is enabled : "+rememberMe());
		Thread.sleep(1000);
		LoginButton.click();
		System.out.println("Clicked login");
		Thread.sleep(1000);
	//	ProfileName.getText();
		Thread.sleep(1000);
	//	LogoutButton.click();
		}
	}
	
	public void userType() {
		Select userTypeOption = new Select(UserTypeDropDown);
		userTypeOption.selectByVisibleText("Super User");
		
	}
	
	
	public boolean forgotPassword() {
		boolean forgotpassword = ForgotPasswordLink.isSelected();
		return forgotpassword;
		
	}
	
	public boolean  rememberMe() {
		boolean rememberme = RememberMeCheckBox.isEnabled();
		return rememberme;
	}
	
	

}
