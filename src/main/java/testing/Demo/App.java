package testing.Demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Hello world!
 *
 */
public class App 
{	
	
	public String screenshot(String url, String path) throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	driver.get(url);
    	
    	TakesScreenshot scr = (TakesScreenshot) driver;
    	File source = scr.getScreenshotAs(OutputType.FILE);
    	File dest = new File(path);
    	FileUtils.copyFile(source, dest);
    	
    	driver.quit();
    	
    	return dest.getAbsolutePath();
	}
	
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output//testReport.html");

	@Test
	public void test1() throws IOException
    {
    	
		String scr1 = "screenshot/method1.png";
		String d1 = screenshot("https://www.facebook.com", scr1);
    	
        ExtentTest test = extent.createTest("Test Case 1", "Facebook Validation");
        test.pass("This test case passed");
        test.addScreenCaptureFromPath(d1);
        
        extent.attachReporter(htmlReporter);  
       // extent.flush();
        
      
    }
	
	
	@Test
	public void test2() throws IOException
    {
		String scr1 = "screenshot/method2.png";
		String d1 = screenshot("https://www.gmail.com", scr1);
    	
        ExtentTest test = extent.createTest("Test Case 2", "Gmail Validation");
        test.fail("This test case Failed");
        test.addScreenCaptureFromPath(d1);
        
        String scr2 = "screenshot/method3.png";
		String d2 = screenshot("https://www.google.com", scr2);
        ExtentTest test2 = extent.createTest("Test Case 3", "Google Validation");
        test2.pass("This test case passed");
        test2.addScreenCaptureFromPath(d2);
        
        extent.attachReporter(htmlReporter);  
     //   extent.flush();
      
    }
	
	
	
	
	
	
	
	
}
