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
	
	/**
	 * Constructor method which populates the child object
	 * 
	 * @param responseString json string from api call.
	 */
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

	/**
	 * This method will return a specific value based on the key string.
	 * This method will return null if the value is not found.
	 * @param keyString
	 * @return the value of the data point
	 */
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
	
	/**
	 * method which returns a list of ForecastIODataPoint 
	 * @param keyString string which defines what data points are required.
	 * @return
	 */
	public ForecastIODataPoint[] getDataPoints(String keyString) {
		ForecastIODataPoint[] value = null;
		try {
			if(keyString == "minutely") {
				value = getMinutely().getData();
			}
			else if(keyString == "hourly") {
				value = getHourly().getData();
			}
			else if(keyString == "daily") {
				value = getDaily().getData();
			}
		}
		catch(NullPointerException e) {
			return null;
		}
		return value;
	}
	
	/**
	 * method which returns the currently object
	 * @return
	 */
	public ForecastIOCurrently getCurrently() {
		return mOutputCurrently;
	}
	
	/**
	 * method which sets up the currently object
	 * @param forecastJsonObject json object used to create object
	 * @return	ForecastIOCurrently object
	 * @see ForecastIOCurrently
	 */
	public ForecastIOCurrently buildForecastIOCurrently(JSONObject forecastJsonObject) {
		return new ForecastIOCurrently(forecastJsonObject);
	}

	/**
	 * method which returns the minutely object
	 * @return
	 */
	public ForecastIOMinutely getMinutely() {
		return mOutputMinutely;
	}
	
	/**
	 * method which sets up and populates the response object
	 * @param forecastJsonObject input json object for parsing
	 * @return ForecastIOMinutely object which contains all flag info
	 * @see ForecastIOMinutely
	 */
	public ForecastIOMinutely buildForecastIOMinutely(JSONObject forecastJsonObject) {
		return new ForecastIOMinutely(forecastJsonObject);
	}

	/**
	 * method which returns hourly object
	 * @return
	 */
	public ForecastIOHourly getHourly() {
		return mOutputHourly;
	}
	
	/**
	 * method which sets up and populates the response object
	 * @param forecastJsonObject input json object for parsing
	 * @return ForecastIOHourly object which contains all flag info
	 * @see ForecastIOHourly
	 */
	public ForecastIOHourly buildForecastIOHourly(JSONObject forecastJsonObject) {
		return new ForecastIOHourly(forecastJsonObject);
	}

	/**
	 * method which returns the daily object in api call.
	 * @return
	 */
	public ForecastIODaily getDaily() {
		return mOutputDaily;
	}
	
	/**
	 * method which sets up and populates the response object
	 * @param forecastJsonObject input json object for parsing
	 * @return ForecastIODaily object which contains all flag info
	 * @see ForecastIODaily
	 */
	public ForecastIODaily buildForecastIODaily(JSONObject forecastJsonObject) {
		return new ForecastIODaily(forecastJsonObject);
	}

	/**
	 * method which returns a list of ForecastIOAlerts
	 * @return ForecastIOAlerts object which contains all alert info
	 * @see ForecastIOAlerts
	 */
	public ForecastIOAlerts getAlerts() {
		return mOutputAlerts;
	}
	
	/**
	 * method which sets up and populates the response object
	 * @param forecastJsonObject input json object for parsing
	 * @return ForecastIOAlerts object which contains all flag info
	 * @see ForecastIOAlerts
	 */
	public ForecastIOAlerts buildForecastIOAlerts(JSONArray forecastJsonArray) {
		return new ForecastIOAlerts(forecastJsonArray);
	}
	
	/**
	 * method which returns a list of ForecastIOFlags
	 * @return ForecastIOFlags object which contains all flag info
	 * @see ForecastIOFlags
	 */
	public ForecastIOFlags getFlags() {
		return mOutputFlags;
	}
	
	/**
	 * method which sets up and populates the response object
	 * @param forecastJsonObject input json object for parsing
	 * @return ForecastIOFlags object which contains all flag info
	 * @see ForecastIOFlags
	 */
	public ForecastIOFlags buildForecastIOFlags(JSONObject forecastJsonObject) {
		return new ForecastIOFlags(forecastJsonObject);
	}
}
