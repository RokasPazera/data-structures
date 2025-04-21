package nl.saxion.cds.solution.application;

public class Tracks {

    private String from;
    private String to;
    private double cost_unit;
    private double distance;

    public Tracks(String from, String to, double cost_unit, double distance) {
        this.from = from;
        this.to = to;
        this.cost_unit = cost_unit;
        this.distance = distance;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getCost_unit() {
        return cost_unit;
    }

    public double getDistance() {
        return distance;
    }
}
