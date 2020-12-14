package hsbc.api.utilities;

import io.restassured.response.Response;

public class BasePojo {
	
	private int statusCode;
	private Response response;
	
	public BasePojo() {
		
	}
	
	public BasePojo(Response response) {
		this.response = response;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int responseStatusCode) {
		statusCode = responseStatusCode;
	}
	
	public Response getAPIResponse() {
		return response;
	}
	
	public void setResponse(Response response) {
		this.response = response;
	}
}
