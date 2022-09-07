package learn.selenium.listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

	@Override
	public void onStart(ITestContext Result) {
		System.out.println("I am in onStart()" +Result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult Result) {
		System.out.println("I am in onTestSuccess() name of test case passed is >> " +Result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult Result) {
		System.out.println("I am in onTestFailed() name of filed test case name is >> " +Result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult Result) {
		System.out.println("I am in onTestSkippeds() name of skipped test is >> " +Result.getName());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		System.out.println("I am in onTestFailedButWithinSuccessPercentage() name of partially failed test is >> " + Result.getName());
	}
	
	@Override
	public void onFinish(ITestContext Result) {
		System.out.println("I am in onFinish()");
	}

	@Override
	public void onTestStart(ITestResult Result) {
		System.out.println("I am in onTestStart() >> " + Result.getName());
	}
}
