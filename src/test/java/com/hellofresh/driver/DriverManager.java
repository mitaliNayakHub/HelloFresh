package com.hellofresh.driver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.hellofresh.reporter.TestLogger;
import com.hellofresh.utility.Constants;

public class DriverManager {
	protected static Properties properties = new Properties();
	// protected static File propertiesFile;
	protected static InputStream propertiesFile;
	protected static WebDriverWait wait;
	protected static String url = null;
	protected static String browserName = null;
	protected static WebDriver driver = null;
	protected static String browserLocation = null;

	public WebDriver getDriver() {
		return this.driver;
	}

	public static void startBrowser() {
		// WebDriver driver = null;
		try {
			// if browser is firefox then open firefox browser using the driver located at
			// the location provided in config.properties file
			if (browserName.toLowerCase().contains("firefox")) {
				System.setProperty("webdriver.gecko.driver", browserLocation);
				driver = new FirefoxDriver();
				Logger log = Logger.getLogger("Info");
				TestLogger.debug("New driver instantiated");
			}
			// if browser is chrome then open chrome browser using the driver located at
			// the location provided in config.properties file
			else if (browserName.toLowerCase().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver", browserLocation);
				driver = new ChromeDriver();
				TestLogger.debug("New driver instantiated");
			}
			driver.manage().window().maximize();
			TestLogger.debug("Maximixing the browser for test");
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Constants.pageLoadTime, TimeUnit.SECONDS);
			// The below message from TestLogger can be seen in Seleniumlogfile.logs <from
			// log4j.appender.file.File> property in log4j.properties file
			TestLogger.debug("implicit wait applied on the driver in seconds");
			// The below message from logReport can be seen in output.html file and testng-results.xml file
			logReport(true, "Launch " + browserName + " with URL " + url, true);
		} catch (Exception e) {
			logReport(false, "Couldn't open " + browserName, true);
			e.printStackTrace();
			TestLogger.debug("The browser couldn't be started");
		}
		TestLogger.info("The browser has started");
	}

	public void closeBrowser() {
		try {
			if (this.getDriver() != null)
				getDriver().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logReport(true, "browser closed successfully", true);
		TestLogger.debug("The browser has closed");
		TestLogger.endTestCase("Test Case Ended");
	}

	//This method uses the default reporter of TestNG
	public static void logReport(boolean result, String Message, boolean snapshot) {
		String snapshotPath = Constants.snapshotFolder;
		if (result) {
			Reporter.log(Constants.passed + Message, true);
		} else {
			Reporter.log(Constants.failed + Message + snapshotPath, true);
			Assert.fail(Constants.failed + Message);
		}

	}

	//The below method is to take screenshot 
	public String takeSnapshot(String TestName) {
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		} catch (Throwable t) {
			Reporter.log("failed to take screenshot");
		}
		String fName = Constants.snapshotFolder + TestName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(fName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fName;
	}

	//This method generates a random string value 
	public String getUniqueID() {
		String randomUUID = "";
		try {
			UUID uuid = UUID.randomUUID();
			randomUUID = uuid.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randomUUID;
	}

}
