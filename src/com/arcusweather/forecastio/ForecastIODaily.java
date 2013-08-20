package com.arcusweather.forecastio;

import org.json.JSONObject;

public class ForecastIODaily {
	private ForecastIODataBlock mDataBlock;
	
	/**
	 * constructor method which populates the fields related to the daily weather conditions
	 * @param forecastCurrentlyJsonObject input json object of the daily conditions
	 */
	public ForecastIODaily(JSONObject forecastDailyJsonObject) {
		//set data points
		mDataBlock = buildForecastIODataBlock(forecastDailyJsonObject);
	}
	
	/**
	 * method which retrieves a list of ForecastIODataPoints 
	 * @return	a list of ForecastIODataPoints 	
	 */
	public ForecastIODataPoint[] getData() {
		return mDataBlock.data();
	}
	
	/**
	 * method which retrieves a particular value from the data, specified by key
	 * @param key	String parameter which provides the key for what data to provide
	 * @return	returns the String value of the key provided, null if it doesnt exist
	 */
	public String getValue(String key) {
		return mDataBlock.getValue(key);
	}
	
	/**
	 * returns a single data block for daily weather
	 * @see ForecastIODataBlock
	 */
	public ForecastIODataBlock get() {
		return mDataBlock;
	}
	
	
	/**
	 * construct the forecastIODataBlock object
	 * @param forecastDailyJsonObject json object of daily weather
	 * @return ForecastIODataBlock which hold all info for that data block
	 * @see ForecastIODataBlock
	 */
	public ForecastIODataBlock buildForecastIODataBlock(JSONObject forecastDailyJsonObject) {
		return new ForecastIODataBlock(forecastDailyJsonObject);
	}
}
