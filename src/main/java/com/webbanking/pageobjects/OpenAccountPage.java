/**
 * 
 */
package com.webbanking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webbanking.utilities.TestUtils;

/**
 * @author Santosh Sharma
 *
 */
public class OpenAccountPage {

	WebDriver driver;
    
	// constructor
	public OpenAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page objects
	@FindBy(xpath = "//select[@id='userSelect']")
	WebElement customerName;

	@FindBy(xpath = "//select[@id='currency']")
	WebElement currency;

	@FindBy(xpath = "//button[normalize-space()='Process']")
	WebElement processBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Home']")
	WebElement homeBtn;

	// action methods
	public void openAccount(String cstname, String cur) throws InterruptedException {
		TestUtils.selectFromDropDown(customerName, cstname);
		Thread.sleep(50);
		TestUtils.selectFromDropDown(currency, cur);
	}

	public String clickProcess() throws InterruptedException {
		processBtn.click();
		Thread.sleep(100);
		String alertMsg = TestUtils.getAlertText(driver);
		return alertMsg;
	}
	
	public ManagerPage goToPreviuousPage() {
		driver.navigate().back();
		return new ManagerPage(driver);
	}
	
	public LoginPage clickHomeButton() {
		homeBtn.click();
		return new LoginPage(driver);
	}

}
