package com.northcoders.drinksapi.model;

import java.util.List;

public class Planet {
    private final String planetName;
    private final long distance;
    private final String planetType;
    private final long size;
    private final long YearLengthInDays;
    private final long orbitalCircumference;
    private final List<String> moons;

    public Planet(String planetName, long distance, String planetType, long yearLengthInDays, List<String> moons, long orbitalCircumference, long size) {
        this.planetName = planetName;
        this.distance = distance;
        this.planetType = planetType;
        YearLengthInDays = yearLengthInDays;
        this.moons = moons;
        this.orbitalCircumference = orbitalCircumference;
        this.size = size;
    }

    public String getPlanetName() {
        return planetName;
    }

    public long getDistance() {
        return distance;
    }

    public String getPlanetType() {
        return planetType;
    }

    public long getYearLengthInDays() {
        return YearLengthInDays;
    }

    public long getSize() {
        return size;
    }

    public long getOrbitalCircumference() {
        return orbitalCircumference;
    }

    public List<String> getMoons() {
        return moons;
    }
}
