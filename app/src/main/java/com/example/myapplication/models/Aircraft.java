package com.example.myapplication.models;

public class Aircraft {
    private String manifest_name;
    private String aircraft_name;
    private int departure_time;
    private String location;
    private int max_capacity;
    private String status;

    public Aircraft() {
    }

    public Aircraft(String manifest_name, String aircraft_name, int departure_time, String location, int max_capacity, String status) {
        this.manifest_name = manifest_name;
        this.aircraft_name = aircraft_name;
        this.departure_time = departure_time;
        this.location = location;
        this.max_capacity = max_capacity;
        this.status = status;
    }

    public String getManifest_name() {
        return manifest_name;
    }

    public void setManifest_name(String manifest_name) {
        this.manifest_name = manifest_name;
    }

    public String getAircraft_name() {
        return aircraft_name;
    }

    public void setAircraft_name(String aircraft_name) {
        this.aircraft_name = aircraft_name;
    }

    public int getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(int departure_time) {
        this.departure_time = departure_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "manifest_name='" + manifest_name + '\'' +
                ", aircraft_name='" + aircraft_name + '\'' +
                ", departure_time=" + departure_time +
                ", location='" + location + '\'' +
                ", max_capacity=" + max_capacity +
                ", status='" + status + '\'' +
                '}';
    }
}
