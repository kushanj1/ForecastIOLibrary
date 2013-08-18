package com.arcusweather.forecastio;

import org.json.JSONObject;

public class ForecastIOMinutely {
	private ForecastIODataBlock mDataBlock;
	
	public ForecastIOMinutely(JSONObject forecastMinutelyJsonObject) {
		//set data points
		mDataBlock = buildForecastIODataBlock(forecastMinutelyJsonObject);
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
	
	public ForecastIODataBlock buildForecastIODataBlock(JSONObject forecastMinutelyJsonObject) {
		return new ForecastIODataBlock(forecastMinutelyJsonObject);
	}

}
