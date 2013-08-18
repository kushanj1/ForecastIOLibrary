package com.arcusweather.forecastio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastIOResponse {
	private ForecastIOCurrently mOutputCurrently;
	private ForecastIOMinutely mOutputMinutely;
	private ForecastIOHourly mOutputHourly;
	private ForecastIODaily mOutputDaily;
	private ForecastIOAlerts mOutputAlerts;
	private ForecastIOFlags mOutputFlags;
	private String latitude;
	private String longitude;
	private String timezone;
	private String offset;
	
	public ForecastIOResponse(String responseString) {
        JSONObject forecastJsonObject = null;
        try {
			forecastJsonObject = new JSONObject(responseString);
		} catch (JSONException e) {
			return;
		}
        
        try {
        	latitude = forecastJsonObject.getString("latitude");
		} catch (JSONException e) {
		}
        
        try {
        	longitude = forecastJsonObject.getString("longitude");
		} catch (JSONException e) {
		}
        
        try {
        	timezone = forecastJsonObject.getString("timezone");
		} catch (JSONException e) {
		}
        
        try {
        	offset = forecastJsonObject.getString("offset");
		} catch (JSONException e) {
		}
        
        try {
			JSONObject currentlyJSONObject = forecastJsonObject.getJSONObject("currently");
	        mOutputCurrently = buildForecastIOCurrently(currentlyJSONObject);
		} catch (JSONException e) {
		}
        
    	try {
			JSONObject minutelyJSONObject = forecastJsonObject.getJSONObject("minutely");
	        mOutputMinutely = buildForecastIOMinutely(minutelyJSONObject);
		} catch (JSONException e) {
		} 
        
    	try {
			JSONObject hourlyJSONObject = forecastJsonObject.getJSONObject("hourly");
	        mOutputHourly = buildForecastIOHourly(hourlyJSONObject);
		} catch (JSONException e) {
		} 

    	try {
			JSONObject dailyJSONObject = forecastJsonObject.getJSONObject("daily");
	        mOutputDaily = buildForecastIODaily(dailyJSONObject);
		} catch (JSONException e) {
		} 

        try {
        	JSONArray alertsJSONArray = forecastJsonObject.getJSONArray("alerts");
	        mOutputAlerts = buildForecastIOAlerts(alertsJSONArray);
        } catch (JSONException e) {
        }
        
        try {
        	JSONObject flagsJSONObject = forecastJsonObject.getJSONObject("flags");
        	mOutputFlags = buildForecastIOFlags(flagsJSONObject);
        } catch (JSONException e) {
        }
        
	}

	public String getValue(String keyString) {
		String[] fields = keyString.split("-");
		String level = fields[0];
		String value = null;

		try {
			if(level.equals(new String("latitude"))) {
				value = latitude;
			}
			else if(level.equals(new String("longitude"))) {
				value = longitude;
			}
			else if(level.equals(new String("timezone"))) {
				value = timezone;
			}
			else if(level.equals(new String("offset"))) {
				value = offset;
			}
			else if(level.equals(new String("currently"))) {
				value = getCurrently().getValue(fields[1]);
			}
			else if(level.equals(new String("minutely"))) {
				try {
					int listIndex = Integer.parseInt(fields[1]);
					value = getMinutely().getData()[listIndex].getValue(fields[2]);
				}
				catch(NumberFormatException e) {
					value = getMinutely().getValue(fields[1]);
				}
			}
			else if(level.equals(new String("hourly"))) {
				try {
					int listIndex = Integer.parseInt(fields[1]);
					value = getHourly().getData()[listIndex].getValue(fields[2]);
				}
				catch(NumberFormatException e) {
					value = getHourly().getValue(fields[1]);
				}
			}
			else if(level.equals(new String("daily"))) {
				try {
					int listIndex = Integer.parseInt(fields[1]);
					value = getDaily().getData()[listIndex].getValue(fields[2]);
				}
				catch(NumberFormatException e) {
					value = getDaily().getValue(fields[1]);
				}
			}
			else if(level.equals(new String("alerts"))) {
				try {
					int listIndex = Integer.parseInt(fields[1]);
					value = getAlerts().getData()[listIndex].getValue(fields[2]);
				}
				catch(NumberFormatException e) {
					value = getAlerts().getData()[0].getValue(fields[1]);
				}
			}
			else if(level.equals(new String("flags"))) {
				value = getFlags().getValue(fields[1]);
			}
		}
		catch(NullPointerException e) {
			return null;
		}
		return value;
	}
	
	public ForecastIODataPoint[] getDataPoints(String keyString) {
		ForecastIODataPoint[] value = null;
		if(keyString == "minutely") {
			value = getMinutely().getData();
		}
		else if(keyString == "hourly") {
			value = getHourly().getData();
		}
		else if(keyString == "daily") {
			value = getDaily().getData();
		}
		return value;
	}
	
	public ForecastIOCurrently getCurrently() {
		return mOutputCurrently;
	}
	
	public ForecastIOCurrently buildForecastIOCurrently(JSONObject forecastJsonObject) {
		return new ForecastIOCurrently(forecastJsonObject);
	}

	public ForecastIOMinutely getMinutely() {
		return mOutputMinutely;
	}
	
	public ForecastIOMinutely buildForecastIOMinutely(JSONObject forecastJsonObject) {
		return new ForecastIOMinutely(forecastJsonObject);
	}

	public ForecastIOHourly getHourly() {
		return mOutputHourly;
	}
	
	public ForecastIOHourly buildForecastIOHourly(JSONObject forecastJsonObject) {
		return new ForecastIOHourly(forecastJsonObject);
	}

	public ForecastIODaily getDaily() {
		return mOutputDaily;
	}
	
	public ForecastIODaily buildForecastIODaily(JSONObject forecastJsonObject) {
		return new ForecastIODaily(forecastJsonObject);
	}

	public ForecastIOAlerts getAlerts() {
		return mOutputAlerts;
	}
	
	public ForecastIOAlerts buildForecastIOAlerts(JSONArray forecastJsonArray) {
		return new ForecastIOAlerts(forecastJsonArray);
	}
	
	public ForecastIOFlags getFlags() {
		return mOutputFlags;
	}
	
	public ForecastIOFlags buildForecastIOFlags(JSONObject forecastJsonObject) {
		return new ForecastIOFlags(forecastJsonObject);
	}
}
