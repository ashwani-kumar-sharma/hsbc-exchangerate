package hsbc.api.utilities;



import org.junit.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.restassured.RestAssured;


public class BaseTest {
	
	public BaseUtil baseUtil;
	
	@BeforeClass
	public static void initializeTest() {
		
		RestAssured.urlEncodingEnabled = false;
		RestAssured.baseURI = FrameWorkConstants.BASEURI;
		System.out.println("Base Test Called");
	}
}
