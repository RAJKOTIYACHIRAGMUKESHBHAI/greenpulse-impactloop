package com.greenpulse.impactloop.entity;

public class Evidence {
    
    private String evidenceId;
    private String interventionId;
    private String type;
    private String photoUrl;
    private double latitude;
    private double longitude;
    private String timestamp;

    public Evidence() {
    }

    public Evidence(
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

    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
