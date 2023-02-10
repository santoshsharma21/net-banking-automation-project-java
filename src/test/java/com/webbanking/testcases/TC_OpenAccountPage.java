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
import com.webbanking.pageobjects.AddCustomerPage;
import com.webbanking.pageobjects.LoginPage;
import com.webbanking.pageobjects.ManagerPage;
import com.webbanking.pageobjects.OpenAccountPage;
import com.webbanking.utilities.TestUtils;

/**
 * @author Santosh Sharma
 *
 */
public class TC_OpenAccountPage extends BaseClass {
	
	LoginPage loginPage;
	ManagerPage managerPage;
	AddCustomerPage addCustomerPage;
	OpenAccountPage openAccountPage;
    
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) throws InterruptedException {
		browserSetup(browser);
		
		loginPage = new LoginPage(driver);
		managerPage = loginPage.clickManagerLoginButton();
		addCustomerPage = managerPage.clickAddCustomerButton();
		addCustomerPage.addNewCustomer(TestUtils.CUSTOMER_FIRST_NAME,TestUtils.CUSTOMER_LAST_NAME, TestUtils.POSTAL_CODE);
		@SuppressWarnings("unused")
		String addCstMsg = addCustomerPage.clickSubmit();
		
		Thread.sleep(500);
		managerPage = addCustomerPage.goToPreviuousPage();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	
	@Test()
	public void openAccountTest() throws InterruptedException {
		log.info("========== " + "openAccountTest" + " START ==========");
		
		openAccountPage = managerPage.clickOpenAccountButton();
		log.info("clicked on openaccount button");
		
		openAccountPage.openAccount(TestUtils.FULL_NAME, TestUtils.CURRENCY);
		log.info("entered name & currency");
		
		String actualAlertMsg = openAccountPage.clickProcess();
		log.info("clicked on process button and saved alert window message");
	
		String expectedAlertMsg = "Account created successfully";
		
		if(actualAlertMsg.contains(expectedAlertMsg)) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "openAccountTest" + " END ==========");
	}

}
