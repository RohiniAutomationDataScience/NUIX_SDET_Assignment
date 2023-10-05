package calc.utility.base.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;

import calc.utility.reports.Reporter;
import calc.utility.utilities.GlobalConstants;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * This class is to initiate the WebDriver
 * 
 * @author Rohini
 * @since Oct 2023
 * @version 0.1
 *
 */
public class Driver {

	public static final String PATH = System.getProperty("user.dir");
	private static final String GRID_ERRORMESAGGE = "Unable to start Selenium HUB for Parallel execution. Error Details - ";
	private static final String HUB_ERRORMESSAGE = "Check Selenium HUB for Parallel is running or not. Error Details - ";
	private static final String HTTP = "http://";
	private static final String PORT = ":4444";
	private static final String HUB = "/wd/hub";
	private static final String downloadFilepath = PATH + File.separator
			+ "downloadFiles";

	public Driver() {

	}

	/**
	 * This method is to initiate the Browser, and to add ngWebDriver object to
	 * map.
	 * 
	 * @param browserName
	 * @return
	 */
	public static WebDriver initiateBrowser(String browserName) {
		WebDriver webDriver = null ;

		String browser = browserName;
		/*
		 * if (browser == null) { browser = GlobalConstants.getBrowserName(); }
		 */
		try {
			if (("Chrome").equalsIgnoreCase(browser)) {
				webDriver = invokeChrome();

			} else {
				System.exit(1);
			}

			webDriver.manage().window().maximize();
			return webDriver;
		} catch (Exception e) {
			return webDriver;
		}

	}

	private static WebDriver invokeChrome() {
		WebDriver webDriver = null;
		try {
			HashMap<String, Object> chromePrefs = new HashMap<>();
			createDirectoryIfNotPresent();
			chromePrefs.put("safebrowsing.enabled", "true");
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			/*if (!GlobalConstants.gethubIP().equals("")) {
				if (GlobalConstants.gethubIP() != null) {
					chromePrefs.put("download.default_directory",
							GlobalConstants.getExecDownloadPath());
				}
			} */ {
				chromePrefs.put("download.default_directory", downloadFilepath);
			}
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			String driverPath = getDriverFolderPath()
					+ "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			options.setExperimentalOption("prefs", chromePrefs);

		
	 {
				webDriver = new ChromeDriver(options);
			}
			DriverManager.setWebDriver(webDriver);
			return webDriver;
		} catch (Exception e) {
			
		}
		return webDriver;

	}


	public static String getDriverFolderPath() {
		String driverFolderPath = "";
		try {
			driverFolderPath = PATH + File.separator + "drivers"
					+ File.separator;
		} catch (Exception e) {
		}
		return driverFolderPath;
	}

	public static synchronized void quitBrowser() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
	
	}

	private static void createDirectoryIfNotPresent() {
		try {
			File directory = new File(downloadFilepath);
			if (!directory.exists()) {
			//	Reporter.logIntoReport(true, true, "Directory Created ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.logIntoReport(false, true,
					"Directory Not Created " + e.getMessage());
		}
	}
}
