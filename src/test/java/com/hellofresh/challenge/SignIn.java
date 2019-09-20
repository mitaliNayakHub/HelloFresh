package com.hellofresh.challenge;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.hellofresh.driver.DriverManager;
import com.hellofresh.reporter.TestLogger;

public class SignIn extends DriverManager {
	public interface Locators {

		By SignIn_email = By.id("email_create");
		By SignIn_submit = By.id("SubmitCreate");
		By SignIn_gender = By.id("id_gender2");
		By SignIn_firstName = By.id("customer_firstname");
		By SignIn_lastName = By.id("customer_lastname");
		By SignIn_password = By.id("passwd");
		By SignIn_days = By.id("days");
		By SignIn_months = By.id("months");
		By SignIn_years = By.id("years");
		By SignIn_company = By.id("company");
		By SignIn_ad1 = By.id("address1");
		By SignIn_ad2 = By.id("address2");
		By SignIn_city = By.id("city");
		By SignIn_state = By.id("id_state");
		By SignIn_postcode = By.id("postcode");
		By SignIn_other = By.id("other");
		By SignIn_phone = By.id("phone");
		By SignIn_mobile = By.id("phone_mobile");
		By SignIn_alias = By.id("alias");
		By SignIn_submitAccount = By.id("submitAccount");
		By SignIn_heading = By.cssSelector("h1");
		By SignIn_account = By.className("account");
		By SignIn_accountInfo = By.className("info-account");
		By SignIn_logout = By.className("logout");
		By SignIn_login = By.className("login");
	}

	public static void SignUp(String email, String name, String surname) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SignIn_login)).click();
		driver.findElement(Locators.SignIn_email).sendKeys(email);
		TestLogger.debug("EmailId entered in the element found");// ------log
		driver.findElement(Locators.SignIn_submit).click();
		TestLogger.debug("Clicked on the element found");// -------log
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SignIn_gender)).click();
		driver.findElement(Locators.SignIn_firstName).sendKeys(name);
		driver.findElement(Locators.SignIn_lastName).sendKeys(surname);
		driver.findElement(Locators.SignIn_password).sendKeys("Qwerty");
		Select select = new Select(driver.findElement(Locators.SignIn_days));
		select.selectByValue("1");
		select = new Select(driver.findElement(Locators.SignIn_months));
		select.selectByValue("1");
		select = new Select(driver.findElement(Locators.SignIn_years));
		select.selectByValue("2000");
		driver.findElement(Locators.SignIn_company).sendKeys("Company");
		driver.findElement(Locators.SignIn_ad1).sendKeys("Qwerty, 123");
		driver.findElement(Locators.SignIn_ad2).sendKeys("zxcvb");
		driver.findElement(Locators.SignIn_city).sendKeys("Qwerty");
		select = new Select(driver.findElement(Locators.SignIn_state));
		select.selectByVisibleText("Colorado");
		driver.findElement(Locators.SignIn_postcode).sendKeys("12345");
		driver.findElement(Locators.SignIn_other).sendKeys("Qwerty");
		driver.findElement(Locators.SignIn_phone).sendKeys("12345123123");
		driver.findElement(Locators.SignIn_mobile).sendKeys("12345123123");
		driver.findElement(Locators.SignIn_alias).sendKeys("hf");
		driver.findElement(Locators.SignIn_submitAccount).click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SignIn_heading));
		assertEquals(heading.getText(), "MY ACCOUNT");
		assertEquals(driver.findElement(Locators.SignIn_account).getText(), name + " " + surname);
		assertTrue(driver.findElement(Locators.SignIn_accountInfo).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
		TestLogger.debug("User registered successfully"); // -------log
		driver.findElement(Locators.SignIn_logout).click();
		System.out.println("*****logging out user*****");
		TestLogger.debug("Clicked on logout element");// ------log
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SignIn_login));
		System.out.println("Test Case One with Thread Id:- " + Thread.currentThread().getId());
		TestLogger.endTestCase(" ****End of signin Test****");
	}

}
