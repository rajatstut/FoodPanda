package com.test.FP;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class verifyloginFunctionality {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.foodpanda.in/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
	}

	@Test
	public void testFP3() throws Exception {
		driver.findElement(By.cssSelector("a.js-auth-login > span.label"))
				.click();
		driver.findElement(By.id("customer_login_email")).clear();
		driver.findElement(By.id("customer_login_email")).sendKeys(
				"rajatstut@yahoo.com");
		driver.findElement(By.id("customer_login_password")).clear();
		driver.findElement(By.id("customer_login_password"))
				.sendKeys("test123");
		driver.findElement(By.id("customer_login_rememberCustomerLogin"))
				.click();
		driver.findElement(By.id("customer_login_login")).click();
		driver.findElement(By.cssSelector("span.label")).click();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
