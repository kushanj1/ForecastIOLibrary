package com.arcusweather.forecastio;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIODataPoint {

	private HashMap<String, String> mDataPointMap;
	
	/**
	 * constructor method which initializes data points provided by the api data
	 * @param forecastJsonObject the json object that will be used for instantiating this project.
	 */
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
	
	/**
	 * Method to retrieve a value for use
	 * @param key	string value of the parameter
	 * @return field value as string
	 */
	public String getValue(String key) {
		return mDataPointMap.get(key);
	}
	
	/**
	 * get Value as an integer
	 * @param key	string value of the parameter
	 * @return	field value as integer;
	 */
	public int getValueAsInt(String key) {
		String val = "";
		try{
			val = getValue(key);
		}
		catch(NullPointerException e) {
			return 0;
		}
		
		int ret = 0;
		try {
			ret = Integer.parseInt(val);;
		}
		catch(NumberFormatException n) {
			return 0;
		}
		return ret;
	}
	
	/**
	 * get Value as an double
	 * @param key	value of the amount 
	 * @return	field value as double;
	 */
	public Double getValueAsDouble(String key) {
		String val = "";
		try{
			val = getValue(key);
		}
		catch(NullPointerException e) {
			return null;
		}
		
		double ret = 0;
		try {
			ret = Double.parseDouble(val);
		}
		catch(NumberFormatException n) {
			return null;
		}
		return ret;
	}
	
	/**
	 * get Value as an long
	 * @param key	value of the amount 
	 * @return	field value as long;
	 */
	public Long getValueAsLong(String key) {
		String val = "";
		try{
			val = getValue(key);
		}
		catch(NullPointerException e) {
			return null;
		}
		
		long ret = 0;
		try {
			ret = Long.parseLong(val);
		}
		catch(NumberFormatException n) {
			return null;
		}
		return ret;
	}
}
