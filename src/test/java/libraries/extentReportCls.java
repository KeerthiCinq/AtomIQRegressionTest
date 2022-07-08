package libraries;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class extentReportCls {
	static ExtentReports reports;
    static ExtentTest test;

	public static void main(String[] args) {
		 
		reports = new ExtentReports("D:\\SeleniumWorkspace\\gitsamplelocalrepo\\AIQ\\extentReportFiletest.html");
    	test = reports.startTest("Role Creation and Mapping");
    	
	
	}

}
