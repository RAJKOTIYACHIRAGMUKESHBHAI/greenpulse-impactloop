package com.greenpulse.impactloop.entity;

public class Issue {

    private String issueId;
    private String type;
    private double latitude;
    private double longitude;
    private String description;
    private String photoUrl;
    private String status;
    private String createdAt;

    public Issue() {
    }

    public Issue(
            String issueId,
            String type,
            double latitude,
            double longitude,
            String description,
            String photoUrl,
            String status,
            String createdAt
    ) {
        this.issueId = issueId;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.photoUrl = photoUrl;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}