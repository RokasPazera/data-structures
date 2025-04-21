package nl.saxion.cds.solution.application;

import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.solution.MyArrayList;
import nl.saxion.cds.solution.MyGraph;
import nl.saxion.cds.solution.MyHashSet;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class TrainStationManager {
    private static MyArrayList<Stations> stations = new MyArrayList<>();
    private static MyArrayList<Tracks> tracks = new MyArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private MyGraph<Stations> graph;

    public void readCSVData() throws FileNotFoundException {
        stations = CSVReader.readStations("resources/stations.csv");
        tracks = CSVReader.readTracks("resources/tracks.csv");

        graph = new MyGraph<>(stations.size());

        buildGraph();

    }

    private void buildGraph() {
        for (Tracks track : tracks) {
            Stations fromStation = getStationByCode(track.getFrom());
            Stations toStation = getStationByCode(track.getTo());

            graph.addEdgeBidirectional(fromStation, toStation, track.getDistance());
        }
    }
    private Stations getStationByCode(String code) {
        for (Stations station : stations) {
            if (station.getCode().equalsIgnoreCase(code)) {
                return station;
            }
        }
        return null;
    }

    public void showStationByCode(String stationCode) throws FileNotFoundException {
        if(stations.isEmpty()){
            readCSVData();
        }
        boolean stationFound = false;

        for (Stations station : stations) {
            if (station.getCode().equalsIgnoreCase(stationCode)) {
                showStationInfo(station);
                stationFound = true;
                break;
            }
        }

        if (!stationFound) System.out.println("Station with that code does not exist.");
    }

    public void showStationByName(String stationName) {
        MyArrayList<Stations> matchingStations = new MyArrayList<>();

        for (Stations station : stations) {
            if (station.getName().toLowerCase().startsWith(stationName.toLowerCase())) {
                matchingStations.addLast(station);
            }
        }

        if (matchingStations.isEmpty()) {
            System.out.println("No stations found with the given name.");
            return;
        } else if (matchingStations.size() == 1) {
            showStationInfo(matchingStations.get(0));
            return;
        }


        System.out.println("Multiple stations found:");
        for (int i = 0; i < matchingStations.size(); i++) {
            System.out.println((i + 1) + ": " + matchingStations.get(i).getName());
        }
        System.out.println("Select the station name from list:");
        String selectedName = scanner.nextLine().trim();

        for (Stations station : matchingStations) {
            if (station.getName().equalsIgnoreCase(selectedName)) {
                showStationInfo(station);
                return;
            }
        }
    }

    public void showStationTypes() throws FileNotFoundException {
        if(stations.isEmpty()){
            readCSVData();
        }
        MyHashSet<String> stationTypes = new MyHashSet<>();

        for (Stations station : stations) {
            stationTypes.add(station.getType());
        }

        for (String type : stationTypes) {
            System.out.println(type);
        }
    }

    public void showStationByType(String type) {
        MyArrayList<Stations> stationsByType = new MyArrayList<>();
        for (Stations station : stations) {
            if (station.getType().equalsIgnoreCase(type)) {
                stationsByType.addLast(station);
            }
        }
        stationsByType.quickSort(Comparator.comparing(Stations::getName));

        System.out.println("All stations with " + type);
        for (Stations station : stationsByType) {
            System.out.println(station.getName());
        }
    }



    public void findShortestRoute(String start, String end) throws FileNotFoundException {
        if(stations.isEmpty()){
            readCSVData();
        }
        Stations startStation = findStation(start);
        Stations destinationStation = findStation(end);

        try {
            SaxList<SaxGraph.DirectedEdge<Stations>> shortestPath = graph.shortestPathAStar(startStation, destinationStation, new SaxGraph.Estimator<Stations>() {
                @Override
                public double estimate(Stations current, Stations target) {
                    return calculateEstimate(current, target);
                }
            });

            if (shortestPath.isEmpty()) {
                System.out.println("No path found between the selected stations.");
                return;
            }

            System.out.println("Shortest route:");


            double totalDistance = 0;
            StringBuilder routeDetails = new StringBuilder();

            for (int i = 1; i < shortestPath.size(); i++) {
                SaxGraph.DirectedEdge<Stations> currentEdge = shortestPath.get(i);
                totalDistance += currentEdge.weight();
                routeDetails.append("From: ").append(currentEdge.from().getCode())
                        .append(" -> To: ").append(currentEdge.to().getCode())
                        .append(" | Distance: ").append(currentEdge.weight())
                        .append(" km (Cumulative: ").append(String.format("%.2f", totalDistance)).append(" km)\n");
            }

            System.out.println(routeDetails);
            System.out.println(STR."Total shortest distance between \{start} and \{end}: \{String.format("%.2f", totalDistance)} km");
        } catch (KeyNotFoundException e) {
            System.out.println(STR."Error: \{e.getMessage()}");
        }
    }

    private Stations findStation(String input) {
        for (Stations station : stations) {
            if (station.getCode().equalsIgnoreCase(input) || station.getName().equalsIgnoreCase(input)) {
                return station;
            }
        }
        return null;
    }

    private double calculateEstimate(Stations start, Stations goal) {
        double Latitude = start.getLatitude() - goal.getLatitude();
        double Longitude = start.getLongitude() - goal.getLongitude();
        return Math.hypot(Latitude, Longitude);
    }








    public void calculateMinimumRailConnections() throws FileNotFoundException {
        if (stations.isEmpty()) {
            readCSVData();
        }

        MyArrayList<Stations> dutchStations = new MyArrayList<>();
        for (Stations station : stations) {
            if ("NL".equalsIgnoreCase(station.getCountry())) {
                dutchStations.addLast(station);
            }
        }

        MyGraph<Stations> dutchStationGraph = new MyGraph<>(dutchStations.size());
        for (Tracks track : tracks) {
            Stations fromStation = getStationByCode(track.getFrom());
            Stations toStation = getStationByCode(track.getTo());

            if (areStationsInNetherlands(fromStation, toStation)) {
                dutchStationGraph.addEdgeBidirectional(fromStation, toStation, track.getDistance());
            }
        }

        SaxGraph<Stations> minimumSpanningTree = dutchStationGraph.minimumCostSpanningTree();

        System.out.println("Number of rail connections needed: " + minimumSpanningTree.size());
        System.out.println("Total length of rail connections: " + String.format("%.2f", minimumSpanningTree.getTotalWeight()) + " km");
    }


    private boolean areStationsInNetherlands(Stations from, Stations to) {
        return from != null && to != null && "NL".equalsIgnoreCase(from.getCountry()) && "NL".equalsIgnoreCase(to.getCountry());
    }



    public void showStationInfo(Stations station) {
        System.out.println("Station Information:");
        System.out.println("Name: " + station.getName());
        System.out.println("Country: " + station.getCountry());
        System.out.println("Type: " + station.getType());
    }
}
