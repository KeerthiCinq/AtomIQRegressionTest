package testBasePackage;



import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("incognito");
		chromeOptions.addArguments("start-maximized");
	//	WebDriverManager.chromedriver().setup();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		chromeOptions.merge(capabilities);
		
		driver = new ChromeDriver(chromeOptions);
		
		
		
		// Navigate to the demoqa website
		driver.get("https://www.demoqa.com");
		
		driver.quit();

	}

    @AfterSuite
    public void afterSuite() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
