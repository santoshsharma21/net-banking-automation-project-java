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
public class CustomerPage {

	WebDriver driver;
    
	// constructor 
	public CustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page objects
	@FindBy(id = "userSelect")
	WebElement usrName;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginBtn;

	// action methods
	public void selectUser(String uname) throws InterruptedException {
		TestUtils.selectFromDropDown(usrName, uname);
	}

	public AccountPage clickLoginButton() {
		loginBtn.click();
		return new AccountPage(driver);
	}

}
