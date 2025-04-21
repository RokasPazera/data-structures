package nl.saxion.cds.solution.application;

import nl.saxion.cds.solution.MyArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import nl.saxion.cds.solution.application.Tracks;
import nl.saxion.cds.solution.application.Stations;

public class CSVReader {
    public static MyArrayList<Stations> readStations(String file) throws FileNotFoundException {
        MyArrayList<Stations> stations = new MyArrayList<>();
        try {
            Scanner in = new Scanner(new File(file));

            in.nextLine();

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] eachLine = line.split(",");

                String code = eachLine[0];
                String name = eachLine[1];
                String country = eachLine[2];
                String type = eachLine[3];
                double latitude = Double.parseDouble(eachLine[4]);
                double longtitude = Double.parseDouble(eachLine[5]);

                Stations station = new Stations(code, name, country, type, latitude, longtitude);
                stations.addLast(station);
            }

        }catch(FileNotFoundException fnfe){
            throw new FileNotFoundException();
        }

        return stations;
    }

    public static MyArrayList<Tracks> readTracks(String file) throws FileNotFoundException {
        MyArrayList<Tracks> tracks = new MyArrayList<>();

        Scanner in = new Scanner(new File(file));

        in.nextLine();

        while(in.hasNextLine()){
            String line =in.nextLine();
            String[] eachLine = line.split(",");


            String from = eachLine[0];
            String to = eachLine[1];
            double cost_unit = Double.parseDouble(eachLine[2]);
            double distance = Double.parseDouble(eachLine[3]);

            Tracks track = new Tracks(from, to, cost_unit, distance);
            tracks.addLast(track);
        }
        return tracks;
    }
}
