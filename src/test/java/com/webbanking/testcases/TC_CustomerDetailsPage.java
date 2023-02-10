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
import com.webbanking.pageobjects.AddCustomerPage;
import com.webbanking.pageobjects.CustomerDetailsPage;
import com.webbanking.pageobjects.LoginPage;
import com.webbanking.pageobjects.ManagerPage;
import com.webbanking.pageobjects.OpenAccountPage;
import com.webbanking.utilities.TestUtils;

/**
 * @author Santosh Sharma
 *
 */
public class TC_CustomerDetailsPage extends BaseClass {

	LoginPage loginPage;
	ManagerPage managerPage;
	AddCustomerPage addCustomerPage;
	OpenAccountPage openAccountPage;
	CustomerDetailsPage customerDetailsPage;
    
	@Parameters("browser")
	@BeforeMethod
	@SuppressWarnings("unused")
	public void beforeMethod(String browser) throws InterruptedException {
		
		browserSetup(browser);
		loginPage = new LoginPage(driver);
		managerPage = loginPage.clickManagerLoginButton();

		addCustomerPage = managerPage.clickAddCustomerButton();
		addCustomerPage.addNewCustomer(TestUtils.CUSTOMER_FIRST_NAME,TestUtils.CUSTOMER_LAST_NAME, TestUtils.POSTAL_CODE);
		String addCstMsg = addCustomerPage.clickSubmit();

		Thread.sleep(500);
		managerPage = addCustomerPage.goToPreviuousPage();
		
		openAccountPage = managerPage.clickOpenAccountButton();
		openAccountPage.openAccount(TestUtils.FULL_NAME, TestUtils.CURRENCY);
		String openActMsg = openAccountPage.clickProcess();
		
		Thread.sleep(500);
		managerPage = openAccountPage.goToPreviuousPage();
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test(priority = 0)
	public void verifyCustomerDetailsTest() throws InterruptedException {
		log.info("========== " + "verifyCustomerDetailsTest" + " START ==========");
		
		customerDetailsPage = managerPage.clickCustomersButton();
		log.info("clicked on customers button");
		
		Map<String,String> cstDetailsMap = customerDetailsPage.getNewCustomerDetails(TestUtils.CUSTOMER_FIRST_NAME);
		log.info("saved customer details");
		
		if((cstDetailsMap.get("First Name").equals(TestUtils.CUSTOMER_FIRST_NAME)) 
				&& (cstDetailsMap.get("Last Name").equals(TestUtils.CUSTOMER_LAST_NAME))){
			
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}

		log.info("========== " + "verifyCustomerDetailsTest" + " END ==========");
	}

	@Test(priority = 1)
	public void deleteCustomerTest() throws InterruptedException {
		log.info("========== " + "deleteCustomerTest" + " START ==========");
		
		customerDetailsPage = managerPage.clickCustomersButton();
		log.info("clicked on customers button");
		
		log.info("entered customer name, postal code");
		boolean cstStatus = customerDetailsPage.deleteCustomer(TestUtils.CUSTOMER_FIRST_NAME
				                                                        , TestUtils.CUSTOMER_LAST_NAME
				                                                        , TestUtils.POSTAL_CODE);
		log.info("clicked on delete button to delete customer");
		
		if(cstStatus == true) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "deleteCustomerTest" + " END ==========");
	}

}
