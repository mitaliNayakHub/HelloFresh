package com.hellofresh.reporter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.hellofresh.driver.*;
import com.hellofresh.reporter.*;


public class ListernerTest extends TestScreenShot implements ITestListener{
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Start Of Execution(TEST)->"+context.getName());		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName());		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getName());
		Object currentClass = result.getInstance();
	    WebDriver driver = ((DriverManager) currentClass).getDriver();
		try {
			takeScreeshot(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Partially Passed Within Limit: " + result.getName());	
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("End Of Execution(TEST)->"+context.getName());		
	}

}
