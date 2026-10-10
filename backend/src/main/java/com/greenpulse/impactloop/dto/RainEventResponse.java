package com.greenpulse.impactloop.dto;

public class RainEventResponse {
    
    private String eventId;
    private String location;
    private String startTime;
    private String endTime;
    private double rainfallMm;

    public RainEventResponse() {
    }

    public RainEventResponse(
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

    public String getLocation() {
        return location;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public double getRainfallMm() {
        return rainfallMm;
    }
}
