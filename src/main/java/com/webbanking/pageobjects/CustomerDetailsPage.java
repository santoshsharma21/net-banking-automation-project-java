/**
 * 
 */
package com.webbanking.pageobjects;

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
public class CustomerDetailsPage {

	WebDriver driver;
    
	// constructor 
	public CustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page objects
	@FindBy(xpath = "//input[@placeholder='Search Customer']")
	WebElement searchBox;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/thead/tr/td")
	List<WebElement> nCols;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr")
	List<WebElement> nRows;

	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	WebElement deletCustomerBtn;

	// actions methods
	public Map<String, String> getNewCustomerDetails(String cstname) throws InterruptedException {
		Map<String, String> cstMap = new HashMap<String, String>();

		Thread.sleep(100);
		searchBox.clear();
		searchBox.sendKeys(cstname);

		Thread.sleep(500);
		for (int i = 1; i < nCols.size(); i++) {
			String keyTxt = driver
					.findElement(
							By.xpath("//table[@class='table table-bordered table-striped']/thead/tr/td[" + i + "]"))
					.getText();
			String valueTxt = driver
					.findElement(
							By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr/td[" + i + "]"))
					.getText();

			if ((keyTxt.equals("First Name")) || (keyTxt.equals("Last Name"))) {
				cstMap.put(keyTxt, valueTxt);
			} else {
				break;
			}
		}

		return cstMap;
	}

	
	public boolean deleteCustomer(String cstFName, String cstLName, String postalCode) throws InterruptedException {
		boolean status = false;

		Thread.sleep(200);
		searchBox.clear();
		searchBox.sendKeys(cstFName);

		Thread.sleep(500);
		String firstName = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr/td[" + 1 + "]"))
				.getText();
		String lastName = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr/td[" + 2 + "]"))
				.getText();
		String postCode = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr/td[" + 3 + "]"))
				.getText();

		if ((cstFName.equals(firstName)) && (cstLName.equals(lastName))
				&& (postalCode.equals(postCode))) {
			deletCustomerBtn.click();

            Thread.sleep(100);
			
			if (nRows.size() == 0) {
				status = true;
			}
		}

		return status;
	}

}
