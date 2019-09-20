package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.driver.DriverManager;
import com.hellofresh.reporter.TestLogger;
import com.hellofresh.utility.Constants;

public class Login extends DriverManager {
	static String userEmail = Constants.existingUserEmail;
	static String userPassword = Constants.existingUserPassword;

	public interface Locators {
		By Login_login = By.className("login");
		By Login_email = By.id("email");
		By Login_passwd = By.id("passwd");
		By Login_submitLogin = By.id("SubmitLogin");
		By Login_header = By.cssSelector("h1");
		By Login_account = By.className("account");
		By Login_accountInfo = By.className("info-account");
		By Login_logout = By.className("logout");

	}

	public static void userLogin(String email, String password) {
		String fullName = "Joe Black";
		System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Login_login)).click();
		driver.findElement(Locators.Login_email).sendKeys(userEmail);
		TestLogger.debug("Email Id entered" + userEmail); // -----log
		driver.findElement(Locators.Login_passwd).sendKeys(userPassword);
		TestLogger.debug("Password Entered");
		driver.findElement(Locators.Login_submitLogin).click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Login_header));
		assertEquals("MY ACCOUNT", heading.getText());
		assertEquals(fullName, driver.findElement(Locators.Login_account).getText());
		assertTrue(driver.findElement(Locators.Login_accountInfo).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(Locators.Login_logout).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}

	public static void userLogout() {
		userLogin(userEmail, userPassword);
		driver.findElement(Locators.Login_logout).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Login_login));
		TestLogger.info("Clicked on logout element"); // -------Log
		TestLogger.endTestCase(" End of Login Test"); // -------Log
	}
	

}
