package hsbc.api.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.SkipException;

public class FrameWorkConstants {
	
	static String test_ENV = null;
	static String api_URL = null;
	static String test_SUITE = null;
	static Properties propEnvData;

	public static enum API_WAIT {
		NORMAL_WAIT(20), SHORT_WAIT(5), LONG_WAIT(50);

		private long waitVal;

		API_WAIT(long wait) {
			waitVal = wait;
		}

		public long getWaitVal() {
			return waitVal;
		}
	}

	public static enum SUPPORTED_ENV {
		QA("QA"), PP("PP");
		
		private final String enviornment;
		SUPPORTED_ENV(String env){
			enviornment = env;
		}
		
		public String getSupportedEnv() {
			return enviornment;
		}
	}
	
	public static final char FSC = File.separatorChar;
	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String PROPERTIES_FILE_PATH = USER_DIR + FSC + "src" + FSC + "test" + FSC + "resources" + FSC;
	public static final String DEFAULT_TEST_SUITE = "AllTest";
	public static final String DEFAULT_TEST_ENV = SUPPORTED_ENV.QA.getSupportedEnv(); // QA, Stage
	public static final String TEST_ENV = getTestEnv();
	public static final String TEST_SUITE = getTestSuite();
	public static final String REPORTS_PATH = USER_DIR + FSC + "ExtentReports";
	public static final String EXTENT_REPORTS_CONFIG = USER_DIR + FSC + "config" + FSC + "extent-config.xml";
	//public static final String APPDATASHEET = USER_DIR + getTestDataFilePath();
	
	public static final String BASEURI = getBaseURL();
	//public static final String TESTDATA_SHEET = "HybrisData_" + COUNTRY_CODE.toUpperCase();
	//public static final String TESTCASES_SHEET = "TestCases";
	
	private static void readPropFile(SUPPORTED_ENV env) {
		FileReader reader = null;
		propEnvData = new Properties();
		try {
			System.out.println(PROPERTIES_FILE_PATH);
			reader = new FileReader(FrameWorkConstants.PROPERTIES_FILE_PATH + env.getSupportedEnv() + "_env.properties");
			
		} catch (FileNotFoundException e) {
			throw new Error("Unbale to find the properties files" + e.getMessage());
		}
		try {
			propEnvData.load(reader);
		} catch (IOException e) {
			throw new Error("Unbale to read properties files" + e.getMessage());
		}
		System.out.println("Property file read");
	}
	
	private static String getBaseURL() {
		if (api_URL == null) {
			try {
				api_URL = propEnvData.getProperty("baseURL");
			} catch(Exception io) {
				throw new Error("Unbale to read the property file" + io.getMessage());
			}
		}
		System.out.println(api_URL);
		return api_URL;
	}

	private static String getTestEnv() {
		if (test_ENV == null) {
			try {
				test_ENV = System.getProperty("env"); // QA, Stage, PProd
				test_ENV = test_ENV.toUpperCase();
				if(SUPPORTED_ENV.QA.getSupportedEnv().equalsIgnoreCase(test_ENV)) {
					test_ENV = SUPPORTED_ENV.QA.getSupportedEnv();
					readPropFile(SUPPORTED_ENV.QA);
				}else if(SUPPORTED_ENV.PP.getSupportedEnv().equalsIgnoreCase(test_ENV)) {
					test_ENV = SUPPORTED_ENV.PP.getSupportedEnv();
					readPropFile(SUPPORTED_ENV.PP);
				}else
					throw new Error("INVALID Test env is provided. Expected is QA/STAGE/PP");
			} catch (Exception e) {
				test_ENV = FrameWorkConstants.DEFAULT_TEST_ENV;
				readPropFile(SUPPORTED_ENV.QA);
				System.out.println("Default ENV SET");
			}
		}
		return test_ENV;
	}

	
	/**
	 * This method fetches the Test suite to be executed
	 * 
	 * @return Test suite (SmokeTest/E2E/Regression). In case of exception, it
	 *         returns the default Test suite (Datatype: String)
	 */
	private static String getTestSuite() {
		if (test_SUITE == null) {
			try {
				test_SUITE = System.getProperty("suite");
				test_SUITE.toLowerCase();
				switch (test_SUITE) {
				case "smoke":
				case "regression":
				case "filter": // Additional suites are to be added here later
					break;
				default:
					throw new Error("INVALID Test suite is provided. Expected is Smoke/Filter/Regression");
				}
			} catch (Exception e) {
				test_SUITE = FrameWorkConstants.DEFAULT_TEST_SUITE;
			}
		}
		return test_SUITE;
	}
}
