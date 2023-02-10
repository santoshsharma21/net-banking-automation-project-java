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

/**
 * @author Santosh Sharma
 *
 */
public class TC_LoginPage extends BaseClass {
	
	LoginPage loginPage;
    
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser) {
		browserSetup(browser);
		loginPage = new LoginPage(driver);
	}
    
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
    
	
	@Test(priority = 0)
	public void customerLoginButtonDisplayTest() {
		log.info("========== " + "customerLoginButtonDisplayTest" + " START ==========");

		boolean status = loginPage.validateCustomerLoginButton();
		log.info("verifying customer login button display status");
		
		if(status == true) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "customerLoginButtonDisplayTest" + " END ==========");
	}
    
	
	@Test(priority = 1)
	public void managerLoginButtonDisplayTest() {
		log.info("========== " + "managerLoginButtonDisplayTest" + " START ==========");
		
		boolean status = loginPage.validateManagerLoginButton();
		log.info("verifying manager login button display status");
		
		if(status == true) {
			Assert.assertTrue(true);
			log.info("Test Passed");
			
		} else {
			log.error("Test Failed");
			Assert.assertTrue(false);
		}

		log.info("========== " + "managerLoginButtonDisplayTest" + " END ==========");
	}

}
