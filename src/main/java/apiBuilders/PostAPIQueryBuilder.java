package apiBuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import hsbc.api.utilities.FrameWorkConstants;

public class PostAPIQueryBuilder {
	
	
	public Map<String, String> postRequestBody(String id, String title, String auther){
		Map<String, String> body = new HashMap<String, String>();
		body.put("id", id);
		body.put("title", title);
		body.put("author", auther);		
		return body;		
	}
	
	public JSONObject postRequestBodyJson(String jsonPath) throws IOException {
		File f = new File(FrameWorkConstants.USER_DIR + jsonPath);
		
		InputStream is = new FileInputStream(f);
	    String jsonBody = IOUtils.toString(is, "UTF-8");
	    System.out.println(jsonBody);
	    JSONObject body = new JSONObject(jsonBody);
	    return body;
	}
}
