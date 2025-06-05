package com.sigma.grandioseEcommerceWebApp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.sigma.Util.GenericUtility;

public class AddToCartPage extends GrandioseWebAppBaseClass {

	GenericUtility utility;
	private static Logger logger = LogManager.getLogger(AddToCartPage.class);

	public AddToCartPage(WebDriver driver) {
		initialize(driver);
		PageFactory.initElements(driver, this);
		utility = new GenericUtility(driver);
	}

	@FindBy(xpath = "//input[@id='search']")
	WebElement search;

	@FindBy(xpath = "//div[@id='featured_product_slider_main']//div[@class='slider_title']")
	WebElement featuredSection;

	@FindBy(xpath = "//div[@class='minicart-wrapper']")
	WebElement cart;

	@FindBy(id = "top-cart-btn-checkout")
	WebElement checkout;

	@FindBy(xpath = "//div[@class='shipping-address-item selected-item']")
	WebElement selectedaddress;

	@FindBy(xpath = "//div[@class='shipping-address-item not-selected-item']")
	List<WebElement> addressToBeSelected;

	@FindBy(xpath = "//div[@class='shipping-address-item selected-item']//label")
	WebElement radioBtn;
	
	@FindBy(xpath = "//a[normalize-space()='Add to Basket']")
	WebElement addBasket;
	
	
	@FindBy(xpath = "//div[@class='field addresses']//div[3]//label[1]")
	WebElement radioBtnToBeSelected;
	
	@FindBy(xpath = "//button[@id='showPaymentForm']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//span[@class='amcheckout-step-title']")
	WebElement paymentText;
	
	@FindBy(xpath = "//button[@class='action primary checkout amasty' and @type='submit']")
	WebElement placeOrder;
	
	@FindBy(xpath = "//h2[normalize-space()='Your Order has been placed successfully']")
	WebElement orderSuccessMsg;
	
	@FindBy(xpath = "//a[@class='order-number']")
	WebElement orderNumber;
	
	@FindBy(xpath = "//label[@for='cashondelivery']")
	WebElement CODPayment;
	
	@FindBy(xpath = "//label[@for='ccod']")
	WebElement CCODPayment;
	
	@FindBy(xpath = "//input[@id='amstorecredit_amount']")
	WebElement wallet;
	
	@FindBy(xpath = "//button[@value='Apply Wallet Credit']")
	WebElement applyWalletCredit;
	
	@FindBy(xpath = "//span[@id='block-reward-heading']")
	WebElement applyWalletCreditHeader;

	@FindBy(xpath = "//strong[@data-bind='text: getStoreCreditLeft()']")
	WebElement balanceCredit;
	
	public void placeorderUsingProductSearch() throws InterruptedException {
		CustomerLogin loginpage = new CustomerLogin(driver);
		loginpage.loginto();
		WebElement searchProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
		searchProduct.sendKeys("733603093130");
		utility.addToBasket();
		search.clear();
		searchProduct.sendKeys("9802521000000");
		utility.waitForSpecificTime(2);
		utility.addToBasket();
		utility.waitForSpecificTime(2);
		cart.click();
		utility.waitForSpecificTime(1);
		checkout.click();
		utility.waitForSpecificTime(10);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	    utility.scrollByPixel(0,500);
	    utility.waitForSpecificTime(1);
	    continueBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
		utility.waitForSpecificTime(6);
		utility.scrollByPixel(0,500);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		utility.waitForSpecificTime(1);
		placeOrder.click();
		wait.until(ExpectedConditions.textToBePresentInElement(orderSuccessMsg, "Your Order has been placed successfully"));
		String succText=orderSuccessMsg.getText();
		Assert.assertEquals(succText,"Your Order has been placed successfully");
		String ordernumbertext=orderNumber.getText();
		logger.info("order number is :"+ordernumbertext);
		
	}
	
	
	public void addItemToBasket() throws InterruptedException {
		CustomerLogin loginpage = new CustomerLogin(driver);
		loginpage.loginto();
		utility.waitForSpecificTime(3);
		utility.scrollByPixel(0,1300);
		utility.waitForSpecificTime(2);
		List<WebElement> basket=driver.findElements(By.xpath("//button[@title='Add to Basket']"));
		for(int i=0;i<4;i++) {
		basket.get(i).click();
		}
		utility.waitForSpecificTime(3);
		cart.click();
		utility.waitForSpecificTime(1);
		checkout.click();
		utility.waitForSpecificTime(10);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	    utility.scrollByPixel(0,500);
	    utility.waitForSpecificTime(1);
	    continueBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
		utility.waitForSpecificTime(4);
		utility.scrollByPixel(0,500);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		utility.waitForSpecificTime(1);
		placeOrder.click();
		wait.until(ExpectedConditions.textToBePresentInElement(orderSuccessMsg, "Your Order has been placed successfully"));
		String succText=orderSuccessMsg.getText();
		Assert.assertEquals(succText,"Your Order has been placed successfully");
		String ordernumbertext=orderNumber.getText();
		logger.info("order number is :"+ordernumbertext);
		
	}
	
	public void placeOrderUsingCOD() throws InterruptedException {
		
		CustomerLogin loginpage = new CustomerLogin(driver);
		loginpage.loginto();
		utility.waitForSpecificTime(3);
		utility.scrollByPixel(0,1300);
		utility.waitForSpecificTime(2);
		List<WebElement> basket=driver.findElements(By.xpath("//button[@title='Add to Basket']"));
		for(int i=0;i<4;i++) {
		basket.get(i).click();
		}
		utility.waitForSpecificTime(3);
		cart.click();
		utility.waitForSpecificTime(1);
		checkout.click();
		utility.waitForSpecificTime(10);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	    utility.scrollByPixel(0,500);
	    utility.waitForSpecificTime(1);
	    continueBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
		utility.waitForSpecificTime(4);
		utility.safeClick(CODPayment);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
		utility.scrollByPixel(0,500);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		utility.waitForSpecificTime(1);
		placeOrder.click();
		wait.until(ExpectedConditions.textToBePresentInElement(orderSuccessMsg, "Your Order has been placed successfully"));
		String succText=orderSuccessMsg.getText();
		Assert.assertEquals(succText,"Your Order has been placed successfully");
		String ordernumbertext=orderNumber.getText();
		logger.info("order number is :"+ordernumbertext);
		
	}
	
public void placeOrderUsingCCOD() throws InterruptedException {
		
		CustomerLogin loginpage = new CustomerLogin(driver);
		loginpage.loginto();
		utility.waitForSpecificTime(3);
		utility.scrollByPixel(0,1300);
		utility.waitForSpecificTime(2);
		List<WebElement> basket=driver.findElements(By.xpath("//button[@title='Add to Basket']"));
		for(int i=0;i<4;i++) {
		basket.get(i).click();
		}
		utility.waitForSpecificTime(3);
		cart.click();
		utility.waitForSpecificTime(1);
		checkout.click();
		utility.waitForSpecificTime(10);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	    utility.scrollByPixel(0,500);
	    utility.waitForSpecificTime(1);
	    continueBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
		utility.waitForSpecificTime(4);
		utility.safeClick(CCODPayment);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
		utility.scrollByPixel(0,500);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		utility.waitForSpecificTime(1);
		placeOrder.click();
		wait.until(ExpectedConditions.textToBePresentInElement(orderSuccessMsg, "Your Order has been placed successfully"));
		String succText=orderSuccessMsg.getText();
		Assert.assertEquals(succText,"Your Order has been placed successfully");
		String ordernumbertext=orderNumber.getText();
		logger.info("order number is :"+ordernumbertext);
		
	}
 
public void placeOrderCOD_Wallet() throws InterruptedException {
	CustomerLogin loginpage = new CustomerLogin(driver);
	loginpage.loginto();
	utility.waitForSpecificTime(3);
	utility.scrollByPixel(0,1300);
	utility.waitForSpecificTime(2);
	List<WebElement> basket=driver.findElements(By.xpath("//button[@title='Add to Basket']"));
	for(int i=0;i<4;i++) {
	basket.get(i).click();
	}
	utility.waitForSpecificTime(3);
	cart.click();
	utility.waitForSpecificTime(1);
	checkout.click();
	utility.waitForSpecificTime(10);
  //  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
    utility.scrollByPixel(0,500);
    utility.waitForSpecificTime(1);
    continueBtn.click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	utility.waitForSpecificTime(4);
	utility.safeClick(CCODPayment);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	String balance=balanceCredit.getText();
	logger.info("Walllet Balance is "+balance);
	wallet.clear();
	wallet.sendKeys("20");
	applyWalletCredit.click();
	//Thread.sleep(1000);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
	utility.scrollByPixel(0,500);
	wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
	utility.waitForSpecificTime(1);
	placeOrder.click();
	wait.until(ExpectedConditions.textToBePresentInElement(orderSuccessMsg, "Your Order has been placed successfully"));
	String succText=orderSuccessMsg.getText();
	Assert.assertEquals(succText,"Your Order has been placed successfully");
	String ordernumbertext=orderNumber.getText();
	logger.info("order number is :"+ordernumbertext);
	
	
	
	
}


}
