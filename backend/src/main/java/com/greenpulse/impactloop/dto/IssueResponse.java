package com.greenpulse.impactloop.dto;

public class IssueResponse {

    private String issueId;
    private String type;
    private double latitude;
    private double longitude;
    private String description;
    private String photoUrl;
    private String status;
    private String createdAt;

    public IssueResponse() {
    }

    public IssueResponse(
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

    public String getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}