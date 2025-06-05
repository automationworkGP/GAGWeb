package com.sigma.grandioseEcommerce_MobileApp;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.sigma.Util.DBUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class GrandiosePickerLogin {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
    By denyNotification= By.id("com.android.permissioncontroller:id/permission_deny_button");
    DBUtil dbutil = new DBUtil();

	@BeforeClass
	public void setUp() throws MalformedURLException {

		try {
			UiAutomator2Options opt = new UiAutomator2Options();
			opt.setDeviceName("Grandiose");
			opt.setPlatformName("Android");
			opt.setAutomationName("UiAutomator2");
			opt.setApp(System.getProperty("user.dir")
					+ "/src/test/resources/GrandPick_2.0.1(v84)_24-Apr-2025-release.apk");

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), opt);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("✅ App launched successfully");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
//	public void dbConnection() {
//		
//		 DBUtil.connectToDB();
//	}
	
	
	
// 
//    public static void closeConnection() {
//        try {
//            if (connection != null) connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

	@Test
	public void testLoginWithValidCredentials() throws InterruptedException {
		
//		ResultSet rs=DBUtil.executeQuery("SELECT otp FROM ktpl_loginviasms_transaction");
//		try {
//			int OTP=rs.getInt(0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		WebElement username = driver.findElement(By.id("net.grandiose.picker:id/edt_user_name"));
		username.sendKeys("anubhavtest");
		WebElement password = driver.findElement(By.id("net.grandiose.picker:id/edt_password"));
		password.sendKeys("sigma@123");
		WebElement selectStoredrop = driver.findElement(By.id("net.grandiose.picker:id/actv_select_store"));
		selectStoredrop.click();
		Thread.sleep(1000);
		WebElement searchstore=driver.findElement(By.id("net.grandiose.picker:id/ed_search"));
		searchstore.sendKeys("Grandiose MM Towers");
		List<WebElement> store= driver.findElements(By.id("net.grandiose.picker:id/rv_area"));
		store.get(0).click();                                                                        //android.widget.TextView[@text="Grandiose MM Towers"]
//		WebElement selectStore = driver
//				.findElement(By.xpath("//android.widget.TextView[@text=\"Grandiose - Waterfront\"]"));
//		selectStore.click();
		WebElement loginBtn = driver.findElement(By.id("net.grandiose.picker:id/btn_get_started"));
		loginBtn.click();
		
		WebElement allownotification=driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
		allownotification.click();
		Thread.sleep(1000);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("net.grandiose.picker:id/tab_new")));
	}
	
	
	
//	public void logout() {
//		
//		WebElement logout = driver.findElement(By.id("net.grandiose.picker:id/btn_logout"));
//		WebElement AssignedTab= driver .findElement(By.x)
//		
//		
//		
//	}
	
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("🛑 Driver session ended.");
		}
	}

}
