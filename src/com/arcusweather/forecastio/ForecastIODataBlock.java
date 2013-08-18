package com.arcusweather.forecastio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIODataBlock {
	private String summary;
	private String icon;
	private ForecastIODataPoint[] data;

	public ForecastIODataBlock(JSONObject forecastBlockJsonObject) {
		try {
			summary = forecastBlockJsonObject.getString("summary");
		} catch (JSONException e) {
		}
		try {
			icon = forecastBlockJsonObject.getString("icon");
		} catch (JSONException e) {
		}
		try {
			JSONArray blockData = forecastBlockJsonObject.getJSONArray("data");
			int blockDataLength = blockData.length();
			data = new ForecastIODataPoint[blockDataLength];
			for(int i=0; i<blockDataLength; i++) {
				ForecastIODataPoint d = new ForecastIODataPoint(blockData.getJSONObject(i));
				data[i] = d;
			}
		} catch (JSONException e) {
		}
	}
	
	public String getValue(String key) {
		if(key.equals(new String("summary"))) {
			return summary;
		}
		else if(key.equals(new String("icon"))) {
			return icon;
		}
		return null;
	}
	
	public String summary() {
		return summary;
	}
	public String icon() {
		return icon;
	}
	public ForecastIODataPoint[] data() {
		return data;
	}
}
