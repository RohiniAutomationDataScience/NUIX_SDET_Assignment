package calc.utility.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class GlobalConstants {

	public static final String PROPERTIES_FILE = System.getProperty("user.dir")
			+ File.separator + "resources" + File.separator
			+ "configuration.properties";

	private static Properties properties = null;

	public static GlobalConstants getInstance() {
		if (instance == null) {
			instance = new GlobalConstants();
		}
		return instance;
	}

	public static Properties getProperties() {
		return properties;
	}



	public static void initializeProperties(String automatedPath)
			throws IOException {
		properties = new Properties();
		String path = !automatedPath.equals("") ? automatedPath
				: PROPERTIES_FILE;
		properties.load(new FileInputStream(path));
	}


	private static GlobalConstants instance = null;
	private final String prod_application_URL;
	private final String stag_application_URL;

	private final String environmentType;


	private GlobalConstants() {

		try {
			initializeProperties("");
		} catch (IOException e) {

		}

		prod_application_URL = getProperties().getProperty("PROD_APPLICATION_URL");
		stag_application_URL = getProperties().getProperty("STAG_APPLICATION_URL");
		environmentType = getProperties().getProperty("EnvironmentType");


	}		
	public static String getProdAPPLICATION_URL() {
		return getInstance().prod_application_URL;
	}
	
	public static String getStagAPPLICATION_URL() {
		return getInstance().stag_application_URL;
	}
	
	public static String getenvironmentType() {
		return getInstance().environmentType;
	}



}