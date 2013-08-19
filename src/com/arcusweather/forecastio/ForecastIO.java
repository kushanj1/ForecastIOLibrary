package com.arcusweather.forecastio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ForecastIO {
	private String mBaseUrl = "https://api.forecast.io/forecast/";
	private String mApiKey;
	private Double mLatitude;
	private Double mLongitude;
	private String mResponseString;
	private String mUserAgent;
	private HashMap<String, String> mRequestOptions;
	private String mUrl;
	
	public ForecastIO(String apiKey, Double latitude, Double longitude) {
		this.mApiKey = apiKey;
		this.mLatitude = latitude;
		this.mLongitude = longitude;
	}
	
	public void setRequestParams(HashMap<String, String> options) {
		this.mRequestOptions = options;
	}
	
	public void makeRequest() {
		URL forecastUrl = buildForecastUrl();
		mResponseString = makeForecastRequest(forecastUrl);
	}
	
	public String getResponseString() {
		return mResponseString;
	}
	
	public String getUrl() {
		return mUrl;
	}
	
	public ForecastIOResponse getForecastIOResponseData() {
		return buildForecastIOResponse(mResponseString);
	}
	
	public URL buildForecastUrl(){
		URL forecastUrl = null;
		try {
			String forecastUrlString = mBaseUrl + mApiKey + "/" + mLatitude + "," + mLongitude + "?";
			//TODO add in the time parameter for time machine requests
			for(String key: mRequestOptions.keySet()) {
				if(key == "userAgent") {
					mUserAgent = mRequestOptions.get(key);
				}
				else {
					forecastUrlString += "&" + key + "=" + mRequestOptions.get(key);
				}
			}
			forecastUrl = new URL(forecastUrlString);
			mUrl = forecastUrlString;
		} catch (MalformedURLException e) {
		}
		return forecastUrl;
	}
	
	public String makeForecastRequest(URL forecastUrl){
		HttpURLConnection con = null;
		String responseString = "";
		try {
			con = (HttpURLConnection) forecastUrl.openConnection();
			if(mUserAgent != null) {
				con.setRequestProperty("User-Agent", mUserAgent);
			}
			con.setDoOutput(false);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					responseString = line;
				}
			} 
			catch (IOException e) {
			} 
			finally {
				if (reader != null) {
					try {
						reader.close();
						reader = null;
					} catch (IOException e) {
					}
				}
			}
		} 
		catch (IOException e1) {
		}
		return responseString;
	}
	
	public ForecastIOResponse buildForecastIOResponse(String responseString) {
		return new ForecastIOResponse(responseString);
	}
}
