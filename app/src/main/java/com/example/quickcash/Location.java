package com.example.quickcash;

import java.io.Serializable;

public class Location implements Serializable {

    // Radius of the Earth in kilometers
    private static final double EARTH_RADIUS = 6371.0;

    private double lat;
    private double lon;

    public Location() {}    // Required for firebase.

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isWithinRadius(Location other, double radius) {
        double distance = haversine(this.lat, this.lon, other.lat, other.lon);
        return distance <= radius;
    }

    // Haversine formula to calculate the distance between two lat/long points
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {

        // Convert latitudes and longitudes from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Differences between the two latitudes and longitudes
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers.
        return EARTH_RADIUS * c;
    }

    public static Location convertAddressToLocation(String address) {
        // TODO: Implement this method to convert an address to a Location object. We need to use google maps API.


        return new Location(0, 0);
    }

    public static String convertLocationToAddress(Location location) {
        // TODO: Implement this method to convert a Location object to an address. We need to use google maps API.
        return "Middle Earth";
    }
}
