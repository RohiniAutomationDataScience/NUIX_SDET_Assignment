package calc.wspages.calcDemo;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import calc.utility.base.BaseTestClass;
import calc.utility.base.driver.DriverManager;
import calc.utility.reports.Reporter;


public class WebCalcLoad23 extends BaseTestClass{


	public static WebDriver driver;

	public WebCalcLoad23() {
		synchronized (this) {
			// This initElements method will create all WebElements
			PageFactory.initElements(DriverManager.getDriver(), this);
			//this.driver = DriverManager.getDriver();

		}
	}


	static Double Finalnumber1;
	static Double Finalnumber2;

	static String txtDisplayResultXX = "//div[@id='display']";
	public static String btnAdditionXX = "//input[@id='add']";
	static String btnSubstractionXX = "//input[@id='subtract']";
	static String btnDevidationXX = "//input[@id='divide']";
	String btnMultiplicationXX = "//input[@id='multiply']";
	static String btnClearXX = "//input[@value='C']";
	static String btnDotXX = "//input[@value='.']";
	static String btnEqualToOperatorXX = "//input[@value='=']";
	String btnNumbers = "//input[@value='%s']";

	@FindBy(xpath = "//div[@id='display']")
	static WebElement txtDisplayResult;

	@FindBy(xpath = "//input[@id='add']")
	static WebElement btnAddition;

	@FindBy(xpath = "//input[@id='divide']")
	static WebElement btnDevidation;

	@FindBy(xpath = "//input[@id='multiply']")
	static WebElement btnMultiplication;

	@FindBy(xpath = "//input[@id='subtract']")
	static WebElement btnSubstraction;

	@FindBy(xpath = "//input[@id='C']")
	static WebElement btnClear;

	@FindBy(xpath = "//input[@id='.']")
	static WebElement btnDot;

	@FindBy(xpath = "//input[@id='=']")
	static WebElement btnEqualToOperator;

	@FindBy(xpath = "//input[@value='%s']")
	static WebElement btnNumbersXX;





	public void LaunchApplication() {
		Reporter.logIntoReport(LogStatus.INFO,
				"User has launched Calcualtor application");

		try {
			if (DriverManager.getDriver().getTitle() != null  && DriverManager.getDriver().getTitle().equalsIgnoreCase("Calculator")) {
				Reporter.logIntoReport(true, true, "", " Launched Successfully");
				//	Utils.takeScreenshot("Home Page", "Home Page displayed");
			} else {
				Reporter.logIntoReport(false, true, " calculator is not launched", "");
			}


		} catch (Exception e) {
			Reporter.logIntoReport(false, true,
					"Error in LaunchApplication test :" + e.getMessage(), "");
		}

	}


	public void LaunchApplication2() {
		Reporter.logIntoReport(LogStatus.INFO,
				"User has launched Calcualtor application");

		try {
			if (DriverManager.getDriver().getCurrentUrl() != null  && DriverManager.getDriver().getCurrentUrl().equalsIgnoreCase("Calculator")) {
				Reporter.logIntoReport(true, true, "", " Launched Successfully");
				//	Utils.takeScreenshot("Home Page", "Home Page displayed");
			} else {
				Reporter.logIntoReport(false, true, " calculator is not launched", "");
			}


		} catch (Exception e) {
			Reporter.logIntoReport(false, true,
					"Error in LaunchApplication test :" + e.getMessage(), "");
		}

	}

	public static double validateOperations(String Operation, Object number1, Object number2) {
		double result = 0.0;
		/*
		 * WebElement add = DriverManager.getDriver().findElement(
		 * By.xpath(btnAddition)); WebElement substract =
		 * DriverManager.getDriver().findElement( By.xpath(btnSubstraction)); WebElement
		 * devide = DriverManager.getDriver().findElement( By.xpath(btnDevidation));
		 * WebElement multiply = DriverManager.getDriver().findElement(
		 * By.xpath(btnAddition));
		 * 
		 * WebElement clear = DriverManager.getDriver().findElement(
		 * By.xpath(btnClear)); WebElement dot = DriverManager.getDriver().findElement(
		 * By.xpath(btnDot)); WebElement equalTo =
		 * DriverManager.getDriver().findElement( By.xpath(btnEqualToOperator));
		 */


		WebElement firstNum = DriverManager.getDriver()
				.findElement(
						By.xpath("//input[@value='" + number1.toString().replace(".0", "") + "']"));

		WebElement secondNum = DriverManager.getDriver()
				.findElement(
						By.xpath("//input[@value='" + number2.toString().replace(".0", "")  + "']"));

		/*
		 * WebElement displayResult = DriverManager.getDriver().findElement(
		 * By.xpath(txtDisplayResult));
		 */



		try {
			switch (Operation) {
			case "+":
				firstNum.click();
				Finalnumber1 = Double.parseDouble(txtDisplayResult.getText());
				btnAddition.click();
				secondNum.click();
				Finalnumber2 = Double.parseDouble(txtDisplayResult.getText());
				btnEqualToOperator.click();
				result = Finalnumber1 + Finalnumber2;
				Reporter.logIntoReport(true, true, " Addition is : " +result, " Addition is : " +result);
				break;

			case "-": 

				firstNum.click();
				Finalnumber1 = Double.parseDouble(txtDisplayResult.getText());
				btnSubstraction.click();
				secondNum.click();
				Finalnumber2 = Double.parseDouble(txtDisplayResult.getText());
				btnEqualToOperator.click();
				result = Finalnumber1 - Finalnumber2;
				Reporter.logIntoReport(true, true, " Substarction is : " +result, " Substarction is : " +result);


				break; 

			case "*": 
				firstNum.click();
				Finalnumber1 = Double.parseDouble(txtDisplayResult.getText());
				btnMultiplication.click();
				secondNum.click();
				Finalnumber2 = Double.parseDouble(txtDisplayResult.getText());
				btnEqualToOperator.click();
				result = Finalnumber1 * Finalnumber2;
				Reporter.logIntoReport(true, true, " Multiplication is : " +result, " Multiplication is : " +result);


				break; 

			case "/":

				if (Finalnumber2 != 0) {

					firstNum.click();
					Finalnumber1 = Double.parseDouble(txtDisplayResult.getText());
					btnDevidation.click();
					secondNum.click();
					Finalnumber2 = Double.parseDouble(txtDisplayResult.getText());
					btnEqualToOperator.click();
					result = Finalnumber1 / Finalnumber2;
					Reporter.logIntoReport(true, true, " Devidation is : " +result, " Devidation is : " +result);



				} else { throw new
					IllegalArgumentException("Division by zero is not allowed."); } break;
			default: throw new IllegalArgumentException("Invalid operator: " +
					Operation);
			}
		}catch (ArithmeticException e) {
			e.printStackTrace();
		}



		return result;
	}



	public void validateResult(Object obj1, Object obj2) {
		boolean flag = false;

		if (obj1.toString().equals(obj2.toString())) {
			flag= true;
			Reporter.logIntoReport(flag, true, " Expected value is : " +obj1 + " Actual value is : " +obj2, " Expected value is : " +obj1 + " Actual value is : " +obj2);
		}

		else {
			flag = false;
			Reporter.logIntoReport(flag, true, " Expected value is : " +obj1 + " Actual value is : " +obj2, " Expected value is : " +obj1 + " Actual value is : " +obj2);
		}
	}


}
