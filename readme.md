#ForecastIOLibrary

###This library was created for and used by the Android App "Arcus Weather".

This project provides a Java library for using the Forecast.IO weather api.
Please take a look at http://developer.forecast.io to get details on the API and what data is returned. 

I have implemented classes for each data block/point that forecast.io provides.

The Classes provided are as follows:

* ForecastIO.java - The main class that sets up the API request.
* ForecastIOResponse.java - Class that handles the response from the API call, and sets all data points.
* ForecastIOCurrently.java - Implements a ForecastIODataPoint representing current weather conditions.
* ForecastIOMinutely.java - Implements a ForecastIODataBlock representing next hour weather conditions.
* ForecastIOHourly.java - Implements a ForecastIODataBlock representing next 48 hour weather conditions.
* ForecastIODaily.java - Implements a ForecastIODataBlock representing next 7 day weather conditions.
* ForecastIODataBlock.java - Implements a list of ForecastIODataPoints.
* ForecastIODataPoint.java - Implements all data points sent back by the API call.
* ForecastIOAlerts.java - Implements a list of ForecastIOAlert.
* ForecastIOAlert.java - Implements all data points sent back for alerts by the API call.
* ForecastIOFlags.java - Implements a portion of data points sent back by the API call.

##Usage

Use the jar file as you normally would. Then proceed as follows:

        ForecastIO FIO = new ForecastIO(apiKey, latitude, longitude);  
        FIO.makeRequest();  

        String responseString = FIO.getResponseString();  
        ForecastIOResponse FIOR = new ForecastIOResponse(responseString);  

        //The library provides an easy way to access values as strings and data points as a list.
        String currentSummary = FIOR.getValue("current-summary");
        String thirdHourlyTemperature = FIOR.getValue("hourly-2-temperature");
        String firstDailyIcon = FIOR.getValue("daily-0-icon");
        String alertDescription = FIOR.getValue("alerts-description"); //alerts defaults to first alert if not given an index.

        ForecastIODataPoint[] minutelyPoints = FIOR.getDataPoints("minutely");
        double thirtiethMinutePrecipitation = FIOR.getValueAsDouble("precipitationIntensity");

        ForecastIODataPoint[] hourlyPoints = FIOR.getDataPoints("hourly");
        ForecastIODataPoint[] dailyPoints = FIOR.getDataPoints("daily");


        //you can also do it the hard way
        String currentSummary = FIOR.getCurrently().getValue("summary");
        String firstDailyIcon = FIOR.getDaily().getData[0].getValue("icon");


Please note that this library requires the JSON project that you will need to add to your build path.

I got the .jar file from here:
http://www.java2s.com/Code/JarDownload/java/java-json.jar.zip

My Java is probably at a very low level, so excuse any weirdness.
