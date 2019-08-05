package Vehicle;

import Simulation.Simulator;
import Simulation.iFlyable;
import Simulation.*;

public class Helicopter extends Aircraft implements iFlyable {
    public WeatherTower weatherTower;
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String initWeatherCoordinates = weatherTower.getWeather(this.coordinates);

        switch (initWeatherCoordinates) {
            case "SUN": this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                break;
            case "RAIN": this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "FOG": this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "SNOW": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                break;
                default: break;
        }
        Simulator.writer.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): got " + initWeatherCoordinates + "ified seriously.");

        if (this.coordinates.getHeight() == 0) {
            Simulator.writer.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
            Simulator.writer.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
