package com.arcusweather.forecastio;

import org.json.JSONObject;

public class ForecastIODaily {
	private ForecastIODataBlock mDataBlock;
	
	public ForecastIODaily(JSONObject forecastDailyJsonObject) {
		//set data points
		mDataBlock = buildForecastIODataBlock(forecastDailyJsonObject);
	}
	
	public ForecastIODataPoint[] getData() {
		return mDataBlock.data();
	}
	
	public String getValue(String key) {
		return mDataBlock.getValue(key);
	}
	
	public ForecastIODataBlock get() {
		return mDataBlock;
	}
	
	public ForecastIODataBlock buildForecastIODataBlock(JSONObject forecastDailyJsonObject) {
		return new ForecastIODataBlock(forecastDailyJsonObject);
	}
}
