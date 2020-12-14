package stepDefinitions;

import apiVerifications.APIVerification;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import exchangeRateRequests.LatestConversionReq;
import hsbc.api.utilities.BasePojo;

public class LatestConversion {
	
	BasePojo responsePojo;
	
	@Given("^I want to get latest exchange rate$")
	public void latestExchangeRate() {		
		responsePojo = LatestConversionReq.getBaseResponse();
	}

	@Then("^I should get (\\d+) as Success status code$")
	public void verifyResponseCode(int statusCode) throws Throwable {		
		APIVerification.responseCodeValiddation(responsePojo, statusCode);	    
	}
	
	@Then("^I validate the response key \"([^\"]*)\"$")
	public void responseValidation(String key) {
		APIVerification.responseKeyValidationFromJsonObject(responsePojo.getAPIResponse(), key);
	}
}
