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
public class ManagerPage {

	WebDriver driver;
    
	// constructor
	public ManagerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page objects
	@FindBy(xpath = "//button[normalize-space()='Add Customer']")
	WebElement addCustomerBtn;

	@FindBy(xpath = "//button[normalize-space()='Open Account']")
	WebElement openAccountBtn;

	@FindBy(xpath = "//button[normalize-space()='Customers']")
	WebElement customersBtn;

	// action methods
	public boolean validateAddCustomerButton() {
		boolean status = addCustomerBtn.isDisplayed();
		return status;
	}

	public AddCustomerPage clickAddCustomerButton() {
		addCustomerBtn.click();
		return new AddCustomerPage(driver);
	}

	public boolean validateOpenAccountButton() {
		boolean status = openAccountBtn.isDisplayed();
		return status;
	}

	public OpenAccountPage clickOpenAccountButton() {
		openAccountBtn.click();
		return new OpenAccountPage(driver);
	}

	public boolean validateCustomersButton() {
		boolean status = customersBtn.isDisplayed();
		return status;
	}

	public CustomerDetailsPage clickCustomersButton() {
		customersBtn.click();
		return new CustomerDetailsPage(driver);
	}
	
}
