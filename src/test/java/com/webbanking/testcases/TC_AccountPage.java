/**
 * 
 */
package com.webbanking.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.webbanking.base.BaseClass;
import com.webbanking.pageobjects.AccountPage;
import com.webbanking.pageobjects.CustomerPage;
import com.webbanking.pageobjects.LoginPage;
import com.webbanking.utilities.TestUtils;

/**
 * @author Santosh Sharma
 *
 */
public class TC_AccountPage extends BaseClass {

	LoginPage loginPage;
	CustomerPage customerPage;
	AccountPage accountPage;

	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) throws InterruptedException {
		browserSetup(browser);
		loginPage = new LoginPage(driver);

		customerPage = loginPage.clickCustomerLoginButton();
		customerPage.selectUser(userName);
		accountPage = customerPage.clickLoginButton();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test(priority = 0)
	public void depositTest() throws InterruptedException {
		log.info("========== " + "depositTest" + " START ==========");

		accountPage.inputDepositAmount(TestUtils.DEPOSIT_AMOUNT);
		log.info("amount entered to deposit");

		String actualMsg = accountPage.clickDeposit();
		log.info("clicked on button to deposit amount and saved message");

		String expectedMsg = "Deposit Successful";

		if (actualMsg.equals(expectedMsg)) {
			Assert.assertTrue(true);
			log.info("Test Passed");

		} else {
			log.error("Test Failed");
			Assert.assertFalse(false);
		}

		log.info("========== " + "depositTest" + " END ==========");
	}

	@SuppressWarnings("unused")
	@Test(priority = 1)
	public void withdrawalTest() throws InterruptedException {
		log.info("========== " + "withdrawalTest" + " START ==========");

		accountPage.inputDepositAmount(TestUtils.DEPOSIT_AMOUNT);
		String msg = accountPage.clickDeposit();

		accountPage.inputWithdrawalAmount(TestUtils.WITHDRAWAL_AMOUNT);
		log.info("amount entered to withdraw");

		String actualMsg = accountPage.clickWithdraw();
		log.info("clicked on button to withdraw amount and saved message");

		String expectedMsg = "Transaction successful";

		if (actualMsg.equals(expectedMsg)) {
			Assert.assertTrue(true);
			log.info("Test Passed");

		} else {
			log.error("Test Failed");
			Assert.assertFalse(false);
		}

		log.info("========== " + "withdrawalTest" + " END ==========");
	}

}
