package Vehicle;

import Simulation.Simulator;
import Simulation.iFlyable;
import Simulation.*;

public class Baloon extends Aircraft  implements iFlyable {
    public WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String initWeatherCoordinates = weatherTower.getWeather(this.coordinates);
        switch (initWeatherCoordinates){
            case "SUN": this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
            break;
            case "RAIN": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
            break;
            case "FOG": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
            break;
            case "SNOW": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
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
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ") registered to weather tower.");
    }

}
