package AIQPages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	WebDriver driver;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    ExtentReports reports;
    ExtentTest test;
    
    public BaseClass(WebDriver driver, ExtentReports reports, ExtentTest test) {
		this.driver=driver;
		this.reports=reports;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
}
