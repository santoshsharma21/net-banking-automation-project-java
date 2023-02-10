package com.webbanking.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.webbanking.base.BaseClass;
import com.webbanking.dataprovider.DataProviders;
import com.webbanking.pageobjects.AccountPage;
import com.webbanking.pageobjects.AddCustomerPage;
import com.webbanking.pageobjects.CustomerPage;
import com.webbanking.pageobjects.LoginPage;
import com.webbanking.pageobjects.ManagerPage;
import com.webbanking.pageobjects.OpenAccountPage;
import com.webbanking.pageobjects.TransactionDetailsPage;


public class TC_EndToEnd extends BaseClass {

	LoginPage loginPage;
	ManagerPage managerPage;
	AddCustomerPage addCustomerPage;
	OpenAccountPage openAccountPage;
	CustomerPage customerPage;
	AccountPage accountPage;
	TransactionDetailsPage transactionDetailsPage;

	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		browserSetup(browser);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@SuppressWarnings("unused")
	@Test(dataProvider = "e2eData", dataProviderClass = DataProviders.class)
	public void endToendTest(String fname, String lname, String pcode, String currency, String depositAmt,
			String withdrawAmt) throws InterruptedException {
		
		log.info("========== " + "endToendTest" + " START ==========");
		String fullName = fname + " " + lname;

		loginPage = new LoginPage(driver);
		managerPage = loginPage.clickManagerLoginButton();
		log.info("clicked on managerlogin button");
		
		addCustomerPage = managerPage.clickAddCustomerButton();
		log.info("clicked on addcustomer button");
		
		addCustomerPage.addNewCustomer(fname, lname, pcode);
		log.info("entered first name, last name and postal code");
		
		String addCstAlertMsg = addCustomerPage.clickSubmit();
		log.info("new customer added successfully");
		
		addCustomerPage.goToPreviuousPage();
		
		openAccountPage = managerPage.clickOpenAccountButton();
		log.info("clicked on openaccount button");
		
		openAccountPage.openAccount(fullName, currency);
		log.info("entered name and currency");
		
		String openActAlertMsg =  openAccountPage.clickProcess();
		log.info("account opened successfully");
		
		loginPage = openAccountPage.clickHomeButton();
		log.info("clicked on home button");
		
		customerPage = loginPage.clickCustomerLoginButton();
		log.info("clicked on customerlogin button");
		
		customerPage.selectUser(fullName);
		log.info("user selected from drop down");
		
		accountPage = customerPage.clickLoginButton();
		log.info("login successful");
		
		accountPage.inputDepositAmount(depositAmt);
		log.info("amount entered to deposit");
		
		String depositMsg = accountPage.clickDeposit();
		log.info("amount credited successfully");
		
		accountPage.inputWithdrawalAmount(withdrawAmt);
		log.info("amount entered to withdraw");
		
		String withdrawalMsg = accountPage.clickWithdraw();
		log.info("amount debited successfully");
		
		transactionDetailsPage = accountPage.clickTransactionsButton();
		log.info("clicked on transactions button");
		
		Map<String,String> transactionDetails = transactionDetailsPage.getTransactionDetails();
		log.info("getting transaction details");
		
		if((transactionDetails.get("Credit").equals(depositAmt)) && (transactionDetails.get("Debit").equals(withdrawAmt))) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "endToendTest" + " END ==========");
	}

}
