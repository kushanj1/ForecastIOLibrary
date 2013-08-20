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
	
	/**
	 * Constructor for the class which takes the apiKey, latitude and logitude arguments.
	 * <p>
	 * This method merely sets these options, but does not actually make the request
	 *
	 * @param  apiKey  		Your forecast.io api key.
	 * @param  latitude		Latitude value for coordinates you are requesting weather for
	 * @param  longitude	Longitude value for coordinates you are requesting weather for
	 */
	public ForecastIO(String apiKey, Double latitude, Double longitude) {
		this.mApiKey = apiKey;
		this.mLatitude = latitude;
		this.mLongitude = longitude;
	}
	
	/**
	 * This method will set the mRequestOptions variable (which will be used later for building the URL)
	 * @param options	Values of parameters that are accepted by the API.
	 */
	public void setRequestParams(HashMap<String, String> options) {
		this.mRequestOptions = options;
	}
	
	/**
	 * Builds the forecast.io api call URL, then will make the request
	 */
	public void makeRequest() {
		URL forecastUrl = buildForecastUrl();
		mResponseString = makeForecastRequest(forecastUrl);
	}
	
	/**
	 * Returns the response string received from the API
	 */
	public String getResponseString() {
		return mResponseString;
	}
	
	/**
	 * Returns the url string received from the API
	 */
	public String getUrl() {
		return mUrl;
	}
	
	/**
	 * Returns a ForecatIOResponse object, built with the response String
	 * @return
	 */
	public ForecastIOResponse getForecastIOResponseData() {
		return buildForecastIOResponse(mResponseString);
	}
	
	/**
	 * Builds a URL object for the api call.
	 * @return	URL	the url object ready for use in the api call.
	 */
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
	
	/**
	 * Method which does the actual connection work 
	 * @param forecastUrl	the url object used to make API calls
	 * @return 	The response string
	 */
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
	
	/**
	 * Build the ForecastIOResponseProject
	 * @param responseString uses the api response string as production for now
	 * @return	ForecastIOResponse object
	 * @see 	ForecastIOResponse
	 */
	public ForecastIOResponse buildForecastIOResponse(String responseString) {
		return new ForecastIOResponse(responseString);
	}
}
