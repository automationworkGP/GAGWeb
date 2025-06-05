package com.sigma.grandioseEcommerce_MobileApp;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.sigma.Util.DBUtil;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class GrandioseE_commerceMobileAppLogin {

	private static Connection connection;
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	By denyNotification = By.id("com.android.permissioncontroller:id/permission_deny_button");
	DBUtil dbutil = new DBUtil();
	By sidemenu = By.id("net.grandiose.retail:id/iv_menu");
	By loginBtn = By.id("net.grandiose.retail:id/btn_login");
	By usermobileNumber = By.id("net.grandiose.retail:id/et_mobile_number");
	By smsBtn = By.id("net.grandiose.retail:id/btn_sms");
	By otpbox = By.id("net.grandiose.retail:id/et_otp");
	By addToCart = By.id("net.grandiose.retail:id/btn_add_to_cart");
	By product1 = By.xpath("(//android.widget.Button[@resource-id=\"net.grandiose.retail:id/btn_add_to_cart\"])[1]");
	By product2 = By.xpath("(//android.widget.Button[@resource-id=\"net.grandiose.retail:id/btn_add_to_cart\"])[2]");
	By cartIcon = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"net.grandiose.retail:id/fl_shopping_cart\"]/android.widget.ImageView");
	By checkout = By.id("net.grandiose.retail:id/btn_checkout");
	By totalamount = By.id("net.grandiose.retail:id/tv_price");
	By placeorderBtn = By.id("net.grandiose.retail:id/btn_place_order");
	By orderconfirmation = By.id("net.grandiose.retail:id/tv_order_conf");
	By orderid = By.id("net.grandiose.retail:id/tv_order_id");
	By reward = By.id("net.grandiose.retail:id/btn_barcode");
	By searchBox= By.id("net.grandiose.retail:id/tv_search");
	By searchBox1=By.id("net.grandiose.retail:id/et_search");
	By allAddresses = By.id("net.grandiose.retail:id/tv_address");
	By jumeirahAddText = By.xpath(
			"//android.widget.TextView[@resource-id='net.grandiose.retail:id/tv_address' and @text='Jumeirah 2, Jumeirah, 22-test, 2, Jumeirah 2, Dubai, United Arab Emirates']");
	By radioAdd = By.xpath("//android.widget.RadioButton[@resource-id='net.grandiose.retail:id/radio_button']");
	By addtocartBtn = By.xpath("(//android.widget.Button[@resource-id='net.grandiose.retail:id/btn_add_to_cart'])[1]");
	By emptyBasket = By.id("net.grandiose.retail:id/tv_empty_cart");
	By itemincart=By.id("net.grandiose.retail:id/tv_item_count");
	By emptycartmsg=By.id("android:id/message");
	By emptyBasketpopupBtn=By.id("android:id/button1");
	By emptyBasketpopupCnclBtn=By.id("android:id/button2");
	By latestDeals=By.xpath("//android.widget.TextView[@resource-id='net.grandiose.retail:id/tv_title' and @text='Latest Deals']");
	String otp;
	WebDriverWait wait;

	@BeforeSuite
	public static Connection connectToDB() {
		String url = "jdbc:mysql://172.22.20.9:3306/live_25_02_2025";
		String user = "admin-user";
		String password = "Vc1kzSUyhFxQHgsu";

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

	@AfterSuite
	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
			System.out.println("Database connection closed successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setUp() throws MalformedURLException {

		try {
			UiAutomator2Options opt = new UiAutomator2Options();
			opt.setDeviceName("Grandiose");
			opt.setPlatformName("Android");
			opt.setAutomationName("UiAutomator2");
			opt.setApp(System.getProperty("user.dir") + "/src/test/resources/app (10).apk");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), opt);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			System.out.println("✅ App launched successfully");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Test
	@BeforeMethod
	public void testLoginWithValidCredentials() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		WebElement allownotification = driver
				.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
		allownotification.click();
//		Thread.sleep(5000);
//		WebElement allowLocation = driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
//		allowLocation.click();
		Thread.sleep(30000);
		WebElement emirateSpinner = driver.findElement(By.id("net.grandiose.retail:id/actv_emirate"));
		emirateSpinner.click();
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Dubai\"]")).click();
		// Select Area
		WebElement areaSpinner = driver.findElement(By.id("net.grandiose.retail:id/til_area"));
		areaSpinner.click();
		WebElement searchArea = driver.findElement(By.id("net.grandiose.retail:id/ed_search"));
		searchArea.sendKeys("Jumeirah 2");
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Jumeirah 2\"]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("net.grandiose.retail:id/btn_proceed")));
		// click on proceed button
		driver.findElement(By.id("net.grandiose.retail:id/btn_proceed")).click();

		driver.findElement(sidemenu).click();
		driver.findElement(loginBtn).click();
		driver.findElement(usermobileNumber).sendKeys("500000033");
		wait.until(ExpectedConditions.elementToBeClickable((smsBtn)));
		driver.findElement(smsBtn).click();
		Thread.sleep(1000);
		ResultSet rs = executeQuery(
				"SELECT otp FROM `ktpl_loginviasms_transaction` where mobile_number='+971500000033';");
		try {
			if (rs != null && rs.next()) {
				otp = rs.getString("otp");
				System.out.println("✅ OTP is: " + otp);
			} else {
				System.out.println("⚠️ No OTP found for the given mobile number.");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(otpbox).sendKeys(otp);
		Thread.sleep(7000);

	}

	@Test (priority=2)
	public void addProductToCart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		// Add product to cart
		addProductToBasket("365 Halloumi Cyprus");
		Thread.sleep(1000);
		addProductToBasketByProdName("3M Scotch Brite Nailsaver");
		Thread.sleep(1000);
		//addProductToBasketByProdName("Acorsa Plain Green Olives");
		// Open cart
		wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

		// Wait for total amount and log it
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(totalamount));
		System.out.println("Total cart value is = " + total.getText());

		// Proceed to checkout
		wait.until(ExpectedConditions.elementToBeClickable(checkout)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(jumeirahAddText));
		List<WebElement> addressElements = driver.findElements(allAddresses);

		for (WebElement addr : addressElements) {
			if (addr.getText().contains("Jumeirah 2, Jumeirah, 22-test, 2, Jumeirah 2, Dubai, United Arab Emirates")) {
				System.out.println("Matching address found: " + addr.getText());
				// driver.findElement(radioAdd).click(); // Clicking the radio button
			}
		}

		// Click checkout again after selecting address
		wait.until(ExpectedConditions.elementToBeClickable(checkout)).click();

		// Place order
		wait.until(ExpectedConditions.elementToBeClickable(placeorderBtn)).click();

		// Confirm order success
		WebElement confirmation = wait.until(ExpectedConditions.presenceOfElementLocated(orderconfirmation));
		System.out.println("Order confirmation: " + confirmation.getText());

		String orderIdText = driver.findElement(orderid).getText();
		System.out.println("Order ID is " + orderIdText);
	}

	@Test (priority=1)
	public void emptyCartTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//		driver.findElement(searchBox).click();
//		wait.until(ExpectedConditions.elementToBeClickable(searchBox1));
//		driver.findElement(searchBox1).sendKeys("Cucumber");
//		selectSearchItem("cucumber");
//		wait.until(ExpectedConditions.elementToBeClickable(addtocartBtn));
//		addProductToBasketByProdName("Guyader Salmon, Cheese And Cucumber Verrines");
//		addProductToBasketByProdName("Double Dutch Cucumber & Watermelon Tonic Mixer");
//		Thread.sleep(2000);
//		addProductToBasket("Organic Baby Cucumber 250G");
//		addProductToBasketByProdName("Taybat Cornichon Cucumber");

		// Add product to cart
		addProductToBasket("365 Halloumi Cyprus");
		Thread.sleep(1000);
		addProductToBasketByProdName("3M Scotch Brite Nailsaver");
		Thread.sleep(1000);
		addProductToBasketByProdName("Acorsa Plain Green Olives");
		// Open cart
		wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
		// Wait for total amount and log it
		WebElement total = wait.until(ExpectedConditions.presenceOfElementLocated(totalamount));
		System.out.println("Total cart value is = " + total.getText());
		String noOfItemsInCart=driver.findElement(itemincart).getText();
		System.out.println("Total No. of item added in cart is :" +noOfItemsInCart);
		driver.findElement(emptyBasket).click();
	    String Text=driver.findElement(emptycartmsg).getText();
	    System.out.println(Text);
		Thread.sleep(1000);
		driver.findElement(emptyBasketpopupBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(latestDeals));

	}

	public void selectSearchItem(String text) {
		String xpath = "//android.widget.TextView[@resource-id='net.grandiose.retail:id/suggestionTv' and @text='"
				+ text + "']";
		WebElement suggclick = driver.findElement(By.xpath(xpath));
		suggclick.click();
//		String suggText = driver.findElement(By.xpath(xpath)).getText();
//		System.out.println("User clicked on search suggestion is :" + suggText);

	}

	public void addProductToBasketByProdName(String productName) {

		String xpath = "//android.widget.TextView[@resource-id='net.grandiose.retail:id/tv_product_label' and @text='"
				+ productName + "']"
				+ "/ancestor::android.view.ViewGroup[@resource-id='net.grandiose.retail:id/product_card_view']"
				+ "//android.widget.Button[@resource-id='net.grandiose.retail:id/btn_add_to_cart']";
	

		WebElement addToBasketButton = driver.findElement(By.xpath(xpath));
		addToBasketButton.click();
		System.out.println("Product added to cart is: " + productName);

	}

	public void scrollToElement(By element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Object> scrollObject = new HashMap<>();
		scrollObject.put("direction", "down");
		scrollObject.put("elementId", ((RemoteWebElement) driver.findElement(element)).getId());
		js.executeScript("mobile: scroll", scrollObject);

	}

	public boolean addProductToBasket(String productName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			// Scroll to product using UiScrollable
			String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().textContains(\"" + productName + "\"))";

			WebElement product = driver.findElement(AppiumBy.androidUIAutomator(uiScroll));
//	            product.click();
//	            System.out.println("Opened product: " + productName);
//	            Thread.sleep(5000);
			// Wait and click Add to Cart button
			WebElement addToCartButton = wait.until(
					ExpectedConditions.elementToBeClickable(AppiumBy.id("net.grandiose.retail:id/btn_add_to_cart")));

			addToCartButton.click();
			System.out.println("Product Added to cart is : " + productName);

			return true;

		} catch (Exception e) {
			System.err.println("Failed to add product: " + productName);
			e.printStackTrace();
			return false;
		}
	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("🛑 App closed successfully");
		}
	}

}
