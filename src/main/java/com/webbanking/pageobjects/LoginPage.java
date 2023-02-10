/**
 * 
 */
package com.webbanking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Santosh Sharma
 *
 */
public class LoginPage {

	WebDriver driver;

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page objects
	@FindBy(xpath = "//button[normalize-space()='Customer Login']")
	WebElement customerLoginBtn;

	@FindBy(xpath = "//button[normalize-space()='Bank Manager Login']")
	WebElement managerLoginBtn;

	@FindBy(xpath = "//button[normalize-space()='Home']")
	WebElement homeBtn;

	// action methods

	public boolean validateCustomerLoginButton() {
		boolean status = customerLoginBtn.isDisplayed();
		return status;
	}

	public CustomerPage clickCustomerLoginButton() {
		customerLoginBtn.click();
		return new CustomerPage(driver);
	}

	public boolean validateManagerLoginButton() {
		boolean status = managerLoginBtn.isDisplayed();
		return status;
	}

	public ManagerPage clickManagerLoginButton() {
		managerLoginBtn.click();
		return new ManagerPage(driver);
	}

	public LoginPage clickHomeButton() {
		homeBtn.click();
		return new LoginPage(driver);
	}

}
