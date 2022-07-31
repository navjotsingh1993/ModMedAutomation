package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {

	public WebDriver driver;
	public static Properties prop;
	
	public static String ReadDataFromPropertyFile(String key) throws IOException {
		FileInputStream file = new FileInputStream(
				"C:\\Users\\navjot.gahir\\Downloads\\Navjot Automation\\src\\main\\java\\resources\\Config.properties");
	    prop = new Properties();
		prop.load(file);
		String value = prop.getProperty(key);
        return value;
	}

	
	public WebDriver initializeDriver() throws IOException

	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\navjot.gahir\\Downloads\\Navjot Automation\\src\\main\\java\\resources\\Config.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Users//navjot.gahir//Documents//chromedriver.exe");  
			driver = new ChromeDriver();

		}

		else if (browserName.equals("firefox")) {
			// execute firefox driver
			driver = new FirefoxDriver();
		}

		else if (browserName.equals("IE")) {

		}
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;  
		File source =ts.getScreenshotAs(OutputType.FILE);   
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".jpg";  
		FileUtils.copyFile(source,new File(destinationFile));   
		return destinationFile;


	}
}
