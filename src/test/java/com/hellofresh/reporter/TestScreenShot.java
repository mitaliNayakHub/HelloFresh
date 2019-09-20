package com.hellofresh.reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import com.hellofresh.driver.DriverManager;

public class TestScreenShot extends DriverManager {
	
	WebDriver driver= null;
	
	public void takeScreeshot(WebDriver driver) throws Exception {
		
		String timeStamp;
		File screenShotName = null;
		try{
			
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		screenShotName =  new File("D:\\Selenium\\Screenshots\\"+timeStamp+".png");
		FileHandler.copy(scrFile, screenShotName);
		}catch(Throwable t){System.out.println("Unable to Take Screenshot");}
		String filePath = screenShotName.toString();
		String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
		Reporter.log(path);
	}

}
