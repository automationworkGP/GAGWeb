package com.sigma.grandioseEcommerceWebApp;

import java.sql.ResultSet;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sigma.Util.GenericUtility;

public class CustomerLogin {
	WebDriverWait wait;
	WebDriver driver;
	String otp;
	GenericUtility utility;
	private static Logger logger = LogManager.getLogger(CustomerLogin.class);
	
    public CustomerLogin(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        utility=new GenericUtility(driver);
    }

    @FindBy(xpath = "//main[@class='page-main']")
	public WebElement maintananceScreen;
	
	@FindBy(xpath = "//input[@id='mobilenumber']")
	public WebElement mobileNum;

	@FindBy(xpath = "//button[@id='send-via-sms']")
	public WebElement contViaSMS;

	@FindBy(xpath = "//div[@class='otp-fields']//input[@type='number']")
	public List<WebElement> otpInputs;
	
	@FindBy(xpath = "//div[@class='otp-fields']//input[@type='number']")
	public WebElement otpfield;
	
	@FindBy(id="home_delivery_mode_emirates")
	WebElement selectEmirates;
	
	@FindBy(xpath="//option[@value='656']")
	WebElement emiratesOption;

	
	@FindBy(xpath="//div[@id='delivery_mode_area_list']")
	WebElement selectArea;
	
	@FindBy(xpath="//div[@class='chosen-search']//input[@type='text']")
	WebElement searchArea;
	
	
	@FindBy(xpath="//option[@value='155']")
	WebElement areaOption;
	
	@FindBy(xpath="//button[@class='confirm-delivery-mode active-btn']")
	WebElement proceedBtn;
	
	@FindBy(xpath="//a[@aria-label='store logo']//img")
	WebElement grandioseLogo;
	
	@FindBy(xpath="//h2[normalize-space()='Featured']")
	WebElement widget;

	@FindBy(xpath = "//h2[contains(text(),'You are far away from your previous location, Are ')]")
	WebElement longLatPopup;
	
	@FindBy(xpath = "//button[@class='same-location']")
	WebElement cancelBtn; 
	
	public void loginto() throws InterruptedException {
		
		mobileNum.sendKeys("500000033");
		logger.info("Mobile number entered");
		contViaSMS.click();
		logger.info("clicked on contact Via SMS button");
		utility.waitForSpecificTime(2);
//		ResultSet rs = GrandioseWebAppBaseClass.executeQuery(
//				"SELECT otp FROM `ktpl_loginviasms_transaction` where mobile_number='+971500000033';");
//
//		try {
//			if (rs != null && rs.next()) {
//				otp = rs.getString("otp");
//				System.out.println("✅ OTP is: " + otp);
//			} else {
//				System.out.println("⚠️ No OTP found for the given mobile number.");
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for (int i = 0; i < otp.length(); i++) {
//			otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
//		}
		otpfield.sendKeys("1234");
		utility.waitForSpecificTime(5);
		grandioseLogo.click();
		//wait.until(ExpectedConditions.visibilityOf(selectEmirates));
		utility.waitForSpecificTime(3);
		WebElement emirates=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_delivery_mode_emirates")));
		emirates.click();
		WebElement emiratesOpt=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='656']")));
		emiratesOpt.click();
		utility.waitForSpecificTime(1);
		WebElement area=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='chosen-single']")));
		area.click();
		area.click();
		Thread.sleep(500);
		area.click();
		WebElement areaOpt=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[normalize-space()='Silicon Oasis']")));
		areaOpt.click();
		Thread.sleep(1000);
		proceedBtn.click();
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(longLatPopup, "You are far away from your previous location, Are you want to change location?"));
			String text =longLatPopup.getText();
			if(text.contains("You are far away from your previous location, Are you want to change location?")) {
				cancelBtn.click();
				Thread.sleep(2000);
			}
		}
		catch(Exception e) {
			System.out.println("update location failed");
		}
				
	}

}
