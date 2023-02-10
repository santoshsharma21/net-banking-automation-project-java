/**
 * 
 */
package com.webbanking.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Santosh Sharma
 *
 */
public class TransactionDetailsPage {

	WebDriver driver;
    
	// constructor
	public TransactionDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page objects
	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr[1]/td")
	List<WebElement> tableCols;

	@FindBy(xpath = "//button[contains(text(),'Reset')]")
	WebElement resetBtn;

	@FindBy(xpath = "//button[normalize-space()='Logout']")
	WebElement logoutBtn;

	@FindBy(xpath = "//button[contains(text(),'Back')]")
	WebElement backBtn;

	// action methods
	public Map<String, String> getTransactionDetails() {
		Map<String, String> transactions = new HashMap<String, String>();

		for (int row = 1; row <= tableRows.size(); row++) {
			List<String> temp = new ArrayList<String>();
			for (int col = 2; col <= tableCols.size(); col++) {
				String txt = driver.findElement(By.xpath(
						"//table[@class='table table-bordered table-striped']/tbody/tr[" + row + "]/td[" + col + "]"))
						.getText();
				temp.add(txt);
			}
			transactions.put(temp.get(1), temp.get(0));
		}

		return transactions;
	}

	public TransactionDetailsPage clickReset() {
		resetBtn.click();
		return this;
	}

	public AccountPage clickBackButton() {
		backBtn.click();
		return new AccountPage(driver);
	}

	public void clickLogout() {
		logoutBtn.click();
	}

}
