package de.tu_berlin.ise.open_data.airquality.model;

/**
 * Created by rohullahayobi on 6/28/17.
 * When mapping location names to coordinates for the importer manually,
 * location names are mapped to attributes (lat, lon) of this class
 */
public class Location {

    private double lat;
    private double lon;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
