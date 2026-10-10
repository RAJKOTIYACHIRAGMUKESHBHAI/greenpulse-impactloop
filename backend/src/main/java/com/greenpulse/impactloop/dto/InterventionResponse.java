package com.greenpulse.impactloop.dto;

public class InterventionResponse {
    
    private String interventionId;
    private String issueId;
    private String actionType;
    private String assignedTeam;
    private String status;
    private String startedAt;
    private String completedAt;

    public InterventionResponse() {
    }

    public InterventionResponse(
            String interventionId,
            String issueId,
            String actionType,
            String assignedTeam,
            String status,
            String startedAt,
            String completedAt
    ) {
        this.interventionId = interventionId;
        this.issueId = issueId;
        this.actionType = actionType;
        this.assignedTeam = assignedTeam;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }

    public String getInterventionId() {
        return interventionId;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getActionType() {
        return actionType;
    }

    public String getAssignedTeam() {
        return assignedTeam;
    }

    public String getStatus() {
        return status;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }
}
