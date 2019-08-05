package Simulation;


//import Simulation.WeatherTower;
import Vehicle.*;

import java.io.*;

import static java.lang.System.exit;

public class Simulator {
     public static PrintWriter writer;
     static int it;

    public static void main(String[] args) {
        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();

        try{
            if (args.length < 1) throw  new IOException();
            String inputFile = args[0];
            File outputFile = new File("simulation.txt");
            writer = new PrintWriter(outputFile);
            if (outputFile.exists()) writer.print("");

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String nextLine;
            int line = 0;

            while ((nextLine = br.readLine()) != null){
                if(line == 0){
                    it = (Integer.parseInt(nextLine));
                    if(it < 0 ) throw new IOException();
                } else {
                    String[] currLine = nextLine.split(" ");
                    if (currLine.length == 5)
                        aircraftFactory.newAircraft(currLine[0],currLine[1], Integer.parseInt(currLine[2]), Integer.parseInt(currLine[3]), Integer.parseInt(currLine[4])).registerTower(weatherTower);
                else throw new IOException();
                }
                line++;
            }
            br.close();
        }catch (IOException e) {
            System.out.println( e+ "Wrong input! Update input file");
            exit(1);
        }
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (it-- > 0) weatherTower.changeWeather();

        writer.close();
    }
}
