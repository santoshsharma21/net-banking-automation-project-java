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
public class AccountPage {

	WebDriver driver;
    
	// constructor 
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
	
	// page objects
	@FindBy(xpath = "//button[normalize-space()='Deposit']")
	WebElement depositBtn;

	@FindBy(xpath = "//input[@placeholder='amount']")
	WebElement amountToDeposit;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement depBtn;

	@FindBy(xpath = "//button[normalize-space()='Withdrawl']")
	WebElement withdrawalBtn;

	@FindBy(xpath = "//input[@placeholder =  'amount']")
	WebElement amountToWithdraw;

	@FindBy(xpath = "//button[normalize-space()='Withdraw']")
	WebElement wthDrawBtn;

	@FindBy(xpath = "//button[normalize-space()='Transactions']")
	WebElement transactionsBtn;

	@FindBy(xpath = "//button[normalize-space()='Logout']")
	WebElement logoutBtn;

	@FindBy(xpath = "//span[@class='fontBig ng-binding']")
	WebElement loggedCstName;
	
	@FindBy(xpath = "//span[@class='error ng-binding']")
	WebElement depositStatusMsg;
	
	@FindBy(xpath = "//span[@class='error ng-binding']")
	WebElement withdrawalStatusMsg;
	
	
	// action methods
	public void inputDepositAmount(String amount) throws InterruptedException {
		depositBtn.click();
		Thread.sleep(500);
		amountToDeposit.sendKeys(amount);
	}
	
	public String clickDeposit() throws InterruptedException {
		depBtn.click();
		Thread.sleep(10);
		String txt = depositStatusMsg.getText();
		return txt;
	}
	
	
	public void inputWithdrawalAmount(String amount) throws InterruptedException {
		withdrawalBtn.click();
		Thread.sleep(800);
		amountToWithdraw.sendKeys(amount);
		wthDrawBtn.click();
	}
	
	public String clickWithdraw() throws InterruptedException {
		wthDrawBtn.click();
		Thread.sleep(10);
		String txt = withdrawalStatusMsg.getText();
		return txt;
	}
	
	
	public TransactionDetailsPage clickTransactionsButton() throws InterruptedException {
		Thread.sleep(200);
		transactionsBtn.click();
		return new TransactionDetailsPage(driver);
	}

	public CustomerPage clickLogout() {
		logoutBtn.click();
		return new CustomerPage(driver);
	}

	public String getLoggedinCustomerName() {
		String cstName = loggedCstName.getText();
		return cstName;
	}

}
