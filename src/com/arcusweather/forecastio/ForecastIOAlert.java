package com.arcusweather.forecastio;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class ForecastIOAlert {

	private HashMap<String, String> mAlertDataMap;

	/**
	 * Constructor which instantiates all data properties for an alert object.
	 * @param forecastAlertJsonObject the input JSON Object
	 */
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
	
	/**
	 * get the value of any data point
	 * @param key String value of the data key 
	 * @return
	 */
	public String getValue(String key) {
		return mAlertDataMap.get(key);
	}
	
	/**
	 * get the value of any data point as an integer
	 * @param key the name of the field you were going to rob.
	 * @return	integer value of data point
	 */
	public int getValueAsInt(String key) {
		return Integer.parseInt(getValue(key));
	}
	
	/**
	 * get the value of any data point as an double
	 * @param key the name of the field you were going to rob.
	 * @return	double value of data point
	 */
	public Double getValueAsDouble(String key) {
		return Double.parseDouble(getValue(key));
	}
	
	/**
	 * get the value of any data point as an long
	 * @param key the name of the field you were going to rob.
	 * @return	long value of data point
		 */
	public Long getValueAsLong(String key) {
		return Long.parseLong(getValue(key));
	}
}
