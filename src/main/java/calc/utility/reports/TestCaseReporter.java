package calc.utility.reports;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseReporter {

	

	private ExtentTest extentTest = null;
	/**
	 * 
	 */
	public TestCaseReporter(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}
	
	public ExtentTest getExtentTest() {
		return this.extentTest;
	}
	
	public void log(LogStatus logStatus, String details) {
		this.extentTest.log(logStatus, details);
	}
	public void logIntoReport(boolean res, boolean toBeAsserted, String failMsg, String passMsg) {
		Reporter.logIntoReport(res, toBeAsserted, failMsg, passMsg);
	}
}
