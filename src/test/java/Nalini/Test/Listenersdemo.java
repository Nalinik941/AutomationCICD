package Nalini.Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Nalini.resources.Extentreporterng;


public class Listenersdemo extends BaseTest implements ITestListener
{
    ExtentTest test;
	ExtentReports extents=Extentreporterng.get();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//Thread set when we run test parallelly then concurrenct will happen test will get confuse in which class or method test got failed to avoid that we are adding threadset
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
                // we are adding ITTestListener
		ITestListener.super.onTestStart(result);
		test= extents.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//uniue thread id (error validation test)for each test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-[generated method stub
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		test.log(Status.FAIL, "Test failed");
	    extentTest.get().fail(result.getThrowable());
		
			try {
				driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} 
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
				String Filepath = null;
		try {
			Filepath = getscreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		test.log(Status.SKIP, "Test skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extents.flush();
	}

}
