package testngListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener

{
	public void onStart(ITestContext context) {
		System.out.println("started test execution"); 
	}
	public void onTestStart(ITestResult result) {
		System.out.println("Test started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test execution completed");
	}

	

}
