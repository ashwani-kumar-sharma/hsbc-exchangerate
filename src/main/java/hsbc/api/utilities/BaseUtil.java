package hsbc.api.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;



public class BaseUtil {

	public static ExtentReports extent = ExtentManager.getExtentManagerInstance();
	public ExtentTest test;
	public int endTestResult = 0;
	public String testName;
	public final char fileSC = FrameWorkConstants.FSC;
	private ArrayList<String> failedScenerios = new ArrayList<String>();
	private File file;
	private static Locale locale = null;
	static public Properties propLocaleData;
	private static ExtentTest methodExtentTest;
	

	public enum DATECOMPARE {
		EQUAL, LESSTHAN, LESSTHANEQUALTO, GREATERTHAN, GREATERTRAHNEQUALTO
	}

	public enum LOCALEFORDATE {
		fr, us, nl, it, ca, gl, de
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param driver1
	 *            WebDriver instance for Front Store (Datatype: WebDriver)
	 * @param driver2
	 *            WebDriver instance for Back Office (Datatype: WebDriver)
	 * @param test1
	 *            instance of ExtentTest (Datatype :ExtentTest)
	 */
	public BaseUtil(ExtentTest test1) {
		test = test1;
		
		
		if (testName == null) {
			testName = test.getModel().getName();
		}
		

	}

	/**
	 * Method to get method ExtentTest
	 * 
	 * @return (datatype:ExtentTest)
	 */
	public static ExtentTest getMethodExtentTest() {
		return methodExtentTest;
	}

	/**
	 * Method to set method ExtentTest
	 * 
	 * @param methodTestTO
	 *            (datatype:ExtentTest)
	 */
	public static void setMethodExtentTest(ExtentTest methodTestTO) {
		methodExtentTest = methodTestTO;
	}

	/**
	 * Method to set wait time in Sec
	 * 
	 * @param timeToWaitInSec
	 *            is the time we need to wait (Datatype: int)
	 */

	public static void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Method to create folder
	 * 
	 * @param folderPath
	 *            path where you want to create folder (Datatype: String)
	 * @param date
	 *            for which folder has to be created (Datatype: date)
	 * @return the folder path (Datatype: String)
	 */
	public static String createFolder(String folderPath, Date date1) {
		String folderName;
		SimpleDateFormat sdtf = new SimpleDateFormat("dd-MMM-YY");
		createFolder(folderPath);
		folderName = folderPath + FrameWorkConstants.FSC + sdtf.format(date1);
		// create a directory in the path
		createFolder(folderName);
		return folderName;
	}

	/**
	 * Method to create folder
	 * 
	 * @param folderPath
	 *            path where you want to create folder (Datatype: String)
	 * 
	 */
	public static void createFolder(String folderPath) {
		try {
			File file = new File(folderPath);
			if (!file.exists())
				file.mkdir();
		} catch (Exception e) {

		}
	}

	/**
	 * Method for execution reporting,report failure and abort the test execution
	 * 
	 * @param failureMessage
	 *            that will be reported at the time of faliure (Datatype: String)
	 * @param driver
	 *            webDriver instance (Datatype: WebDriver)
	 */

	public void reportFailure(String failureMessage) {
		if (methodExtentTest == null)
			test.fail(failureMessage);
		else
			methodExtentTest.fail(failureMessage);
		endTestResult = 0;
		Assert.fail("Got Failure in following Test Object: " + this.getClass().getSimpleName() + "\n" + failureMessage);
	}

	/**
	 * Method to report information about the progress of the test execution
	 * 
	 * @param infoMessage
	 *            information about the test execution progress (Datatype: String)
	 */

	public void reportInfo(String infoMessage) {
		if (methodExtentTest == null)
			test.info(infoMessage);
		else
			methodExtentTest.info(infoMessage);
	}

	
	/**
	 * Method to set final test status
	 * 
	 * @param result
	 *            parameter used for setting test status (Datatype: int)
	 * @param test1
	 *            used to report the final test staus in extent (Datatype:
	 *            ExtentTest)
	 */
	public static void finalTestStatus(int result, ExtentTest test1, int endResultBaseTo) {
		try {
			if (result == 1) {
				test1.pass("Test case is PASSED");
			} else if (result == 0) {
				test1.fail("Test case is FAILED");
			} else if (result == -1 || endResultBaseTo == -1) {
				test1.error("ERROR in Test execution. Please re-run the test.");
			} else if (result == -2) {
				test1.skip("Test case is skipped as expected.");
			}
		} catch (Exception e) {
			test1.error("Got an exception while setting the final test status");
		}

	}

	
	/**
	 * Method to sort list in ascending order
	 * 
	 * @param unsortedProductList
	 *            (Datatype: ArrayList<String>)
	 */
	public void sortingAscendingOrder(List<String> unsortedProductList) {
		Collections.sort(unsortedProductList);
	}

	/**
	 * Method to sort list in descending order
	 * 
	 * @param unsortedProductList
	 *            (Datatype: ArrayList<String>)
	 */
	public void sortingDescendingOrder(List<String> unsortedProductList) {
		Collections.sort(unsortedProductList, Collections.reverseOrder());
	}

	
	/**
	 * Method to give a warning message to Extent Report and take screenshot as well
	 * 
	 * @param warningMessage
	 *            (datatype: String)
	 * @param driver
	 *            (datatype: WebDriver)
	 */
	public void reportWarning(String warningMessage) {
		test.warning(warningMessage);
		
	}

	
	/**
	 * Method will generate Random string by appending Current System date
	 * 
	 * @param textToAppend
	 *            Required text that needs to be appended with date and time
	 * @return String will be concatenated by Time in format 'ddMMyyyyHHmmss' and
	 *         returned (datatype:String)
	 */
	public String randomStringGenerator(String textToAppend) {
		String randString;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String systemDate = dateFormat.format(date).replaceAll(" ", "").replaceAll(":", "").replaceAll("/", "");
		randString = textToAppend.concat(systemDate);
		return randString;
	}

	
	/**
	 * Method to find patterns in a String
	 * 
	 * @param regexExpression
	 *            Regex Expression String by which Match should be compiled
	 *            (datatype:String)
	 * @param toMatch
	 *            String in which Pattern to be find (datatype:String)
	 * @return If pattern found then it will return matched String otherwise null
	 *         (datatype:String)
	 */
	public String getMatchedStrings(String regexExpression, String toMatch) {
		String matchedString = "";
		int startIndex, endIndex;
		Pattern pattern = Pattern.compile(regexExpression);
		Matcher matcher = pattern.matcher(toMatch);
		if (Pattern.matches(regexExpression, toMatch)) {
			return toMatch;
		} else if (pattern.split(toMatch) != null) {
			while (matcher.find()) {
				startIndex = matcher.start();
				endIndex = matcher.end();
				matchedString = matchedString + toMatch.substring(startIndex, endIndex);
			}
		} else {
			return null;
		}
		return matchedString;
	}

	
	/**
	 * Method to Remove unnecessary chars like spaces and "\n" from addresses
	 * 
	 * @param textToRemoveChars
	 *            Text from which chars to be removed (datatype:String)
	 * @return Removed chars string will be returned (datatype:String)
	 */
	public String removeUnnecessaryChars(String textToRemoveChars) {
		String removedChars = textToRemoveChars.trim().replaceAll("\\r|\\n", "").replaceAll("\\s+", "");
		return removedChars;
	}

	
	/**
	 * Method to convert integer value to string
	 * 
	 * @param value
	 *            Integer value to be converted (datatype:int)
	 * 
	 * @return Converted value will be returned (datatype:String)
	 */
	public String convertIntegerToString(int value) {
		return "" + value;
	}

	/**
	 * Method to convert float value to string
	 * 
	 * @param value
	 *            Float value to be converted (datatype:float)
	 * 
	 * @return Converted value will be returned (datatype:String)
	 */
	public String convertIntegerToString(float value) {
		return "" + value;
	}

	
	

	

	/**
	 * Method to collect failures
	 * 
	 * @param failedMessage
	 *            Failed Message (datatype:String)
	 */
	public void collectFailures(String failedMessage) {
		failedScenerios.add(failedMessage);
	}

	/**
	 * Method to get List of Failed Cases
	 * 
	 * @return list of failed cases (datatype:ArrayList<String>)
	 */
	public ArrayList<String> getFailedScenerios() {
		return failedScenerios;
	}

	/**
	 * Removing all failed Scenarios
	 */
	public void flushFailedScenerios() {
		failedScenerios.clear();
	}

	

	/**
	 * Method to get To date ahead of date provided
	 * 
	 * @param strDateFormat
	 *            date format (datatype:String)
	 * @param strDate
	 *            To date (datatype:String)
	 * @param addedDays
	 *            Days required to add (datatype:String)
	 * @return Date will be returned if days added successfully otherwise null
	 */
	public Date getDateAheadOfGivenDate(String strDateFormat, String strDate, int addedDays) {
		Date date = convertStringToDate(strDate, strDateFormat);
		if (date == null)
			return null;
		Calendar calendar = new GregorianCalendar(locale);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addedDays);
		date = calendar.getTime();
		return date;
	}

	/**
	 * Method to generate random number
	 * 
	 * @param noOfDigitsReq
	 *            Number of digits required in a single long number (datatype:int)
	 * @return long number will be returned (datatype:long)
	 */
	public long generateRandomNumber(int noOfDigitsReq) {
		Random rnd = new Random();
		char[] digits = new char[noOfDigitsReq];
		digits[0] = (char) (rnd.nextInt(9) + '1');
		for (int i = 1; i < digits.length; i++) {
			digits[i] = (char) (rnd.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}

	/**
	 * Method to get Ago/Next month from current date
	 * 
	 * @param monthsToAdd
	 *            (datatype:int)
	 * @return month ahead from current month, on failure it will return null
	 */
	public String getMonthFromCurrentMonth(int monthsToAdd) {
		Date date = new Date();
		String newMonth = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, monthsToAdd);
			date = calendar.getTime();
			newMonth = dateFormat.format(date);
		} catch (NumberFormatException e) {
			reportInfo("Got Exception while adding number of months " + monthsToAdd
					+ " to current date. Exception details: " + e.getMessage());
			return newMonth;
		}
		return newMonth;
	}

	/**
	 * Method to get Ago/Next year from current date
	 * 
	 * @param yearsToAdd
	 *            (datatype:int)
	 * @return year ahead from current year, on failure it will return null
	 */
	public String getYearFromCurrentYear(int yearsToAdd) {
		Date date = new Date();
		String newYear = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, yearsToAdd);
			date = calendar.getTime();
			newYear = dateFormat.format(date);
		} catch (NumberFormatException e) {
			reportInfo("Got Exception while adding number of years " + yearsToAdd
					+ " to current date. Exception details: " + e.getMessage());
			return newYear;
		}
		return newYear;
	}

	/**
	 * Method to convert date in String to Date object
	 * 
	 * @param strDate
	 *            (datatype:String)
	 * @param strDateFormat
	 *            (datatype:String)
	 * @return Date object returned after successful conversion otherwise null
	 *         (datatype:Date)
	 */
	public Date convertStringToDate(String strDate, String strDateFormat) {
		Date actualDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat, locale);
			Calendar calendar = new GregorianCalendar(locale);
			actualDate = dateFormat.parse(strDate);
			calendar.setTime(actualDate);
			actualDate = calendar.getTime();
		} catch (ParseException e) {
			reportInfo(e.getMessage() + " Got Exception while converting string date to Date. Actual Date " + strDate
					+ " Date Format " + strDateFormat);
			return actualDate;
		}
		return actualDate;
	}

	/**
	 * Method to convert date in Date to String Date
	 * 
	 * @param date
	 *            (datatype:Date)
	 * @param strDateFormat
	 *            (datatype:String)
	 * @return String date returned after successful conversion otherwise null
	 *         (datatype:Date)
	 */
	public String convertDateToString(Date date, String strDateFormat) {
		String actualDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat, locale);
			actualDate = dateFormat.format(date);

		} catch (Exception e) {
			reportInfo("Got Exception while converting date to String date. Actual Date " + date + " Date Format "
					+ strDateFormat);
			return actualDate;
		}

		return actualDate;
	}

	/**
	 * Method to compare dates
	 * 
	 * @param strActualDate
	 *            (datatype:String)
	 * 
	 * @param strExpectedDate
	 *            (datatype:String)
	 * 
	 * @param strDateFormat
	 *            (datatype:String)
	 * @param dateOperator
	 *            (datatype:DATECOMPARE)
	 * @return Date object returned after successful conversion otherwise null
	 *         (datatype:Date)
	 */
	public boolean compareDates(String strActualDate, String strExpectedDate, String strDateFormat,
			DATECOMPARE dateOperator) {
		Date actualDate = convertStringToDate(strActualDate, strDateFormat);
		Date expectedDate = convertStringToDate(strExpectedDate, strDateFormat);
		boolean isDateExpected = false;
		switch (dateOperator) {
		case EQUAL:
			if (actualDate.compareTo(expectedDate) == 0)
				isDateExpected = true;
			break;
		case LESSTHAN:
			if (actualDate.compareTo(expectedDate) < 0)
				isDateExpected = true;
			break;
		case GREATERTHAN:
			if (actualDate.compareTo(expectedDate) > 0)
				isDateExpected = true;
			break;
		case LESSTHANEQUALTO:
			if (actualDate.compareTo(expectedDate) < 0 || actualDate.compareTo(expectedDate) == 0)
				isDateExpected = true;
			break;
		case GREATERTRAHNEQUALTO:
			if (actualDate.compareTo(expectedDate) > 0 || actualDate.compareTo(expectedDate) == 0)
				isDateExpected = true;
			break;
		default:
			reportFailure("Date Compare operator mismatched");
			break;
		}
		return isDateExpected;
	}

	/**
	 * method to set Download path of Browser
	 * 
	 * @param downloadFilePath
	 *            (datatype: String)
	 * @return chromePrefs Object After setting the preference (datatype:Date)
	 */
	public static Object setDownloadPath(String downloadFilePath) {
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);
		return chromePrefs;
	}

	/**
	 * Method is to test file exist or not
	 * 
	 * @param downloadPath
	 *            (datatype:String)
	 * @param fileName
	 *            (datatype:String)
	 * @return flag (datatype:boolean)
	 */
	public boolean isFileExist(String downloadPath, String fileName) {
		boolean flag = false;
		reportInfo("Checking for required file ");
		wait(5);
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equalsIgnoreCase(fileName)) {
				file = dir_contents[i];
				flag = true;
				reportInfo("Expected " + fileName + " File is exist");
				break;
			}
		}
		if (!flag) {
			reportInfo("Expected " + fileName + " File is not exist");
		}
		return flag;

	}

	/**
	 * Method is to test file is removed or not
	 * 
	 * @param downloadPath
	 *            (datatype:String)
	 * @param fileName
	 *            (datatype:String)
	 * @return flag (datatype:boolean)
	 */
	public boolean FileToBeDeleted(String downloadPath, String fileName) {
		boolean flag = false;
		if (isFileExist(downloadPath, fileName) && file != null) {
			file.delete();
			flag = true;
			reportInfo("Expected " + fileName + " File is deleted successfully");
		}

		if (!flag) {
			flag = false;
			reportInfo("Expected " + fileName + "File is  not exist");
		}
		return flag;

	}

	/**
	 * Method to get current system date
	 * 
	 * @param strDateFormat
	 *            (datatype:String)
	 * @return Current date will be returned (datatype:String)
	 */
	public String getCurrentDate(String strDateFormat) {
		Date date;
		SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		Calendar calendar = new GregorianCalendar();
		date = calendar.getTime();
		return dateFormat.format(date);
	}

	
	

	/**
	 * Method to verify sorting on list
	 * 
	 * @param iterable
	 *            list of items (datatype:ArrayList<T>)
	 * @param ascOrder
	 *            (datatype:boolean)
	 * 
	 * @return if sorted then return true otherwise false (datatype:boolean)
	 */
	public <T extends Comparable<T>> boolean verifySortOnList(ArrayList<T> iterable, boolean ascOrder) {
		Iterator<T> it = iterable.iterator();
		boolean isSorted = true;
		if (it.hasNext()) {
			T prev = it.next();
			while (it.hasNext()) {
				T next = it.next();
				if (ascOrder) {
					if (prev.compareTo(next) > 0) {
						reportInfo("Ascending order sorting not proper for previous item " + prev.toString()
								+ " next item " + next.toString());
						isSorted = false;
						break;
					}
				} else {
					if (prev.compareTo(next) < 0) {
						reportInfo("Descending order sorting not proper for previous item " + prev.toString()
								+ " next item " + next.toString());
						isSorted = false;
						break;
					}
				}
				prev = next;
			}
		}
		return isSorted;
	}
	/**
	 * Method to check failed Scenerios
	 */
	public boolean checkFailedScenerios(String failureMsg) {
		boolean noFailedTest = true;
		if (getFailedScenerios().size() > 0) {
			for (String failure : getFailedScenerios()) {
				reportInfo(failure);
			}
			flushFailedScenerios();
			reportFailure(failureMsg);
		}
		return noFailedTest;
	}
}