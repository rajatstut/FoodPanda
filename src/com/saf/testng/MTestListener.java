package com.saf.testng;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.saf.util.FileOperation;

public class MTestListener extends TestListenerAdapter {
	public static int m_count = 0;
	public static float pass_count=0;
	public static float fail_count=0;
	public static float skip_count=0;
	public static float percent=0;

	@Override
	public void onTestFailure(ITestResult tr) {
		//tr.setStatus(ITestResult.SUCCESS);
		//log("F");
		fail_count++;
		update_status("<font color=red>"+">"+tr.getName()+"</font>");
	}

	private void update_status(String msg) {
		percent=pass_count/(pass_count+fail_count);
		percent*=100.0;
		FileOperation.flog("status.html","<b>"+msg+", "+pass_count+" PASS, "+fail_count+" FAIL, "+skip_count+" SKIP, "+percent+"% PASS</b>");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		skip_count++;//log("S");
		update_status("<font color=yellow>"+">"+tr.getName()+"</font>");
	}
	@Override
	public void onTestStart(ITestResult tr) {
		FileOperation.flog("status.html","<b>Executing: "+tr.getName()+"</b>");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		//tr.getTestContext().getFailedTests()t.removeResult(tr);
		pass_count++;
		update_status("<font color=green>"+">"+tr.getName()+"</font>");
	}

	public void slog(String msg) {
		System.out.println(msg);
		
	}

	@Override
    public void onFinish(ITestContext testContext) {
        update_status("Final Status:");
        // List of test results which we will delete later
        List<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();

        // collect all id's from passed test
        Set <Integer> passedTestIds = new HashSet<Integer>();
        for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
            passedTestIds.add(TestUtil.getId(passedTest));
        }

        Set <Integer> failedTestIds = new HashSet<Integer>();
        for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {

            // id = class + method + dataprovider
            int failedTestId = TestUtil.getId(failedTest);

            // if we saw this test as a failed test before we mark as to be deleted
            // or delete this failed test if there is at least one passed version
            if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
                testsToBeRemoved.add(failedTest);
            } else {
                failedTestIds.add(failedTestId);
            }
        }

        // finally delete all tests that are marked
        for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext(); ) {
            ITestResult testResult = iterator.next();
            if (testsToBeRemoved.contains(testResult)) {
                iterator.remove();
            }
        }
        super.onFinish(testContext);


    }

	@SuppressWarnings("unused")
	private void log(String string) {
		System.out.print(string);
		if (++m_count % 40 == 0) {
			System.out.println("");
		}
	}
}
