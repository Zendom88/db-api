package api.util.formatter;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListMapToJson {
		
	public static JSONArray convert(List<Map<String, Object>> mapList) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj;
		if (mapList==null || mapList.isEmpty()) return null;
		for (Map<String, Object> map : mapList) {
			jsonObj=new JSONObject(map);
			jsonArray.put(jsonObj);
		}
		return jsonArray;
	}

}
