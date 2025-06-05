package com.sigma.grandioseEcommerceWebApp;

import org.testng.annotations.Test;

import com.sigma.Util.RetryAnalyzer;

public class AddToCartTest extends GrandioseWebAppBaseClass {

	@Test(priority =0,retryAnalyzer = RetryAnalyzer.class ,enabled=true)
	public void checkoutUsingProductSearch() throws InterruptedException {
		AddToCartPage addToCartPage= new AddToCartPage(driver);
		addToCartPage.placeorderUsingProductSearch();
	}
	
	@Test(priority =1,retryAnalyzer = RetryAnalyzer.class)
	public void checkoutUsingProductHomepage() throws InterruptedException {
		AddToCartPage addToCartPage= new AddToCartPage(driver);
		addToCartPage.addItemToBasket();
		
	}	
	@Test(priority =2,retryAnalyzer = RetryAnalyzer.class)
	public void placeOrderUsingCODpayment() throws InterruptedException {
		AddToCartPage addToCartPage= new AddToCartPage(driver);
		addToCartPage.placeOrderUsingCOD();
	}
	
	@Test(priority =3,retryAnalyzer = RetryAnalyzer.class)
	public void placeOrderUsingCCODpayment() throws InterruptedException {
		AddToCartPage addToCartPage= new AddToCartPage(driver);
		addToCartPage.placeOrderUsingCCOD();
	}
	
	
	@Test(priority =4,retryAnalyzer = RetryAnalyzer.class)
	public void placeOrderUsingCOD_Wallet() throws InterruptedException {
		AddToCartPage addToCartPage= new AddToCartPage(driver);
		addToCartPage.placeOrderCOD_Wallet();
	}
	
}
