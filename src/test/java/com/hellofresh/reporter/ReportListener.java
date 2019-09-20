package com.hellofresh.reporter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log("Test Finished" ,true);
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		Reporter.log("Execution Started" ,true);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		Reporter.log("Acceptable" ,true);
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		Reporter.log("Test Failed" ,true);
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		Reporter.log("Test skipped" ,true);
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Reporter.log("Test Started" ,true);
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		Reporter.log("Test SuccessFull" ,true);
		
	}

}
