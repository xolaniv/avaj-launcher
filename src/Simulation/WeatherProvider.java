package Simulation;

import Vehicle.*;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();

    private static String[] weather = {
            "RAIN",
            "FOG",
            "SUN",
            "SNOW"
    };

     public WeatherProvider() {

     }

    public static WeatherProvider getProvider(){
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        return weather[((coordinates.getLongitude() > 10) ? 1 : 0) + ((coordinates.getLatitude() > 20) ? 1 : 0) + ((coordinates.getHeight() > 5) ? 1 : 0)];
    }
}
