package com.greenpulse.impactloop.dto;

public class EvidenceResponse {
    
    private String evidenceId;
    private String interventionId;
    private String type;
    private String photoUrl;
    private double latitude;
    private double longitude;
    private String timestamp;

    public EvidenceResponse() {
    }

    public EvidenceResponse(
            String evidenceId,
            String interventionId,
            String type,
            String photoUrl,
            double latitude,
            double longitude,
            String timestamp
    ) {
        this.evidenceId = evidenceId;
        this.interventionId = interventionId;
        this.type = type;
        this.photoUrl = photoUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public String getType() {
        return type;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
