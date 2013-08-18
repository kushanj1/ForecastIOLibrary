package com.arcusweather.forecastio;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIODataPoint {

	private HashMap<String, String> mDataPointMap;
	
	public ForecastIODataPoint(JSONObject forecastJsonObject) {
		mDataPointMap = new HashMap<String, String>();
		JSONArray dataPointNames = forecastJsonObject.names();
		for(int i=0;i<dataPointNames.length();i++) {
			String dataPointName = "";
			String dataPointValue = "";
			try {
				dataPointName = dataPointNames.getString(i);
			} catch (JSONException e) {
				//go to next
				continue;
			}
			
			try {
				dataPointValue = forecastJsonObject.getString(dataPointName);
			} catch (JSONException e) {
				//go to next
				continue;
			}
			mDataPointMap.put(dataPointName, dataPointValue);
		}

	}
	
	public String getValue(String key) {
		return mDataPointMap.get(key);
	}
	
	public int getValueAsInt(String key) {
		return Integer.parseInt(getValue(key));
	}
	
	public Double getValueAsDouble(String key) {
		return Double.parseDouble(getValue(key));
	}
	
	public Long getValueAsLong(String key) {
		return Long.parseLong(getValue(key));
	}
}
