package com.greenpulse.impactloop.entity;

public class RainEvent {
    
    private String eventId;
    private String location;
    private String startTime;
    private String endTime;
    private double rainfallMm;

    public RainEvent() {
    }

    public RainEvent(
            String eventId,
            String location,
            String startTime,
            String endTime,
            double rainfallMm
    ) {
        this.eventId = eventId;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rainfallMm = rainfallMm;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getRainfallMm() {
        return rainfallMm;
    }

    public void setRainfallMm(double rainfallMm) {
        this.rainfallMm = rainfallMm;
    }
}
