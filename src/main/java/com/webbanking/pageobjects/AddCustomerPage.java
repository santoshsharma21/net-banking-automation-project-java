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
public class AddCustomerPage {
	
	WebDriver driver;
	
	// constructor 
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// page objects
	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement lastName;
	
	@FindBy(xpath = "//input[@placeholder='Post Code']")
	WebElement postalCode;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement clickAddCstSubmitBtn;
	
	
	// page actions
	public void addNewCustomer(String fname, String lname, String pcode) throws InterruptedException {
		Thread.sleep(50);
		firstName.clear();
		firstName.sendKeys(fname);
		
		Thread.sleep(50);
		lastName.clear();
		lastName.sendKeys(lname);
		
		Thread.sleep(50);
		postalCode.clear();
		postalCode.sendKeys(pcode);
	}
	
	
	public String clickSubmit() throws InterruptedException {
		clickAddCstSubmitBtn.click();
		Thread.sleep(500);
		//TestUtils.acceptAlert(driver);
		String alertMsg = TestUtils.getAlertText(driver);
		return alertMsg;
	}
	
	public ManagerPage goToPreviuousPage() {
		driver.navigate().back();
		return new ManagerPage(driver);
	}

}
