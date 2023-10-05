package calc.utility.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentTest;

import calc.utility.base.driver.Driver;
import calc.utility.reports.Reporter;
import calc.utility.reports.TestCaseReporter;
import calc.utility.utilities.GlobalConstants;


/**
 * This Class is the Base Class that regulates the execution flow
 * 
 * @author Rohini
 * @since Sept 2023
 * @version 0.1
 */

public class BaseTestClass {

	protected TestCaseReporter parent = null;
	public static WebDriver driver;


	@BeforeSuite(alwaysRun = true)
	public void setSuiteSetUp() {
		try {
			Reporter.initializeExtentReport();
		} catch (Exception e) {
			Reporter.logIntoReport(false, false, "setSuiteSetUp failed " + "<br />" +"<pre>" + e.getLocalizedMessage() + "</pre>","");
		}

	}

	@BeforeClass(alwaysRun = true)
	public void beforeBaseClass(ITestContext testContext) {
		try {
			String className = this.getClass().getSimpleName();
			ExtentTest test;
			test = Reporter.extentReport.startTest(className);
			parent = new TestCaseReporter(test);
		} catch (Exception e) {
			Reporter.logIntoReport(false, false, "beforeBaseClass failed " + "<br />" +"<pre>" + e.getLocalizedMessage() + "</pre>","");
		}
	}

	@BeforeMethod(alwaysRun = true)
	protected void invokeWSTestcase(Method method, Object[] testData) {
		try {
			Reporter.startTest(method.getName()  + "_" + testData[0]);
			driver = Driver.initiateBrowser("Chrome");
			if (("PRODUCTION").equalsIgnoreCase(GlobalConstants.getenvironmentType())) {
				driver.get(GlobalConstants.getProdAPPLICATION_URL());

			}
			else if (("STAGING").equalsIgnoreCase(GlobalConstants.getenvironmentType())) {
				driver.get(GlobalConstants.getStagAPPLICATION_URL());

			}

		} catch (Exception e) {
			Reporter.logIntoReport(false, false, "invokeTestcase failed " + "<br />" +"<pre>" + e.getLocalizedMessage() + "</pre>","");
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		try {
			parent.getExtentTest().appendChild(Reporter.getTest());
			Reporter.flushReporter();


		} catch (Exception e) {
			Reporter.logIntoReport(false, false, "afterMethod failed " + "<br />" +"<pre>" + e.getLocalizedMessage() + "</pre>","");
		}

	}

	@AfterClass(alwaysRun = true)
	public void afterBaseClass() {
		try {
			Reporter.extentReport.endTest(parent.getExtentTest());

		} catch (Exception e) {
			Reporter.logIntoReport(false, false, "afterBaseClass failed " + "<br />" +"<pre>" + e.getLocalizedMessage() + "</pre>","");

		}
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		try {
			Reporter.closeReporter();
			Reporter.openReport();
		} catch (Exception e) {
		}
	}
}