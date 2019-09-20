package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.challenge.SignIn;
import com.hellofresh.driver.DriverManager;
import com.hellofresh.reporter.TestLogger;
import com.hellofresh.utility.Constants;
import com.hellofresh.challenge.Login;

public class CheckOut extends DriverManager {
	public interface Locators {
		By Checkout_gender = By.linkText("Women");
		By Checkout_item = By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li");
		By Checkout_addCart = By.xpath("//span[contains(text(),'Add to cart')]");
		By Checkout_checkout = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
		By Checkout_navigation = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
		By Checkout_processAddress = By.name("processAddress");
		By Checkout_uniform = By.id("uniform-cgv");
		By Checkout_processCarrier = By.name("processCarrier");
		By Checkout_bankwire = By.className("bankwire");
		By Checkout_cartButton = By.xpath("//*[@id='cart_navigation']/button");
		By Checkout_header = By.cssSelector("h1");
		By Checkout_stepDone = By.xpath("//li[@class='step_done step_done_last four']");
		By Checkout_stepEnd = By.xpath("//li[@id='step_end' and @class='step_current last']");
		By Checkout_chequeIndent = By.xpath("//*[@class='cheque-indent']/strong");
		By Checkout_logout = By.className("logout");
		By Checkout_login = By.className("login");
	}

	public static void buyProcess() {
		System.out.println("Test Case four with Thread Id:- " + Thread.currentThread().getId());
		Login.userLogin(Login.userEmail, Login.userPassword);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_gender)).click();
		driver.findElement(Locators.Checkout_item).click();
		driver.findElement(Locators.Checkout_addCart).click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_checkout)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_navigation)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_processAddress)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_uniform)).click();
		driver.findElement(By.name("processCarrier")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_bankwire)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_cartButton)).click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Checkout_header));
		assertEquals("ORDER CONFIRMATION", heading.getText());
		assertTrue(driver.findElement(Locators.Checkout_stepDone).isDisplayed());
		assertTrue(driver.findElement(Locators.Checkout_stepEnd).isDisplayed());
		assertTrue(driver.findElement(Locators.Checkout_chequeIndent).getText()
				.contains("Your order on My Store is complete."));
		assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
		driver.findElement(Login.Locators.Login_logout).click();
		TestLogger.info("Clicked on logout element");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Login.Locators.Login_login));
		TestLogger.endTestCase(" End of Checkout Test");
	}
}
