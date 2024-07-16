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
import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTest {
	WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	void setup(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox is launched");
		}else if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome is launched");
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge is launched");
		}
	}

	@Test
	void Test() {
		System.out.println("-------------TEST RESULT---------------");
		driver.get("https://www.google.co.in/");
		String actual = driver.getTitle();
		String expect = "Google";
		Assert.assertEquals(actual.contains("google"), expect.contains("Google"));
		System.out.println(actual);
		System.out.println(driver.getCurrentUrl());
	}
	
	@AfterTest
	void CloseBrowser() {
		driver.quit();
	}
}
