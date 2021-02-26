package apiConfigs;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfigs {
	
	public Map<String, String> headerWithToken(){
		Map<String, String> map = new HashMap<>();
		map.put("X-CMC_PRO_API_KEY", "b49f54df-8826-46b7-820e-b8099e6175fb");
		map.put("Accept", "application/json");
		return map;
	}

}


