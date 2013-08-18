package com.arcusweather.forecastio;

import org.json.JSONObject;

public class ForecastIOCurrently {
	private ForecastIODataPoint mDataPoint;
	
	public ForecastIOCurrently(JSONObject forecastCurrentlyJsonObject) {
		//set data points
		mDataPoint = buildForecastIODataPoint(forecastCurrentlyJsonObject);
	}
	
	public String getValue(String key) {
		return mDataPoint.getValue(key);
	}
	
	public ForecastIODataPoint get() {
		return mDataPoint;
	}
	
	public ForecastIODataPoint buildForecastIODataPoint(JSONObject forecastCurrentlyJsonObject) {
		return new ForecastIODataPoint(forecastCurrentlyJsonObject);
	}

}
