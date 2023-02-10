/**
 * 
 */
package com.webbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

/**
 * @author Santosh Sharma
 *
 */
public class ReportingClass {

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest testlog;

	public static void setupExtent() throws IOException {

		String dateTime = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(new Date());
		String reportName = "Test-Report_" + dateTime + ".html";

		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentreports/" + reportName);
		spark.loadJSONConfig(new File(System.getProperty("user.dir") + "/spark-config.json"));
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR
                ,ViewName.DEVICE, ViewName.EXCEPTION}).apply();

		extent = new ExtentReports();
		extent.attachReporter(spark);
        
		//extent.setSystemInfo("Tester", "Santosh Sharma");
		extent.setSystemInfo("Project Name", "Web-Banking Automation Project");
		extent.setSystemInfo("App URL", ReadConfig.pro.getProperty("url"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java version", System.getProperty("java.version"));
	}

	public static void endReport() {
		extent.flush();
	}

}
