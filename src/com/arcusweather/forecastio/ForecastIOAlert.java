package com.arcusweather.forecastio;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class ForecastIOAlert {

	private HashMap<String, String> mAlertDataMap;

	public ForecastIOAlert(JSONObject forecastAlertJsonObject) {
		mAlertDataMap = new HashMap<String, String>();
		JSONArray dataPointNames = forecastAlertJsonObject.names();
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
				dataPointValue = forecastAlertJsonObject.getString(dataPointName);
			} catch (JSONException e) {
				//go to next
				continue;
			}
			mAlertDataMap.put(dataPointName, dataPointValue);
		}
	}
	
	public String getValue(String key) {
		return mAlertDataMap.get(key);
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
