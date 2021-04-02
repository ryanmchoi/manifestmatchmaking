package com.example.myapplication.models;

public class Manifest {
    private String aircraft_name;
    private String id;

    public Manifest() {
    }

    public Manifest(String aircraft_name, String id) {
        this.aircraft_name = aircraft_name;
        this.id = id;
    }

    public String getAircraft_name() {
        return aircraft_name;
    }

    public void setAircraft_name(String aircraft_name) {
        this.aircraft_name = aircraft_name;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return "Manifest{" +
                "aircraft_name='" + aircraft_name + '\'' +
                ", id='" + id;
    }
}
