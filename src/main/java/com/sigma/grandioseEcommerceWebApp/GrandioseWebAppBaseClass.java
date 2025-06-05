package com.sigma.grandioseEcommerceWebApp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.sigma.Util.DBUtil;

public class GrandioseWebAppBaseClass {

	private static Connection connection;
	DBUtil dbutil = new DBUtil();
	String otp;
	WebDriverWait wait;
	WebDriver driver;
	CustomerLogin customerLogin;

	public void initialize(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	//@BeforeSuite
	public static Connection connectToDB() {
		String url = "jdbc:mysql://172.28.5.132:3306/preprod_290325?useSSL=false&serverTimezone=UTC";
		String user = "uat-user";
		String password = "OIhdknsihfuatvdh";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Database connected successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static ResultSet executeQuery(String query) {
		if (connection == null) {
			System.out.println("❌ Connection is null. Aborting query.");
			return null;
		}
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	//@AfterSuite
	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
			System.out.println("Database connection closed successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		driver.get("https://preprod.grandiose.ae/customer/account/login");
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
