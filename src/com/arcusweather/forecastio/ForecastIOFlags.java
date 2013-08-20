package com.arcusweather.forecastio;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIOFlags {
	private HashMap<String, String> mFlagsDataMap;
	
	/**
	 * constructor method which sets up and populates the response flag object
	 * @param forecastFlagJsonObject
	 */
	public ForecastIOFlags(JSONObject forecastFlagJsonObject) {
		mFlagsDataMap = new HashMap<String, String>();
		try {
			String darkskyunavailable = forecastFlagJsonObject.getString("darksky-unavailable");
			mFlagsDataMap.put("darkskyunavailable", darkskyunavailable);
		} catch (JSONException e) {
		}

		try {
			String metnolicense = forecastFlagJsonObject.getString("metno-license");
			mFlagsDataMap.put("metnolicense", metnolicense);
		} catch (JSONException e) {
		}
		
		try {
			String units = forecastFlagJsonObject.getString("units");
			mFlagsDataMap.put("units", units);
		} catch (JSONException e) {
		}
	}
	
	/**
	 * method which retrieves a particular value from the data, specified by key
	 * @param key	String parameter which provides the key for what data to provide
	 * @return	returns the String value of the key provided, null if it doesnt exist
	 */
	public String getValue(String key) {
		return mFlagsDataMap.get(key);
	}
	
}
