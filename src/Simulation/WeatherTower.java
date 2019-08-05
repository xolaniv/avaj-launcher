package Simulation;

//import Simulation.Tower;
import Vehicle.*;
//import Simulation.WeatherProvider;

public class WeatherTower extends Tower {

    void WeatherTower() {
    }

    public String getWeather(Coordinates coordinates){
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather(){
        this.conditionsChanged();
    }
}
