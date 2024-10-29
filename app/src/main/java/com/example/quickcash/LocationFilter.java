package com.example.quickcash;

public class LocationFilter implements IJobPostFilter {
    private final Location location;
    private final double radius;

    public LocationFilter(Location location, double radiusInKilometers) {
        this.location = location;
        this.radius = radiusInKilometers;
    }

    @Override
    public boolean satisfy(JobPost jobPost) {
        return location.isWithinRadius(jobPost.getLocation(), radius);
    }
}
