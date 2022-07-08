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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Dataset {
	WebDriver driver;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    ExtentReports reports;
    ExtentTest test;
    WebDriverWait wait;
	
    @FindBy(linkText="Datasets")
    WebElement DatasetLink;
	
//	@FindBy(xpath="//i-plus[@class='AddBfeather-32']")
    @FindBy(tagName = "i-plus")
	WebElement AddButton;
	
	@FindBy(xpath="//small[contains(text(),'Oracle')]")
	WebElement OracleAdapter;
	
	@FindBy(xpath="//select[@id='enviornmentId']")
	WebElement Connection;
			
//	@FindBy(xpath="//input[@id='inheritFlag']")
	@FindBy(xpath="//label[(@class='custom-control custom-checkbox')]")
	WebElement InheritCheckBox;
		
	@FindBy(xpath="//select[@id='credentialId']")
	WebElement User;
	
	@FindBy(xpath="//input[@id='datasetName']")
	WebElement DatasetName;
	
	@FindBy(xpath="//input[@id='dataSetTemplate']")
	WebElement DatesetIdentifier;
	
	@FindBy(xpath="//textarea[@id='query']")
	WebElement SQLQuery;
	
	@FindBy(xpath="//select[@id='rowSetLimit']")
	WebElement Rowlimit;
	
	@FindBy(xpath="//button[contains(text(),'Analyze')]")
	WebElement Analyze;
	
	@FindBy(xpath="//div/fieldset/div//div/table/thead/tr//th/label[@class='custom-control custom-checkbox']")
	WebElement SelectAllCheckBox;
	
	@FindBy(xpath="//div/fieldset/div//div/table//tr//td/label[@class='custom-control custom-checkbox']")
	WebElement SelectEachBox;
	
	@FindBy(id="datasetValidate")
	WebElement Validate;
	
	@FindBy(xpath="//span[contains(text(),'Dataset validated')]")
	WebElement ValidationMessage;

	@FindBy(id="datasetSave")
	WebElement ValidateAndSave;
	
		
	public Dataset(WebDriver driver, ExtentReports reports, ExtentTest test,WebDriverWait wait) {
		this.driver=driver;
		this.reports=reports;
		this.test=test;
		this.wait=wait;
		PageFactory.initElements(driver, this);
	}
	
	public void createDataset() throws InterruptedException, IOException {
		
		System.out.println("Waiting for Datasets link to be loaded");
		wait.until(ExpectedConditions.presenceOfElementLocated (By.linkText("Datasets")));
		System.out.println("Datasets found");
		DatasetLink.click();
		System.out.println("Datasets from left panel is clicked");
		
		//Get workbook and sheet		   
        workbook = new XSSFWorkbook("D:\\InputFilesForSeleniumJavaAutomation\\AIQ Users.xlsx");
   
       sheet = workbook.getSheet("Dataset");
       int totalrowCount= sheet.getLastRowNum();
       System.out.println("Total row count in users excel : "+totalrowCount);
		for(int i = 1;i <= totalrowCount; i++){  
			
		System.out.println("Current iteration is "+i);	
		XSSFRow rowNumber = sheet.getRow(i);
         
		String connection = rowNumber.getCell(0).getRichStringCellValue().toString();
//		System.out.println("Connection Value is : "+connection);
		String user =rowNumber.getCell(1).getRichStringCellValue().toString();
//		System.out.println("User Value is : "+user);
		String name =rowNumber.getCell(2).getRichStringCellValue().toString(); 
//		System.out.println("Dataset Name is : "+name);
		String identifier =rowNumber.getCell(3).getRichStringCellValue().toString(); 
//		System.out.println("Dataset Identifier is : "+identifier);
		String query =rowNumber.getCell(4).getRichStringCellValue().toString(); 
//		System.out.println("Query value is : "+query);
		
		DataFormatter dataFormatter = new DataFormatter();
		String rowlimit = dataFormatter.formatCellValue(rowNumber.getCell(5));
//		System.out.println("Rowlimit is : "+rowlimit);
		 
		
		//Click add button to create dataset
		AddButton.click();
		System.out.println("Clicked add button to create dataset");
		//Choose database adapter
		OracleAdapter.click();
		System.out.println("Oracle adapter is selected");
		//Select connection from available options
		selectConnection(connection);
		System.out.println("Connection is chosen");
		//Un check inherit check box
		InheritCheckBox.click();
		System.out.println("Inherit check box is unchecked");
		//Select Database user credential from dropdown
		selectUser(user);
		System.out.println("User is selected from drop down");
		//Enter Dataset name
		DatasetName.sendKeys(name);
		System.out.println("Entered dataset name");
		//Enter Dataset identifier
		DatesetIdentifier.sendKeys(identifier);
		System.out.println("Entered dataset identifier");
		//Enter query
		SQLQuery.sendKeys(query);
		System.out.println("Entered sql query");
		//Choose row limit
		selectRowlimit(rowlimit);
		System.out.println("Rowlimit is selected");
		//Analyze the query
		Analyze.click();
		System.out.println("Analyze button clicked");
		Thread.sleep(2000);

		//Click check box for first query field
		checkBoxSelectionForEachField();
		Thread.sleep(2000);
		
		//Click check box to select all query fields
		checkBoxSelection();
		Thread.sleep(2000);
		
		//Validate the query fields
		Validate.click();
		System.out.println("Dataset validated");
		//Validate the query fields and save the dataset
		
		validationMsgCheck();
		Thread.sleep(3000);
		
		ValidateAndSave.click();
		System.out.println("Dataset validated and saved");
		Thread.sleep(4000);
		}
	}
	
	public void selectConnection(String SelectConnection) {
		Select connectionOption = new Select(Connection);
		connectionOption.selectByVisibleText(SelectConnection);
	}
	
	public void selectUser(String SelectUser) {
		Select userOption = new Select(User);
		userOption.selectByVisibleText(SelectUser);
	}
	
	public void selectRowlimit(String SelectRowLimit) {
		Select rowlimitOption = new Select(Rowlimit);
		rowlimitOption.selectByVisibleText(SelectRowLimit);
	}
	
	public void checkBoxSelection() {
		boolean queryfieldcheckbeforeselection = SelectAllCheckBox.isEnabled();
		System.out.println("Is the checkbox for all fields selected : "+queryfieldcheckbeforeselection);
		SelectAllCheckBox.click();
		boolean queryfieldcheckafterselection = SelectAllCheckBox.isEnabled();
		System.out.println("Is the checkbox for all fields selected : "+queryfieldcheckafterselection);
		System.out.println("Dataset field check box is selected");
		
		System.out.println("Text value for check box : "+SelectAllCheckBox.getText());
		System.out.println("Class name for check box : "+SelectAllCheckBox.getClass());
		System.out.println("Tag name for check box : "+SelectAllCheckBox.getTagName());
		
	}
	
	public void checkBoxSelectionForEachField() {
		boolean queryfieldcheckbeforeselection = SelectEachBox.isEnabled();
		System.out.println("Is the checkbox for all fields selected : "+queryfieldcheckbeforeselection);
		SelectEachBox.click();
		boolean queryfieldcheckafterselection = SelectEachBox.isEnabled();
		System.out.println("Is the checkbox for all fields selected : "+queryfieldcheckafterselection);
		System.out.println("Dataset field check box is selected");
		
		System.out.println("Text value for check box : "+SelectEachBox.getText());
		System.out.println("Class name for check box : "+SelectEachBox.getClass());
		System.out.println("Tag name for check box : "+SelectEachBox.getTagName());
		
	}
	
	public void validationMsgCheck() {
		ValidationMessage.isDisplayed();
		System.out.println("Is the validation message displayed : "+ValidationMessage.isDisplayed());
	}
	
}
