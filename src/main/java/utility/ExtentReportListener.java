package utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import driverFactory.InitDriver;

public class ExtentReportListener extends ExtentReportManager implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
		//test = extent.createTest(result.getName());
		ExtentReportManager.startTest(result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if(result.getStatus()== ITestResult.SUCCESS) {
			ExtentReportManager.getTest().log(Status.PASS, "Passed testcase is:" +result.getName());
			ExtentReportManager.getTest().log(Status.PASS, MarkupHelper.createLabel("passed testcase is" +result.getName(), ExtentColor.GREEN));	
			try {
				String screenshotPath = ScreenshotUtil.takeScreenshot(InitDriver.getDriver(), result.getName());
				ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if(result.getStatus()== ITestResult.FAILURE) {
			ExtentReportManager.getTest().log(Status.FAIL, "Failed testcase is:" +result.getName());
			ExtentReportManager.getTest().log(Status.FAIL, MarkupHelper.createLabel("failed testcase is" +result.getName(), ExtentColor.RED));
			ExtentReportManager.getTest().log(Status.FAIL, MarkupHelper.createLabel("failed testcase is" +result.getThrowable(), ExtentColor.RED));
//			try {
//				String screenshotPath = ScreenshotUtil.takeScreenshot(InitDriver.getDriver(), result.getName());
//				ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if(result.getStatus()== ITestResult.SKIP) {
			ExtentReportManager.getTest().log(Status.SKIP, "Skipped testcase is:" +result.getName());
		}
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}
	

}
