package com.example.myapplication.models;

public class Manifest {
    private String aircraft_name;
    private int departure_time;
    private String id;
    private String location;
    private int max_capacity;
    private String status;

    public Manifest() {
    }

    public Manifest(String aircraft_name, int departure_time, String id, String location, int max_capacity, String status) {
        this.aircraft_name = aircraft_name;
        this.departure_time = departure_time;
        this.id = id;
        this.location = location;
        this.max_capacity = max_capacity;
        this.status = status;
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

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getLocation() { return location; }

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
        return "Manifest{" +
                "aircraft_name='" + aircraft_name + '\'' +
                ", departure_time=" + departure_time +
                ", id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", max_capacity=" + max_capacity +
                ", status='" + status + '\'' +
                '}';
    }
}
