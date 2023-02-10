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
import com.webbanking.pageobjects.LoginPage;
import com.webbanking.pageobjects.ManagerPage;

/**
 * @author Santosh Sharma
 *
 */
public class TC_ManagerPage extends BaseClass {

	LoginPage loginPage;
	ManagerPage managerPage;
    
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		browserSetup(browser);
		loginPage = new LoginPage(driver);
		managerPage = loginPage.clickManagerLoginButton();

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test(priority = 0)
	public void addCustomerButtonDisplayTest() {
		log.info("========== " + "addCustomerButtonDisplayTest" + " START =========="); 

		boolean status = managerPage.validateAddCustomerButton();
		log.info("verifying addcustomer button");

		if (status == true) {
			Assert.assertTrue(true);
			log.info("Test Passed");

		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}

		log.info("========== " + "addCustomerButtonDisplayTest" + " END ==========");
	}

	@Test(priority = 1)
	public void openAccountButtonDisplayTest() {
		log.info("========== " + "openAccountButtonDisplayTest" + " START ==========");
		
		boolean status = managerPage.validateOpenAccountButton();
		log.info("verifying openaccount button");

		if (status == true) {
			Assert.assertTrue(true);
			log.info("Test Passed");

		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "openAccountButtonDisplayTest" + " END ==========");
	}

	@Test(priority = 2)
	public void customersButtonDisplayTest() {
		log.info("========== " + "customersButtonDisplayTest" + " START ==========");
		
		boolean status = managerPage.validateCustomersButton();
		log.info("verifying customers button");

		if (status == true) {
			Assert.assertTrue(true);
			log.info("Test Passed");

		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "customersButtonDisplayTest" + " END ==========");
	}

}
