package apiVerifications;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import hsbc.api.utilities.BasePojo;
import io.restassured.response.Response;

public class APIVerification {

	public static void responseCodeValiddation(BasePojo response, int statusCode) {
		assertEquals(statusCode, response.getStatusCode());
	}

	public static void responseKeyValidationfromArray(Response response, String keyArray, String keyToFind) {
		JSONObject json = new JSONObject(response.getBody().asString());
		JSONArray array = new JSONArray(json.get(keyArray).toString());
		for(int i=0; i<array.length();i++) {
			JSONObject obj = array.getJSONObject(i);
			assertTrue(obj.has(keyToFind));
			assertNotNull(obj.get(keyToFind));
		}
		
	}
	
	public static void responseKeyValidationFromJsonObject(Response response, String key) {
		JSONObject json = new JSONObject(response.getBody().asString());
		assertTrue(json.has(key));
		assertNotNull(json.get(key));
	}
}
