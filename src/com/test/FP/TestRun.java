package com.test.FP;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRun {
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
	public void testFP() throws Exception {
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
		driver.findElement(
				By.xpath(".//*[@id='wrapper-element-1']/span/input[2]"))
				.click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(
				"Pune");
		driver.findElement(
				By.cssSelector("div.homepage-area-selection-container.lazy-loaded > div.container"))
				.click();
		driver.findElement(By.id("area")).clear();
		driver.findElement(By.id("area")).sendKeys("Viman nagar");
		driver.findElement(By.id("button")).click();
		driver.findElement(By.cssSelector("p")).click();
		driver.findElement(By.id("button")).click();
	}

	@Test
	public void testFP2() throws Exception {
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(
				"Pune");
		driver.findElement(By.id("area")).clear();
		driver.findElement(By.id("area")).sendKeys("Wakad");
		driver.findElement(By.id("button")).click();
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
