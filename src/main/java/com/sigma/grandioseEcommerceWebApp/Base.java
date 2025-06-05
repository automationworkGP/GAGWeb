package com.sigma.grandioseEcommerceWebApp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
	
	WebDriverWait wait;
	WebDriver driver;
	CustomerLogin customerLogin;

	public void getscreenshot() throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File src = sc.getScreenshotAs(OutputType.FILE);
		File folderPath = new File("/home/anubhavkuamrjha/eclipse-workspace/GrandioseEcommerceProject/screenshot");
		String filePath = folderPath + File.separator + "screenshot_" + System.currentTimeMillis() + ".png";
		File dest = new File(filePath);
		FileUtils.copyFile(src, dest);

	}

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		driver = new ChromeDriver();
		System.out.println("------------Invoking Browser------------");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://regenics.com/weight-loss-health-screening");
//		String maintainancetext = driver.findElement(By.xpath("//h1[contains(text(),'We’ll be back soon!')]"))
//				.getText();
//		if (maintainancetext.contains("We’ll be back soon!")) {
//			throw new RuntimeException("Test aborted: Page is under maintenance.");
//
//		} else {
//			System.out.println("jn");
//		}

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("🛑 Browser closed successfully");
		}
	}
	
	
	
	
	
	
	
	
	
}
