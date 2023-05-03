/**
 * 
 */
package com.webbanking.base;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.webbanking.utilities.ReadConfig;
import com.webbanking.utilities.ReportingClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Santosh Sharma
 *
 */
public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	public String baseUrl = readConfig.baseUrl();
	public String userName = readConfig.uName();
   
	public static WebDriver driver;
	public static Logger log;
	

	@BeforeSuite
	public void beforeSuite() throws IOException {
		ReportingClass.setupExtent();
	}

	@AfterSuite
	public void afterSuite() {
		ReportingClass.endReport();
	}

	// return browser name and version
	public static String getBrowserName() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String brname = cap.getBrowserName();
		String brversion = cap.getBrowserVersion();
		return brname + "-" + brversion;
	}

	// initialize browser
	public void browserSetup(String browserName) {

		log = Logger.getLogger("webbank");
		PropertyConfigurator.configure("log4j.properties");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		// open url
		driver.get(baseUrl);

		// maximize window
		driver.manage().window().maximize();

		// add implicit timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

}
