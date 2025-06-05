package com.sigma.Util;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sigma.grandioseEcommerceWebApp.CustomerLogin;

public class GenericUtility {

	WebDriver driver;
	WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(GenericUtility.class);
	

	public GenericUtility(WebDriver driver) {
		this.driver = driver;
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	/**
	 * 
	 * @param element
	 * @param value
	 */
	public void selectByVisibleText(WebElement element, String value) {

		Select select = new Select(element);
		select.selectByVisibleText(value);

	}
	/**
	 * 
	 * @param element
	 * @param value
	 */

	public void deSelectDrop(WebElement element, String value) {

		Select select = new Select(element);
		select.deselectByVisibleText(value);

	}
	/**
	 * 
	 * @param element
	 */

   public void scrollElementIntoView(WebElement element){
	   
	   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
   }

   /**
    * 
    * @param xPixels
    * @param yPixels
    */
   public void scrollByPixel(int xPixels, int yPixels) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(arguments[0], arguments[1]);", xPixels, yPixels);
	}

 public void scrollToBottomOfPage() throws InterruptedException{
	   
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	 Thread.sleep(1000);
   }

   
	/**
	 * 
	 * @param sku
	 */
   public void addToBasket(long sku) {
	   
	   WebElement addtobasket= driver.findElement(By.xpath("//a[normalize-space()='Add to Basket' and @data-add-to-cart-btn-sku='"+sku+"']"));
	   wait.until(ExpectedConditions.elementToBeClickable(addtobasket));
	   addtobasket.click();
   }
   
   public void addToBasket() {
	   
	   WebElement addtobasket= driver.findElement(By.xpath("//a[normalize-space()='Add to Basket']"));
	   wait.until(ExpectedConditions.elementToBeClickable(addtobasket));
	   addtobasket.click();
	   try {
		   String outOfStockText=driver.findElement(By.xpath("//div[@class='box-tocart']//h3")).getText();
		   if(outOfStockText.contains("Out of stock")) {
			 String outOfStockProduct= driver.findElement(By.xpath("//span[@class='base']")).getText();
			 logger.info( outOfStockProduct +": This product is currently out of stock");
			 driver.navigate().back();
			 waitForSpecificTime(2);
		   }
		   
	   }
	   catch(Exception e) {
		   
		   
	   }
   }
   
   public void waitForSpecificTime(long sec) throws InterruptedException {
	   Thread.sleep(Duration.ofSeconds(sec)); 
   }
   
   /**
    * 
    * @param driver
    * @param element
    */
   public void safeClick(WebElement element) {
	    try {
	        new WebDriverWait(driver, Duration.ofSeconds(10))
	            .until(ExpectedConditions.elementToBeClickable(element))
	            .click();
	    } catch (ElementClickInterceptedException e) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	    }
	}
   
}
