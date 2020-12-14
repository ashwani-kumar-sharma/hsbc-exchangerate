package hsbc.api.utilities;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class ExtentManager {
	private static ExtentReports extent;

	private ExtentManager() {

	}

	/**
	 * Method to initialize ExtentReports(3.0) object
	 * 
	 * @return returns an object of ExtentReports API
	 */
	synchronized public static ExtentReports getExtentManagerInstance() {
		if (extent == null) {
			Date date1 = new Date();
			String todaysFolder = BaseUtil.createFolder(FrameWorkConstants.REPORTS_PATH, date1);
			String fileName = date1.toString().replace(":", "_").replace(" ", "_") + ".html";
			String enviornment = FrameWorkConstants.TEST_ENV.toUpperCase() + "_";
			String suite = FrameWorkConstants.TEST_SUITE.toUpperCase() + "_";
			BaseUtil.createFolder(todaysFolder);
			String reportPath = todaysFolder + FrameWorkConstants.FSC + enviornment + suite + fileName;
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
			extent = new ExtentReports();
			htmlReporter.config().setDocumentTitle(enviornment + suite + fileName);
			htmlReporter.setAppendExisting(true);
			htmlReporter.loadXMLConfig(FrameWorkConstants.EXTENT_REPORTS_CONFIG);
			extent.setSystemInfo("Test Env", FrameWorkConstants.TEST_ENV.toUpperCase());
			extent.setSystemInfo("Test Suite", FrameWorkConstants.TEST_SUITE);
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}
}
