/**
 * 
 */
package com.webbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;



/**
 * @author Santosh Sharma
 *
 */
public class TestUtils {
	
	// constants
	public static String CUSTOMER_FIRST_NAME = "Sony";
	public static String CUSTOMER_LAST_NAME = "Sharma";
	public static String FULL_NAME = "Sony Sharma";
	public static String CURRENCY = "Rupee";
	public static String POSTAL_CODE = "12345";
	
	public static String DEPOSIT_AMOUNT = "500";
	public static String WITHDRAWAL_AMOUNT = "200";

	
	// Function will select visible text from drop down menu
	public static void selectFromDropDown(WebElement ele, String str) throws InterruptedException {
		Select drpDwn = new Select(ele);
		Thread.sleep(20);
		drpDwn.selectByVisibleText(str);
	}

	// Function accepts alert 
	public static void acceptAlert(WebDriver driver) throws InterruptedException {
		Alert alrt = driver.switchTo().alert();
		Thread.sleep(20);
		alrt.accept();
	}
    
	// Function accepts alert and return alert window text
	public static String getAlertText(WebDriver driver) throws InterruptedException {
		Alert alrt = driver.switchTo().alert();
		Thread.sleep(500);
		String alertTxt = alrt.getText();
		alrt.accept();
		return alertTxt;
	}
	
	
	// Function takes screenshots and return location string
	public static String captureScreen(WebDriver driver, String filename) {
		
		String dateTime = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(new Date());
		String imageFile = filename + "_" + dateTime + ".png";
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/" + imageFile; 
		
		try {
			FileUtils.copyFile(source, new File(destination));
			
		} catch (IOException e) {
			e.getMessage();
		}
		
		// image path for jenkins
//		String imgPathJenkins = "http://localhost:8080/job/net-banking-project/ws/screenshots/" + imageFile;
//		return imgPathJenkins;
		
		return destination;
	}
	
}
