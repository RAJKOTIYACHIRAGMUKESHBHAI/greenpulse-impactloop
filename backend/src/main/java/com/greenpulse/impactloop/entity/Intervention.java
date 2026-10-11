package com.greenpulse.impactloop.entity;

public class Intervention {
    
    private String interventionId;
    private String issueId;
    private String actionType;
    private String assignedTeam;
    private String status;
    private String startedAt;
    private String completedAt;

    public Intervention() {
    }

    public Intervention(
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

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }
}
