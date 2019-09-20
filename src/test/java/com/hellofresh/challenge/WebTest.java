package com.hellofresh.challenge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hellofresh.driver.DriverManager;
import com.hellofresh.reporter.TestLogger;
import com.hellofresh.utility.Constants;

public class WebTest extends DriverManager {
	String existingUserEmail = null;
	String existingUserPassword = null;
	String firstname = "firstname";
	String lastname = "lastname";
	String timestamp = String.valueOf(new Date().getTime());
	String userDirectory = Constants.userDirectory;
	String email = "user_" + getUniqueID().substring(10) + "@hf.com"; // unique user email id

	@BeforeMethod
	public void setUp() throws FileNotFoundException {
		// path to the log4j property file to be used for logger
		PropertyConfigurator.configure(userDirectory + "\\Resources\\log4j.properties");
		try {
			// property file to read the properties from the config file
			properties.load(new FileInputStream(userDirectory + "\\Resources\\config.properties"));
		} catch (IOException e) {
			Logger.getLogger(getClass().getName()).log(Level.ERROR, e.getMessage(), e);
		}
		// read properties from config file
		url = properties.getProperty("Url");
		browserName = properties.getProperty("Browser");
		browserLocation = properties.getProperty("browserLocation");
		startBrowser();
		wait = new WebDriverWait(getDriver(), 20, 1);
	}

	// This is a signup page where the user creates his account on the application.
	@Test(priority = 1)
	public void signIn() {
		TestLogger.startTestCase("SignIn Test");
		SignIn.SignUp(email, firstname, lastname);
		TestLogger.endTestCase("SignIn Test");
	}

	// This is a login test where an existing user logins into the application. The
	// user details are coming from Constants.java file
	@Test(priority = 2)
	public void logInTest() {
		TestLogger.startTestCase("LogIn Test");
		Login.userLogin(Login.userEmail, Login.userPassword);
		TestLogger.endTestCase("LogIn Test");
	}

	// adding a test to fail to check the failure in reports
	// This is a test where a negative test case is added to fail the test to check
	// the report and screenshot functionality
	@Test(priority = 3)
	public void failedlogInTest() {
		TestLogger.startTestCase("This test is a failed test case to check failure in reports");
		Login.userLogin(email, timestamp);
		logReport(false, "TC failed", true);
		TestLogger.error("The test Failed");
		TestLogger.endTestCase("This test is a failed test case to check failure in reports");
	}

	// This test verifies a user checkout process where the user logins,add item to
	// cart, checkout and then signout
	@Test(priority = 4)
	public void checkoutTest() {
		TestLogger.startTestCase("Checkout Test");
		CheckOut.buyProcess();
		TestLogger.endTestCase("Checkout Test");
	}

	//This after method checks if the test result fails, it captures the screenshot
	//Once the tests are executed the action logs are recorded and then the driver closes.
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws InterruptedException {
		// capture only if test fails
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				// Call method to capture screenshot
				String ScreenshotName = result.getName() + "_" + timestamp.toString(); // screenshot with timestamp
				takeSnapshot(ScreenshotName);
				String ScreenshotPath = Constants.snapshotFolder + "ScreenshotName";
				System.out.println(
						"Error Screenshot " + ScreenshotName + " of test " + result.getName() + " at" + ScreenshotPath);
				TestLogger.info("Captured Error Screenshot " + ScreenshotName + " of test " + result.getName());
			} catch (Exception e) {

				System.out.println("Exception while taking screenshot " + e.getMessage());
				TestLogger.error("Exception taking screenshot");
			}
		}
		closeBrowser();
	}

}
