/**
 * 
 */
package com.webbanking.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.webbanking.base.BaseClass;
import com.webbanking.pageobjects.AccountPage;
import com.webbanking.pageobjects.CustomerPage;
import com.webbanking.pageobjects.LoginPage;
import com.webbanking.pageobjects.TransactionDetailsPage;
import com.webbanking.utilities.TestUtils;

/**
 * @author Santosh Sharma
 *
 */
public class TC_TransactionDetailsPage extends BaseClass {

	LoginPage loginPage;
    CustomerPage customerPage;
    AccountPage accountPage;
    TransactionDetailsPage transactionDetailsPage;
	
    @SuppressWarnings("unused")
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) throws InterruptedException {
		browserSetup(browser);
		loginPage = new LoginPage(driver);
		
		customerPage = loginPage.clickCustomerLoginButton();
		customerPage.selectUser(userName);
		accountPage = customerPage.clickLoginButton();
		accountPage.inputDepositAmount(TestUtils.DEPOSIT_AMOUNT);
		String dmsg = accountPage.clickDeposit();
		accountPage.inputWithdrawalAmount(TestUtils.WITHDRAWAL_AMOUNT);	
		String wmsg = accountPage.clickWithdraw();
	}
	
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	
	@Test
	public void verifyTransactionDetailsTest() throws InterruptedException {
		log.info("========== " + "verifyTransactionDetailsTest" + " START ==========");
		
		transactionDetailsPage = accountPage.clickTransactionsButton();
		log.info("clicked on transactions button");
		
		Map<String,String> transaction = transactionDetailsPage.getTransactionDetails();
		log.info("getting transaction details");
		
		if((transaction.get("Credit").equals(TestUtils.DEPOSIT_AMOUNT)) && (transaction.get("Debit").equals(TestUtils.WITHDRAWAL_AMOUNT))) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "verifyTransactionDetailsTest" + " END ==========");
	}

}
