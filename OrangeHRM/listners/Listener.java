package learn.selenium.listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("I am in onFinish()");
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("I am in onStart()");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("I am in onTestFailedButWithinSuccessPercentage()");
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("I am in onTestFailed()");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("I am in onTestSkippeds()");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("I am in onTestStart()");
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("I am in onTestSuccess()");
	}
}
