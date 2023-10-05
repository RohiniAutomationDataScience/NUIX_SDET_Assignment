package calc.utility.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import org.testng.Assert;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * This Class is related to Extent Report and contains the methods used to
 * interact with the Extent Report.
 * 
 * @author Rohini
 * @since sept 2023
 * @version 0.1
 *
 */
public class Reporter {

	private static String resultFolderPath;
	private static String reportPath;
	public static ExtentReports extentReport;
	public static final Date date = new Date();
	private static final HashMap<Long, TestCaseReporter> threadToTestCaseReporter = new HashMap<>();


	private Reporter() {

	}

	public static void initializeExtentReport() {

		String projectPath = System.getProperty("user.dir");
		setResultFolderPath(projectPath + File.separator + "extentReports"
				+ File.separator + date.toString().replaceAll("[: ]", "_")
				+ File.separator);
		String xmlPath = projectPath + File.separator + "reportsUtil"
				+ File.separator + "ExtentReportCustomisation.xml";

		File reportFolder = new File(getResultFolderPath());

		if (!reportFolder.exists()) {
			reportFolder.mkdir();
		}

		reportPath = resultFolderPath + "Report.html";
		extentReport = new ExtentReports(reportPath, true,
				DisplayOrder.NEWEST_FIRST);

		getExtentReport().loadConfig(new File(xmlPath));
	}

	
	/**
	 * This method is to copy the file from Source to destination
	 * 
	 * @author Rohini
	 */


	public static void openReport() throws IOException {
		String os = System.getProperty("os.name");
		if (os.contains("Windows")) {
			File htmlFile = new File(Reporter.getReportPath());
			Desktop.getDesktop().browse(htmlFile.toURI());
		}
	} 

	public static synchronized void flushReporter() {
		if (getExtentReport() != null) {
			getExtentReport().flush();

		}
	}

	public static synchronized void closeReporter() {
		if (getExtentReport() != null) {
			getExtentReport().close();
		}
	}

	public static synchronized TestCaseReporter startTest(String testName) {
		Long threadID = Thread.currentThread().getId();
		ExtentTest test = getExtentReport().startTest(testName);
		TestCaseReporter testCaseReporter = new TestCaseReporter(test);
		threadToTestCaseReporter.put(threadID, testCaseReporter);
		return testCaseReporter;
	}

	public static synchronized ExtentTest getTest() {
		try {
			Long threadID = Thread.currentThread().getId();
			if (threadToTestCaseReporter.containsKey(threadID)) {
				return threadToTestCaseReporter.get(threadID).getExtentTest();
			}

		} catch (Exception e) {
		}
		return getExtentReport().startTest(null);
	}


	/**
	 * This Method log the message into the HTML report.
	 * if 'res' value is true then it logs the pass <passMsg>.
	 * if 'res' value is false then it logs: "Condition not fulfilled: <failMsg>"
	 * Note: If 'res==false' and 'toBeAsserted==true' then It does the Assertion and fails the test case.
	 * 		 If 'res==false' and 'toBeAsserted==false' then It just log the failMsg as Warning and it desn't fail test case.
	 * 
	 * @param res
	 * @param toBeAsserted specifies want to perform assertion or not.
	 * @param failMsg
	 * @param passMsg
	 */
	public static synchronized void logIntoReport(boolean res, boolean toBeAsserted, String failMsg, String passMsg) {
		String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();
		if (res) {
			logPass(passMsg, methodName);
		} else {
			if (toBeAsserted) {
				logFail(res, failMsg, methodName);

			} else {
				logWarning(failMsg, methodName);
			}

		}
	}


	private static void logPass(String passMsg, String methodName) {
		//getLogger().info(passMsg + " , Step is Passed");
		if (getTest() != null) {
			getTest().log(LogStatus.PASS, passMsg);
		}
	}



	public static void logFail(boolean res, String failMsg, String methodName) {
		//getLogger().info(failMsg + " , Step is Failed");
		if (getTest() != null) {
			getTest().log(LogStatus.FAIL, failMsg);
		}

		Assert.assertTrue(res, failMsg);
	}

	private static void logWarning(String failMsg, String methodName) {

		//getLogger().info(failMsg + " , Step is Passed with Warning");
		if (getTest() != null) {
			getTest().log(LogStatus.WARNING, failMsg);
		}

	}

	/**
	 * This Method log the message into the HTML report. if 'res' value is true
	 * then it logs the pass <passMsg>. if 'res' value is false then it logs:
	 * "Condition not fulfilled: <failMsg>" Note: If 'res==false' and
	 * 'toBeAsserted==true' then It does the Assertion and fails the test case.
	 * If 'res==false' and 'toBeAsserted==false' then It just log the failMsg as
	 * Warning and it desn't fail test case.
	 * 
	 * 
	 * @param res
	 * @param toBeAsserted specifies want to perform assertion or not.
	 * @param failMsg
	 * @param passMsg
	 */
	public static void logIntoReport(boolean res, boolean toBeAsserted, String passMsg) {

		String failMsg = "Condition not fulfilled: " + passMsg;
		logIntoReport(res, toBeAsserted, failMsg, passMsg);
	}


	public static void logIntoReport(LogStatus logStatus, String details) {
		if (getTest() != null) {
			getTest().log(logStatus, details);
		}
	}


	public static ExtentReports getExtentReport() {
		return extentReport;
	}



	public static String getReportPath() {
		return reportPath;
	}

	public static String getResultFolderPath() {
		return resultFolderPath;
	}

	private static void setResultFolderPath(String resultFolderPath) {
		Reporter.resultFolderPath = resultFolderPath;
	}
}
