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
import com.webbanking.utilities.TestUtils;



/**
 * @author Santosh Sharma
 *
 */
public class TC_AddCustomerPage extends BaseClass {
	
	LoginPage loginPage;
	ManagerPage managerPage;
	AddCustomerPage addCustomerPage;
	
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		browserSetup(browser);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	
	@Test()
	public void addCustomerTest() throws InterruptedException {
		log.info("========== " + "addCustomerTest" + " START ==========");
		loginPage = new LoginPage(driver);
		
		managerPage = loginPage.clickManagerLoginButton();
		log.info("clicked on managerlogin button");
		
		addCustomerPage = managerPage.clickAddCustomerButton();
		log.info("clicked on addcustomer button");
		
		addCustomerPage.addNewCustomer(TestUtils.CUSTOMER_FIRST_NAME,TestUtils.CUSTOMER_LAST_NAME, TestUtils.POSTAL_CODE);
		log.info("entered first name, last name & postal code");
		
		String actualAlertMsg = addCustomerPage.clickSubmit();
		log.info("clicked on submit button and saved alert window message");
		
		String expectedAlertMsg = "Customer added successfully";
		
		if(actualAlertMsg.contains(expectedAlertMsg)) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "addCustomerTest" + " END ==========");
	}

}
