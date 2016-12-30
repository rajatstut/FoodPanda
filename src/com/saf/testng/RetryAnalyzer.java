package com.saf.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.saf.global.Global;
import com.saf.global.LoadProperties;
import com.saf.testng.MTestListener;

public class RetryAnalyzer extends Global implements IRetryAnalyzer {

	private static final int maxCount = LoadProperties.RETRY;
	private static int retries = 0;

	public synchronized boolean retry(ITestResult result) {
		//ITestContext context = result.getTestContext();
		//IResultMap failedTests = context.getFailedTests();
		// result.getTestContext().getFailedTests().removeResult(
		// result.getMethod() );
		//System.out.println(result.getMethod());
		// failedTests.removeResult(result);
		String methodName = result.getMethod().toString().split("\\(")[0].trim();
		if (getRetryCount(methodName) < maxCount) {
			increamentRetryCount(methodName);
			String message = Thread.currentThread().getName() + ": Error in " + result.getName() + " Retrying " + (maxCount + 1 - getRetryCount(methodName)) + " more times";

			//System.out.println(message);
			//System.out.println("getMethod:"+result.getMethod()+" getTestName :"+result.getTestName()+" getTestClass :"+result.getTestClass());
			Reporter.log(message);
			Reporter.log("Retries:" + ++retries);
			result.setStatus(ITestResult.SUCCESS);

	//		result.getTestContext().getFailedTests().removeResult(result);

			//result.getTestContext().getFailedTests().removeResult(result);

			MTestListener.fail_count--;
			return true;
		} else {
			// result.setStatus(ITestResult.FAILURE);
			//result.getTestContext().getFailedTests().removeResult(result.getMethod());
			return false;
		}

	}

}