package nl.saxion.cds.solution.application;

public class Stations {
    private String code;
    private String name;
    private String country;
    private String type;
    private double latitude;
    private double longtitude;

    public Stations(String code, String name, String country, String type, double latitude, double longtitude) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.type = type;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longtitude;
    }

    @Override
    public String toString() {
        return "Stations{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                '}';
    }
}
