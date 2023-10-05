package calc.utility.base.driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;


/**
 * This Class is to handle the driver instances and to use them accordingy
 * 
 * @author sharngau
 * @since Nov 2018
 * @version 0.1
 *
 */
public class DriverManager {

	
	private static ConcurrentHashMap<Long, WebDriver> webDriverThread = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<Long, String> tcInstanceThread = new ConcurrentHashMap<>();
	
	private DriverManager(){
		
	}

	public static synchronized WebDriver getDriver() {
		Long threadID = Thread.currentThread().getId();
		return webDriverThread.getOrDefault(threadID,null);
	}

	public static synchronized String getUniqueID() {
		
		return tcInstanceThread.get(Thread.currentThread().getId());
	}
	
	public static synchronized void setWebDriver(WebDriver driver) {
		if (driver != null) {
			Long threadID = Thread.currentThread().getId();
			webDriverThread.put(threadID, driver);
		}
		
	}
	
	/*
	 * public static synchronized void setUniqueID() { Long threadID =
	 * Thread.currentThread().getId(); String uuid = new
	 * SimpleDateFormat("yyMMddHHmmss").format(new Date())+Utils.getUniqueNumber(5);
	 * tcInstanceThread.put(threadID, uuid); }
	 */

	public static synchronized void removeUniqueID() {
		Long threadID = Thread.currentThread().getId();
		if(tcInstanceThread.containsKey(threadID))
		{
			tcInstanceThread.remove(threadID);
		}
	}
	
	public static synchronized void removeDriverFromThreadMap() {
		Long threadID = Thread.currentThread().getId();
		if(webDriverThread.containsKey(threadID))
		{
			webDriverThread.remove(threadID);
		}
	}
}
