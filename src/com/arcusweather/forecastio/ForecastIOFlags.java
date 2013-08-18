package com.arcusweather.forecastio;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIOFlags {
	private HashMap<String, String> mFlagsDataMap;
	
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
	
	public String getValue(String key) {
		return mFlagsDataMap.get(key);
	}
	
}
