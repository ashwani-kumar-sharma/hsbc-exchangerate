package hsbc.api.utilities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;

import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;



public class BaseTest extends BaseUtil{
	
	public BaseUtil baseUtil;
	public static Logger logger;
	
	protected RequestSpecification httpRequest;
	protected String bodyAsString;
	protected ResponseBody<?> body;
	protected Response response;
	protected int actStatusCode;
	protected Scenario scenerio=null;
	
	/*
	 * Function Objective:constructor
	 */
	public BaseTest() {
		super();
		logger = Logger.getLogger(Logger.class);
	}
	
	@BeforeClass
	public static void initializeTest() {
		
		RestAssured.urlEncodingEnabled = false;
		RestAssured.baseURI = FrameWorkConstants.BASEURI;
		System.out.println("Base Test Called");
	}
	
	/**
	 * Get Response
	 */
	public Response getResponse() {
		return response;
	}
	
	
	/**
	 * Get Json Path
	 * @return
	 */
	public JsonPath getJsonPath() {
		return response.jsonPath();
	}
	
	// Fetch rates HashMap from the response
	public HashMap<String, String> getResponseMap() {
		Map<String,Object> map = getJsonPath().getMap("rates");
		HashMap<String,String> ratesMap =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		       if(entry.getValue() instanceof String){
		    	   ratesMap.put(entry.getKey(), (String) entry.getValue());
		          }
		 }
		return ratesMap;
	}
	
	/**
	 * Method to hit endpoint
	 * 
	 * @param endPoint
	 *            : This parameter gives the end point to hit (datatype: String)
	 * @param queryParamField
	 *            : This parameter gives the query parameter (datatype: String)
	 * @return test : void
	 */
		public void hitEndPointURL(String endPoint, String queryParamField, String queryParamValue) {
			logger.info(
					"Hitting endpoint '" + endPoint + "', queryParams '" + queryParamField + "," + queryParamValue + "'");
			try {
				// Setup Requestspecification object
				RestAssured.baseURI = endPoint;
				httpRequest = RestAssured.given();

				// Setup query parameters
				response = httpRequest.queryParam(queryParamField, queryParamValue).get();

				// Get response and status code
				body = response.getBody();
				bodyAsString = body.asString();
				actStatusCode = response.getStatusCode();

				// log response and status code
				logger.info("Response received : " + bodyAsString);
				logger.info("Response status code : '" + actStatusCode + "'");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception caught : " + e.getMessage());
				throw new RuntimeException("TestBase::hitEndpoint() -> Exception caught : " + e.getMessage());
			}
		}
		
		// *************************************************************************************************************************************
		// Function:hitEndpointURL()
		// Objective:Hit plain endpoint without query parameters and capture response
		// Usage:hitEndpoint(baseURI+"2020-06-02"
		// *************************************************************************************************************************************
		public void hitEndpointURL(String endPoint) {
			logger.info("Hitting endpoint '" + endPoint + "'");
			try {
				// Setup Requestspecification object
				RestAssured.baseURI = endPoint;
				httpRequest = RestAssured.given();

				// Get response
				response = httpRequest.get();
				body = response.getBody();
				actStatusCode = response.getStatusCode();

				// log response
				bodyAsString = body.asString();
				logger.info("Response received : " + bodyAsString);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception caught : " + e.getMessage());
				throw new RuntimeException("TestBase::hitEndpoint() -> Exception caught : " + e.getMessage());
			}
		}
}
