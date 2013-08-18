package com.arcusweather.forecastio;

import org.json.JSONObject;

public class ForecastIOHourly {
	private ForecastIODataBlock mDataBlock;
	
	public ForecastIOHourly(JSONObject forecastHourlyJsonObject) {
		//set data points
		mDataBlock = buildForecastIODataBlock(forecastHourlyJsonObject);
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
	
	public ForecastIODataBlock buildForecastIODataBlock(JSONObject forecastHourlyJsonObject) {
		return new ForecastIODataBlock(forecastHourlyJsonObject);
	}
}
