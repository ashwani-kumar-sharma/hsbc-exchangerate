package stepDefinitions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import apiConfigs.APIPath;
import apiVerifications.APIVerification;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exchangeRateRequests.LatestConversionReq;
import hsbc.api.utilities.BasePojo;
import hsbc.api.utilities.BaseTest;
import hsbc.api.utilities.FrameWorkConstants;


public class LatestConversion extends BaseTest {
	
	BasePojo responsePojo;
	
	
	@Before
	public void stepsStart(Scenario s){
		scenerio = s;
	}
	
	
	/**
	 * Function Objective:Background check to get latest exchange rate
	 */
	@Given("^I want to get latest exchange rate$")
	public void latestExchangeRate() {		
		responsePojo = LatestConversionReq.getBaseResponse();
	}

	/*
	 * Function Objective:Health check for API by validating response status code
	 */
	@Then("^I should get (\\d+) as Success status code$")
	public void verifyResponseCode(int statusCode) throws Throwable {		
		APIVerification.responseCodeValiddation(responsePojo, statusCode);	    
	}
	
	/*
	 * Function Objective:Validating Keys of Response
	 */
	@Then("^I validate the response key \"([^\"]*)\"$")
	public void responseValidation(String key) {
		APIVerification.responseKeyValidationFromJsonObject(responsePojo.getAPIResponse(), key);
	}
	
	// *************************************************************************************************************************************
	// Function Objective:Hit endpoint as per inputs
	// *************************************************************************************************************************************
	@When("^API is hit with end point as \\?base=\"([^\"]*)\"$")
	public void api_is_hit_with_end_point_as(String baseCurrency) throws Throwable {
		scenerio.write("Hitting endpoint with base = " + baseCurrency);
		hitEndPointURL(FrameWorkConstants.BASEURI, "base", baseCurrency);
		scenerio.write("Response received : " + getResponse().asString());
	}

	// *************************************************************************************************************************************
	// Function Objective:Hit endpoint based on query parameters input
	// *************************************************************************************************************************************
		@When("^API is hit with end point as \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
		public void api_is_hit_with_end_point_as(String endPoint, String queryParam, String baseCurrency) throws Throwable {
			try {
				if (endPoint.contains("latest")) {
					scenerio.write("Hitting endpoint '" + FrameWorkConstants.BASEURI + APIPath.apiExchangePath.getLatest() + " with " + queryParam + " = " + baseCurrency);
					hitEndPointURL(FrameWorkConstants.BASEURI + APIPath.apiExchangePath.getLatest(), queryParam, baseCurrency);
				} else {
					scenerio.write("Hitting endpoint '" + FrameWorkConstants.BASEURI + " with " + queryParam + " = " + baseCurrency);
					hitEndPointURL(FrameWorkConstants.BASEURI + endPoint, queryParam, baseCurrency);
				}
				scenerio.write("Response received : " + getResponse().asString());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception caught : " + e.getMessage());
			}
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate that response contains base currency as expected
		// *************************************************************************************************************************************
		@And("^Response should contain base currency as \"([^\"]*)\"$")
		public void response_should_contain_base_currency_as(String expBaseCurrency) throws Throwable {
			try {
				Assert.assertEquals(200, actStatusCode);
				logger.info("Actual status code : '" + actStatusCode + "' matches with expected status code : '200'");
				scenerio.write("Actual status code : '" + actStatusCode + "' matches with expected status code : '200'");
				String actBaseCurrency = getJsonPath().get("base");
				Assert.assertEquals(expBaseCurrency.trim(), actBaseCurrency.trim());
				logger.info("Actual response base currency : '" + actBaseCurrency
						+ "' matches with expected base currency : '" + expBaseCurrency + "'");
				scenerio.write("Actual response base currency : '" + actBaseCurrency
						+ "' matches with expected base currency : '" + expBaseCurrency + "'");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception caught : " + e.getMessage());
			}
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate that response status code is in line with the
		// expectation
		// *************************************************************************************************************************************
		@Then("^API Should respond with status code as \"([^\"]*)\"$")
		public void api_Should_respond_with_status_code_as(int expStatusCode) throws Throwable {
			Assert.assertEquals(expStatusCode,actStatusCode);
			logger.info("Actual status code : '" + actStatusCode + "' matches with expected status code : '" + expStatusCode
					+ "'");
			scenerio.write("Actual status code : '" + actStatusCode + "' matches with expected status code : '" + expStatusCode
					+ "'");
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate that desired error message was contained in the
		// response message
		// *************************************************************************************************************************************
		@Then("^Error message should be displayed as \"([^\"]*)\"$")
		public void error_message_should_be_displayed_as(String expErrorMessage) throws Throwable {
			// Checking if value retrieved is expected
			Assert.assertEquals(bodyAsString.trim().contains(expErrorMessage.trim()), true);
			logger.info("Expected error message received : '" + bodyAsString + "'");
			scenerio.write("Expected error message received : '" + bodyAsString + "'");
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate individual currency values from the response map
		// *************************************************************************************************************************************
		@And("^Response should contain value \"([^\"]*)\" for \"([^\"]*)\"$")
		public void Response_should_contain_value(String expValue, String field) throws Throwable {
			HashMap<String, String> ratesData = getResponseMap();
			String actValue = null;
			boolean matched = false;

			// iterate through the response map and compare actual and expected values
			try {
				for (Map.Entry m : ratesData.entrySet()) {
					if (m.getKey().equals(field.trim())) {
						actValue = m.getValue().toString();
						if (actValue.equals(expValue)) {
							logger.info("Field : '" + field + "' , Actual value '" + actValue
									+ "' matches with Expected value '" + expValue);
							scenerio.write("Field : '" + field + "' , Actual value '" + actValue 
									+ "' matches with Expected value '" + expValue);
							matched = true;
							break;
						}
					}
				}
				if (!matched) {
					logger.error("Field : '" + field + "' , Actual value '" + actValue
							+ "' does not match with Expected value '" + expValue);
					scenerio.write("Field : '" + field + "' , Actual value '" + actValue
							+ "' does not match with Expected value '" + expValue);				
				}
			} catch (Exception e) {
				logger.error("Exception caught during response map validation for field '" + field + "', exp value '"
						+ expValue + "'");
				scenerio.write("Exception caught during response map validation for field '" + field + "', exp value '"
						+ expValue + "'");			
				logger.error("Exception details : " + e.getMessage());
				throw new RuntimeException("Exception caught during response map validation for field '" + field
						+ "', exp value '" + expValue + "'");
			}
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate that date contained in the response is in line
		// with the expectation
		// *************************************************************************************************************************************
		@And("^Response should contain date as \"([^\"]*)\"$")
		public void Response_should_contain_expected_date(String expDate) throws Throwable {
			String actResponseDate = null;
			try {
				actResponseDate = getJsonPath().get("date");

				// If Today is mentioned in the feature file step, programmatically calculate
				// today's date
				if (expDate.toLowerCase().contains("today")) {
					expDate = getTodaysDate().toString();
				}

				// Do weekend calculation and fetch correct working date
				expDate = getWorkingDate(LocalDate.parse(expDate));

				// Assert that dates match
				Assert.assertEquals(expDate, actResponseDate);
				scenerio.write("Expected Date " + expDate + " matches with actual response date " + actResponseDate);
			} catch (Exception e) {
				logger.error("Exception caught during date validation Exp date '" + expDate + "' , Actual date '"
						+ actResponseDate + "'");
				logger.error("Exception details : " + e.getMessage());
				throw new RuntimeException("Exception caught during date validation Exp date '" + expDate
						+ "' , Actual date '" + actResponseDate + "'");
			}
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate that the response currency values are not empty
		// *************************************************************************************************************************************
		@And("^Response should contain not null values for \"([^\"]*)\"$")
		public void response_should_contain_not_null_values_for_Currencies(String checkCurrencies) throws Throwable {
			try {
				// Split to fetch multiple currencies input
				String[] currencies = checkCurrencies.split(",");
				String actValue = null;

				// Fetch response hashmap
				HashMap<String, String> currencyData = getResponseMap();

				// Iterate for each currency, get corresponding response map value and validate
				for (String currency : currencies) {
					for (Map.Entry m : currencyData.entrySet()) {
						if (m.getKey().equals(currency.trim())) {
							actValue = m.getValue().toString();
							if (actValue.isEmpty()) {
								logger.error("Response currency : '" + currency + "' , Actual value '" + actValue
										+ "' is black or empty");
								scenerio.write("Response currency : '" + currency + "' , Actual value '" + actValue
										+ "' is black or empty");							
								break;
							} else {
								logger.info("Response currency : '" + currency + "' , returned a valid actual value '"
										+ actValue + "'");
								scenerio.write("Response currency : '" + currency + "' , returned a valid actual value '"
										+ actValue + "'");							
								break;
							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception caught : " + e.getMessage());
				scenerio.write("Exception caught : " + e.getMessage());
			}
		}

		// *************************************************************************************************************************************
		// Function Objective:Validate that response received when invoked with a future
		// date is same as that for current date
		// *************************************************************************************************************************************
		@And("^Response for future date should match with response for today$")
		public void response_for_future_date_should_match_with_response_for_today() throws Throwable {
			// Capture the already received response for future date
			String respFutureDate = bodyAsString;
			
			scenerio.write("Future date response : " + respFutureDate);

			// Hit endpoint again with today's date
			hitEndpointURL(FrameWorkConstants.BASEURI + getTodaysDate());

			// Get response and assert for correctness
			String respToday = bodyAsString;
			Assert.assertEquals(respFutureDate, respToday);
			
			scenerio.write("Current date response : " + respToday + ", matches with future date response");
		}
}
