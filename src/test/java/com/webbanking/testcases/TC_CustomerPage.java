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

/**
 * @author Santosh Sharma
 *
 */
public class TC_CustomerPage extends BaseClass {
	
	LoginPage loginPage;
	CustomerPage customerPage;
	AccountPage accountPage;
	
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
	
	
	@Test
	public void customerLoginTest() throws InterruptedException {
		log.info("========== " + "customerLoginTest" + " START ==========");
		
		customerPage = loginPage.clickCustomerLoginButton();
		log.info("clicked on customer login button");
		
		customerPage.selectUser(userName);  
		log.info("selected user name from drop down");
		
		accountPage = customerPage.clickLoginButton();
		log.info("clicked on login button");
		
		String currentCustomer = accountPage.getLoggedinCustomerName();
		
		if(currentCustomer.equals(userName)) {
			Assert.assertTrue(true);
			log.info("login successful");
			log.info("Test Passed");
			
		} else {
			log.info("login unsuccessful");
			log.error("Test Failed");
			Assert.assertTrue(false);
		}
		
		log.info("========== " + "customerLoginTest" + " END ==========");
	}

}
