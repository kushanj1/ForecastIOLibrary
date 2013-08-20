package com.arcusweather.forecastio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIODataBlock {
	private String summary;
	private String icon;
	private ForecastIODataPoint[] data;
	
	/**
	 * constructor method which initializes data points provided by the api data
	 * @param forecast the json object that will be used for instantiating this project.
	 */
	public ForecastIODataBlock(JSONObject forecastBlockJsonObject) {
		try {
			summary = forecastBlockJsonObject.getString("summary");
		} catch (JSONException e) {
		}
		try {
			icon = forecastBlockJsonObject.getString("icon");
		} catch (JSONException e) {
		}
		try {
			JSONArray blockData = forecastBlockJsonObject.getJSONArray("data");
			int blockDataLength = blockData.length();
			data = new ForecastIODataPoint[blockDataLength];
			for(int i=0; i<blockDataLength; i++) {
				ForecastIODataPoint d = new ForecastIODataPoint(blockData.getJSONObject(i));
				data[i] = d;
			}
		} catch (JSONException e) {
		}
	}
	
	/**
	 * Method to retrieve a value for use
	 * @param key String key value
	 * @return field value as string
	 */
	public String getValue(String key) {
		if(key.equals(new String("summary"))) {
			return summary;
		}
		else if(key.equals(new String("icon"))) {
			return icon;
		}
		return null;
	}
	
	/**
	 * method returns the summary for this data block;
	 * @return string value of summary
	 */
	public String summary() {
		return summary;
	}
	
	/**
	 * method returns the icon for this data block;
	 * @return string value of icon
	 */
	public String icon() {
		return icon;
	}
	
	/**
	 * method returns the dataPoints for this data block;
	 * @return ForecastIODataPoint[] 
	 * @see ForecastIODataPoint
	 */
	public ForecastIODataPoint[] data() {
		return data;
	}
}
