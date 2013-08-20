package com.arcusweather.forecastio;

import org.json.JSONObject;

public class ForecastIOCurrently {
	private ForecastIODataPoint mDataPoint;
	
	/**
	 * constructor method which populates the fields related to the current weather conditions
	 * @param forecastCurrentlyJsonObject input json object of the current conditions
	 */
	public ForecastIOCurrently(JSONObject forecastCurrentlyJsonObject) {
		//set data points
		mDataPoint = buildForecastIODataPoint(forecastCurrentlyJsonObject);
	}
	
	/**
	 * method which retrieves a particular value from the data, specified by key
	 * @param key	String parameter which provides the key for what data to provide
	 * @return	returns the String value of the key provided
	 */
	public String getValue(String key) {
		return mDataPoint.getValue(key);
	}
	
	/**
	 * returns a single data point for current weather
	 * @see ForecastIODataPoint
	 */
	public ForecastIODataPoint get() {
		return mDataPoint;
	}
	
	/**
	 * construct the forecastIODataPoint object
	 * @param forecastCurrentlyJsonObject json object of currently weather
	 * @return ForecastIODataPoint which hold all info for that data point.
	 */
	public ForecastIODataPoint buildForecastIODataPoint(JSONObject forecastCurrentlyJsonObject) {
		return new ForecastIODataPoint(forecastCurrentlyJsonObject);
	}

}
