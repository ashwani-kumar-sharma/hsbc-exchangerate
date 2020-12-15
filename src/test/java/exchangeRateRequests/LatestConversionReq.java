package exchangeRateRequests;

import apiConfigs.APIPath;
import apiConfigs.HeaderConfigs;
import exchangeRatePojo.LatestRate;
import hsbc.api.utilities.BasePojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class LatestConversionReq {

	static BasePojo responsePojo;
	String baseURI, baseURILatest;
	
	public static BasePojo getBaseResponse() {
		RequestSpecification request = RestAssured.given();
		HeaderConfigs hc = new HeaderConfigs();
		request.headers(hc.defaultHeaders());
		Response response = request.get(APIPath.apiExchangePath.getLatest());
		System.out.println("Response:"+response.getBody().asString());
		ResponseBody body = response.getBody();
		responsePojo = body.as(LatestRate.class);
		responsePojo.setStatusCode(response.getStatusCode());
		responsePojo.setResponse(response);
		return responsePojo;
	}
	
}
