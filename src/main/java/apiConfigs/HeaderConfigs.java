package apiConfigs;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfigs {
	
	public HeaderConfigs() {
		
	}
	
	public Map<String, String> defaultHeaders(){
	
		Map<String, String> defalutHeaders = new HashMap<String, String>();
		defalutHeaders.put("Content-Type", "application/json");
		return defalutHeaders;
		
	}

}
