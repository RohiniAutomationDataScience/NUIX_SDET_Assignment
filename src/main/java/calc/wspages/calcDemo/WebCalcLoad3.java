package calc.wspages.calcDemo;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import calc.utility.base.driver.DriverManager;
import calc.utility.reports.Reporter;


public class WebCalcLoad3 {

	WebDriver driver;

	public WebCalcLoad3() {
		synchronized (this) 
		{
			// This initElements method will create all WebElements
			//PageFactory.initElements(DriverManager.getDriver(), this);
			this.driver = DriverManager.getDriver();



		}
	}


	/*
	 * WebDriver driver;
	 * 
	 * public WebCalcLoad(WebDriver driver){ this.driver = driver;
	 * PageFactory.initElements(new WebDriver(driver), this); }
	 */


	static String txtDisplayResult = "//div[@id='display']";
	public static String btnAddition = "//input[@id='add']";
	static String btnSubstraction = "//input[@id='subtract']";
	static String btnDevidation = "//input[@id='divide']";
	String btnMultiplication = "//input[@id='multiply']";
	static String btnClear = "//input[@value='C']";
	static String btnDot = "//input[@value='.']";
	static String btnEqualToOperator = "//input[@value='=']";
	String btnNumbers = "//input[@value='%s']";


	//WebElement txtDisplayResultXX1 = driver.findElement(By.xpath("//div[@id='display']"));

	@FindBy(xpath = "//div[@id='display']")
	static WebElement txtDisplayResultXX;

	@FindBy(xpath = "//input[@id='add']")
	static WebElement btnAdditionXX;

	@FindBy(xpath = "//input[@id='divide']")
	static WebElement btnDevidationXX;

	@FindBy(xpath = "//input[@id='multiply']")
	static WebElement btnMultiplicationXX;

	@FindBy(xpath = "//input[@id='subtract']")
	static WebElement btnSubstractionXX;

	@FindBy(xpath = "//input[@id='C']")
	static WebElement btnClearXX;

	@FindBy(xpath = "//input[@id='.']")
	static WebElement btnDotXX;

	@FindBy(xpath = "//input[@id='=']")
	static WebElement btnEqualToOperatorXX;

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
						By.xpath("//input[@value='" + number1 + "']"));

		WebElement secondNum = DriverManager.getDriver()
				.findElement(
						By.xpath("//input[@value='" + number2 + "']"));

		/*
		 * WebElement displayResult = DriverManager.getDriver().findElement(
		 * By.xpath(txtDisplayResult));
		 * 
		 */

		try {
			switch (Operation) {
			case "+":
				firstNum.click();
				Double Finalnumber1 = Double.parseDouble(txtDisplayResultXX.getText());
				btnAdditionXX.click();
				secondNum.click();
				Double Finalnumber2 = Double.parseDouble(txtDisplayResultXX.getText());
				btnEqualToOperatorXX.click();
				result = Finalnumber1 + Finalnumber2;
				Reporter.logIntoReport(true, true, " Addition is : " +result, " Addition is : " +result);
				break;
			}
		}
		/*case "-":
				result = number1 - number2;
				Reporter.logIntoReport(true, true, " Substarction is : " +result, "");

				break;
			case "*":
				result = number1 * number2;
				Reporter.logIntoReport(true, true, " Multiplication is : " +result, "");

				break;
			case "/":
				if (number2 != 0) {
					result = number1 / number2;
					Reporter.logIntoReport(true, true, " Devision is : " +result, "");

				} else {
					throw new IllegalArgumentException("Division by zero is not allowed.");
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid operator: " + Operation);
			}
		 */		//}

		catch (ArithmeticException e) {
			e.printStackTrace();
		}



		return result;
	}



	public static void sum() {


	}

	public static double performOperation(double operand1, double operand2, String operation) {
		BigDecimal result = BigDecimal.ZERO;

		switch (operation.toLowerCase()) {
		case "add":
			result = BigDecimal.valueOf(operand1).add(BigDecimal.valueOf(operand2));
			break;
		case "subtract":
			result = BigDecimal.valueOf(operand1).subtract(BigDecimal.valueOf(operand2));
			break;
		case "multiply":
			result = BigDecimal.valueOf(operand1).multiply(BigDecimal.valueOf(operand2));
			break;
		case "divide":
			if (operand2 != 0) {
				result = BigDecimal.valueOf(operand1).divide(BigDecimal.valueOf(operand2), 4, BigDecimal.ROUND_HALF_UP);
			} else {
				throw new IllegalArgumentException("Division by zero is not allowed.");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid operation: " + operation);
		}

		return result.doubleValue();
	}

}
