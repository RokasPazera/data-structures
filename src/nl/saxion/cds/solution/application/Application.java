package nl.saxion.cds.solution.application;

import nl.saxion.cds.solution.Coordinate;
import nl.saxion.cds.solution.MyArrayList;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) throws FileNotFoundException {


        TrainStationManager manager = new TrainStationManager();


        Scanner in = new Scanner(System.in);
        int option = -1;


        while(option!=0){
            System.out.println("select menu option");
            System.out.println("1: look up the station information on station CODE");
            System.out.println("2: look up the station information on station NAME");
            System.out.println("3: look up the stations based on types alphabetically");
            System.out.println("4: find the shortest route between two stations");
            System.out.println("5. Display minimum cost spanning tree (MCST)");
            System.out.println("6. Show the rail network (Graphical Interface)");
            System.out.println("0. Exit\n");

            option = readInt();
            System.out.println();

            switch(option){
                case 0:{
                    break;
                }
                case 1:{
                    System.out.println("station code:");
                    String stationCode = readString();
                    manager.showStationByCode(stationCode);
                    break;
                }
                case 2:{
                    System.out.println("Station name: ");
                    String stationName = readString();
                    manager.showStationByName(stationName);
                    break;
                }
                case 3: {
                    manager.showStationTypes();
                    System.out.println("Choose exact type: ");
                    String choseType = in.nextLine().replaceAll("\\s+", "");
                    manager.showStationByType(choseType);
                    break;
                }
                case 4:{
                    System.out.println("Enter start station code:");
                    String startCode = in.nextLine().trim().toUpperCase();
                    System.out.println("Enter end station code:");
                    String endCode = in.nextLine().trim().toUpperCase();
                    manager.findShortestRoute(startCode, endCode);
                    break;

                }
                case 5:{
                    manager.calculateMinimumRailConnections();
                    break;
                }
                case 7:{
                    break;
                }
            }
        }



    }

    private static final Scanner sc = new Scanner(System.in);

    public static String readString() {
        return sc.nextLine();
    }

    public static int readInt() {
        while (true) {
            try {
                String input = readString();
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid value (unable to parse), please try again: ");
            }
        }
    }




}
