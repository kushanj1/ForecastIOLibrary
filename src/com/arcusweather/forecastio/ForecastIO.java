package com.arcusweather.forecastio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ForecastIO {
	private String mBaseUrl = "https://api.forecast.io/forecast/";
	private String mApiKey;
	private Double mLatitude;
	private Double mLongitude;
	private String mUnits;
	private String[] mExcludeBlocks;
	private boolean mExtendHourly;
	private String mResponseString;
	
	public ForecastIO(String apiKey, Double latitude, Double longitude) {
		this.mApiKey = apiKey;
		this.mLatitude = latitude;
		this.mLongitude = longitude;
		this.mUnits = "us"; //default to US because forecast defaults to that
		this.mExcludeBlocks = new String[] {};
		this.mExtendHourly = false;
	}
	
	public void setRequestOptions(String units, String[] excludeBlocks, boolean extendHourly) {
		this.mUnits = units;
		this.mExcludeBlocks = excludeBlocks;
		this.mExtendHourly = extendHourly;
	}
	
	public void makeRequest() {
		URL forecastUrl = buildForecastUrl();
		mResponseString = makeForecastRequest(forecastUrl);
	}

	public void makeRequest(String userAgent) {
		URL forecastUrl = buildForecastUrl();
		mResponseString = makeForecastRequest(forecastUrl, userAgent);
	}
	
	public String getResponseString() {
		return mResponseString;
	}
	
	public ForecastIOResponse getForecastIOResponseData() {
		return buildForecastIOResponse(mResponseString);
	}
	
	public URL buildForecastUrl(){
		URL forecastUrl = null;
		try {
			String forecastUrlString = mBaseUrl + mApiKey + "/" + mLatitude + "," + mLongitude + "?";
			//TODO add in the time parameter for time machine requests
			
			//add units to request
			forecastUrlString += "&units=" + mUnits;
			
			//add exclude blocks
			String excludeString = "";
			for(int i=0; i<mExcludeBlocks.length; i++) {
				excludeString += mExcludeBlocks[i] + ",";
			}
			forecastUrlString += "&exclude=" + excludeString;
			
			//add extend if set
			if(mExtendHourly == true) {
				forecastUrlString += "&extend=hourly";
			}

			forecastUrl = new URL(forecastUrlString);
		} catch (MalformedURLException e) {
		}
		return forecastUrl;
	}

	public String makeForecastRequest(URL forecastUrl){
		HttpURLConnection con = null;
		String responseString = "";
		try {
			con = (HttpURLConnection) forecastUrl.openConnection();
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
	
	public String makeForecastRequest(URL forecastUrl, String userAgent){
		HttpURLConnection con = null;
		String responseString = "";
		try {
			con = (HttpURLConnection) forecastUrl.openConnection();
			con.setRequestProperty("User-Agent", userAgent);
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
