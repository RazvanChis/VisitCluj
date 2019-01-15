package com.example.guest.myapplication;

public class Route {

    private String name;
    private String type;
    private String duration;
    private String cost;
    private String distance;

    public Route(String name, String type, String duration, String cost, String distance) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.cost = cost;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}
