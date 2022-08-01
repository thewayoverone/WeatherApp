package com.example.weatherapp;

public class City {
    private String name;
    private String rName;
    private String lat;
    private String lon;

    public City(String name, String rName, String lat, String lon) {
        this.rName = rName;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getrName(){
        return rName;
    }
    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
