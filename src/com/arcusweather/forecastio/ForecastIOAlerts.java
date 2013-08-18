package com.arcusweather.forecastio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIOAlerts {
	private ForecastIOAlert[] mAlerts;
	
	public ForecastIOAlerts(JSONArray forecastAlertsJsonArray) {
		if(forecastAlertsJsonArray.length() > 0) {
			mAlerts = new ForecastIOAlert[forecastAlertsJsonArray.length()];
			for(int i=0; i<forecastAlertsJsonArray.length(); i++) {
				try {
					JSONObject forecastIOAlert = forecastAlertsJsonArray.getJSONObject(i);
					ForecastIOAlert alertData = new ForecastIOAlert(forecastIOAlert);
					mAlerts[i] = alertData;
				}
				catch(JSONException e) {
				}
			}
		}
	}
	
	public ForecastIOAlert[] getData() {
		return mAlerts;
	}
}
