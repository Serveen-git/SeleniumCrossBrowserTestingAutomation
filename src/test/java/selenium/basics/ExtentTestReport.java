package selenium.basics;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentTestReport {
	
	static ExtentSparkReporter htmlReport;
	static ExtentReports er;
	static ExtentTest et;
	static WebDriver driver;
	
	public static void main(String[] args) {
		htmlReport = new ExtentSparkReporter("extentReport.html");
		er = new ExtentReports();
		er.attachReporter(htmlReport);
		et = er.createTest("Google Search","validate google search page open");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		et.pass("Starting test case");
		driver.manage().window().maximize();
		et.pass("Window maximize has done");
		driver.get("https://www.google.co.in/");
		et.pass("Navigate to google.com");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expectedTitle = "Google";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		et.pass("Title is correct");
		driver.close();
		er.flush();
	}
}
