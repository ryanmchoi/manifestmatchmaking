package com.example.myapplication.models;

public class Ranger {
    private String manifest;
    private String name;
    private String status;
    private int unit;

    public Ranger() {
    }

    public Ranger(String manifest, String name, String status, int unit) {
        this.manifest = manifest;
        this.name = name;
        this.status = status;
        this.unit = unit;
    }
    public String getManifest() {
        return manifest;
    }

    public void setManifest(String manifest) {
        this.manifest = manifest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ranger{" +
                "manifest='" + manifest + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", unit=" + unit +
                '}';
    }
}
