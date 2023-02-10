/**
 * 
 */
package com.webbanking.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.webbanking.base.BaseClass;

/**
 * @author Santosh Sharma
 *
 */
public class ListenerClass extends ReportingClass implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		testlog = extent.createTest(result.getName()); // + " (" + BaseClass.getBrowserName() + ")");
		testlog.assignAuthor("santosh")
		.assignDevice(BaseClass.getBrowserName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testlog.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Passed", ExtentColor.GREEN));
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			try {
				testlog.log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Failed", ExtentColor.RED));
				testlog.log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - Failed", ExtentColor.RED));

				String imagePath = TestUtils.captureScreen(BaseClass.driver, result.getName());

				testlog.fail("Screenshot is attached",
						MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			testlog.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Skiped", ExtentColor.ORANGE));
		}
	}
	
}
