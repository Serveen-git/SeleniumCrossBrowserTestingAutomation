package selenium.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTest {
	WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Parameters("browser")
	@BeforeTest
	void setup(String browser) {
		
	 htmlReporter = new ExtentSparkReporter("testNGExtentReport.html");
	 extentReport = new ExtentReports();
	 extentReport.attachReporter(htmlReporter);
	 
	 if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome is launched");
		}
	}

	@Test
	void Test() {
		extentTest = extentReport.createTest("Google Search","Validate google search page open");
		extentTest.log(Status.INFO, "Starting test case");
		System.out.println("-------------TEST RESULT---------------");
		driver.get("https://www.google.co.in/");
		extentTest.pass("Navigation is done");
		driver.manage().window().maximize();
		extentTest.pass("Window maximization is done");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = driver.getTitle();
		String expected = "Google";
		Assert.assertEquals(actual, expected);
		extentTest.pass("Title is corret");
		extentReport.flush();
	}
	
	@AfterTest
	void CloseBrowser() {
		driver.quit();
	}
}
