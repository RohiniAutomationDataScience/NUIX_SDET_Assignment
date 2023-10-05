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


public class WebCalcLoad extends BaseTestClass{


	public static WebDriver driver;

	/*
	 * public WebCalcLoad() { synchronized (this) { // This initElements method will
	 * create all WebElements PageFactory.initElements(DriverManager.getDriver(),
	 * this); //this.driver = DriverManager.getDriver();
	 * 
	 * } }
	 */

	//Initializing the Page Objects:
	public WebCalcLoad(){
		PageFactory.initElements(driver, this);
	}


	/**
	 * Instantiates a new google page.
	 *
	 * @param driver the driver
	 */
	public WebCalcLoad(WebDriver driver) {
		this.driver = DriverManager.getDriver();
	}

	static Double Finalnumber1;
	static Double Finalnumber2;

	static String txtDisplayResult = "//div[@id='display']";
	public static String btnAddition = "//input[@id='add']";
	static String btnSubstraction = "//input[@id='subtract']";
	static String btnDevidation = "//input[@id='divide']";
	static String btnMultiplication = "//input[@id='multiply']";
	static String btnClear = "//input[@value='C']";
	static String btnDot = "//input[@value='.']";
	static String btnEqualToOperator = "//input[@value='=']";
	String btnNumbers = "//input[@value='%s']";




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
		WebElement btnadd = DriverManager.getDriver().findElement(By.xpath(btnAddition)); 
		WebElement btnsubstract = DriverManager.getDriver().findElement( By.xpath(btnSubstraction));
		WebElement btndevide = DriverManager.getDriver().findElement( By.xpath(btnDevidation));
		WebElement btnmultiply = DriverManager.getDriver().findElement(By.xpath(btnMultiplication));
		WebElement btnclear = DriverManager.getDriver().findElement(By.xpath(btnClear));
		WebElement btndot = DriverManager.getDriver().findElement(By.xpath(btnDot));
		WebElement btnequalTo = DriverManager.getDriver().findElement( By.xpath(btnEqualToOperator));

		WebElement firstNum = DriverManager.getDriver()
				.findElement(By.xpath("//input[@value='" + number1.toString().replace(".0", "") + "']"));

		WebElement secondNum = DriverManager.getDriver()
				.findElement(By.xpath("//input[@value='" + number2.toString().replace(".0", "")  + "']"));

		WebElement btndisplayResult = DriverManager.getDriver().findElement(By.xpath(txtDisplayResult));




		try {
			switch (Operation) {
			case "+":
				Reporter.logIntoReport(LogStatus.INFO, "First number is : " + firstNum.getAttribute("value")+ " Second Number is : " + secondNum.getAttribute("value"));

				firstNum.click();
				Finalnumber1 = Double.parseDouble(btndisplayResult.getText());
				btnadd.click();
				secondNum.click();
				Finalnumber2 = Double.parseDouble(btndisplayResult.getText());
				btnequalTo.click();
				result = Finalnumber1 + Finalnumber2;
				Reporter.logIntoReport(true, true, " Addition is : " +result, " Addition is : " +result);
				break;

			case "-": 
				Reporter.logIntoReport(LogStatus.INFO, "First number is : " + firstNum.getAttribute("value")+ " Second Number is : " + secondNum.getAttribute("value"));

				firstNum.click();
				Finalnumber1 = Double.parseDouble(btndisplayResult.getText());
				btnsubstract.click();
				secondNum.click();
				Finalnumber2 = Double.parseDouble(btndisplayResult.getText());
				btnequalTo.click();
				result = Finalnumber1 - Finalnumber2;
				Reporter.logIntoReport(true, true, " Substarction is : " +result, " Substarction is : " +result);


				break; 

			case "*": 
				Reporter.logIntoReport(LogStatus.INFO, "First number is : " + firstNum.getAttribute("value")+ " Second Number is : " + secondNum.getAttribute("value"));

				firstNum.click();
				Finalnumber1 = Double.parseDouble(btndisplayResult.getText());
				btnmultiply.click();
				secondNum.click();
				Finalnumber2 = Double.parseDouble(btndisplayResult.getText());
				btnequalTo.click();
				result = Finalnumber1 * Finalnumber2;
				Reporter.logIntoReport(true, true, " Multiplication is : " +result, " Multiplication is : " +result);


				break; 

			case "/":
				Reporter.logIntoReport(LogStatus.INFO, "First number is : " + firstNum.getAttribute("value")+ " Second Number is : " + secondNum.getAttribute("value"));

				if (Finalnumber2 != 0) {

					firstNum.click();
					Finalnumber1 = Double.parseDouble(btndisplayResult.getText());
					btndevide.click();
					secondNum.click();
					Finalnumber2 = Double.parseDouble(btndisplayResult.getText());
					btnequalTo.click();
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
			Reporter.logIntoReport(flag, true, " Expected value is : " +obj1 + " Actual value is : " +obj2, " Actual value is : " +obj1 + " Expected value is : " +obj2);
		}

		else {
			flag = false;
			Reporter.logIntoReport(flag, true, " Actual value is : " +obj1 + " Expected value is : " +obj2, " Expected value is : " +obj1 + " Actual value is : " +obj2);
		}
	}


}
